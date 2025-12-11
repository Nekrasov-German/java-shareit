package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ItemDto> createItem(@RequestHeader(HEADER_USER_ID) Long owner,
                                              @RequestBody ItemDto itemDto) {
        return ResponseEntity.ok().body(itemService.createItem(owner, itemDto));
    }

    @PostMapping("/{itemId}/comment")
    public ResponseEntity<CommentDto> createComment(@PathVariable("itemId") Long itemId,
                                    @RequestHeader(HEADER_USER_ID) Long userId,
                                    @RequestBody CommentRequest comment) {
        return ResponseEntity.ok().body(itemService.createComment(itemId, userId, comment));
    }

    @PatchMapping("/{itemId}")
    public ResponseEntity<ItemDto> updateItem(@RequestHeader(HEADER_USER_ID) Long owner,
                           @RequestBody ItemDto itemDto,
                           @PathVariable("itemId") Long id) {
        return ResponseEntity.ok().body(itemService.updateItem(id, owner, itemDto));
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemDto> getItem(@PathVariable("itemId") Long id) {
        return ResponseEntity.ok().body(itemService.getItem(id));
    }

    @GetMapping
    public ResponseEntity<List<ItemDto>> getItemsOwner(@RequestHeader("X-Sharer-User-Id") Long owner) {
        return ResponseEntity.ok().body(itemService.getItemsForOwner(owner));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ItemDto>> getItemsForText(@RequestParam("text") String text) {
        return ResponseEntity.ok().body(itemService.getAllItemsForText(text));
    }
}
