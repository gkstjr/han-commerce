package com.toy.hancommerce.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderItem is a Querydsl query type for OrderItem
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderItem extends EntityPathBase<OrderItem> {

    private static final long serialVersionUID = -973293471L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderItem orderItem = new QOrderItem("orderItem");

    public final NumberPath<Long> count = createNumber("count", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.toy.hancommerce.model.item.QItem item;

    public final NumberPath<Long> itemPrice = createNumber("itemPrice", Long.class);

    public final com.toy.hancommerce.model.order.QOrder order;

    public QOrderItem(String variable) {
        this(OrderItem.class, forVariable(variable), INITS);
    }

    public QOrderItem(Path<? extends OrderItem> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrderItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrderItem(PathMetadata metadata, PathInits inits) {
        this(OrderItem.class, metadata, inits);
    }

    public QOrderItem(Class<? extends OrderItem> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.item = inits.isInitialized("item") ? new com.toy.hancommerce.model.item.QItem(forProperty("item"), inits.get("item")) : null;
        this.order = inits.isInitialized("order") ? new com.toy.hancommerce.model.order.QOrder(forProperty("order"), inits.get("order")) : null;
    }

}

