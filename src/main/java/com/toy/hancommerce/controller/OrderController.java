package com.toy.hancommerce.controller;

import com.toy.hancommerce.model.dto.OrderRequestDTO;
import com.toy.hancommerce.model.dto.OrderResponseDTO;
import com.toy.hancommerce.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    @PostMapping()
    public ResponseEntity<List<OrderResponseDTO>> create(@RequestBody @Valid List<OrderRequestDTO> orderRequestDTOS) {
        return new ResponseEntity<>(orderService.createOrder(orderRequestDTOS), HttpStatus.CREATED);
    }
}
