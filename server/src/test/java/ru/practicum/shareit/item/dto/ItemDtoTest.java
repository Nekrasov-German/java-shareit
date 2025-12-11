package ru.practicum.shareit.item.dto;

import org.junit.jupiter.api.Test;
import ru.practicum.shareit.booking.dto.BookingDto;

import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class ItemDtoTest {

    @Test
    void testGettersAndSetters() {
        BookingDto lastBooking = new BookingDto();
        lastBooking.setId(1L);

        BookingDto nextBooking = new BookingDto();
        nextBooking.setId(2L);

        CommentDto comment1 = new CommentDto();
        comment1.setId(10L);
        comment1.setText("Good!");

        CommentDto comment2 = new CommentDto();
        comment2.setId(11L);
        comment2.setText("Thanks!");

        List<CommentDto> comments = Arrays.asList(comment1, comment2);

        ItemDto itemDto = new ItemDto();
        itemDto.setId(100L);
        itemDto.setName("Laptop");
        itemDto.setDescription("A powerful laptop");
        itemDto.setAvailable(true);
        itemDto.setLastBooking(lastBooking);
        itemDto.setNextBooking(nextBooking);
        itemDto.setComments(comments);
        itemDto.setRequestId(500L);

        assertThat(itemDto.getId()).isEqualTo(100L);
        assertThat(itemDto.getName()).isEqualTo("Laptop");
        assertThat(itemDto.getDescription()).isEqualTo("A powerful laptop");
        assertThat(itemDto.getAvailable()).isTrue();
        assertThat(itemDto.getLastBooking()).isEqualTo(lastBooking);
        assertThat(itemDto.getNextBooking()).isEqualTo(nextBooking);
        assertThat(itemDto.getComments()).containsExactly(comment1, comment2);
        assertThat(itemDto.getRequestId()).isEqualTo(500L);
    }

    @Test
    void testEqualsAndHashCode() {
        BookingDto booking = new BookingDto();
        booking.setId(1L);

        CommentDto comment = new CommentDto();
        comment.setId(10L);

        List<CommentDto> comments = List.of(comment);

        ItemDto dto1 = new ItemDto();
        dto1.setId(1L);
        dto1.setName("Item1");
        dto1.setDescription("Desc1");
        dto1.setAvailable(true);
        dto1.setLastBooking(booking);
        dto1.setNextBooking(booking);
        dto1.setComments(comments);
        dto1.setRequestId(100L);

        ItemDto dto2 = new ItemDto();
        dto2.setId(1L);
        dto2.setName("Item1");
        dto2.setDescription("Desc1");
        dto2.setAvailable(true);
        dto2.setLastBooking(booking);
        dto2.setNextBooking(booking);
        dto2.setComments(comments);
        dto2.setRequestId(100L);

        assertThat(dto1).isEqualTo(dto2);
        assertThat(dto1.hashCode()).isEqualTo(dto2.hashCode());
    }

    @Test
    void testNullValues() {
        ItemDto dto = new ItemDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getName()).isNull();
        assertThat(dto.getDescription()).isNull();
        assertThat(dto.getAvailable()).isNull(); // Boolean может быть null
        assertThat(dto.getLastBooking()).isNull();
        assertThat(dto.getNextBooking()).isNull();
        assertThat(dto.getComments()).isNull(); // List может быть null
        assertThat(dto.getRequestId()).isNull();
    }

    @Test
    void testEmptyList() {
        ItemDto dto = new ItemDto();
        dto.setComments(List.of());

        assertThat(dto.getComments()).isEmpty();
        assertThat(dto.getComments()).hasSize(0);
    }

    @Test
    void testDifferentObjects() {
        ItemDto dto1 = new ItemDto();
        dto1.setId(1L);

        ItemDto dto2 = new ItemDto();
        dto2.setId(2L);

        assertThat(dto1).isNotEqualTo(dto2);
        assertThat(dto1.hashCode()).isNotEqualTo(dto2.hashCode());
    }
}
