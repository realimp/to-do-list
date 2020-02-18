package pro.nikolaev.todolist.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import pro.nikolaev.todolist.api.requests.RegisterRequest;
import pro.nikolaev.todolist.entities.User;
import pro.nikolaev.todolist.repositories.UserRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@EnableAutoConfiguration
@SpringBootTest(classes = AccountService.class)
@AutoConfigureMockMvc
public class AccountServiceTest {
    private static User user;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private AccountService accountService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserRepository userRepository;

    @BeforeAll
    public static void setUp() {
        user = new User();
        user.setFirstName("First");
        user.setLastName("Last");
        user.setEmail("test@email.com");
        user.setPassword("123456789");
        user.setId(1);
    }

    @Test
    public void findByEmailTest() throws JsonProcessingException {
        when(userRepository.findByEmail("test@email.com")).thenReturn(user);

        User returnedUser = accountService.findByEmail("test@email.com");

        verify(userRepository, times(1)).findByEmail("test@email.com");
        Assertions.assertEquals(objectMapper.writeValueAsString(user), objectMapper.writeValueAsString(returnedUser));
    }

    @Test
    public void registerTest() {
        String expected = "redirect:/";

        RegisterRequest request = new RegisterRequest();
        request.setFirstName("First");
        request.setLastName("Last");
        request.setEmail("test@email.com");
        request.setPassword("123456789");

        when(userRepository.saveAndFlush(any(User.class))).thenReturn(user);

        Assertions.assertEquals(expected, accountService.register(request));
        verify(userRepository, times(1)).saveAndFlush(any(User.class));
    }
}
