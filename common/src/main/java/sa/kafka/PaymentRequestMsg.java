package sa.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestMsg {

    private Long orderId;
    private Long userId;
    private int totalPrice;
}
