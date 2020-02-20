package pro.nikolaev.todolist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pro.nikolaev.todolist.api.requests.NewListRequest;
import pro.nikolaev.todolist.entities.TasksList;
import pro.nikolaev.todolist.services.AccountService;

import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;


    @ModelAttribute("newList")
    public NewListRequest newListRequest() {
        return new NewListRequest();
    }

    @GetMapping("/profile")
    public String showProfile(Model model) {
        List<TasksList> lists = accountService.getCurrentUser().getLists();
        model.addAttribute("taskLists", lists);
        return "account";
    }

    @GetMapping("/settings")
    public String showSettings(Model model) {
        List<TasksList> lists = accountService.getCurrentUser().getLists();
        model.addAttribute("taskLists", lists);
        return "settings";
    }
}
