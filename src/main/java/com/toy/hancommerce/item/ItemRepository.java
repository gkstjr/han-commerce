package com.toy.hancommerce.item;

import com.toy.hancommerce.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {

    boolean existsByName(String name);
    @Query("select i from Item i join fetch i.category where i.id = :id")
    Optional<Item> findOneWithCategoryById(long id);

    @Query("select i from Item i join fetch i.category")
    List<Item> findAllWithCategory();

    @Query("select i from Item i join fetch  i.category c where c.id = :categoryId")
    List<Item> findAllWithCategoryByCategoryId(long categoryId);


}
