package com.toy.hancommerce.controller;

import com.toy.hancommerce.model.Item;
import com.toy.hancommerce.model.dto.ItemCreateDto;
import com.toy.hancommerce.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    @PostMapping("/items")
    public ResponseEntity<Item> create(@RequestBody @Valid ItemCreateDto itemCreateDto) {
        return new ResponseEntity<>(itemService.create(itemCreateDto), HttpStatus.CREATED);
    }

    @GetMapping("/items")
    public ResponseEntity<List<Item>> findAll() {
        return new ResponseEntity<List<Item>>(itemService.findAll() , HttpStatus.OK);
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<Item> findOne(@PathVariable long id) {
        return new ResponseEntity<>(itemService.findById(id),HttpStatus.OK);
    }

    @GetMapping("/items/categorys/{categoryId}")
    public ResponseEntity<List<Item>> findAllByCategoryId(@PathVariable long categoryId) {
        return new ResponseEntity<>(itemService.findAllByCategoryId(categoryId),HttpStatus.OK);
    }

}
