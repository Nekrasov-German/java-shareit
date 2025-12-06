package ru.practicum.shareit.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.ConflictException;
import ru.practicum.shareit.exception.NotFoundException;
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
        return UserMapper.mapToUserDto(userRepository.save(user));
    }

    @Override
    public UserDto updateUser(User user) {
        User updateUser = UserMapper.updateFieldsUser(user, userRepository.findById(user.getId()).get());
        return UserMapper.mapToUserDto(userRepository.save(updateUser));
    }

    @Override
    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::mapToUserDto)
                .toList();
    }

    @Override
    public UserDto getUserForId(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            return UserMapper.mapToUserDto(userRepository.findById(userId).get());
        } else {
            throw new NotFoundException("Такого пользователя не существует.");
        }
    }

    @Override
    public void deleteUserForId(Long userId) {
        userRepository.deleteById(userId);
    }
}
