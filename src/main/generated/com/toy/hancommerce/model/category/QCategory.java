package com.toy.hancommerce.model.category;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCategory is a Querydsl query type for Category
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCategory extends EntityPathBase<Category> {

    private static final long serialVersionUID = -1296724722L;

    public static final QCategory category = new QCategory("category");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.toy.hancommerce.model.item.Item, com.toy.hancommerce.model.item.QItem> items = this.<com.toy.hancommerce.model.item.Item, com.toy.hancommerce.model.item.QItem>createList("items", com.toy.hancommerce.model.item.Item.class, com.toy.hancommerce.model.item.QItem.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public QCategory(String variable) {
        super(Category.class, forVariable(variable));
    }

    public QCategory(Path<? extends Category> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCategory(PathMetadata metadata) {
        super(Category.class, metadata);
    }

}

