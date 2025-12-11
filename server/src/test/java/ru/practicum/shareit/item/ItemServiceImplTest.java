package ru.practicum.shareit.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.practicum.shareit.booking.Booking;
import ru.practicum.shareit.booking.BookingRepository;
import ru.practicum.shareit.booking.State;
import ru.practicum.shareit.exception.BadRequestException;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.dto.CommentRequest;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.UserRepository;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ItemServiceImplTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private CommentRepository commentRepository;

    private User owner;
    private User booker;
    private Item item;
    private Booking booking;

    @BeforeEach
    void setUp() {
        owner = new User();
        owner.setName("Owner");
        owner.setEmail("owner@test.com");
        userRepository.save(owner);

        booker = new User();
        booker.setName("Booker");
        booker.setEmail("booker@test.com");
        userRepository.save(booker);

        item = new Item();
        item.setName("Item1");
        item.setDescription("Description1");
        item.setAvailable(true);
        item.setOwner(owner.getId());
        itemRepository.save(item);

        booking = new Booking();
        booking.setItem(item);
        booking.setBooker(booker);
        booking.setStart(LocalDateTime.now().minusDays(2));
        booking.setEnd(LocalDateTime.now().plusDays(1));
        booking.setStatus(State.APPROVED);
        bookingRepository.save(booking);
    }

    @Test
    void createItem_success() {
        ItemDto itemDto = new ItemDto();
        itemDto.setName("New Item");
        itemDto.setDescription("New Description");
        itemDto.setAvailable(true);


        ItemDto result = itemService.createItem(owner.getId(), itemDto);

        assertThat(result.getId()).isNotNull();
        assertThat(result.getName()).isEqualTo("New Item");
        assertThat(result.getDescription()).isEqualTo("New Description");
        assertThat(itemRepository.count()).isEqualTo(2);
    }

    @Test
    void createItem_userNotFound() {
        ItemDto itemDto = new ItemDto();
        itemDto.setName("test");
        itemDto.setDescription("test");
        itemDto.setAvailable(true);

        assertThatThrownBy(() -> itemService.createItem(999L, itemDto))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Такого пользователя не существует");
    }

    @Test
    void createComment_itemNotFound() {
        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setText("test");

        assertThatThrownBy(() -> itemService.createComment(999L, booker.getId(), commentRequest))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Такого товара не найдено");
    }

    @Test
    void createComment_userNotFound() {
        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setText("Test");

        assertThatThrownBy(() -> itemService.createComment(item.getId(), 999L, commentRequest))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Такого пользователя не существует");
    }

    @Test
    void createComment_noBooking() {
        User otherUser = new User();
        otherUser.setName("Other");
        otherUser.setEmail("other@test.com");

        userRepository.save(otherUser);

        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setText("No booking!");

        assertThatThrownBy(() -> itemService.createComment(item.getId(), otherUser.getId(), commentRequest))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Такого бронирования у пользователя не найдено.");
    }

    @Test
    void updateItem_success() {
        ItemDto updatedDto = new ItemDto();
        updatedDto.setName("Updated Name");
        updatedDto.setDescription("Updated Desc");
        updatedDto.setAvailable(false);

        ItemDto result = itemService.updateItem(item.getId(), owner.getId(), updatedDto);

        assertThat(result.getName()).isEqualTo("Updated Name");
        assertThat(result.getAvailable()).isFalse();
    }

    @Test
    void updateItem_userNotFound() {
        ItemDto updatedDto = new ItemDto();
        updatedDto.setName("Test");

        assertThatThrownBy(() -> itemService.updateItem(item.getId(), 999L, updatedDto))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Такого пользователя не существует");
    }

    @Test
    void getItem_success() {
        ItemDto result = itemService.getItem(item.getId());

        assertThat(result.getId()).isEqualTo(item.getId());
        assertThat(result.getName()).isEqualTo("Item1");
        assertThat(result.getComments()).isEmpty(); // Пока нет комментариев
    }

    @Test
    void getItem_notFound() {
        assertThatThrownBy(() -> itemService.getItem(999L))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Такого товара не найдено");
    }

    @Test
    void getItemsForOwner_success() {
        List<ItemDto> items = itemService.getItemsForOwner(owner.getId());

        assertThat(items).hasSize(1);
        assertThat(items.get(0).getName()).isEqualTo("Item1");
    }

    @Test
    void getAllItemsForText_success() {
        List<ItemDto> items = itemService.getAllItemsForText("Item1");

        assertThat(items).hasSize(1);
        assertThat(items.get(0).getName()).isEqualTo("Item1");
    }

    @Test
    void getAllItemsForText_emptyText() {
        List<ItemDto> items = itemService.getAllItemsForText("");
        assertThat(items).isEmpty();
    }
}