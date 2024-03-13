package com.toy.hancommerce.model.delivery;

import com.toy.hancommerce.model.order.Order;
import com.toy.hancommerce.model.user.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Delivery {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private long id;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @Embedded
    private Address address;

    @OneToOne(mappedBy = "delivery")
    private Order order;
    // N + 1 테스트(추후에 개발용 DB 에서도 컬럼 삭제)
    private int DeliveryTest;
}
