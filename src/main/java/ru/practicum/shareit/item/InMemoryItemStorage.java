package ru.practicum.shareit.item;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class InMemoryItemStorage implements ItemRepository {
    private final AtomicLong getNextId = new AtomicLong(1);
    private final Map<Long, Item> itemsStorage = new HashMap<>();

    @Override
    public ItemDto updateItem(Item item) {
        if (itemsStorage.containsKey(item.getId())) {
            Item updateItem = itemsStorage.get(item.getId());

            return ItemMapper.mapToItemDto(ItemMapper.updateFieldsItem(item, updateItem));
        } else {
            throw new NotFoundException("Такого товара не найдено");
        }
    }

    @Override
    public ItemDto addItem(Item item) {
        item.setId(getNextId.getAndIncrement());
        itemsStorage.put(item.getId(), item);
        return ItemMapper.mapToItemDto(item);
    }

    @Override
    public ItemDto getItem(Long id) {
        if (itemsStorage.containsKey(id)) {
            return ItemMapper.mapToItemDto(itemsStorage.get(id));
        } else {
            throw new NotFoundException("Такого товара не найдено");
        }
    }

    @Override
    public List<ItemDto> getItemForOwner(Long ownerId) {
        return itemsStorage.values().stream()
                .filter(item -> item.getOwner().equals(ownerId))
                .map(ItemMapper::mapToItemDto)
                .toList();
    }

    @Override
    public List<ItemDto> getAllItems(String text) {
        return itemsStorage.values().stream()
                .filter(Item::getAvailable)
                .filter(item -> text.equalsIgnoreCase(item.getName()) ||
                        text.equalsIgnoreCase(item.getDescription()))
                .map(ItemMapper::mapToItemDto)
                .toList();
    }
}
