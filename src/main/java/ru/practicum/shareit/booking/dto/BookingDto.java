package ru.practicum.shareit.booking.dto;

import lombok.Data;
import ru.practicum.shareit.booking.State;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;

/**
 * TODO Sprint add-bookings.
 */
@Data
public class BookingDto {
    private Long id;
    private Item item;
    private LocalDateTime start;
    private LocalDateTime end;
    private User booker;
    private State status;
}
