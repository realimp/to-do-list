package pro.nikolaev.todolist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.nikolaev.todolist.entities.TasksList;

@Repository
public interface ListsRepository extends JpaRepository<TasksList, Integer> {
}
