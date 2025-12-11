package ru.practicum.shareit.item.dto;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;

class CommentDtoTest {

    @Test
    void testGettersAndSetters() {
        CommentDto dto = new CommentDto();
        dto.setId(1L);
        dto.setAuthorName("Alice");
        dto.setText("Great item!");
        dto.setCreated(LocalDateTime.of(2025, 12, 7, 21, 0));

        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getAuthorName()).isEqualTo("Alice");
        assertThat(dto.getText()).isEqualTo("Great item!");
        assertThat(dto.getCreated()).isEqualTo(LocalDateTime.of(2025, 12, 7, 21, 0));
    }

    @Test
    void testEqualsAndHashCode() {
        CommentDto dto1 = new CommentDto();
        dto1.setId(1L);
        dto1.setAuthorName("Bob");
        dto1.setText("Nice!");
        dto1.setCreated(LocalDateTime.now());

        CommentDto dto2 = new CommentDto();
        dto2.setId(1L);
        dto2.setAuthorName("Bob");
        dto2.setText("Nice!");
        dto2.setCreated(dto1.getCreated());

        assertThat(dto1).isEqualTo(dto2);

        assertThat(dto1.hashCode()).isEqualTo(dto2.hashCode());
    }

    @Test
    void testToString() {
        CommentDto dto = new CommentDto();
        dto.setId(2L);
        dto.setAuthorName("Charlie");
        dto.setText("Thanks!");
        dto.setCreated(LocalDateTime.of(2025, 12, 7, 22, 0));

        String str = dto.toString();

        assertThat(str).contains("id=2");
        assertThat(str).contains("authorName=Charlie");
        assertThat(str).contains("text=Thanks!");
        assertThat(str).contains("created=2025-12-07T22:00");
    }

    @Test
    void testNullValues() {
        CommentDto dto = new CommentDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getAuthorName()).isNull();
        assertThat(dto.getText()).isNull();
        assertThat(dto.getCreated()).isNull();
    }

    @Test
    void testDifferentObjects() {
        CommentDto dto1 = new CommentDto();
        dto1.setId(1L);

        CommentDto dto2 = new CommentDto();
        dto2.setId(2L); // другой id

        assertThat(dto1).isNotEqualTo(dto2);
        assertThat(dto1.hashCode()).isNotEqualTo(dto2.hashCode());
    }
}
