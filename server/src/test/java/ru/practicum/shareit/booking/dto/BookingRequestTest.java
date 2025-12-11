package ru.practicum.shareit.booking.dto;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;

class BookingRequestTest {

    @Test
    void testGettersAndSetters() {
        BookingRequest request = new BookingRequest();
        request.setItemId(100L);
        request.setStart(LocalDateTime.of(2025, 12, 1, 10, 0));
        request.setEnd(LocalDateTime.of(2025, 12, 2, 10, 0));

        assertThat(request.getItemId()).isEqualTo(100L);
        assertThat(request.getStart())
                .isEqualTo(LocalDateTime.of(2025, 12, 1, 10, 0));
        assertThat(request.getEnd())
                .isEqualTo(LocalDateTime.of(2025, 12, 2, 10, 0));
    }

    @Test
    void testNullValues() {
        BookingRequest request = new BookingRequest();

        assertThat(request.getItemId()).isNull();
        assertThat(request.getStart()).isNull();
        assertThat(request.getEnd()).isNull();
    }

    @Test
    void testEquals_hashCode_notOverridden() {
        BookingRequest req1 = new BookingRequest();
        req1.setItemId(1L);

        BookingRequest req2 = new BookingRequest();
        req2.setItemId(1L);

        assertThat(req1).isNotEqualTo(req2);

        assertThat(req1.hashCode()).isNotEqualTo(req2.hashCode());
    }
}
