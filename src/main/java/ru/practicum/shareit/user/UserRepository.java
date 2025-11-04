package ru.practicum.shareit.user;

import java.util.List;

public interface UserRepository {

    User addUser(User user);

    User updateUser(User user);

    List<User> getUsers();

    User getUserForId(Long userId);

    void deleteUserForId(Long userId);

}
