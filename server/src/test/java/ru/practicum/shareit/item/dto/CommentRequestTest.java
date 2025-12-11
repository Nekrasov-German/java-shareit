package ru.practicum.shareit.item.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class CommentRequestTest {

    @Test
    void testGettersAndSetters() {
        CommentRequest request = new CommentRequest();
        request.setText("Отличный товар!");

        assertThat(request.getText()).isEqualTo("Отличный товар!");
    }

    @Test
    void testNullValue() {
        CommentRequest request = new CommentRequest();

        assertThat(request.getText()).isNull();
    }

    @Test
    void testJsonSerialization() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        CommentRequest request = new CommentRequest();
        request.setText("Тест JSON");

        String json = mapper.writeValueAsString(request);

        assertThat(json).contains("\"text\":\"Тест JSON\"");
    }
}
