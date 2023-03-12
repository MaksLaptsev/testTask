package com.maksru2009.entity;

import java.util.Objects;

public class DiscountCard {
    private int id;
    private int percentDiscount;
    private double percentDiscountInDouble;
    public DiscountCard() {
    }

    public DiscountCard(int id, int percentDiscount, double percentDiscountInDouble ) {
        this.id = id;
        this.percentDiscount = percentDiscount;
        this.percentDiscountInDouble = percentDiscountInDouble;
    }

    public DiscountCard(int id, int percentDiscount) {
        this.id = id;
        this.percentDiscount = percentDiscount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPercentDiscount() {
        return percentDiscount;
    }

    public void setPercentDiscount(int percentDiscount) {
        this.percentDiscount = percentDiscount;
    }

    public void setPercentDiscountInDouble(int percentDiscountInDouble) {
        this.percentDiscountInDouble = percentDiscountInDouble;
    }

    public double getPercentDiscountInDouble(){
        return  (100-(double)percentDiscount)/100;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscountCard that = (DiscountCard) o;
        return id == that.id && percentDiscount == that.percentDiscount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, percentDiscount);
    }
}
