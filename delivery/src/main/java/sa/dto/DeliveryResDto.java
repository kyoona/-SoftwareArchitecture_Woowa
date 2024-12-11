package sa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import sa.domain.*;

@Getter
@AllArgsConstructor
public class DeliveryResDto {
    private Long deliveryId;
    private UserInfo customer;
    private UserInfo store;
    private Long orderId;
    private int deliveryPrice;
    public DeliveryResDto(Delivery delivery) {
        this.deliveryId = delivery.getId();
        this.customer = delivery.getUser();
        this.store = delivery.getStore();
        this.orderId = delivery.getOrderId();
        this.deliveryPrice = delivery.getDeliveryPrice();
    }
}
