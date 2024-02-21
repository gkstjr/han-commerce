package com.toy.hancommerce.controller;

import com.toy.hancommerce.model.delivery.dto.DeliveryRespDTO;
import com.toy.hancommerce.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliverys")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PatchMapping("/{deliveryId}")
    public ResponseEntity<DeliveryRespDTO> complete(@PathVariable long deliveryId) {
        return new ResponseEntity<DeliveryRespDTO>(deliveryService.complete(deliveryId), HttpStatus.OK);
    }

    @GetMapping("/my-delivery")
    public ResponseEntity<List<DeliveryRespDTO>> searchMy() {
        return new ResponseEntity<List<DeliveryRespDTO>>(deliveryService.searchMy(),HttpStatus.OK);
    }
}
