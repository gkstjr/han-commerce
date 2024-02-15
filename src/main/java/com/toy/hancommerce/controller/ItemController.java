package com.toy.hancommerce.controller;

import com.toy.hancommerce.model.Item;
import com.toy.hancommerce.model.dto.CategoryResponseDto;
import com.toy.hancommerce.model.dto.ItemCreateDto;
import com.toy.hancommerce.model.dto.ItemResponseDto;
import com.toy.hancommerce.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    @PostMapping("/items")
    public ResponseEntity<Item> create(@RequestBody @Valid ItemCreateDto itemCreateDto) {
        return new ResponseEntity<>(itemService.create(itemCreateDto), HttpStatus.CREATED);
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<ItemResponseDto> findOne(@PathVariable long id) {
        Item item = itemService.findById(id);
        //지연로딩 -> 프록시 호출 오류 피하기 위해 ResponseDto에 변환시 Category 프록시 초기화
        return new ResponseEntity<ItemResponseDto>(
                             ItemResponseDto.builder()
                                            .categoryResponseDto(CategoryResponseDto.of(item.getCategory()))
                                            .price(item.getPrice())
                                            .name(item.getName())
                                            .content(item.getContent())
                                            .stockQuantity(item.getStockQuantity())
                                            .build(),
                                                    HttpStatus.OK);
    }

}
