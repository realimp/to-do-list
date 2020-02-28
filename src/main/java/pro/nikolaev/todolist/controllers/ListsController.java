package pro.nikolaev.todolist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pro.nikolaev.todolist.api.requests.AddTaskRequest;
import pro.nikolaev.todolist.api.requests.NewListRequest;
import pro.nikolaev.todolist.entities.TasksList;
import pro.nikolaev.todolist.services.AccountService;
import pro.nikolaev.todolist.services.ListsService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping
public class ListsController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private ListsService listsService;

    @ModelAttribute("task")
    public AddTaskRequest addTaskRequest() {
        return new AddTaskRequest();
    }

    @ModelAttribute("newList")
    public NewListRequest newListRequest() {
        return new NewListRequest();
    }

    @GetMapping("/")
    public String showList(Model model) {
        List<TasksList> lists = accountService.getCurrentUser().getLists();
        TasksList activeList = accountService.getCurrentUser().getActiveList();
        model.addAttribute("taskLists", lists);
        model.addAttribute("activeList", activeList);
        return "index";
    }

    @GetMapping("/lists/{id}")
    public String showList(@PathVariable int id, Model model) {
        List<TasksList> lists = accountService.getCurrentUser().getLists();
        accountService.setActiveList(id);
        TasksList activeList = accountService.getCurrentUser().getActiveList();
        model.addAttribute("taskLists", lists);
        model.addAttribute("activeList", activeList);
        return "index";
    }

    @GetMapping("/lists/{id}/delete")
    public String deleteList(@PathVariable int id, Model model) {
        return listsService.delete(id);
    }

    @PostMapping("/")
    public String addTask(@ModelAttribute("task") @Valid AddTaskRequest addTaskRequest, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "index";
        }
        List<TasksList> lists = accountService.getCurrentUser().getLists();
        TasksList activeList = accountService.getCurrentUser().getActiveList();
        model.addAttribute("taskLists", lists);
        model.addAttribute("activeList", activeList);
        return listsService.addTask(addTaskRequest);
    }

    @PostMapping("/lists")
    public String newList(@ModelAttribute("newList") @Valid NewListRequest newListRequest, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "index";
        }
        List<TasksList> lists = accountService.getCurrentUser().getLists();
        TasksList activeList = accountService.getCurrentUser().getActiveList();
        model.addAttribute("taskLists", lists);
        model.addAttribute("activeList", activeList);
        return listsService.newList(newListRequest);
    }
}
