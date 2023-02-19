package com.maksru2009.operations;

import com.maksru2009.entity.Cart;
import com.maksru2009.entity.DiscountCard;
import com.maksru2009.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OperationsWithCartTest {
    Map<Product,Integer> productsWithPromo;
    Map<Product,Integer> productsWithoutPromo;
    Map<Integer,Product> bdProd;
    Cart cart;

    private double promoDisc = 0d;
    private double cardDisc = 0d;

    @BeforeAll
    void setUp() {
        productsWithPromo = new HashMap<>();
        productsWithoutPromo = new HashMap<>();
        bdProd = new DefaultMaps().initMapWithDefaultProduct();
        Map<Product,Integer> map = new HashMap<>();
        map.put(bdProd.get(1),5);
        map.put(bdProd.get(2),3);
        cart = new Cart();
        cart.setProductMap(map);
        cart.setCard(new DiscountCard(1,5,0.95));

        cart.getProductMap().forEach((key,value) ->{
            if(key.isDiscount() & value >= 5){
                this.productsWithPromo.put(key,value);
            }else {
                this.productsWithoutPromo.put(key,value);
            }
        });
    }


    @Test
    void testAmountWithoutDiscount() {
        AtomicReference<Double> sum = new AtomicReference<>(0.0);
        cart.getProductMap().forEach((key,value) -> sum.updateAndGet(v -> (v + key.getPrice() * value)));
        Assertions.assertEquals(1025d,sum.get());
    }

    @Test
    void testAmountWithPromoAndDiscount(){
        AtomicReference<Double> amount = new AtomicReference<>(0.0);
        productsWithPromo.forEach((key,value) -> amount.updateAndGet(v -> v + key.getPrice() * value));
        promoDisc = new RoundingPrice().roundPrice(amount.get()- (amount.get()*0.9));
        Assertions.assertEquals(948.75,new RoundingPrice().roundPrice((amount.get()*0.9)+498.75));
    }

    @Test
    void testAmountWithoutPromo(){
        AtomicReference<Double> amount = new AtomicReference<>(0.0);
        productsWithoutPromo.forEach((key,value) -> amount.updateAndGet(v -> (v + key.getPrice() * value)));

        if (cart.getCard()!=null){
            cardDisc = new RoundingPrice().roundPrice(amount.get()-amount.get()*cart.getCard().getPercentDiscountInDouble());
             Assertions.assertEquals(498.75,new RoundingPrice().roundPrice(amount.get()*cart.getCard().getPercentDiscountInDouble()));
        }else {
             Assertions.assertEquals(525d,amount.get());
        }
    }

    @Test
    void testGetCartWithUpdPrices() {
        cart.setAmountWithDiscount(948.75d);
        cart.setAmountWithoutDiscount(1025d);
        cart.setCardDisc(26.25d);
        cart.setPromoDisc(50d);
        Assertions.assertEquals(cart,new OperationsWithCart(cart).getCartWithUpdPrices());
    }



}