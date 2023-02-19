package com.maksru2009.interfaces;

import com.maksru2009.entity.DiscountCard;
import com.maksru2009.entity.Product;


public interface Order {

    void addProduct(Product product);
    void removeProduct(Product product);
    void setCard(DiscountCard card);
    DiscountCard getCard();
    void setAmountWithDiscount(double amount);
    double getAmountWithDiscount();
    void setAmountWithoutDiscount(double amount);
    double getAmountWithoutDiscount();

}
