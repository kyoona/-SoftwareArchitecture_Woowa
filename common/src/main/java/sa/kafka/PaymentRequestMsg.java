package sa.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sa.domain.Payment;
import sa.domain.PaymentMethod;
import sa.domain.PaymentMethodType;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestMsg {

    private Long orderId;
    private Long userId;
    private PaymentMethodType paymentMethodType;
    private int totalPrice;
}
