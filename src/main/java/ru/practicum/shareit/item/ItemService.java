package ru.practicum.shareit.item;

import ru.practicum.shareit.item.dto.ItemDto;

import java.util.List;

public interface ItemService {

    ItemDto createItem(Long owner, ItemDto itemDto);

    ItemDto updateItem(Long id, Long owner, ItemDto itemDto);

    ItemDto getItem(Long id);

    List<ItemDto> getItemsForOwner(Long ownerId);

    List<ItemDto> getAllItemsForText(String text);

}
