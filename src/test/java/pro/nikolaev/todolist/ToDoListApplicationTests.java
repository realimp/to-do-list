package pro.nikolaev.todolist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pro.nikolaev.todolist.controllers.AuthController;
import pro.nikolaev.todolist.controllers.ListsController;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ToDoListApplicationTests {
	@Autowired
	private AuthController authController;

	@Autowired
	private ListsController listsController;

	@Test
	void contextLoads() {
		assertThat(authController).isNotNull();
		assertThat(listsController).isNotNull();
	}
}
