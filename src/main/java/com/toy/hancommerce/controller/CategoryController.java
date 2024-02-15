package com.toy.hancommerce.controller;

import com.toy.hancommerce.model.Category;
import com.toy.hancommerce.model.dto.CategoryDto;
import com.toy.hancommerce.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "CATEGORY" , description = "카테고리 기능 API")
@RequestMapping("/categorys")
@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    @Operation(summary = "등록(관리자)")
    @PostMapping()
    public ResponseEntity<Category> create(@RequestBody @Valid CategoryDto categoryDto) {
        return new ResponseEntity<Category>(categoryService.create(categoryDto), HttpStatus.CREATED);
    }
    @Operation(summary = "전체 조회(사용자/관리자)")
    @GetMapping()
    public ResponseEntity<List<Category>> findAll() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }
    @Operation(summary = "수정(관리자)")
    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable long id, @RequestBody CategoryDto categoryDto) {
        return new ResponseEntity<>(categoryService.update(id, categoryDto) , HttpStatus.OK);
    }
    @Operation(summary = "삭제(관리자)")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        return new ResponseEntity<>( categoryService.delete(id) + " 카테고리가 삭제되었습니다.",HttpStatus.OK);
    }
}
