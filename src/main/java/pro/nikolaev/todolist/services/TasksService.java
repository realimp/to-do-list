package pro.nikolaev.todolist.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.nikolaev.todolist.api.requests.EditTaskRequest;
import pro.nikolaev.todolist.entities.Task;
import pro.nikolaev.todolist.entities.TasksList;
import pro.nikolaev.todolist.repositories.TasksRepository;

@Service
public class TasksService {
    @Autowired
    private TasksRepository tasksRepository;

    @Autowired
    private ListsService listsService;

    @Autowired
    private AccountService accountService;

    public Task findById(int id) {
        return tasksRepository.findById(id).get();
    }

    public String deleteTask(int listId, int taskId) {
        TasksList list = listsService.findById(listId);
        if (list != null && list.getUser().equals(accountService.getCurrentUser())) {
            tasksRepository.deleteById(taskId);
        }
        return "redirect:/";
    }

    public String editTask(int listId, int taskId, EditTaskRequest request) {
        TasksList currentList = listsService.findById(listId);
        Task taskToEdit = findById(taskId);

        if (taskToEdit != null && currentList != null && currentList.getUser().equals(accountService.getCurrentUser())) {
            taskToEdit.setTask(request.getTask());
            taskToEdit.setDetails(request.getDetails());
            tasksRepository.saveAndFlush(taskToEdit);
        }

        return "redirect:/";
    }

    public String completedToggle(int listId, int taskId) {
        TasksList list = listsService.findById(listId);
        if (list != null && list.getUser().equals(accountService.getCurrentUser())) {
            Task task = tasksRepository.findById(taskId).get();
            if (task.isCompleted()) {
                task.setCompleted(false);
            } else {
                task.setCompleted(true);
            }
            tasksRepository.saveAndFlush(task);
        }
        return "redirect:/";
    }
}
