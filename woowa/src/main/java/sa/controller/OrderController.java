package sa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sa.dto.OrderAddDto;
import sa.dto.OrderResDto;
import sa.dto.SimpleResDto;
import sa.service.OrderService;

import java.util.List;

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

    @PostMapping("/{orderId}/accept")
    public SimpleResDto acceptOrder(@PathVariable Long userId,
                                    @PathVariable Long orderId){
        orderService.acceptOrder(userId, orderId);

        return new SimpleResDto(orderId);
    }

    @PostMapping("/{orderId}/deny")
    public SimpleResDto denyOrder(@PathVariable Long userId,
                                    @PathVariable Long orderId){
        orderService.denyOrder(userId, orderId);

        return new SimpleResDto(orderId);
    }

    @GetMapping("/stores/{storeId}/wait")
    public List<OrderResDto> getWaitOrderList(@PathVariable Long userId,
                                              @PathVariable Long storeId){
        List<OrderResDto> result = orderService.getWaitOrderList(userId, storeId);

        return result;
    }
}
