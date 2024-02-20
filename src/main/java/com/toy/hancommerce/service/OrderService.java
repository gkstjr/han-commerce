package com.toy.hancommerce.service;

import com.toy.hancommerce.error.ErrorCode;
import com.toy.hancommerce.error.MyException;
import com.toy.hancommerce.model.CustomPage;
import com.toy.hancommerce.model.CustomPageBack;
import com.toy.hancommerce.model.OrderItem;
import com.toy.hancommerce.model.delivery.Delivery;
import com.toy.hancommerce.model.delivery.DeliveryStatus;
import com.toy.hancommerce.model.order.Order;
import com.toy.hancommerce.model.order.dto.OrderRequestDTO;
import com.toy.hancommerce.model.order.dto.OrderResponseDTO;
import com.toy.hancommerce.model.order.dto.OrderSearchCondition;
import com.toy.hancommerce.model.order.dto.SearchAllResponseDTO;
import com.toy.hancommerce.model.user.User;
import com.toy.hancommerce.repository.DeliveryRepository;
import com.toy.hancommerce.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ItemService itemService;
    private final DeliveryRepository deliveryRepository;
    @Transactional
    public OrderResponseDTO createOrder(List<OrderRequestDTO> orderRequestDTOS) {

        User user = userService.getMyUserWithAuthorities().orElseThrow(()-> new MyException(ErrorCode.USERNAME_NOT_FOUND));

        Delivery delivery = Delivery.builder()
                                    .address(user.getAddress())
                                    .status(DeliveryStatus.READY)
                                    .build();
        deliveryRepository.save(delivery);

        List<OrderItem> orderItems = new ArrayList<>();

        for(OrderRequestDTO orderRequestDTO : orderRequestDTOS) {
            OrderItem orderItem = OrderItem.createOrderItem(itemService.findById(orderRequestDTO.getItemId()), orderRequestDTO.getCount());
            orderItems.add(orderItem);
        }

        Order order = Order.createOrder(user , delivery , orderItems);
        orderRepository.save(order);

        return OrderResponseDTO.builder()
                .id(order.getId())
                .deliveryId(delivery.getId())
                .userId(user.getId())
                .CreateDate(LocalDateTime.now())
                .totalPrice(order.getTotalPrice())
                .status(order.getStatus())
                .build();
    }
    @Transactional
    public OrderResponseDTO cancel(long id) {
        Order order = orderRepository.findOneWithDeliveryOrderItemById(id).orElseThrow(() -> new RuntimeException("해당 주문이 없습니다."));

        order.cancel();
        order = orderRepository.save(order);

        return OrderResponseDTO.builder()
                .status(order.getStatus())
                .id(order.getId())
                .UpdateDate(LocalDateTime.now())
                .userId(order.getUser().getId())
                .deliveryId(order.getDelivery().getId())
                .CreateDate(order.getCreateDate())
                .totalPrice(order.getTotalPrice())
                .build();
    }

    public Page<SearchAllResponseDTO> searchAll(OrderSearchCondition orderSearchCondition, Pageable pageable) {
        //결과가 0일때는 content 빈 배열이 나옴
        return orderRepository.searchAll(orderSearchCondition , pageable);
    }

    public CustomPage searchMy(OrderSearchCondition orderSearchCondition, Pageable pageable) {
        User user = userService.getMyUserWithAuthorities().orElseThrow(()-> new RuntimeException("로그인 된 아이디가 잘못 되었음"));
        Page<SearchAllResponseDTO> page = orderRepository.searchMy(orderSearchCondition , pageable , user.getId());
        return new CustomPage(page.getContent(),page.getNumber(), page.getSize(), page.getTotalElements(),page.getTotalPages());
    }
}
