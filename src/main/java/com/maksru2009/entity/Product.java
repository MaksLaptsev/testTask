package com.maksru2009.entity;

import java.util.Objects;

public class Product {

    private int id;
    private String name;
    private String maker;
    private double height;
    private double width;
    private double length;
    private double weight;
    private double price;
    private boolean isDiscount;


    public Product() {
    }

    public Product(int id, String name, String maker, double height, double width, double length,
                   double weight, double price, boolean isDiscount) {
        this.id = id;
        this.name = name;
        this.maker = maker;
        this.height = height;
        this.width = width;
        this.length = length;
        this.weight = weight;
        this.price = price;
        this.isDiscount = isDiscount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isDiscount() {
        return isDiscount;
    }

    public void setDiscount(boolean discount) {
        isDiscount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && Double.compare(product.height, height) == 0 && Double.compare(product.width, width) == 0 && Double.compare(product.length, length) == 0 && Double.compare(product.weight, weight) == 0 && Double.compare(product.price, price) == 0 && isDiscount == product.isDiscount && Objects.equals(name, product.name) && Objects.equals(maker, product.maker);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, maker, height, width, length, weight, price, isDiscount);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maker='" + maker + '\'' +
                ", height=" + height +
                ", width=" + width +
                ", length=" + length +
                ", weight=" + weight +
                ", price=" + price +
                ", isDiscount=" + isDiscount +
                '}';
    }
}
