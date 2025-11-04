package ru.practicum.shareit.item;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    @PostMapping
    public Item createItem(@RequestHeader("X-Sharer-User-Id") String owner,@Valid @RequestBody ItemDto itemDto) {
        return itemService.createItem(Long.parseLong(owner), itemDto);
    }

    @PatchMapping("/{itemId}")
    public Item updateItem(@RequestHeader("X-Sharer-User-Id") String owner,
                           @RequestBody ItemDto itemDto,
                           @PathVariable("itemId") Long id) {
        return itemService.updateItem(id, Long.parseLong(owner), itemDto);
    }

    @GetMapping("/{itemId}")
    public Item getItem(@PathVariable("itemId") Long id) {
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
