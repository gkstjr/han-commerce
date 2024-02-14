package com.toy.hancommerce.service;

import com.toy.hancommerce.error.ErrorCode;
import com.toy.hancommerce.error.MyException;
import com.toy.hancommerce.model.Category;
import com.toy.hancommerce.model.Item;
import com.toy.hancommerce.model.dto.ItemCreateDto;
import com.toy.hancommerce.repository.CategoryRepository;
import com.toy.hancommerce.repository.ItemRepository;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    @Transactional
    public Item create(ItemCreateDto itemCreateDto) {
        if(itemRepository.existsByName(itemCreateDto.getName())) {
            throw  new MyException(ErrorCode.DUPLICATED_NAME);
        }
        if(itemCreateDto.getStockQuantity() == 0 || itemCreateDto.getPrice() == 0) {
            throw new MyException(ErrorCode.PRICE_OR_STOCK_QUANTITY);
        }

        Category category = categoryRepository.findById(itemCreateDto.getCategoryId()).orElseThrow(() ->new MyException(ErrorCode.NOT_FOUND));

        Item item = Item.builder()
                .category(category)
                .name(itemCreateDto.getName())
                .price(itemCreateDto.getPrice())
                .stockQuantity(itemCreateDto.getStockQuantity())
                .content(itemCreateDto.getContent())
                .build();

        return itemRepository.save(item);
    }
    @Transactional
    public Item findById(long id) {
        return itemRepository.findById(id).orElseThrow(() -> new MyException(ErrorCode.NOT_FOUND));
    }
}
