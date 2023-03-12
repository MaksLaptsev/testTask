package com.maksru2009.interfaces;

import com.maksru2009.entity.Product;

public interface Builder {
    Builder setId(int id);
    Builder setName(String name);
    Builder setMaker(String maker);
    Builder setHeight(double height);
    Builder setWidth(double width);
    Builder setLength(double length);
    Builder setWeight(double weight);
    Builder setPrice(double price);
    Builder setIsDiscount(boolean isDiscount);
    Product getResult();

}
