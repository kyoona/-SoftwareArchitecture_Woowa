package sa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "credit_cards")
public class CreditCard implements PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "credit_card_id")
    private Long id;

    private long userId;

    private String cardNumber;

    private String bankName;

    @Temporal(TemporalType.DATE) // 날짜 형식
    private Date expirationDate;

    public CreditCard(long userId, String cardNumber, String bankName) {
        this.userId = userId;
        this.cardNumber = cardNumber;
        this.bankName = bankName;
    }

    @Override
    public int pay(int amount) { return amount; }

    @Override
    public int refund(int amount) { return amount; }

    @Override
    public PaymentMethodType getPaymentMethodType() {
        return PaymentMethodType.CREDIT_CARD;
    }

    private boolean isExpired() {
        return expirationDate.before(new Date());
    }
}
