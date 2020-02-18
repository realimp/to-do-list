package pro.nikolaev.todolist.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.nikolaev.todolist.api.requests.RegisterRequest;
import pro.nikolaev.todolist.entities.User;
import pro.nikolaev.todolist.repositories.UserRepository;

@Service
public class AccountService {
    @Autowired
    private UserRepository userRepository;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public String register(RegisterRequest request) {
        User newUser = new User();
        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getLastName());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(request.getPassword());
        userRepository.saveAndFlush(newUser);
        return "redirect:/";
    }
}
