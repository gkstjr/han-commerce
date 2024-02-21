package com.toy.hancommerce.model.delivery.dto;

import com.toy.hancommerce.model.delivery.DeliveryStatus;
import com.toy.hancommerce.model.user.Address;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DeliveryRespDTO {

    private long deliveryId;
    private long orderId;
    private DeliveryStatus status;
    private Address address;
}
