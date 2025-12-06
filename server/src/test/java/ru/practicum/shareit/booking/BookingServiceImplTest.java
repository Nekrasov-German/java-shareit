package ru.practicum.shareit.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingRequest;
import ru.practicum.shareit.exception.BadRequestException;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.ItemRepository;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.UserRepository;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookingServiceImplTest {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private BookingRepository bookingRepository;

    private User owner;
    private User booker;
    private Item item;

    @BeforeEach
    void setUp() {
        // Создаём владельца через new + сеттеры
        owner = new User();
        owner.setName("Owner");
        owner.setEmail("owner@test.com");
        owner = userRepository.save(owner);

        // Создаём арендатора
        booker = new User();
        booker.setName("Booker");
        booker.setEmail("booker@test.com");
        booker = userRepository.save(booker);

        // Создаём доступный товар
        item = new Item();
        item.setName("Item1");
        item.setDescription("Description1");
        item.setAvailable(true);
        item.setOwner(owner.getId());
        item = itemRepository.save(item);
    }

    @Test
    void create_success() {
        BookingRequest request = new BookingRequest();
        request.setItemId(item.getId());
        request.setStart(LocalDateTime.now().plusDays(1));
        request.setEnd(LocalDateTime.now().plusDays(2));

        BookingDto result = bookingService.create(booker.getId(), request);

        assertThat(result.getId()).isNotNull();
        assertThat(result.getItem().getId()).isEqualTo(item.getId());
        assertThat(result.getBooker().getId()).isEqualTo(booker.getId());
        assertThat(result.getStart()).isEqualTo(request.getStart());
        assertThat(result.getEnd()).isEqualTo(request.getEnd());
        assertThat(result.getStatus()).isEqualTo(State.WAITING);
        assertThat(bookingRepository.count()).isEqualTo(1);
    }

    @Test
    void create_userNotFound() {
        BookingRequest request = new BookingRequest();
        request.setItemId(item.getId());
        request.setStart(LocalDateTime.now().plusDays(1));
        request.setEnd(LocalDateTime.now().plusDays(2));

        assertThatThrownBy(() -> bookingService.create(999L, request))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("такого пользователя не существует.");
    }

    @Test
    void create_itemNotFound() {
        BookingRequest request = new BookingRequest();
        request.setItemId(999L);  // несуществующий item
        request.setStart(LocalDateTime.now().plusDays(1));
        request.setEnd(LocalDateTime.now().plusDays(2));


        assertThatThrownBy(() -> bookingService.create(booker.getId(), request))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("такой вещи не найдено.");
    }

    @Test
    void create_itemNotAvailable() {
        item.setAvailable(false);
        itemRepository.save(item);

        BookingRequest request = new BookingRequest();
        request.setItemId(item.getId());
        request.setStart(LocalDateTime.now().plusDays(1));
        request.setEnd(LocalDateTime.now().plusDays(2));

        assertThatThrownBy(() -> bookingService.create(booker.getId(), request))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Товар нельзя забронировать.");
    }

    @Test
    void update_approve_success() {
        Booking booking = new Booking();
        booking.setItem(item);
        booking.setBooker(booker);
        booking.setStart(LocalDateTime.now().plusDays(1));
        booking.setEnd(LocalDateTime.now().plusDays(2));
        booking = bookingRepository.save(booking);

        BookingDto result = bookingService.update(owner.getId(), booking.getId(), true);

        assertThat(result.getStatus()).isEqualTo(State.APPROVED);
        assertThat(result.getId()).isEqualTo(booking.getId());
    }

    @Test
    void update_reject_success() {
        Booking booking = new Booking();
        booking.setItem(item);
        booking.setBooker(booker);
        booking.setStart(LocalDateTime.now().plusDays(1));
        booking.setEnd(LocalDateTime.now().plusDays(2));
        booking = bookingRepository.save(booking);

        BookingDto result = bookingService.update(owner.getId(), booking.getId(), false);

        assertThat(result.getStatus()).isEqualTo(State.REJECTED);
        assertThat(result.getId()).isEqualTo(booking.getId());
    }

    @Test
    void update_bookingNotFound() {
        assertThatThrownBy(() -> bookingService.update(owner.getId(), 999L, true))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Такой брони не существует.");
    }
}