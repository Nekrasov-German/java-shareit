package ru.practicum.shareit.user;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class InMemoryUserStorage implements UserRepository {
    private final AtomicLong getNextId = new AtomicLong(1);
    private final Map<Long, User> userStorage = new HashMap<>();

    @Override
    public UserDto addUser(User user) {
        user.setId(getNextId.getAndIncrement());
        userStorage.put(user.getId(), user);
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public UserDto updateUser(User user) {
        if (userStorage.containsKey(user.getId())) {
            User updateUser = userStorage.get(user.getId());
            User userResult =  UserMapper.updateFieldsUser(user, updateUser);
            userStorage.put(userResult.getId(), userResult);
            return UserMapper.mapToUserDto(userResult);
        } else {
            throw new NotFoundException("Такого пользователя не существует.");
        }
    }

    @Override
    public List<UserDto> getUsers() {
        return userStorage.values().stream()
                .map(UserMapper::mapToUserDto)
                .toList();
    }

    @Override
    public UserDto getUserForId(Long userId) {
        Optional<User> user = Optional.ofNullable(userStorage.get(userId));
        if (user.isPresent()) {
            return UserMapper.mapToUserDto(user.get());
        } else {
            throw new NotFoundException("Такого пользователя не существует.");
        }
    }

    @Override
    public void deleteUserForId(Long userId) {
        if (userStorage.containsKey(userId)) {
            userStorage.remove(userId);
        } else {
            throw new NotFoundException("Такого пользователя не существует.");
        }
    }
}
