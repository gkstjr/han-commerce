package com.toy.hancommerce.repository;

import com.toy.hancommerce.model.delivery.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery,Long> {
    @Query("select d from Delivery d join fetch d.order where d.id = :deliveryId")
    Optional<Delivery> findWithOrderById(long deliveryId);
}
