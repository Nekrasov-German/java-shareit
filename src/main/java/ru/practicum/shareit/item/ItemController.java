package ru.practicum.shareit.item;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;

import java.util.List;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {
    private static final String HEADER_USER_ID = "X-Sharer-User-Id";
    private final ItemService itemService;

    @PostMapping
    public ItemDto createItem(@RequestHeader(HEADER_USER_ID) String owner,@Valid @RequestBody ItemDto itemDto) {
        return itemService.createItem(Long.parseLong(owner), itemDto);
    }

    @PatchMapping("/{itemId}")
    public ItemDto updateItem(@RequestHeader(HEADER_USER_ID) String owner,
                           @RequestBody ItemDto itemDto,
                           @PathVariable("itemId") Long id) {
        return itemService.updateItem(id, Long.parseLong(owner), itemDto);
    }

    @GetMapping("/{itemId}")
    public ItemDto getItem(@PathVariable("itemId") Long id) {
        return itemService.getItem(id);
    }

    @GetMapping
    public List<ItemDto> getItemsOwner(@RequestHeader("X-Sharer-User-Id") Long owner) {
        return itemService.getItemsForOwner(owner);
    }

    @GetMapping("/search")
    public List<ItemDto> getItemsForText(@RequestParam("text") String text) {
        return itemService.getAllItemsForText(text);
    }
}
