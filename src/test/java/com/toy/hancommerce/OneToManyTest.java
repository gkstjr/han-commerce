package com.toy.hancommerce;

import com.toy.hancommerce.model.category.Category;
import com.toy.hancommerce.model.item.Item;
import com.toy.hancommerce.repository.CategoryRepository;
import com.toy.hancommerce.repository.ItemRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;

import static java.lang.String.format;
@SpringBootTest
@Transactional
public class OneToManyTest {

    @Autowired
    EntityManager em;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    @DisplayName("OneToMany 관계로 조회 시 N + 1 테스트")
    void test() {
        saveSampleData(); // category 3개와 , 각각의 category마다 item 2개씩 저장
        em.flush();
        em.clear();
        System.out.println("------------ 영속성 컨텍스트 비우기 -----------\n\n");



        System.out.println("------------ CATEGORY 전체 조회 요청 [1번]------------");
        List<Category> categories = categoryRepository.findAll();
        System.out.println("------------ CATEGORY 전체 조회 완료 ------------\n\n");



        System.out.println("------------ CATEGORY와 연관된 ITEM 조회 [ N + 1 문제 발생 ] ------------");
        categories.forEach(category -> {
            category.getItems().forEach(item -> {
                System.out.println("CATEGORY 이름 : [%s], ITEM 이름 : [%s]".formatted(category.getName(),item.getName()));
            });
        });
        System.out.println("------------ CATEGORY 연관된 ITEM 조회 완료 ------------\n\n");
    }

    private void saveSampleData() {
        final String categoryNameFormat = "[%d] category-name";
        final String itemNameFormat = "[%d] item-name";

        IntStream.rangeClosed(1 , 3).forEach(i -> {
            Category category = Category.builder()
                    .name(categoryNameFormat.formatted(i))
                    .build();
            IntStream.rangeClosed(1 , 2).forEach(j -> {
                category.createItem(format(categoryNameFormat,i) + format(itemNameFormat,j));
            });
            //category와 item은 연관관계에 cascade.all 이므로 저장 시 연관관계 엔티티도 같이 저장됨
            categoryRepository.save(category);
        });
    }
}
