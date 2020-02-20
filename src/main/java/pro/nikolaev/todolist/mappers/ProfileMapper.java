package pro.nikolaev.todolist.mappers;

import pro.nikolaev.todolist.api.requests.ProfileUpdateRequest;
import pro.nikolaev.todolist.entities.User;

public class ProfileMapper {
    private ProfileMapper() {}

    public static ProfileUpdateRequest getMapping(User user) {
        ProfileUpdateRequest result = new ProfileUpdateRequest();

        result.setFirstName(user.getFirstName());
        result.setLastName(user.getLastName());
        result.setEmail(user.getEmail());

        return result;
    }
}
