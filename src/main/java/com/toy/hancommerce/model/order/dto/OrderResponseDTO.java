package com.toy.hancommerce.model.order.dto;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.toy.hancommerce.model.delivery.Delivery;
import com.toy.hancommerce.model.order.OrderStatus;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
public class OrderResponseDTO {
    private long id;
    private long userId;
    private long deliveryId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime CreateDate;

    private OrderStatus status;
    private long totalPrice;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime UpdateDate;

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public long getDeliveryId() {
        return deliveryId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

}
