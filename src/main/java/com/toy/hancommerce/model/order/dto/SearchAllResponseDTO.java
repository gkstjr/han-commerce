package com.toy.hancommerce.model.order.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.toy.hancommerce.model.delivery.DeliveryStatus;
import com.toy.hancommerce.model.order.OrderStatus;
import com.toy.hancommerce.model.user.Address;
import lombok.Data;

@Data
public class SearchAllResponseDTO {

    private long orderId;
    private String username;
    private DeliveryStatus deliveryStatus;
    private Address address;
    private OrderStatus orderStatus;
    private long totalPrice;
    @QueryProjection
    public SearchAllResponseDTO(long orderId , String username , DeliveryStatus deliveryStatus , Address address , OrderStatus orderStatus , long totalPrice) {
        this.orderId = orderId;
        this.username = username;
        this.deliveryStatus = deliveryStatus;
        this.address = address;
        this.orderStatus = orderStatus;
        this.totalPrice = totalPrice;
    }
}
