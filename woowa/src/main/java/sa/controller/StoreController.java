package sa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sa.dto.SimpleResDto;
import sa.dto.StoreRequestDto;
import sa.service.StoreService;

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
}
