package sa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sa.domain.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAddDto {
    private UserInfo user;
    private UserInfo store;
    private Long orderId;
    private int deliveryPrice;
}
