package pro.nikolaev.todolist.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.nikolaev.todolist.api.requests.AddTaskRequest;
import pro.nikolaev.todolist.entities.Task;
import pro.nikolaev.todolist.entities.TasksList;
import pro.nikolaev.todolist.entities.User;
import pro.nikolaev.todolist.repositories.ListsRepository;
import pro.nikolaev.todolist.repositories.TasksRepository;

@Service
public class ListsService {
    @Autowired
    private ListsRepository listsRepository;

    @Autowired
    private TasksRepository tasksRepository;

    @Autowired
    private AccountService accountService;

    public TasksList createDefaultList(User savedUser) {
        TasksList list = new TasksList();
        list.setUser(savedUser);
        list.setName("To-Do:");
        return listsRepository.saveAndFlush(list);
    }

    public String addTask(AddTaskRequest addTaskRequest) {
        Task newTask = new Task(addTaskRequest.getTask());
        newTask.setList(accountService.getCurrentUser().getActiveList());
        tasksRepository.saveAndFlush(newTask);
        return "redirect:/";
    }
}
