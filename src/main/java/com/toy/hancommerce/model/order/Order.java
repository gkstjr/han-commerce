package com.toy.hancommerce.model.order;

import com.toy.hancommerce.model.delivery.Delivery;
import com.toy.hancommerce.model.delivery.DeliveryStatus;
import com.toy.hancommerce.model.OrderItem;
import com.toy.hancommerce.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private long totalPrice;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;


    //=====비스니스 로직======//
    //=====생성 메소드===//
    public static Order createOrder(User user, Delivery delivery , List<OrderItem> orderItems) {
        Order order = new Order();

        order.setUser(user);
        order.setDelivery(delivery);

        long totalPrice = 0;
        for(OrderItem orderItem : orderItems) {
            order.getOrderItems().add(orderItem);
            orderItem.setOrder(order);
            totalPrice += orderItem.getTotalPrice();
        }
        order.setTotalPrice(totalPrice);
        order.setStatus(OrderStatus.ORDER);
        order.setCreateDate(LocalDateTime.now());
        return order;
    }
    /*주문 취소*/
    public void cancel() {
        if(delivery.getStatus() == DeliveryStatus.COMP)
            throw new RuntimeException("이미 배송완료된 상품은 취소가 불가능합니다.");


        this.setStatus(OrderStatus.CANCEL);
        this.delivery.setStatus(DeliveryStatus.CANCEL);

        for(OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }
    
}
