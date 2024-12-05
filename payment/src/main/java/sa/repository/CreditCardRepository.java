package sa.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sa.domain.CreditCard;

import static sa.domain.QCreditCard.creditCard;

@RequiredArgsConstructor
@Repository
public class CreditCardRepository implements PaymentMethodRepository<CreditCard> {

    private final JPAQueryFactory query;

    @Override
    public CreditCard save(CreditCard paymentMethod) {
        query.insert(creditCard)
                .columns(creditCard.userId, creditCard.cardNumber, creditCard.bankName, creditCard.expirationDate)
                .values(paymentMethod.getUserId(), paymentMethod.getCardNumber(), paymentMethod.getBankName(), paymentMethod.getExpirationDate())
                .execute();

        return paymentMethod;
    }

    @Override
    public boolean exists(CreditCard paymentMethod) {
        return query.selectDistinct(creditCard)
                .where(creditCard.eq(paymentMethod))
                .fetchOne() != null;
    }

    @Override
    public CreditCard findByUserId(Long userId) {
        return query.selectFrom(creditCard)
                .where(creditCard.userId.eq(userId))
                .fetchOne();
    }

    @Override
    public CreditCard delete(CreditCard paymentMethod) {
        query.delete(creditCard)
                .where(creditCard.id.eq(paymentMethod.getId()))
                .execute();

        return paymentMethod;
    }
}
