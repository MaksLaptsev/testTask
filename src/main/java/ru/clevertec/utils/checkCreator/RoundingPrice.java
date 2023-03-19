package ru.clevertec.utils.checkCreator;

import java.text.DecimalFormat;

public class RoundingPrice {
    public double roundPrice(double d){
        int a = (int) (d*1000);
        String price = new DecimalFormat("#.##").format((double) a/1000).replace(",",".");
        return Double.parseDouble(price);
    }
}
