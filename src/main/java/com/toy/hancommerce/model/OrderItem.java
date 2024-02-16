package com.toy.hancommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ORDER_ITEM")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OrderItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private long itemPrice;

    private long count;

    //=====비스니스 로직======//
    //주문 취소시
    public void cancel() {
        getItem().addStock(count);
    }

    //주문상품 전체 가격 조회
    public long getTotalPrice() {
        return getItemPrice() * getCount();
    }

}
