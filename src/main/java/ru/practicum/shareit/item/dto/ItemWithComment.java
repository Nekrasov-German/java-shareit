package ru.practicum.shareit.item.dto;

import lombok.Data;
import ru.practicum.shareit.booking.Booking;
import ru.practicum.shareit.item.model.Comment;

@Data
public class ItemWithComment {
    private Long id;
    private String name;
    private String description;
    private Boolean available;
    private Booking lastBooking;
    private Booking nextBooking;
    private Comment comment;
}
