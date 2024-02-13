package com.toy.hancommerce.service;

import com.toy.hancommerce.error.ErrorCode;
import com.toy.hancommerce.error.MyException;
import com.toy.hancommerce.model.Category;
import com.toy.hancommerce.model.dto.CategoryDto;
import com.toy.hancommerce.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category create(CategoryDto categoryDto) {
        if(categoryRepository.findByName(categoryDto.getName()).orElse(null) != null) {
                throw new MyException(ErrorCode.DUPLICATED_CATEGORY_NAME);
        }
        Category category = Category.builder()
                            .name(categoryDto.getName())
                            .build();

        return categoryRepository.save(category);
    }
}
