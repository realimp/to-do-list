package pro.nikolaev.todolist.api.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class ProfileUpdateRequest {
    private String firstName;
    private String lastName;

    @Email
    @NotEmpty
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
