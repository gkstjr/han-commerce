package com.toy.hancommerce.model.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.toy.hancommerce.model.item.Item;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private long id;

    @Column(unique = true , nullable = false , length = 50)
    private String name;

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<Item>();
}
