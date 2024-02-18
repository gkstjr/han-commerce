package com.toy.hancommerce.model.item.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemCreateDto {

    private long categoryId;
    @NotNull
    private String name;
    private long price;
    private long stockQuantity;
    @NotNull
    private String content;

}
