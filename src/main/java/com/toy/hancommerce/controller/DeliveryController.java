package com.toy.hancommerce.controller;

import com.toy.hancommerce.model.delivery.dto.DeliveryRespDTO;
import com.toy.hancommerce.service.DeliveryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "DELIVERY" , description = "배송관련 기능 API")
@RestController
@RequestMapping("/deliverys")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;
    @Operation(summary = "배송 완료 설정(사용자/관리자)")
    @PatchMapping("/{deliveryId}")
    public ResponseEntity<DeliveryRespDTO> complete(@PathVariable long deliveryId) {
        return new ResponseEntity<DeliveryRespDTO>(deliveryService.complete(deliveryId), HttpStatus.OK);
    }
    @Operation(summary = "내 배송 조회(사용자/관리자)")
    @GetMapping("/my-delivery")
    public ResponseEntity<List<DeliveryRespDTO>> searchMy() {
        return new ResponseEntity<List<DeliveryRespDTO>>(deliveryService.searchMy(),HttpStatus.OK);
    }
    @Operation(summary = "전체 배송 조회(관리자)")
    @GetMapping()
    public ResponseEntity<List<DeliveryRespDTO>> searchAll() {
        return new ResponseEntity<List<DeliveryRespDTO>>(deliveryService.searchAll(),HttpStatus.OK);
    }
}
