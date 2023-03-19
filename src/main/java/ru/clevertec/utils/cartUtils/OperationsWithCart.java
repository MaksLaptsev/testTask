package ru.clevertec.utils.cartUtils;

import ru.clevertec.dao.CartDao;
import ru.clevertec.entity.Cart;
import ru.clevertec.entity.Product;
import ru.clevertec.utils.checkCreator.RoundingPrice;
import java.util.*;
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
        countPromoProduct(cart.getListProduct());
    }

    //цена товаров без учета каких либо скидок и акций
    public double amountWithoutDiscount(){
        AtomicReference<Double> sum = new AtomicReference<>(0.0);
        cart.getListProduct().forEach(x-> sum.updateAndGet(v->v+ x.getPrice()));
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

       if (cart.getDiscountCard()!=null){
           cardDisc = new RoundingPrice().roundPrice(amount.get()-amount.get()*cart.getDiscountCard().getPercentDiscountInDouble());
           return new RoundingPrice().roundPrice(amount.get()*cart.getDiscountCard().getPercentDiscountInDouble());
       }else {
           return amount.get();
       }
    }
    //сортировка товаров по условию соответствия акции >= 5
    private void countPromoProduct(List<Product> productList){
        Map<Product, Integer> map = new HashMap<>();
        for (Product product:productList) {
            if (!map.containsKey(product)){
                map.put(product,1);
            }else {map.put(product,map.get(product)+1);}
        }
        map.forEach((key,value) ->{
            if(key.isDiscount() & value >= 5){
                productsWithPromo.put(key,value);
            }else {
                productsWithoutPromo.put(key,value);
            }
        });
    }

    public Cart getCartWithUpdPrices(){
        cart.setAmountWithDis(amountDiscount());
        cart.setAmountWithoutDis(amountWithoutDiscount());
        cart.setCardDisc(cardDisc);
        cart.setPromoDisc(promoDisc);
        new CartDao().update(cart);
        return cart;
    }

}

