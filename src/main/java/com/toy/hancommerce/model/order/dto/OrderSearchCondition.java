package com.toy.hancommerce.model.order.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.toy.hancommerce.model.delivery.DeliveryStatus;
import com.toy.hancommerce.model.order.OrderStatus;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class OrderSearchCondition {

    private String orderStatus;
    private String deliveryStatus;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDateGoe;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDateLoe;

    private Long totalPriceGoe;
    private Long totalPriceLoe;

}
