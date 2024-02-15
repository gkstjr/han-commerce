package com.toy.hancommerce.repository;

import com.toy.hancommerce.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {

    boolean existsByName(String name);
}
