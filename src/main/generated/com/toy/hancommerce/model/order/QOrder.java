package com.toy.hancommerce.model.order;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrder is a Querydsl query type for Order
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrder extends EntityPathBase<Order> {

    private static final long serialVersionUID = -1506799218L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrder order = new QOrder("order1");

    public final DateTimePath<java.time.LocalDateTime> createDate = createDateTime("createDate", java.time.LocalDateTime.class);

    public final com.toy.hancommerce.model.delivery.QDelivery delivery;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.toy.hancommerce.model.OrderItem, com.toy.hancommerce.model.QOrderItem> orderItems = this.<com.toy.hancommerce.model.OrderItem, com.toy.hancommerce.model.QOrderItem>createList("orderItems", com.toy.hancommerce.model.OrderItem.class, com.toy.hancommerce.model.QOrderItem.class, PathInits.DIRECT2);

    public final EnumPath<OrderStatus> status = createEnum("status", OrderStatus.class);

    public final NumberPath<Long> totalPrice = createNumber("totalPrice", Long.class);

    public final DateTimePath<java.time.LocalDateTime> updateDate = createDateTime("updateDate", java.time.LocalDateTime.class);

    public final com.toy.hancommerce.model.user.QUser user;

    public QOrder(String variable) {
        this(Order.class, forVariable(variable), INITS);
    }

    public QOrder(Path<? extends Order> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrder(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrder(PathMetadata metadata, PathInits inits) {
        this(Order.class, metadata, inits);
    }

    public QOrder(Class<? extends Order> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.delivery = inits.isInitialized("delivery") ? new com.toy.hancommerce.model.delivery.QDelivery(forProperty("delivery"), inits.get("delivery")) : null;
        this.user = inits.isInitialized("user") ? new com.toy.hancommerce.model.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

