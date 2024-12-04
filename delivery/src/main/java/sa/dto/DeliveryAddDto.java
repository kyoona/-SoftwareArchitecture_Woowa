package sa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sa.domain.Order;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAddDto {
    private Order order;
    private int deliveryPrice;
}
