package sa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sa.dto.OrderAddDto;
import sa.dto.SimpleResDto;
import sa.service.OrderService;

@RequestMapping("/woowa/users/{userId}/orders")
@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public SimpleResDto requestOrder(@PathVariable Long userId,
                                     @RequestBody OrderAddDto orderAddDto){

        Long orderId = orderService.requestOrder(userId, orderAddDto);

        return new SimpleResDto(orderId);
    }
}