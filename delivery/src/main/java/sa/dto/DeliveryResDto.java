package sa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import sa.domain.Delivery;
import sa.domain.Order;

@Getter
@AllArgsConstructor
public class DeliveryResDto {
    private Long deliveryId;
    private Order order;
    private int deliveryPrice;
    public DeliveryResDto(Delivery delivery) {
        this.deliveryId = delivery.getId();
        this.order = delivery.getOrder();
        this.deliveryPrice = delivery.getDeliveryPrice();
    }
}
