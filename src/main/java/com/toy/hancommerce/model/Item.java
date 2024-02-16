package com.toy.hancommerce.model;

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
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    @Column(unique = true)
    private String name;

    private long price;

    private long stockQuantity;

    private String content;

    //=====비즈니스 로직======//
    public void addStock(long quantity) {
        this.stockQuantity += quantity;
    }

    public void removeStock(long quantity) {
        long restStock = stockQuantity - quantity;
        if(restStock < 0) {
            throw new RuntimeException(name + "상품의 재고가 없습니다.");
        }

        this.stockQuantity = restStock;
    }
}
