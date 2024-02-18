package com.toy.hancommerce.controller;

import com.toy.hancommerce.model.order.dto.OrderRequestDTO;
import com.toy.hancommerce.model.order.dto.OrderResponseDTO;
import com.toy.hancommerce.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping()
    public ResponseEntity<OrderResponseDTO> create(@RequestBody @Valid List<OrderRequestDTO> orderRequestDTOS) {
        return new ResponseEntity<OrderResponseDTO>(orderService.createOrder(orderRequestDTOS), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> cancel(@PathVariable long id) {
        return new ResponseEntity<OrderResponseDTO>(orderService.cancel(id),HttpStatus.OK);
    }
}
