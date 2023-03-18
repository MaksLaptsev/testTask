package ru.clevertec.operations;

import ru.clevertec.entity.Cart;
import ru.clevertec.entity.DiscountCard;
import ru.clevertec.entity.Product;
import ru.clevertec.utils.cartUtils.OperationsWithCart;
import ru.clevertec.utils.checkCreator.RoundingPrice;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OperationsWithCartTest {
    private Map<Product,Integer> productsWithPromo;
    private Map<Product,Integer> productsWithoutPromo;
    private Map<Integer,Product> bdProd;
    private Cart cart;

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
                productsWithPromo.put(key,value);
            }else {
                productsWithoutPromo.put(key,value);
            }
        });
    }


    @Test
    void checkAmountWithoutDiscountCorrect() {
        double expectedPrice = 1025d;
        AtomicReference<Double> sum = new AtomicReference<>(0.0);

        cart.getProductMap().forEach((key,value) -> sum.updateAndGet(v -> (v + key.getPrice() * value)));

        Assertions.assertThat(sum.get()).isEqualTo(expectedPrice);

    }
    @Test
    void checkAmountWithoutDiscountUnCorrect() {
        double expectedPrice = 1054.1d;
        AtomicReference<Double> sum = new AtomicReference<>(0.0);

        cart.getProductMap().forEach((key,value) -> sum.updateAndGet(v -> (v + key.getPrice() * value)));

        Assertions.assertThat(sum.get()).isNotEqualTo(expectedPrice);
    }

    @Test
    void checkAmountDiscountCorrect() {
        OperationsWithCart operations = new OperationsWithCart(cart);
        double expected = 948.75d;

        Assertions.assertThat(operations.amountDiscount()).isEqualTo(expected);
    }
    @Test
    void checkAmountDiscountUnCorrect() {
        OperationsWithCart operations = new OperationsWithCart(cart);
        double expected = 949.75d;

        Assertions.assertThat(operations.amountDiscount()).isNotEqualTo(expected);
    }


    @Test
    void checkAmountWithPromoAndDiscountCorrect(){
        AtomicReference<Double> amount = new AtomicReference<>(0.0);
        productsWithPromo.forEach((key,value) -> amount.updateAndGet(v -> v + key.getPrice() * value));
        double expectedAmount = 948.75d;

        Assertions.assertThat(new RoundingPrice().roundPrice((amount.get()*0.9)+498.75)).isEqualTo(expectedAmount);
    }


    @Test
    void checkGetCartWithUpdPricesCorrect() {
        cart.setAmountWithDiscount(948.75d);
        cart.setAmountWithoutDiscount(1025d);
        cart.setCardDisc(26.25d);
        cart.setPromoDisc(50d);

        Assertions.assertThat(new OperationsWithCart(cart).getCartWithUpdPrices()).isEqualTo(cart);
    }
    @Test
    void checkGetCartWithUpdPricesUnCorrect() {
        Cart expectedCart = new Cart();
        expectedCart.setAmountWithDiscount(948.75d);
        expectedCart.setAmountWithoutDiscount(1025d);
        expectedCart.setCardDisc(26.25d);
        expectedCart.setPromoDisc(51d);

        Assertions.assertThat(new OperationsWithCart(cart).getCartWithUpdPrices()).isNotEqualTo(expectedCart);
    }
}
