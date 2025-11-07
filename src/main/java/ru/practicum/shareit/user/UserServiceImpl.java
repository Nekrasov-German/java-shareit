package ru.practicum.shareit.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.ConflictException;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto createUser(User user) {
        for (UserDto userStorage : getUsers()) {
            if (user.getEmail().equals(userStorage.getEmail())) {
                throw new ConflictException("Такой Email уже используется.");
            }
        }
        return userRepository.addUser(user);
    }

    @Override
    public UserDto updateUser(User user) {
        if (user.getEmail() != null) {
            for (UserDto userStorage : getUsers()) {
                if (user.getEmail().equals(userStorage.getEmail())) {
                    throw new ConflictException("Такой Email уже используется.");
                }
            }
        }
        return userRepository.updateUser(user);
    }

    @Override
    public List<UserDto> getUsers() {
        return userRepository.getUsers();
    }

    @Override
    public UserDto getUserForId(Long userId) {
        return userRepository.getUserForId(userId);
    }

    @Override
    public void deleteUserForId(Long userId) {
        userRepository.deleteUserForId(userId);
    }
}
