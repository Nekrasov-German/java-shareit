package ru.practicum.shareit.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * TODO Sprint add-controllers.
 */
@Data
public class User {
    private Long id;
    @NotBlank(message = "Имя не может содержать только пробелы.")
    private String name;
    @NotNull(message = "Email не может быть пустым.")
    @Email(message = "Не корректный Email.")
    private String email;
}
