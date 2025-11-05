package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Override
    public ItemDto createItem(Long ownerId, ItemDto itemDto) {
        if (userRepository.getUserForId(ownerId) == null) {
            throw new NotFoundException("Такого пользователя не существует");
        }
        return itemRepository.addItem(ItemMapper.mapToItem(ownerId, itemDto));
    }

    @Override
    public ItemDto updateItem(Long id, Long ownerId, ItemDto itemDto) {
        if (userRepository.getUserForId(ownerId) == null) {
            throw new NotFoundException("Такого пользователя не существует");
        }
        Item item = ItemMapper.mapToItem(ownerId, itemDto);
        item.setId(id);
        return itemRepository.updateItem(item);
    }

    @Override
    public ItemDto getItem(Long id) {
        return itemRepository.getItem(id);
    }

    @Override
    public List<ItemDto> getItemsForOwner(Long ownerId) {
        return itemRepository.getItemForOwner(ownerId);
    }

    @Override
    public List<ItemDto> getAllItemsForText(String text) {
        return itemRepository.getAllItems(text);
    }
}
