package ru.practicum.shareit.request;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.request.dto.ItemRequestDto;

import java.util.List;

@RestController
@RequestMapping(path = "/requests")
@RequiredArgsConstructor
public class ItemRequestController {
    private static final String HEADER_USER_ID = "X-Sharer-User-Id";
    private final ItemRequestService itemRequestService;

    @PostMapping
    public ResponseEntity<ItemRequestDto> createRequestItem(@RequestHeader(HEADER_USER_ID) Long userId,
                                                           @RequestBody ItemRequestDto itemRequestDto) {
        return ResponseEntity.ok().body(itemRequestService.createItemRequest(userId, itemRequestDto));
    }

    @GetMapping
    public ResponseEntity<List<ItemRequestDto>> getUserRequests(@RequestHeader(HEADER_USER_ID) Long userId) {
        return ResponseEntity.ok().body(itemRequestService.getUserRequests(userId));
    }

    @GetMapping("/{requestId}")
    public ResponseEntity<ItemRequestDto> getRequestId(@PathVariable("requestId") Long requestId) {
        return ResponseEntity.ok().body(itemRequestService.getItemRequestForId(requestId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ItemRequestDto>> getAllRequest() {
        return ResponseEntity.ok().body(itemRequestService.getAllRequest());
    }
}
