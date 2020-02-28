package pro.nikolaev.todolist.entities;

import javax.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String task;
    private String details;

    @ManyToOne
    @JoinColumn(name = "list_id", referencedColumnName = "id")
    private TasksList list;

    private boolean completed;

    public Task() {
    }

    public Task(String task) {
        this.task = task;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public TasksList getList() {
        return list;
    }

    public void setList(TasksList list) {
        this.list = list;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
