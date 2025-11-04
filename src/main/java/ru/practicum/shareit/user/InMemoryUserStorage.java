package ru.practicum.shareit.user;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.exception.NotFoundException;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class InMemoryUserStorage implements UserRepository {
    private final AtomicLong getNextId = new AtomicLong(1);
    private final Map<Long, User> userStorage = new HashMap<>();

    @Override
    public User addUser(User user) {
        user.setId(getNextId.getAndIncrement());
        userStorage.put(user.getId(), user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        if (userStorage.containsKey(user.getId())) {
            User userResult = userStorage.get(user.getId());
            if (user.getName() != null) {
                userResult.setName(user.getName());
            }
            if (user.getEmail() != null) {
                userResult.setEmail(user.getEmail());
            }
            userStorage.put(userResult.getId(), userResult);
            return userResult;
        } else {
            throw new NotFoundException("Такого пользователя не существует.");
        }
    }

    @Override
    public List<User> getUsers() {
        return new ArrayList<>(userStorage.values());
    }

    @Override
    public User getUserForId(Long userId) {
        Optional<User> user = Optional.ofNullable(userStorage.get(userId));
        if (user.isPresent()) {
            return user.get();
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
