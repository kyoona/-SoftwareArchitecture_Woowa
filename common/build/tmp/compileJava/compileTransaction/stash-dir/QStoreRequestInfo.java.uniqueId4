package sa.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStoreRequestInfo is a Querydsl query type for StoreRequestInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStoreRequestInfo extends EntityPathBase<StoreRequestInfo> {

    private static final long serialVersionUID = -1537402714L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStoreRequestInfo storeRequestInfo = new QStoreRequestInfo("storeRequestInfo");

    public final NumberPath<Integer> deliveryPrice = createNumber("deliveryPrice", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QLocation location;

    public final NumberPath<Integer> minimumOrderPrice = createNumber("minimumOrderPrice", Integer.class);

    public final EnumPath<StoreRequestStatus> status = createEnum("status", StoreRequestStatus.class);

    public final StringPath storeName = createString("storeName");

    public QStoreRequestInfo(String variable) {
        this(StoreRequestInfo.class, forVariable(variable), INITS);
    }

    public QStoreRequestInfo(Path<? extends StoreRequestInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStoreRequestInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStoreRequestInfo(PathMetadata metadata, PathInits inits) {
        this(StoreRequestInfo.class, metadata, inits);
    }

    public QStoreRequestInfo(Class<? extends StoreRequestInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.location = inits.isInitialized("location") ? new QLocation(forProperty("location")) : null;
    }

}

