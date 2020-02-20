package pro.nikolaev.todolist.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @OneToOne
    @JoinColumn(name = "active_list", referencedColumnName = "id")
    private TasksList activeList;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TasksList> lists;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TasksList getActiveList() {
        return activeList;
    }

    public void setActiveList(TasksList activeList) {
        this.activeList = activeList;
    }

    public List<TasksList> getLists() {
        return lists;
    }

    public void setLists(List<TasksList> lists) {
        this.lists = lists;
    }
}
