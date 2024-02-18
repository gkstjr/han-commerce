package com.toy.hancommerce.model.item.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toy.hancommerce.model.category.dto.CategoryResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
public class ItemResponseDto {
    @JsonProperty(value = "category")
    private CategoryResponseDto categoryResponseDto;
    private String name;
    private long price;
    private long stockQuantity;
    private String content;

}
