package com.toy.hancommerce.controller;

import com.toy.hancommerce.model.order.dto.OrderSearchCondition;
import com.toy.hancommerce.model.order.dto.SearchAllResponseDTO;
import com.toy.hancommerce.model.order.dto.OrderRequestDTO;
import com.toy.hancommerce.model.order.dto.OrderResponseDTO;
import com.toy.hancommerce.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping()
    public ResponseEntity<Page<SearchAllResponseDTO>> searchAll(OrderSearchCondition orderSearchCondition , Pageable pageable) {
        return new ResponseEntity<>(orderService.searchAll(orderSearchCondition,pageable),HttpStatus.OK);
    }
}
