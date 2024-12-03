package sa.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCreditCard is a Querydsl query type for CreditCard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCreditCard extends EntityPathBase<CreditCard> {

    private static final long serialVersionUID = -1378176621L;

    public static final QCreditCard creditCard = new QCreditCard("creditCard");

    public final StringPath bankName = createString("bankName");

    public final StringPath cardNumber = createString("cardNumber");

    public final DatePath<java.util.Date> expirationDate = createDate("expirationDate", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QCreditCard(String variable) {
        super(CreditCard.class, forVariable(variable));
    }

    public QCreditCard(Path<? extends CreditCard> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCreditCard(PathMetadata metadata) {
        super(CreditCard.class, metadata);
    }

}

