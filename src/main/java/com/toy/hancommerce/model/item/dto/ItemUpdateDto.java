package com.toy.hancommerce.model.item.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemUpdateDto {

    private String categoryId;
    private String name;
    private long price;
    private long stockQuantity;
    private String content;

}
