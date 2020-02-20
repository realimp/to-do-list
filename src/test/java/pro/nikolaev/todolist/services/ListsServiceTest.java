package pro.nikolaev.todolist.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pro.nikolaev.todolist.entities.TasksList;
import pro.nikolaev.todolist.entities.User;
import pro.nikolaev.todolist.repositories.ListsRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@EnableAutoConfiguration
@SpringBootTest(classes = ListsService.class)
@AutoConfigureMockMvc
public class ListsServiceTest {
    private static User user;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private ListsService listsService;

    @MockBean
    private ListsRepository listsRepository;

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
    public void createDefaultListTest() {
        listsService.createDefaultList(user);
        verify(listsRepository, times(1)).saveAndFlush(any(TasksList.class));
    }
}
