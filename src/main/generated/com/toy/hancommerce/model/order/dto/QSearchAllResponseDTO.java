package com.toy.hancommerce.model.order.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.toy.hancommerce.model.order.dto.QSearchAllResponseDTO is a Querydsl Projection type for SearchAllResponseDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QSearchAllResponseDTO extends ConstructorExpression<SearchAllResponseDTO> {

    private static final long serialVersionUID = -1637882956L;

    public QSearchAllResponseDTO(com.querydsl.core.types.Expression<Long> orderId, com.querydsl.core.types.Expression<String> username, com.querydsl.core.types.Expression<com.toy.hancommerce.model.delivery.DeliveryStatus> deliveryStatus, com.querydsl.core.types.Expression<? extends com.toy.hancommerce.model.user.Address> address, com.querydsl.core.types.Expression<com.toy.hancommerce.model.order.OrderStatus> orderStatus, com.querydsl.core.types.Expression<Long> totalPrice) {
        super(SearchAllResponseDTO.class, new Class<?>[]{long.class, String.class, com.toy.hancommerce.model.delivery.DeliveryStatus.class, com.toy.hancommerce.model.user.Address.class, com.toy.hancommerce.model.order.OrderStatus.class, long.class}, orderId, username, deliveryStatus, address, orderStatus, totalPrice);
    }

}

