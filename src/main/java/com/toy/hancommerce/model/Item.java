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

    //연관관계 메소드(동작원리 주석처리하면서 이해하기)
//    public void setCategory(Category category) {
//        this.category = category;
//        category.getItems().add(this);
//    }

}
