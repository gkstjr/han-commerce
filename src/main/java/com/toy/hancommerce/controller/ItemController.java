package com.toy.hancommerce.controller;

import com.toy.hancommerce.model.Item;
import com.toy.hancommerce.model.dto.ItemCreateDto;
import com.toy.hancommerce.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "ITEM" , description = "상품관련 기능 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;
    @Operation(summary = "등록(관리자)")
    @PostMapping()
    public ResponseEntity<Item> create(@RequestBody @Valid ItemCreateDto itemCreateDto) {
        return new ResponseEntity<>(itemService.create(itemCreateDto), HttpStatus.CREATED);
    }
    @Operation(summary = "전체 조회(사용자/관리자)")
    @GetMapping()
    public ResponseEntity<List<Item>> findAll() {
        return new ResponseEntity<List<Item>>(itemService.findAll() , HttpStatus.OK);
    }
    @Operation(summary = "상세 조회(사용자/관리자)")
    @GetMapping("/{id}")
    public ResponseEntity<Item> findOne(@PathVariable long id) {
        return new ResponseEntity<>(itemService.findById(id),HttpStatus.OK);
    }
    @Operation(summary = "카테고리 별 조회(사용자/관리자)")
    @GetMapping("/categorys/{categoryId}")
    public ResponseEntity<List<Item>> findAllByCategoryId(@PathVariable long categoryId) {
        return new ResponseEntity<>(itemService.findAllByCategoryId(categoryId),HttpStatus.OK);
    }
    @Operation(summary = "수정(관리자)")
    @PatchMapping("/{id}")
    public ResponseEntity<Item> update(@PathVariable long id , @RequestBody @Valid ItemCreateDto itemCreateDto) {
        return new ResponseEntity<Item>(itemService.update(id , itemCreateDto),HttpStatus.OK);
    }
    @Operation(summary = "삭제(관리자)")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        return new ResponseEntity<>(itemService.delete(id) + " 상품이 삭제되었습니다.",HttpStatus.OK);
    }
}
