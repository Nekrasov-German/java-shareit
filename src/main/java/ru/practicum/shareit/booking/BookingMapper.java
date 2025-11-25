package ru.practicum.shareit.booking;

import lombok.experimental.UtilityClass;
import ru.practicum.shareit.booking.dto.BookingDto;

@UtilityClass
public class BookingMapper {
    public static BookingDto bookingToBookingDto(Booking booking) {
        BookingDto bookingDto = new BookingDto();
        bookingDto.setId(booking.getId());
        bookingDto.setItem(booking.getItem());
        bookingDto.setStart(booking.getStart());
        bookingDto.setEnd(booking.getEnd());
        bookingDto.setStatus(booking.getStatus());
        bookingDto.setBooker(booking.getBooker());
        return bookingDto;
    }
}
