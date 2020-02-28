package pro.nikolaev.todolist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.nikolaev.todolist.entities.Task;

@Repository
public interface TasksRepository extends JpaRepository<Task, Integer> {
}
