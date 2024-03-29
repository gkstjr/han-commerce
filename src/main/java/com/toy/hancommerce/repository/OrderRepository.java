package com.toy.hancommerce.repository;

import com.toy.hancommerce.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> , OrderRepositoryCustom{


    @Query("select o from Order o join fetch o.delivery  join fetch o.orderItems where o.id = :id" )
    Optional<Order> findOneWithDeliveryOrderItemById(long id);

    @Query("select o from Order o join fetch o.delivery where o.user.username = :username")
    List<Order> findOrdersWithDeliverysByUsername(String username);
}
