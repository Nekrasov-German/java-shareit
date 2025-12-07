package ru.practicum.shareit.request.dto;

import org.junit.jupiter.api.Test;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class ItemRequestDtoTest {

    @Test
    void testGettersAndSetters() {
        ItemDto item1 = new ItemDto();
        item1.setId(1L);
        item1.setName("Laptop");

        ItemDto item2 = new ItemDto();
        item2.setId(2L);
        item2.setName("Mouse");

        List<ItemDto> items = Arrays.asList(item1, item2);

        User user = new User();
        user.setId(100L);

        ItemRequestDto dto = new ItemRequestDto();
        dto.setId(10L);
        dto.setDescription("Need a laptop and mouse");
        dto.setUserId(user);
        dto.setCreated(LocalDateTime.of(2025, 12, 7, 10, 0));
        dto.setItems(items);

        assertThat(dto.getId()).isEqualTo(10L);
        assertThat(dto.getDescription()).isEqualTo("Need a laptop and mouse");
        assertThat(dto.getUserId()).isEqualTo(user);
        assertThat(dto.getCreated())
                .isEqualTo(LocalDateTime.of(2025, 12, 7, 10, 0));
        assertThat(dto.getItems()).containsExactly(item1, item2);
    }

    @Test
    void testNullValues() {
        ItemRequestDto dto = new ItemRequestDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getDescription()).isNull();
        assertThat(dto.getUserId()).isNull();
        assertThat(dto.getCreated()).isNull();
        assertThat(dto.getItems()).isNull();
    }

    @Test
    void testEmptyItemsList() {
        ItemRequestDto dto = new ItemRequestDto();
        dto.setItems(List.of());

        assertThat(dto.getItems()).isEmpty();
        assertThat(dto.getItems()).hasSize(0);
    }

    @Test
    void testEquals_hashCode_notOverridden() {
        ItemRequestDto dto1 = new ItemRequestDto();
        dto1.setId(1L);

        ItemRequestDto dto2 = new ItemRequestDto();
        dto2.setId(1L);

        assertThat(dto1).isNotEqualTo(dto2); // разные объекты
        assertThat(dto1.hashCode()).isNotEqualTo(dto2.hashCode());
    }
}
