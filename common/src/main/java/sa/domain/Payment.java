package sa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name = "payments")
@Entity
public class Payment {

    @Column(name = "paymentId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Long userId;

    private PaymentMethodType paymentMethodType;

    private int totalPrice;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus = PaymentStatus.WAIT;

    public Payment(Long userId, PaymentMethodType paymentMethodType, int totalPrice, PaymentStatus paymentStatus) {
        this.userId = userId;
        this.paymentMethodType = paymentMethodType;
        this.totalPrice = totalPrice;
        this.paymentStatus = paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
