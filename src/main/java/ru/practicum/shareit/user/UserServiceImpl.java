package ru.practicum.shareit.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.ConflictException;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        for (User userStorage : getUsers()) {
            if (user.getEmail().equals(userStorage.getEmail())) {
                throw new ConflictException("Такой Email уже используется.");
            }
        }
        return userRepository.addUser(user);
    }

    @Override
    public User updateUser(User user) {
        if (user.getEmail() != null) {
            for (User userStorage : getUsers()) {
                if (user.getEmail().equals(userStorage.getEmail())) {
                    throw new ConflictException("Такой Email уже используется.");
                }
            }
        }
        return userRepository.updateUser(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    @Override
    public User getUserForId(Long userId) {
        return userRepository.getUserForId(userId);
    }

    @Override
    public void deleteUserForId(Long userId) {
        userRepository.deleteUserForId(userId);
    }
}
