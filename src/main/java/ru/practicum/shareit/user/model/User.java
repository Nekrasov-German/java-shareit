package ru.practicum.shareit.user.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * TODO Sprint add-controllers.
 */
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Имя не может содержать только пробелы.")
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull(message = "Email не может быть пустым.")
    @Email(message = "Не корректный Email.")
    @Column(name = "email", nullable = false, unique = true)
    private String email;
}
