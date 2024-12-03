package sa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "account_transfers")
public class AccountTransfer implements PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_transfer_id")
    private Long id;

    private long userId;

    private String accountNumber;

    private long balance;

    public AccountTransfer(long userId, String accountNumber, long balance) {
        this.userId = userId;
        this.accountNumber = accountNumber;
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
        return PaymentMethodType.ACCOUNT_TRANSFER;
    }
}
