package ru.practicum.shareit.item;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class InMemoryItemStorage implements ItemRepository {
    private final AtomicLong getNextId = new AtomicLong(1);
    private final Map<Long, Item> itemsStorage = new HashMap<>();

    @Override
    public Item updateItem(Item item) {
        if (itemsStorage.containsKey(item.getId())) {
            Item updateItem = itemsStorage.get(item.getId());
            return ItemMapper.updateFieldsItem(item, updateItem);
        } else {
            throw new NotFoundException("Такого товара не найдено");
        }
    }

    @Override
    public Item addItem(Item item) {
        item.setId(getNextId.getAndIncrement());
        itemsStorage.put(item.getId(), item);
        return item;
    }

    @Override
    public Item getItem(Long id) {
        if (itemsStorage.containsKey(id)) {
            return itemsStorage.get(id);
        } else {
            throw new NotFoundException("Такого товара не найдено");
        }
    }

    @Override
    public List<ItemDto> getItemForOwner(Long ownerId) {
        List<ItemDto> result = new ArrayList<>();
        for (Item item : itemsStorage.values()) {
            if (item.getOwner().equals(ownerId)) {
                result.add(ItemMapper.mapToItemDto(item));
            }
        }
        return result;
    }

    @Override
    public List<ItemDto> getAllItems(String text) {
        List<ItemDto> result = new ArrayList<>();
        for (Item item : itemsStorage.values()) {
            if (item.getAvailable()) {
                if (text.equalsIgnoreCase(item.getName())) {
                    result.add(ItemMapper.mapToItemDto(item));
                }
                if (text.equalsIgnoreCase(item.getDescription())) {
                    result.add(ItemMapper.mapToItemDto(item));
                }
            }
        }
        return result;
    }
}
