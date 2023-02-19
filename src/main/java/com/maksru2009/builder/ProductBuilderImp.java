package com.maksru2009.builder;

import com.maksru2009.entity.Product;
import com.maksru2009.interfaces.Builder;

public class ProductBuilderImp implements Builder {
    private int id;
    private String name;
    private String maker;
    private double height;
    private double width;
    private double length;
    private double weight;
    private double price;
    private boolean isDiscount;

    public ProductBuilderImp(){
        this.name = "unknown";
        this.maker = "unknown";
        this.height = 0;
        this.width = 0;
        this.length =0;
        this.weight = 0;
        this.price = 0;
        this.isDiscount = false;
    }





    @Override
    public Builder setId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public Builder setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Builder setMaker(String maker) {
        this.maker = maker;
        return this;
    }

    @Override
    public Builder setHeight(double height) {
        this.height = height;
        return this;
    }

    @Override
    public Builder setWidth(double width) {
        this.width = width;
        return this;
    }

    @Override
    public Builder setLength(double length) {
        this.length = length;
        return this;
    }

    @Override
    public Builder setWeight(double weight) {
        this.weight = weight;
        return this;
    }

    @Override
    public Builder setPrice(double price) {
        this.price = price;
        return this;
    }

    @Override
    public Builder setIsDiscount(boolean isDiscount) {
        this.isDiscount = isDiscount;
        return this;
    }

    @Override
    public Product getResult(){
        return new Product(id, name, maker, height, width, length, weight, price, isDiscount);
    }
}
