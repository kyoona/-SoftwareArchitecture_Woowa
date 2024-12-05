package sa.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryStatusMsg {

    private Long orderId;
    private DeliveryStatus deliveryStatus;
}
