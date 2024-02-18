package com.toy.hancommerce.service;

import com.toy.hancommerce.error.ErrorCode;
import com.toy.hancommerce.error.MyException;
import com.toy.hancommerce.model.category.Category;
import com.toy.hancommerce.model.category.dto.CategoryDto;
import com.toy.hancommerce.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    @Transactional
    public Category create(CategoryDto categoryDto) {
        if(categoryRepository.findByName(categoryDto.getName()).orElse(null) != null) {
                throw new MyException(ErrorCode.DUPLICATED_CATEGORY_NAME);
        }
        Category category = Category.builder()
                            .name(categoryDto.getName())
                            .build();

        return categoryRepository.save(category);
    }
    @Transactional
    public List<Category> findAll() {
        List<Category> categorys = categoryRepository.findAll(Sort.by(Sort.Direction.ASC,"id"));
        if(categorys.isEmpty()) throw new MyException(ErrorCode.NOT_FOUND);

        return categorys;
    }
    @Transactional
    public Category update(long id,CategoryDto categoryDto) {
        //같은 이름의 카테고리가 있을 시 에러 던짐
        if(categoryRepository.existsByName(categoryDto.getName()))
            throw  new MyException(ErrorCode.DUPLICATED_CATEGORY_NAME);

        Category findCategory = categoryRepository.findById(id).orElseThrow(() -> new MyException(ErrorCode.NOT_FOUND));

        findCategory.setName(categoryDto.getName());

        return categoryRepository.save(findCategory);
    }

    public String delete(long id) {

        Category category = categoryRepository.findById(id)
                                                .orElseThrow(() -> new MyException(ErrorCode.NOT_FOUND));

        categoryRepository.delete(category);
        return category.getName();
    }
}

