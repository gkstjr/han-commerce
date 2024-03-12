package com.toy.hancommerce;

import com.toy.hancommerce.model.category.Category;
import com.toy.hancommerce.repository.CategoryRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.stream.IntStream;

import static java.lang.String.format;

@SpringBootTest
@Transactional
public class CategoryRepositoryTest {

    @Autowired
    private EntityManager em;
    @Autowired
    private CategoryRepository categoryRepository;


    @Test
    @DisplayName("N + 1 발생 테스트")
    void test() {
        saveSampleData(); // 10개 카테고리와 각 카테고리별 3개의 item
        em.flush();
        em.clear();
        System.out.println("-------------- 영속성 컨텍스트 비우기------------------\n\n ");

        System.out.println("-------------- Category 전체 조회------------------ ");
        List<Category> categorys = categoryRepository.findAll();
        System.out.println("-------------- Category 전체 조회 완료. [쿼리 1개 발생]-----------------\n\n ");

        System.out.println("------------ Category 이름 조회 요청 ------------");
        categorys.forEach(ca -> System.out.println("CATEGORY 이름: [%s]".formatted(ca.getName())));
        System.out.println("------------ Category 이름 조회 완료. [추가적인 쿼리 발생하지 않음]------------\n\n");

        System.out.println("------------ CATEGORY에 달린 Item 이름 조회 요청 [조회된 CATEGORY의 개수(N=10) 만큼 추가적인 쿼리 발생]------------");
        categorys.forEach(category -> {
            category.getItems().forEach(item -> {
                System.out.println("Category 이름 : [%s] , ITEM 내용 : [%s]".formatted(category.getName(),item.getName()));
            });
        });
        System.out.println("------------ CATEGORY에 달린 ITEM 내용 조회 완료 ------------\n\n");
    }

    private void saveSampleData() {
        final String categoryNameFormat = "[%d] category-name";
        final String itemNameFormat = "[%d] item-name";

        IntStream.rangeClosed(1,10).forEach(i -> {
            Category category = Category.builder()
                                            .name(format(categoryNameFormat , i))
                                            .build();

            IntStream.rangeClosed(1,3).forEach(j -> {
                category.createItem(format(itemNameFormat,j) + format(categoryNameFormat , i));
            });

           categoryRepository.save(category);
        });
    }

}
