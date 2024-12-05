package sa.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sa.domain.AccountTransfer;
import sa.domain.QAccountTransfer;

import static sa.domain.QAccountTransfer.accountTransfer;

@RequiredArgsConstructor
@Repository
public class AccountTransferRepository implements PaymentMethodRepository<AccountTransfer> {

    private final JPAQueryFactory query;

    @Override
    public AccountTransfer save(AccountTransfer paymentMethod) {
        query.insert(accountTransfer)
                .columns(accountTransfer.userId, accountTransfer.accountNumber, accountTransfer.balance)
                .values(paymentMethod.getUserId(), paymentMethod.getAccountNumber(), paymentMethod.getBalance())
                .execute();

        return paymentMethod;
    }

    @Override
    public boolean exists(AccountTransfer paymentMethod) {
         return query.selectDistinct(accountTransfer)
                 .where(accountTransfer.eq(paymentMethod))
                 .fetchOne() != null;
    }

    @Override
    public AccountTransfer findByUserId(Long userId) {
        return query.selectFrom(accountTransfer)
                .where(QAccountTransfer.accountTransfer.userId.eq(userId))
                .fetchOne();
    }

    @Override
    public AccountTransfer delete(AccountTransfer paymentMethod) {
        query.delete(accountTransfer)
                .where(accountTransfer.id.eq(paymentMethod.getId()))
                .execute();

        return paymentMethod;
    }
}
