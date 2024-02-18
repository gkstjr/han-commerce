package com.toy.hancommerce.repository;

import com.toy.hancommerce.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {


    @Query("select o from Order o join fetch o.delivery  join fetch o.orderItems where o.id = :id" )
    Optional<Order> findOneWithDeliveryOrderItemById(long id);
}
