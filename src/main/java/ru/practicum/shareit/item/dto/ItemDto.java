package ru.practicum.shareit.item.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.practicum.shareit.booking.Booking;

import java.util.List;

/**
 * TODO Sprint add-controllers.
 */
@Data
public class ItemDto {
    private Long id;
    @NotBlank(message = "Название не может быть пустым.")
    private String name;
    @NotBlank(message = "Описание не может быть пустым.")
    private String description;
    @NotNull(message = "Статус не может быть пустым.")
    private Boolean available;
    private Booking lastBooking;
    private Booking nextBooking;
    private List<CommentDto> comments;
}
