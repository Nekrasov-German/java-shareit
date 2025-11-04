package ru.practicum.shareit.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TODO Sprint add-controllers.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User createdUser = userService.createUser(user);

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Sharer-User-Id", createdUser.getId().toString());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .headers(headers)
                .body(createdUser);
    }

    @PatchMapping("/{userId}")
    public User updateUser(@PathVariable("userId") Long userId, @RequestBody User user) {
        user.setId(userId);
        return userService.updateUser(user);
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    public User getUserForId(@PathVariable("userId") Long userId) {
        return userService.getUserForId(userId);
    }

    @DeleteMapping("/{userId}")
    public void deleteUserForId(@PathVariable("userId") Long userId) {
        userService.deleteUserForId(userId);
    }
}
