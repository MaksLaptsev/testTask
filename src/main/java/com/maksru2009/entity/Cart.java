package com.maksru2009.entity;

import com.maksru2009.interfaces.Order;

import java.util.*;

public class Cart implements Order {

    private List<Product> listProduct;
    private Map<Product,Integer> productMap;
    private DiscountCard discountCard;
    private double amountWithDis;
    private double amountWithoutDis;
    private double promoDisc;
    private double cardDisc;

    public Cart(){
        this.productMap = new HashMap<>();
        this.listProduct = new ArrayList<>();
        this.discountCard = null;
    }

    public Map<Product,Integer> getProductMap(){
        return this.productMap;
    }
    public void setProductMap(Map<Product,Integer> map){
        this.productMap = map;
    }

    public void setPromoDisc(double promoDisc){
        this.promoDisc = promoDisc;
    }
    public double getPromoDisc(){
        return promoDisc;
    }

    public void setCardDisc(double cardDisc){
        this.cardDisc = cardDisc;
    }
    public double getCardDisc(){
        return cardDisc;
    }

    @Override
    public void addProduct(Product product) {
        this.listProduct.add(product);
    }

    @Override
    public void removeProduct(Product product) {
        this.listProduct.remove(product);
    }

    @Override
    public void setCard(DiscountCard card) {
        this.discountCard = card;
    }
    @Override
    public DiscountCard getCard() {
        return discountCard;
    }
    @Override
    public void setAmountWithDiscount(double amount) {
        this.amountWithDis = amount;
    }
    @Override
    public double getAmountWithDiscount() {
        return amountWithDis;
    }

    @Override
    public void setAmountWithoutDiscount(double amount) {
        this.amountWithoutDis = amount;
    }
    @Override
    public double getAmountWithoutDiscount() {
        return amountWithoutDis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Double.compare(cart.amountWithDis, amountWithDis) == 0 && Double.compare(cart.amountWithoutDis, amountWithoutDis) == 0 && Objects.equals(listProduct, cart.listProduct) && Objects.equals(discountCard, cart.discountCard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listProduct, discountCard, amountWithDis, amountWithoutDis);
    }
}
