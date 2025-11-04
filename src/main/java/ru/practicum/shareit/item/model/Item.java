package ru.practicum.shareit.item.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * TODO Sprint add-controllers.
 */
@Data
public class Item {
    private Long id;
    @NotNull(message = "Название не может быть пустым.")
    @NotBlank(message = "Название не может быть пустым.")
    private String name;
    @NotNull(message = "Описание не может быть пустым.")
    @NotBlank(message = "Описание не может быть пустым.")
    private String description;
    @NotNull(message = "Статус не может быть пустым.")
    private Boolean available;
    @NotNull(message = "Пользователь не может быть пустым.")
    private Long owner;
}
