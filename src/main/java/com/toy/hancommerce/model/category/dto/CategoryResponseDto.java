package com.toy.hancommerce.model.category.dto;


import com.toy.hancommerce.model.category.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryResponseDto {
    private long id;
    private String name;

    public static CategoryResponseDto of(Category category) {
        return new CategoryResponseDto(category.getId() , category.getName());
    }
}
