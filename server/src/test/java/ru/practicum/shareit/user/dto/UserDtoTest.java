package ru.practicum.shareit.user.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class UserDtoTest {

    @Test
    void testGettersAndSetters() {
        UserDto dto = new UserDto();
        dto.setId(1L);
        dto.setName("Alice");
        dto.setEmail("alice@example.com");

        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getName()).isEqualTo("Alice");
        assertThat(dto.getEmail()).isEqualTo("alice@example.com");
    }

    @Test
    void testEqualsAndHashCode() {
        UserDto dto1 = new UserDto();
        dto1.setId(1L);
        dto1.setName("Bob");
        dto1.setEmail("bob@example.com");

        UserDto dto2 = new UserDto();
        dto2.setId(1L);
        dto2.setName("Bob");
        dto2.setEmail("bob@example.com");

        assertThat(dto1).isEqualTo(dto2);
        assertThat(dto1.hashCode()).isEqualTo(dto2.hashCode());
    }

    @Test
    void testToString() {
        UserDto dto = new UserDto();
        dto.setId(2L);
        dto.setName("Charlie");
        dto.setEmail("charlie@example.com");

        String str = dto.toString();

        assertThat(str).contains("id=2");
        assertThat(str).contains("name=Charlie");
        assertThat(str).contains("email=charlie@example.com");
    }

    @Test
    void testNullValues() {
        UserDto dto = new UserDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getName()).isNull();
        assertThat(dto.getEmail()).isNull();
    }

    @Test
    void testDifferentObjects() {
        UserDto dto1 = new UserDto();
        dto1.setId(1L);

        UserDto dto2 = new UserDto();
        dto2.setId(2L); // другой id

        assertThat(dto1).isNotEqualTo(dto2);
        assertThat(dto1.hashCode()).isNotEqualTo(dto2.hashCode());
    }
}
