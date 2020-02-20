package pro.nikolaev.todolist.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pro.nikolaev.todolist.api.requests.RegisterRequest;
import pro.nikolaev.todolist.entities.User;
import pro.nikolaev.todolist.repositories.UserRepository;

@Service
public class AccountService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ListsService listsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public String register(RegisterRequest request) {
        User newUser = new User();
        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getLastName());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        User savedUser = userRepository.saveAndFlush(newUser);
        savedUser.setActiveList(listsService.createDefaultList(savedUser));
        userRepository.saveAndFlush(savedUser);

        return "redirect:/";
    }

    public User getCurrentUser() {
        return userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    public void setActiveList(int id) {
        User currentUser = getCurrentUser();
        currentUser.setActiveList(listsService.findById(id));
        userRepository.saveAndFlush(currentUser);
    }
}
