package com.toy.hancommerce.controller;

import com.toy.hancommerce.model.Category;
import com.toy.hancommerce.model.dto.CategoryDto;
import com.toy.hancommerce.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/categorys")
    public ResponseEntity<Category> create(@RequestBody @Valid CategoryDto categoryDto) {
        return new ResponseEntity<Category>(categoryService.create(categoryDto), HttpStatus.CREATED);
    }
}
