package pro.nikolaev.todolist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pro.nikolaev.todolist.api.requests.EditTaskRequest;
import pro.nikolaev.todolist.api.requests.NewListRequest;
import pro.nikolaev.todolist.entities.Task;
import pro.nikolaev.todolist.entities.TasksList;
import pro.nikolaev.todolist.entities.User;
import pro.nikolaev.todolist.services.AccountService;
import pro.nikolaev.todolist.services.TasksService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/lists")
public class TasksController {
    @Autowired
    private TasksService tasksService;

    @Autowired
    private AccountService accountService;

    @ModelAttribute("newList")
    public NewListRequest newListRequest() {
        return new NewListRequest();
    }

    @ModelAttribute("task")
    public EditTaskRequest editTaskRequest(@PathVariable(name = "list_id") int listId, @PathVariable(name = "id") int taskId) {
        User currentUser = accountService.getCurrentUser();
        Task taskToEdit = tasksService.findById(taskId);

        if (currentUser.getActiveList().getId() == listId) {
            if (taskToEdit != null && taskToEdit.getList().getId() == listId) {
                EditTaskRequest taskRequest = new EditTaskRequest();
                taskRequest.setTask(taskToEdit.getTask());
                taskRequest.setDetails(taskToEdit.getDetails());
                return taskRequest;
            }
        }
        return null;
    }

    @GetMapping("/{list_id}/delete/{id}")
    public String deleteTask(@PathVariable(name = "list_id") int listId, @PathVariable(name = "id") int taskId) {
        return tasksService.deleteTask(listId, taskId);
    }

    @GetMapping("/{list_id}/toggle/{id}")
    public String completedToggle(@PathVariable(name = "list_id") int listId, @PathVariable(name = "id") int taskId) {
        return tasksService.completedToggle(listId, taskId);
    }

    @GetMapping("/{list_id}/edit/{id}")
    public String showTaskEditForm(@ModelAttribute("task") @Valid EditTaskRequest editTaskRequest, Model model) {
        List<TasksList> lists = accountService.getCurrentUser().getLists();
        model.addAttribute("taskLists", lists);
        return "task";
    }

    @PostMapping("/{list_id}/edit/{id}")
    public String saveTask(@PathVariable(name = "list_id") int listId, @PathVariable(name = "id") int taskId,
                           @ModelAttribute("task") @Valid EditTaskRequest editTaskRequest, BindingResult result, Model model) {
        List<TasksList> lists = accountService.getCurrentUser().getLists();
        model.addAttribute("taskLists", lists);
        return tasksService.editTask(listId, taskId, editTaskRequest);
    }
}
