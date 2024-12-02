package sa.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponseMsg {

    private Long orderId;
    private Long userId;
    private int totalPrice;
    private boolean isSuccess;
}
