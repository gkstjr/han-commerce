package com.toy.hancommerce.service;

import com.toy.hancommerce.model.delivery.Delivery;
import com.toy.hancommerce.model.delivery.DeliveryStatus;
import com.toy.hancommerce.model.delivery.dto.DeliveryRespDTO;
import com.toy.hancommerce.model.order.Order;
import com.toy.hancommerce.model.order.OrderStatus;
import com.toy.hancommerce.model.user.User;
import com.toy.hancommerce.repository.DeliveryRepository;
import com.toy.hancommerce.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final UserService userService;
    private final OrderRepository orderRepository;
    @Transactional//주문기능에선 주로 entity단에서 비즈니스 로직을 수행해봤으니 배달기능에선 service단에서 처리 해볼 것
    public DeliveryRespDTO complete(long deliveryId) {
        Delivery delivery = deliveryRepository.findWithOrderById(deliveryId).orElseThrow(() -> new RuntimeException("해당 번호 배송 조회가 없습니다."));
        System.out.println(delivery.getStatus() != DeliveryStatus.READY);
        //취소 상품은 불가능
        if(delivery.getStatus() != DeliveryStatus.READY)
            throw new RuntimeException("배송이 이미 취소되거나 완료되어 완료 변경 불가능합니다.");

        delivery.setStatus(DeliveryStatus.COMP);
        delivery.getOrder().setStatus(OrderStatus.COMP);

        delivery = deliveryRepository.save(delivery);

        //DTO 변환
        return DeliveryRespDTO.builder()
                .orderId(delivery.getOrder().getId())
                .deliveryId(delivery.getId())
                .address(delivery.getAddress())
                .status(delivery.getStatus())
                .build();
    }
    @Transactional
    public List<DeliveryRespDTO> searchMy() {
        User user = userService.getMyUserWithAuthorities().orElseThrow(()-> new RuntimeException("현재 아이디의 정보가 없습니다.")); // 이미 userService에서 에러처리 되서 따로 처리 x

        List<Order> orders = orderRepository.findOrdersWithDeliverysByUsername(user.getUsername());
        if(orders.isEmpty())
            throw new RuntimeException("해당 아이디의 주문목록이 없습니다.");

        //searchAll 메소드의 stream 코드와 가독성 비교 해 볼 것
        List<DeliveryRespDTO> deliveries = new ArrayList<>();
        for(Order order : orders) {
            DeliveryRespDTO dto = DeliveryRespDTO.builder()
                    .orderId(order.getId())
                    .deliveryId(order.getDelivery().getId())
                    .address(order.getDelivery().getAddress())
                    .status(order.getDelivery().getStatus())
                    .build();
            deliveries.add(dto);
        }
        return deliveries;
    }
    @Transactional
    public List<DeliveryRespDTO> searchAll() {
        List<Delivery> list = deliveryRepository.findAllWithOrder();

        if(list.isEmpty()) throw new RuntimeException("배송 목록이 없습니다.");

        // serachMy 메소드랑 직접적인 가독성 비교를 해보고 싶어 stream 함수로 구성
        return list.stream()
                .map(delivery -> DeliveryRespDTO.builder()
                        .orderId(delivery.getOrder().getId())
                        .deliveryId(delivery.getId())
                        .status(delivery.getStatus())
                        .address(delivery.getAddress())
                        .build())
                .collect(Collectors.toList());
    }
}
