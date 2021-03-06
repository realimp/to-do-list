package pro.nikolaev.todolist.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.nikolaev.todolist.api.requests.AddTaskRequest;
import pro.nikolaev.todolist.api.requests.NewListRequest;
import pro.nikolaev.todolist.entities.Task;
import pro.nikolaev.todolist.entities.TasksList;
import pro.nikolaev.todolist.entities.User;
import pro.nikolaev.todolist.repositories.ListsRepository;
import pro.nikolaev.todolist.repositories.TasksRepository;
import pro.nikolaev.todolist.repositories.UserRepository;

@Service
public class ListsService {
    @Autowired
    private ListsRepository listsRepository;

    @Autowired
    private TasksRepository tasksRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountService accountService;

    public TasksList createDefaultList(User savedUser) {
        TasksList list = new TasksList("To-Do:", savedUser);
        return listsRepository.saveAndFlush(list);
    }

    public TasksList findById(int id) {
        return listsRepository.findById(id).get();
    }

    public String addTask(AddTaskRequest addTaskRequest) {
        Task newTask = new Task(addTaskRequest.getTask());
        newTask.setList(accountService.getCurrentUser().getActiveList());
        tasksRepository.saveAndFlush(newTask);
        return "redirect:/";
    }

    public String newList(NewListRequest newListRequest) {
        User currentUser = accountService.getCurrentUser();
        TasksList list = new TasksList(newListRequest.getName(), currentUser);
        accountService.setActiveList(listsRepository.saveAndFlush(list).getId());
        return "redirect:/";
    }

    public String delete(int listId) {
        User currentUser = accountService.getCurrentUser();
        TasksList listToDelete = findById(listId);
        if (listToDelete != null && currentUser.getLists().contains(listToDelete)) {
            currentUser.getLists().remove(listToDelete);
            listsRepository.deleteById(listId);
            if (currentUser.getLists().size() == 0) {
                currentUser.setActiveList(createDefaultList(currentUser));
            } else {
                TasksList newActiveList = currentUser.getLists().get(0);
                currentUser.setActiveList(newActiveList);
            }
            userRepository.save(currentUser);
        }

        return "redirect:/";
    }
}
