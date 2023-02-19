package com.maksru2009.operations;

import com.maksru2009.entity.Cart;
import com.maksru2009.entity.DiscountCard;
import com.maksru2009.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UpdateCartInfTest {
    String [] strings;
    Map<Integer, Product> productMap;
    Map<Integer, DiscountCard> discountCardMap;
    Cart cart;
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
    void testConvertStrInProduct() {
        Map<Product,Integer> map = new HashMap<>();
        map.put(productMap.get(1),2);
        Assertions.assertEquals(map,new UpdateCartInf().convertStrInProduct(strings[0],productMap, new HashMap<>()));
    }


    @Test
    void testConvertStrInCard() {
        Assertions.assertEquals(new DiscountCard(1,20),new UpdateCartInf().convertStrInCard(strings[1],discountCardMap));
    }

    @Test
    void testGetCartWithUpdProductAndPrices() {
        Assertions.assertEquals(cart,new UpdateCartInf().getCartWithUpdProductAndPrices(strings,discountCardMap,productMap));
    }
}