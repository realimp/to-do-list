package pro.nikolaev.todolist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pro.nikolaev.todolist.api.requests.NewListRequest;
import pro.nikolaev.todolist.api.requests.ProfileUpdateRequest;
import pro.nikolaev.todolist.entities.TasksList;
import pro.nikolaev.todolist.mappers.ProfileMapper;
import pro.nikolaev.todolist.services.AccountService;

import javax.validation.Valid;
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

    @ModelAttribute("profile")
    public ProfileUpdateRequest profileUpdateRequest() {
        return ProfileMapper.getMapping(accountService.getCurrentUser());
    }

    @GetMapping("/profile")
    public String showProfile(Model model) {
        List<TasksList> lists = accountService.getCurrentUser().getLists();
        model.addAttribute("taskLists", lists);
        return "account";
    }

    @PostMapping("/profile")
    public String updateProfile(@ModelAttribute("profile") @Valid ProfileUpdateRequest profileUpdateRequest, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "account";
        }
        List<TasksList> lists = accountService.getCurrentUser().getLists();
        model.addAttribute("taskLists", lists);
        return accountService.updateProfile(profileUpdateRequest);
    }

    @GetMapping("/settings")
    public String showSettings(Model model) {
        List<TasksList> lists = accountService.getCurrentUser().getLists();
        model.addAttribute("taskLists", lists);
        return "settings";
    }

    @GetMapping("/delete")
    public String deleteAccount(Model model) {
        return "redirect:/";
    }
}
