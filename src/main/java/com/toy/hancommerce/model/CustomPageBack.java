package com.toy.hancommerce.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class CustomPageBack<T> extends PageImpl<T> {

    // json으로 응답 하고 싶은 필드를 정의
    private long totalElements;
    private int totalPages;

    public CustomPageBack(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
        this.totalElements = total;
        this.totalPages = super.getTotalPages();
    }
}
