package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.CommentRequest;
import ru.practicum.shareit.item.dto.ItemDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {
    private static final String HEADER_USER_ID = "X-Sharer-User-Id";
    private final ItemService itemService;

    @PostMapping
    public ItemDto createItem(@RequestHeader(HEADER_USER_ID) Long owner, @RequestBody ItemDto itemDto) {
        return itemService.createItem(owner, itemDto);
    }

    @PostMapping("/{itemId}/comment")
    public CommentDto createComment(@PathVariable("itemId") Long itemId,
                                    @RequestHeader(HEADER_USER_ID) Long userId,
                                    @RequestBody CommentRequest comment) {
        return itemService.createComment(itemId, userId, comment);
    }

    @PatchMapping("/{itemId}")
    public ItemDto updateItem(@RequestHeader(HEADER_USER_ID) Long owner,
                           @RequestBody ItemDto itemDto,
                           @PathVariable("itemId") Long id) {
        return itemService.updateItem(id, owner, itemDto);
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
