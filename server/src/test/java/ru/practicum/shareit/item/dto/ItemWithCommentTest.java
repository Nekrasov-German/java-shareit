package ru.practicum.shareit.item.dto;

import org.junit.jupiter.api.Test;
import ru.practicum.shareit.booking.dto.BookingDto;

import static org.assertj.core.api.Assertions.assertThat;

class ItemWithCommentTest {

    @Test
    void testGettersAndSetters() {
        BookingDto lastBooking = new BookingDto();
        lastBooking.setId(1L);

        BookingDto nextBooking = new BookingDto();
        nextBooking.setId(2L);

        CommentDto comment = new CommentDto();
        comment.setId(10L);
        comment.setText("Отличный товар!");

        ItemWithComment item = new ItemWithComment();
        item.setId(100L);
        item.setName("Ноутбук");
        item.setDescription("Мощный ноутбук для работы");
        item.setAvailable(true);
        item.setLastBooking(lastBooking);
        item.setNextBooking(nextBooking);
        item.setComment(comment);

        assertThat(item.getId()).isEqualTo(100L);
        assertThat(item.getName()).isEqualTo("Ноутбук");
        assertThat(item.getDescription()).isEqualTo("Мощный ноутбук для работы");
        assertThat(item.getAvailable()).isTrue();
        assertThat(item.getLastBooking()).isEqualTo(lastBooking);
        assertThat(item.getNextBooking()).isEqualTo(nextBooking);
        assertThat(item.getComment()).isEqualTo(comment);
    }

    @Test
    void testEqualsAndHashCode() {
        BookingDto booking = new BookingDto();
        booking.setId(1L);

        CommentDto comment = new CommentDto();
        comment.setId(10L);

        ItemWithComment dto1 = new ItemWithComment();
        dto1.setId(1L);
        dto1.setName("Item1");
        dto1.setDescription("Описание 1");
        dto1.setAvailable(true);
        dto1.setLastBooking(booking);
        dto1.setNextBooking(booking);
        dto1.setComment(comment);

        ItemWithComment dto2 = new ItemWithComment();
        dto2.setId(1L);
        dto2.setName("Item1");
        dto2.setDescription("Описание 1");
        dto2.setAvailable(true);
        dto2.setLastBooking(booking);
        dto2.setNextBooking(booking);
        dto2.setComment(comment);

        assertThat(dto1).isEqualTo(dto2);
        assertThat(dto1.hashCode()).isEqualTo(dto2.hashCode());
    }

    @Test
    void testNullValues() {
        ItemWithComment item = new ItemWithComment();

        assertThat(item.getId()).isNull();
        assertThat(item.getName()).isNull();
        assertThat(item.getDescription()).isNull();
        assertThat(item.getAvailable()).isNull();
        assertThat(item.getLastBooking()).isNull();
        assertThat(item.getNextBooking()).isNull();
        assertThat(item.getComment()).isNull();
    }

    @Test
    void testDifferentObjects() {
        ItemWithComment dto1 = new ItemWithComment();
        dto1.setId(1L);

        ItemWithComment dto2 = new ItemWithComment();
        dto2.setId(2L);

        assertThat(dto1).isNotEqualTo(dto2);
        assertThat(dto1.hashCode()).isNotEqualTo(dto2.hashCode());
    }
}
