package sa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sa.domain.Store;
import sa.dto.MenuAddDto;
import sa.dto.SimpleResDto;
import sa.dto.StoreRequestDto;
import sa.service.StoreService;

import java.util.List;

@RequestMapping("/woowa/users/{userId}/stores")
@RequiredArgsConstructor
@RestController
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/request")
    public SimpleResDto requestStore(@PathVariable Long userId,
                                     @RequestBody StoreRequestDto storeRequestDto){
        Long storeRequestInfoId = storeService.requestStore(userId, storeRequestDto);

        return new SimpleResDto(storeRequestInfoId);
    }

    @PostMapping("/{requestId}/accept")
    public SimpleResDto acceptStore(@PathVariable Long userId,
                                     @PathVariable Long requestId){
        Long storeId = storeService.acceptStore(userId, requestId);

        return new SimpleResDto(storeId);
    }

    @PostMapping("/{requestId}/deny")
    public SimpleResDto denyStore(@PathVariable Long userId,
                                    @PathVariable Long requestId){
        Long storeRequestInfoId = storeService.denyStore(userId, requestId);

        return new SimpleResDto(storeRequestInfoId);
    }

    @PostMapping("/{storeId}")
    public SimpleResDto addMenu(@PathVariable Long userId,
                                @PathVariable Long storeId,
                                @RequestBody MenuAddDto menuAddDto){
        storeService.addMenu(userId, storeId, menuAddDto);

        return new SimpleResDto(storeId);
    }

    @GetMapping
    public List<Store> getStoreList(@PathVariable Long userId){
        return storeService.getStoreList(userId);
    }
}
