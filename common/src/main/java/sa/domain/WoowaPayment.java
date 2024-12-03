package sa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "woowa_payments")
public class WoowaPayment implements PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "woowa_payment_id")
    private Long id;

    private long userId;

    private String password;

    private long balance;

    public WoowaPayment(long userId, String password, long balance) {
        this.userId = userId;
        this.password = password;
        this.balance = balance;
    }

    @Override
    public int pay(int amount) {
        balance -= amount;
        return amount;
    }

    @Override
    public int refund(int amount) {
        balance += amount;
        return amount;
    }

    @Override
    public PaymentMethodType getPaymentMethodType() {
        return PaymentMethodType.WOOWA_PAYMENT;
    }
}
