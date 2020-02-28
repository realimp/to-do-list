package pro.nikolaev.todolist.api.requests;

import javax.validation.constraints.NotEmpty;

public class EditTaskRequest {
    @NotEmpty
    private String task;
    private String details;

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
}
