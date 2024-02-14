package com.toy.hancommerce.controller;

import com.toy.hancommerce.model.Category;
import com.toy.hancommerce.model.dto.CategoryDto;
import com.toy.hancommerce.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/categorys")
    public ResponseEntity<Category> create(@RequestBody @Valid CategoryDto categoryDto) {
        return new ResponseEntity<Category>(categoryService.create(categoryDto), HttpStatus.CREATED);
    }

    @GetMapping("/categorys")
    public ResponseEntity<List<Category>> findAll() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @PatchMapping("/categorys/{id}")
    public ResponseEntity<Category> update(@PathVariable long id, @RequestBody CategoryDto categoryDto) {
        return new ResponseEntity<>(categoryService.update(id, categoryDto) , HttpStatus.OK);
    }
}
