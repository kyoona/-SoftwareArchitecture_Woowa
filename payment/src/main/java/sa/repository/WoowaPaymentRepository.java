package sa.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sa.domain.WoowaPayment;

import static sa.domain.QWoowaPayment.woowaPayment;

@RequiredArgsConstructor
@Repository
public class WoowaPaymentRepository implements PaymentMethodRepository<WoowaPayment> {

    private JPAQueryFactory query;

    @Override
    public WoowaPayment save(WoowaPayment paymentMethod) {
        query.insert(woowaPayment)
                .columns(woowaPayment.userId, woowaPayment.password, woowaPayment.balance)
                .values(paymentMethod.getUserId(), paymentMethod.getPassword(), paymentMethod.getBalance())
                .execute();

        return paymentMethod;
    }

    @Override
    public boolean exists(WoowaPayment paymentMethod) {
        return query.selectDistinct(woowaPayment)
                .where(woowaPayment.eq(paymentMethod))
                .fetchOne() != null;
    }

    @Override
    public WoowaPayment findByUserId(Long userId) {
        return query.selectFrom(woowaPayment)
                .where(woowaPayment.userId.eq(userId))
                .fetchOne();
    }

    @Override
    public WoowaPayment delete(WoowaPayment paymentMethod) {
        query.delete(woowaPayment)
                .where(woowaPayment.id.eq(paymentMethod.getId()))
                .execute();

        return paymentMethod;
    }
}
