package com.toy.hancommerce.item;

import com.toy.hancommerce.error.ErrorCode;
import com.toy.hancommerce.error.MyException;
import com.toy.hancommerce.model.Category;
import com.toy.hancommerce.model.Item;
import com.toy.hancommerce.model.dto.ItemCreateDto;
import com.toy.hancommerce.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        Item item = itemRepository.findOneWithCategoryById(id).orElseThrow(() -> new MyException(ErrorCode.NOT_FOUND));
        return item;
        /*지연로딩 -> 프록시 호출 오류 피하기 위해 ResponseDto에 변환시 Category 프록시 초기화
        return  ItemResponseDto.builder()
                                    .categoryResponseDto(CategoryResponseDto.of(item.getCategory()))
                                    .price(item.getPrice())
                                    .name(item.getName())
                                    .content(item.getContent())
                                    .stockQuantity(item.getStockQuantity())
                                    .build();
         */
    }
    @Transactional
    public List<Item> findAll() {
        List<Item> items = itemRepository.findAllWithCategory();
        if(items.isEmpty())
            throw new MyException(ErrorCode.NOT_FOUND);

        return items;
    }
    @Transactional
    public List<Item> findAllByCategoryId(long categoryId) {
        List<Item> items = itemRepository.findAllWithCategoryByCategoryId(categoryId);
        if(items.isEmpty())
            throw new MyException(ErrorCode.NOT_FOUND);

        return items;
    }
}
