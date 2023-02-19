package com.maksru2009.operations;

import com.maksru2009.entity.Cart;
import com.maksru2009.entity.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class OperationsWithCart {
    private final Cart cart;
    private  Map<Product,Integer> productsWithPromo;
    private double promoDisc;
    private double cardDisc;
    private  Map<Product,Integer> productsWithoutPromo;

    public OperationsWithCart  (Cart cart){
        this.cart = cart;
        this.productsWithPromo = new HashMap<>();
        this.productsWithoutPromo = new HashMap<>();
        countPromoProduct(cart.getProductMap());

    }

    //цена товаров без учета каких либо скидок и акций
    public double amountWithoutDiscount(){
        AtomicReference<Double> sum = new AtomicReference<>(0.0);
        cart.getProductMap().forEach((key,value) -> sum.updateAndGet(v -> (v + key.getPrice() * value)));
        return sum.get();
    }

    //цена товаров со всеми скидками и акциями итоговая
    public double amountDiscount(){
        if(productsWithPromo.isEmpty()){
            return amountWithoutPromo();
        }else {
            return amountWithPromoAndDiscount();
        }
    }

    //цена товаров подходящих под акцию >= 5 минус 10% к стоимости,
    //а т.ж. является итоговой ценной с учетом всех скидок, если таковые есть
    private double amountWithPromoAndDiscount(){

        AtomicReference<Double> amount = new AtomicReference<>(0.0);
        productsWithPromo.forEach((key,value) -> amount.updateAndGet(v -> v + key.getPrice() * value));
        promoDisc = new RoundingPrice().roundPrice(amount.get()- (amount.get()*0.9));
        return new RoundingPrice().roundPrice((amount.get()*0.9)+amountWithoutPromo());
    }

    //цена товаров не подходящих под акцию >=5 минус 10% к стоимости, но с учетом скидки от скидочной карты,
    //если таковая имеется
    private double amountWithoutPromo(){
       AtomicReference<Double> amount = new AtomicReference<>(0.0);
       productsWithoutPromo.forEach((key,value) -> amount.updateAndGet(v -> (v + key.getPrice() * value)));

       if (cart.getCard()!=null){
           cardDisc = new RoundingPrice().roundPrice(amount.get()-amount.get()*cart.getCard().getPercentDiscountInDouble());
           return new RoundingPrice().roundPrice(amount.get()*cart.getCard().getPercentDiscountInDouble());
       }else {
           return amount.get();
       }
    }
    //сортировка товаров по условию соответствия акции >= 5
    private void countPromoProduct(Map<Product,Integer> map){
        map.forEach((key,value) ->{
            if(key.isDiscount() & value >= 5){
                productsWithPromo.put(key,value);
            }else {
                productsWithoutPromo.put(key,value);
            }
        });

    }

    public Cart getCartWithUpdPrices(){
        cart.setAmountWithDiscount(amountDiscount());
        cart.setAmountWithoutDiscount(amountWithoutDiscount());
        cart.setCardDisc(cardDisc);
        cart.setPromoDisc(promoDisc);
        return cart;
    }


}
