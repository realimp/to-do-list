package pro.nikolaev.todolist.api.requests;

import javax.validation.constraints.NotEmpty;

public class AddTaskRequest {
    @NotEmpty
    private String task;

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
