package com.toy.hancommerce.repository;

import com.toy.hancommerce.model.order.dto.OrderSearchCondition;
import com.toy.hancommerce.model.order.dto.SearchAllResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderRepositoryCustom {

    Page<SearchAllResponseDTO> searchAll(OrderSearchCondition orderSearchCondition , Pageable pageable);
}
