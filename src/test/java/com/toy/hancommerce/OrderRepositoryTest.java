package com.toy.hancommerce;

import com.toy.hancommerce.model.delivery.Delivery;
import com.toy.hancommerce.model.order.Order;
import com.toy.hancommerce.repository.DeliveryRepository;
import com.toy.hancommerce.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
@Transactional
public class OrderRepositoryTest {
    @Autowired
    EntityManager em;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    DeliveryRepository deliveryRepository;

    @BeforeEach
    public void init() {
        IntStream.rangeClosed(1 , 3).forEach(i -> {
            Order order = Order.builder()
                                .totalPrice(i)
                                .build();
            Delivery delivery = Delivery.builder().DeliveryTest(i).build();
            order.register(delivery);
            orderRepository.save(order);
            deliveryRepository.save(delivery); // order만 등록하면 같이 등록되는 지 테스트해보자
        });

        em.flush();;
        em.clear();
    }

    @Test
    @DisplayName("OneToOne 연관관계 주인으로 조회")
    void test1() {
        System.out.println("============ ORDER 조회 ==========");
        List<Order> orders = orderRepository.findAll();
        System.out.println("============ ORDER 조회 완료 ==========\n\n");

        orders.forEach(order -> {
            System.out.println("[%d]번 ORDER 사용하는 Delivery 번호 : [%d] \n".formatted(order.getTotalPrice(),order.getDelivery().getDeliveryTest()));
        });
    }

    @Test
    @DisplayName("OneToOne 연관관계 주인이 아닌 엔티티로 조회 - 지연 로딩으로 설정하더라도 즉시 로딩된다.")
    void test2() {
        System.out.println("============ DELIVERY 조회 ==========");
        List<Delivery> deliveries = deliveryRepository.findAll();
        System.out.println("============ DELIVERY 조회 완료 ==========\n\n");

        deliveries.forEach(del -> {
            System.out.println("[%d] 번 Delivery 사용하는 ORDER 번호 : [%d] \n".formatted(del.getDeliveryTest(),del.getOrder().getTotalPrice()));
        });
    }
}
