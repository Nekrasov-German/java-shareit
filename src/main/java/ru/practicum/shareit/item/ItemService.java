package ru.practicum.shareit.item;

import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Comment;

import java.util.List;

public interface ItemService {

    ItemDto createItem(Long owner, ItemDto itemDto);

    CommentDto createComment(Long itemId, Long userId, Comment comment);

    ItemDto updateItem(Long id, Long owner, ItemDto itemDto);

    ItemDto getItem(Long id);

    List<ItemDto> getItemsForOwner(Long ownerId);

    List<ItemDto> getAllItemsForText(String text);

}
