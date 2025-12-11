package ru.practicum.shareit.booking.dto;

import org.junit.jupiter.api.Test;
import ru.practicum.shareit.booking.State;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.user.dto.UserDto;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class BookingDtoTest {

    @Test
    void testGettersAndSetters() {
        BookingDto dto = new BookingDto();
        dto.setId(1L);
        dto.setItem(new ItemDto());
        dto.setStart(LocalDateTime.of(2025, 1, 1, 12, 0));
        dto.setEnd(LocalDateTime.of(2025, 1, 2, 12, 0));
        dto.setBooker(new UserDto());
        dto.setStatus(State.APPROVED);

        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getItem()).isNotNull();
        assertThat(dto.getStart()).isEqualTo(LocalDateTime.of(2025, 1, 1, 12, 0));
        assertThat(dto.getEnd()).isEqualTo(LocalDateTime.of(2025, 1, 2, 12, 0));
        assertThat(dto.getBooker()).isNotNull();
        assertThat(dto.getStatus()).isEqualTo(State.APPROVED);
    }

    @Test
    void testEquals() {
        BookingDto dto1 = new BookingDto();
        dto1.setId(1L);
        dto1.setItem(new ItemDto());

        BookingDto dto2 = new BookingDto();
        dto2.setId(1L);
        dto2.setItem(new ItemDto());

        assertThat(dto1).isEqualTo(dto2); // equals должен учитывать все поля
    }

    @Test
    void testHashCode() {
        BookingDto dto1 = new BookingDto();
        dto1.setId(1L);

        BookingDto dto2 = new BookingDto();
        dto2.setId(1L);

        assertThat(dto1.hashCode()).isEqualTo(dto2.hashCode());
    }

    @Test
    void testToString() {
        BookingDto dto = new BookingDto();
        dto.setId(1L);
        dto.setStatus(State.APPROVED);

        String str = dto.toString();
        assertThat(str).contains("id=1");
        assertThat(str).contains("status=APPROVED");
    }
}