package pro.nikolaev.todolist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pro.nikolaev.todolist.api.requests.RegisterRequest;
import pro.nikolaev.todolist.entities.User;
import pro.nikolaev.todolist.services.AccountService;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AccountService accountService;

    @ModelAttribute("user")
    public RegisterRequest registerRequest() {
        return new RegisterRequest();
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") @Valid RegisterRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }
        if (!request.getPassword().equals(request.getPasswordConfirmation())) {
            result.rejectValue("passwordConfirmation", null, "Password do not match!");
        }
        User existing = accountService.findByEmail(request.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "Account with this email already registered!");
        }

        return accountService.register(request);
    }
}
