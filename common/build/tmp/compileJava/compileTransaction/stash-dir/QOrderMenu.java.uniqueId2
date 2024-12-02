package sa.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderMenu is a Querydsl query type for OrderMenu
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderMenu extends EntityPathBase<OrderMenu> {

    private static final long serialVersionUID = 1660180643L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderMenu orderMenu = new QOrderMenu("orderMenu");

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMenu menu;

    public final StringPath menuName = createString("menuName");

    public final NumberPath<Integer> menuPrice = createNumber("menuPrice", Integer.class);

    public final QOrder order;

    public QOrderMenu(String variable) {
        this(OrderMenu.class, forVariable(variable), INITS);
    }

    public QOrderMenu(Path<? extends OrderMenu> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrderMenu(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrderMenu(PathMetadata metadata, PathInits inits) {
        this(OrderMenu.class, metadata, inits);
    }

    public QOrderMenu(Class<? extends OrderMenu> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.menu = inits.isInitialized("menu") ? new QMenu(forProperty("menu"), inits.get("menu")) : null;
        this.order = inits.isInitialized("order") ? new QOrder(forProperty("order"), inits.get("order")) : null;
    }

}

