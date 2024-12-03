package sa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sa.domain.Payment;
import sa.domain.PaymentMethodType;
import sa.domain.PaymentStatus;

@Getter
@AllArgsConstructor
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

    public PaymentDto(Payment payment) {
        this.paymentId = payment.getId();
        this.userId = payment.getUserId();
        this.paymentMethodType = payment.getPaymentMethodType();
        this.totalPrice = payment.getTotalPrice();
        this.paymentStatus = payment.getPaymentStatus();
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
