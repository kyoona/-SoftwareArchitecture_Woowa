package sa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sa.dto.OrderAddDto;
import sa.dto.OrderResDto;
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

    @GetMapping("/{orderId}")
    public OrderResDto getOrder(@PathVariable Long userId,
                                @PathVariable Long orderId){

        OrderResDto result = orderService.getOrder(userId, orderId);

        return result;
    }

    @GetMapping("/{orderId}/accept")
    public SimpleResDto acceptOrder(@PathVariable Long userId,
                                    @PathVariable Long orderId){

        orderService.acceptOrder(userId, orderId);

        return new SimpleResDto(orderId);
    }
}
