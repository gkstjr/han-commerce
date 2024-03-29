package com.toy.hancommerce.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.toy.hancommerce.model.delivery.DeliveryStatus;
import com.toy.hancommerce.model.order.OrderStatus;
import com.toy.hancommerce.model.order.dto.OrderSearchCondition;
import com.toy.hancommerce.model.order.dto.QSearchAllResponseDTO;
import com.toy.hancommerce.model.order.dto.SearchAllResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.time.LocalDateTime;
import java.util.List;

import static com.toy.hancommerce.model.delivery.QDelivery.*;
import static com.toy.hancommerce.model.order.QOrder.order;
import static com.toy.hancommerce.model.user.QUser.*;

@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<SearchAllResponseDTO> searchAll(OrderSearchCondition condition, Pageable pageable) {

        //fetchResult가 향후 미지원이 확정되어 조회결과와 count를 따로 구함
        List<SearchAllResponseDTO> content = queryFactory.
                select(new QSearchAllResponseDTO( //projection 기능 사용하여 반환 DTO 지정
                        order.id,
                        user.username,
                        delivery.status,
                        delivery.address,
                        order.status,
                        order.totalPrice))
                .from(order)
                .join(order.user, user)
                .join(order.delivery, delivery)
                .where(orderStatusEq(condition.getOrderStatus()),
                        deliveryStatusEq(condition.getDeliveryStatus()),
                        dateGoe(condition.getCreateDateGoe()),
                        dateLoe(condition.getCreateDateLoe()),
                        totalPriceGoe(condition.getTotalPriceGoe()),
                        totalPriceLoe(condition.getTotalPriceLoe()))
                .orderBy(order.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        //개수
        JPAQuery<Long> countQuery = queryFactory
                        .select(order.count())
                        .from(order)
                        .join(order.user, user)
                        .join(order.delivery, delivery)
                        .where(orderStatusEq(condition.getOrderStatus()),
                                deliveryStatusEq(condition.getDeliveryStatus()),
                                dateGoe(condition.getCreateDateGoe()),
                                dateLoe(condition.getCreateDateLoe()),
                                totalPriceGoe(condition.getTotalPriceGoe()),
                                totalPriceLoe(condition.getTotalPriceLoe()));

        return PageableExecutionUtils.getPage(content , pageable , countQuery::fetchOne);
    }

    @Override
    public Page<SearchAllResponseDTO> searchMy(OrderSearchCondition condition, Pageable pageable , long userId) {
        List<SearchAllResponseDTO> content = queryFactory.
                                select(new QSearchAllResponseDTO(
                                        order.id,
                                        delivery.status,
                                        delivery.address,
                                        order.status,
                                        order.totalPrice))
                .from(order)
                .join(order.user,user)
                .join(order.delivery,delivery)
                .where(user.id.eq(userId), // 자신의 아이디와 같은 결과만
                        orderStatusEq(condition.getOrderStatus()),
                        deliveryStatusEq(condition.getDeliveryStatus()),
                        dateGoe(condition.getCreateDateGoe()),
                        dateLoe(condition.getCreateDateLoe()),
                        totalPriceGoe(condition.getTotalPriceGoe()),
                        totalPriceLoe(condition.getTotalPriceLoe()))
                .orderBy(order.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        //개수
        JPAQuery<Long> countQuery = queryFactory
                .select(order.count())
                .from(order)
                .join(order.user, user)
                .join(order.delivery, delivery)
                .where(user.id.eq(userId),
                        orderStatusEq(condition.getOrderStatus()),
                        deliveryStatusEq(condition.getDeliveryStatus()),
                        dateGoe(condition.getCreateDateGoe()),
                        dateLoe(condition.getCreateDateLoe()),
                        totalPriceGoe(condition.getTotalPriceGoe()),
                        totalPriceLoe(condition.getTotalPriceLoe()));

        return PageableExecutionUtils.getPage(content , pageable , countQuery::fetchOne);
    }


    private BooleanExpression orderStatusEq(String orderStatus) {
        //String을 Enum변환 시 valueOf 호출 전 null체크
        if(orderStatus == null) {
            return null;
        }
        OrderStatus status = OrderStatus.valueOf(orderStatus);

        return order.status.eq(status);
    }

    private BooleanExpression deliveryStatusEq(String deliveryStatus) {
        if(deliveryStatus == null) {
            return null;
        }
        DeliveryStatus status = DeliveryStatus.valueOf(deliveryStatus);

        return delivery.status.eq(status);
    }

    private BooleanExpression dateGoe(LocalDateTime dateGoe) {
        return dateGoe != null ? order.createDate.goe(dateGoe) : null;
    }
    private Predicate dateLoe(LocalDateTime dateLoe) {
        return dateLoe != null ? order.createDate.loe(dateLoe) : null;
    }
    private BooleanExpression totalPriceLoe(Long totalPriceLoe) {
        return totalPriceLoe != null ? order.totalPrice.loe(totalPriceLoe) : null;
    }

    private BooleanExpression totalPriceGoe(Long totalPriceGoe) {
        return totalPriceGoe != null ? order.totalPrice.goe(totalPriceGoe) : null;
    }

}
