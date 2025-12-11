package ru.practicum.shareit.booking;

import org.junit.jupiter.api.Test;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class BookingMapperTest {
    @Test
    void bookingToBookingDto_allFieldsCopied() {
        Item item = new Item();
        item.setId(10L);
        item.setName("Laptop");
        item.setDescription("Powerful laptop");
        item.setAvailable(true);

        User booker = new User();
        booker.setId(20L);
        booker.setName("Alice");
        booker.setEmail("alice@example.com");

        Booking booking = new Booking();
        booking.setId(100L);
        booking.setItem(item);
        booking.setBooker(booker);
        booking.setStart(LocalDateTime.of(2025, 12, 10, 12, 0));
        booking.setEnd(LocalDateTime.of(2025, 12, 15, 12, 0));
        booking.setStatus(State.WAITING);

        BookingDto dto = BookingMapper.bookingToBookingDto(booking);

        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(100L);

        assertThat(dto.getItem()).isNotNull();
        assertThat(dto.getItem().getId()).isEqualTo(10L);
        assertThat(dto.getItem().getName()).isEqualTo("Laptop");
        assertThat(dto.getItem().getDescription()).isEqualTo("Powerful laptop");
        assertThat(dto.getItem().getAvailable()).isTrue();

        assertThat(dto.getBooker()).isNotNull();
        assertThat(dto.getBooker().getId()).isEqualTo(20L);
        assertThat(dto.getBooker().getName()).isEqualTo("Alice");
        assertThat(dto.getBooker().getEmail()).isEqualTo("alice@example.com");

        assertThat(dto.getStart()).isEqualTo(LocalDateTime.of(2025, 12, 10, 12, 0));
        assertThat(dto.getEnd()).isEqualTo(LocalDateTime.of(2025, 12, 15, 12, 0));
        assertThat(dto.getStatus()).isEqualTo(State.WAITING);
    }

    @Test
    void bookingToBookingDto_startAndEndNull() {
        Item item = new Item();
        item.setId(40L);

        User booker = new User();
        booker.setId(50L);

        Booking booking = new Booking();
        booking.setId(500L);
        booking.setItem(item);
        booking.setBooker(booker);
        booking.setStart(null);
        booking.setEnd(null);
        booking.setStatus(State.WAITING);

        BookingDto dto = BookingMapper.bookingToBookingDto(booking);

        assertThat(dto.getStart()).isNull();
        assertThat(dto.getEnd()).isNull();
    }

    @Test
    void bookingToBookingDto_statusIsNull() {
        Item item = new Item();
        item.setId(60L);

        User booker = new User();
        booker.setId(70L);

        Booking booking = new Booking();
        booking.setId(600L);
        booking.setItem(item);
        booking.setBooker(booker);
        booking.setStart(LocalDateTime.now());
        booking.setEnd(LocalDateTime.now().plusDays(3));
        booking.setStatus(null);

        BookingDto dto = BookingMapper.bookingToBookingDto(booking);

        assertThat(dto.getStatus()).isNull();
    }
}