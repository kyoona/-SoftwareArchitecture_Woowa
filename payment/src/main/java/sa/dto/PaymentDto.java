package sa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sa.domain.Payment;
import sa.domain.PaymentMethodType;
import sa.domain.PaymentStatus;

@Getter
@NoArgsConstructor
public class PaymentDto {

    private Long orderId;
    private Long paymentId;
    private Long userId;
    private PaymentMethodType paymentMethodType;
    private int totalPrice;
    private PaymentStatus paymentStatus = PaymentStatus.WAIT;

    public PaymentDto(Long orderId, Long userId, PaymentMethodType paymentMethodType, int totalPrice) {
        this.orderId = orderId;
        this.userId = userId;
        this.paymentMethodType = paymentMethodType;
        this.totalPrice = totalPrice;
    }

    public static Payment getPayment(PaymentDto paymentDto) {
        return new Payment(
                paymentDto.getUserId(),
                paymentDto.getPaymentMethodType(),
                paymentDto.getTotalPrice(),
                paymentDto.getPaymentStatus());
    }

    public void setPaymentId(Long paymentId) { this.paymentId = paymentId; }
    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
