package ru.practicum.shareit.item;

import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemService {

    Item createItem(Long owner, ItemDto itemDto);

    Item updateItem(Long id, Long owner, ItemDto itemDto);

    Item getItem(Long id);

    List<ItemDto> getItemsForOwner(Long ownerId);

    List<ItemDto> getAllItemsForText(String text);

}
