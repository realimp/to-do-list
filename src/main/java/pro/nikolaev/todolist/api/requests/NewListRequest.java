package pro.nikolaev.todolist.api.requests;

import javax.validation.constraints.NotEmpty;

public class NewListRequest {
    @NotEmpty
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
