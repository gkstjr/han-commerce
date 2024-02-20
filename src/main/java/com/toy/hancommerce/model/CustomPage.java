package com.toy.hancommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@AllArgsConstructor
@Data
public class CustomPage {

    private List content;
    private int pageNumber;
    private int pageSize;

    private long totalElements;
    private int totalPages;


}
