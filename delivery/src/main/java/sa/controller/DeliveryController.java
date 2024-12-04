package sa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sa.domain.Delivery;
import sa.domain.Order;
import sa.domain.Store;
import sa.dto.DeliveryAddDto;
import sa.dto.DeliveryResDto;
import sa.dto.SimpleResDto;
import sa.service.DeliveryService;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/delivery/users/{userId}/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping(path = "reqeuest")
    public SimpleResDto requestDelivery(@PathVariable Long userId,
                                        @RequestBody DeliveryAddDto deliveryAddDto) {
        // request delivery
        return new SimpleResDto(1L); // return deliveryId
    }

    @DeleteMapping(path = "{deliveryId}")
    public SimpleResDto cancelDelivery(@PathVariable Long userId,
                                       @PathVariable Long deliveryId) {
        // delete delivery
        return new SimpleResDto(1L);
    }

    @GetMapping(path = "{deliveryId}")
    public DeliveryResDto getDelivery(@PathVariable Long userId,
                                      @PathVariable Long deliveryId) {
        // get delivery
        DeliveryResDto deliveryResDto = deliveryService.getDelivery(deliveryId);
        return deliveryResDto; // return delivery result
    }

    @GetMapping(path = "")
    public List<DeliveryResDto> getDeliveries(@PathVariable Long userId) {
        List<DeliveryResDto> deliveryResDtoList = new ArrayList<DeliveryResDto>();
        // get deliveries
        return deliveryResDtoList; // return delivery result
    }

    @PostMapping(path = "{deliveryId}/accept")
    public SimpleResDto acceptDelivery(@PathVariable Long userId,
                                       @PathVariable Long deliveryId) {
        // accept delivery
        return new SimpleResDto(1L);
    }

    @PostMapping(path = "{deliveryId}/deny")
    public SimpleResDto denyDelivery(@PathVariable Long userId,
                                     @PathVariable Long deliveryId) {
        // deny delivery
        return new SimpleResDto(1L);
    }

    @PostMapping(path = "{deliveryId}/done")
    public SimpleResDto doneDelivery(@PathVariable Long userId,
                                     @PathVariable Long deliveryId) {
        // done delivery
        return new SimpleResDto(1L);
    }
}
