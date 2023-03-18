package ru.clevertec.operations;

import ru.clevertec.entity.Cart;
import ru.clevertec.entity.DiscountCard;
import ru.clevertec.entity.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.HashMap;
import java.util.Map;

class UpdateCartInfTest {
    private String [] strings;
    private Map<Integer, Product> productMap;
    private Map<Integer, DiscountCard> discountCardMap;
    private Cart cart;
    @BeforeEach
    void setUp() {
        strings = new String[]{"1-2", "card-1"};
        productMap = new DefaultMaps().initMapWithDefaultProduct();
        discountCardMap = new DefaultMaps().initMapWithDefaultDiscount();
        cart = new Cart();
        HashMap<Product,Integer> m = new HashMap<>();
        m.put(productMap.get(1),2);
        cart.setProductMap(m);
        cart.setCard(new DiscountCard(1,20));
    }

    @Test
    void checkConvertStrInProductCorrect() {
        Map<Product,Integer> expectedMap = new HashMap<>();
        expectedMap.put(productMap.get(1),2);

        Assertions.assertThat(new UpdateCartInf().convertStrInProduct(strings[0],productMap, new HashMap<>())).isEqualTo(expectedMap);
    }
    @Test
    void checkConvertStrInProductUnCorrect() {
        Map<Product,Integer> expectedMap = new HashMap<>();
        expectedMap.put(productMap.get(1),3);

        Assertions.assertThat(new UpdateCartInf().convertStrInProduct(strings[0],productMap, new HashMap<>())).isNotEqualTo(expectedMap);
    }


    @Test
    void checkConvertStrInCardCorrect() {
        DiscountCard expectedDisc = new DiscountCard(1,20);

        Assertions.assertThat(new UpdateCartInf().convertStrInCard(strings[1],discountCardMap)).isEqualTo(expectedDisc);
    }
    @Test
    void checkConvertStrInCardUnCorrect() {
        DiscountCard expectedDisc = new DiscountCard(1,254);

        Assertions.assertThat(new UpdateCartInf().convertStrInCard(strings[1],discountCardMap)).isNotEqualTo(expectedDisc);
    }

    @Test
    void checkGetCartWithUpdProductAndPricesCorrect() {
        Cart expectedCart = cart;

        Assertions.assertThat(new UpdateCartInf().getCartWithUpdProductAndPrices(strings,discountCardMap,productMap)).isEqualTo(expectedCart);
    }

    @Test
    void checkGetCartWithUpdProductAndPricesUnCorrect() {
        Cart expectedCart = cart;
        expectedCart.setCard(new DiscountCard(1,33));

        Assertions.assertThat(new UpdateCartInf().getCartWithUpdProductAndPrices(strings,discountCardMap,productMap)).isNotEqualTo(expectedCart);
    }
}