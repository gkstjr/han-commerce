package com.toy.hancommerce.model;

import com.toy.hancommerce.error.MyException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;

import java.lang.reflect.Member;
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
    @JoinColumn(name = "delevery_id")
    private Delivery delivery;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private long totalPrice;

    private Date createDate;
    private Date updateDate;


    //=====비스니스 로직======//
    //=====생성 메소드===//
    public static Order createOrder(User user, Delivery delivery , List<OrderItem> orderItems) {
        Order order = new Order();

        order.setUser(user);
        order.setDelivery(delivery);
        for(OrderItem orderItem : orderItems) {
            order.getOrderItems().add(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setCreateDate(new Date());
        return order;
    }
    /*주문 취소*/
    public void cancel() {
        if(delivery.getStatus() == DeliveryStatus.COMP)
            throw new RuntimeException("이미 배송완료된 상품은 취소가 불가능합니다.");


        this.setStatus(OrderStatus.CANCEL);
        for(OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }
    
}
