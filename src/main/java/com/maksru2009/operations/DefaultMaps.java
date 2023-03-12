package com.maksru2009.operations;

import com.maksru2009.builder.ProductBuilderImp;
import com.maksru2009.entity.DiscountCard;
import com.maksru2009.entity.Product;

import java.util.HashMap;
import java.util.Map;

public class DefaultMaps {
    public Map<Integer, Product> initMapWithDefaultProduct(){
        Product p1 = new ProductBuilderImp().setId(1).setIsDiscount(true).setName("Purrfect Treats").setPrice(100).getResult();
        Product p2 = new ProductBuilderImp().setId(2).setIsDiscount(false).setName("Furry Friends").setPrice(175).getResult();
        Product p3 = new ProductBuilderImp().setId(3).setIsDiscount(false).setName("Cuddlr").setPrice(1000).getResult();
        Product p4 = new ProductBuilderImp().setId(4).setIsDiscount(true).setName("Petropolitan").setPrice(321).getResult();
        Product p5 = new ProductBuilderImp().setId(5).setIsDiscount(false).setName("Colorart").setPrice(444).getResult();
        Product p6 = new ProductBuilderImp().setId(6).setIsDiscount(false).setName("Roche").setPrice(700).getResult();
        Product p7 = new ProductBuilderImp().setId(7).setIsDiscount(false).setName("God's apple ").setPrice(100000).getResult();
        Product p8 = new ProductBuilderImp().setId(8).setIsDiscount(false).setName("Phone").setPrice(55).getResult();
        Product p9 = new ProductBuilderImp().setId(9).setIsDiscount(false).setName("Car").setPrice(123.55).getResult();
        Product p10 = new ProductBuilderImp().setId(10).setIsDiscount(true).setName("Fruit").setPrice(750).getResult();

        Map<Integer,Product> bdWithProducts = new HashMap<>();
        bdWithProducts.put(1,p1);
        bdWithProducts.put(2,p2);
        bdWithProducts.put(3,p3);
        bdWithProducts.put(4,p4);
        bdWithProducts.put(5,p5);
        bdWithProducts.put(6,p6);
        bdWithProducts.put(7,p7);
        bdWithProducts.put(8,p8);
        bdWithProducts.put(9,p9);
        bdWithProducts.put(10,p10);

        return bdWithProducts;
    }

    public Map<Integer, DiscountCard> initMapWithDefaultDiscount(){
        DiscountCard card = new DiscountCard(1,20);
        DiscountCard card1 = new DiscountCard(154,10);
        DiscountCard card2 = new DiscountCard(1234,5);
        DiscountCard card3 = new DiscountCard(2,13);
        DiscountCard card4 = new DiscountCard(26,6);

        Map<Integer,DiscountCard> bDWithCard = new HashMap<>();
        bDWithCard.put(card.getId(),card);
        bDWithCard.put(card1.getId(),card1);
        bDWithCard.put(card2.getId(),card2);
        bDWithCard.put(card3.getId(),card3);
        bDWithCard.put(card4.getId(),card4);

        return bDWithCard;
    }

    public Map<Integer,Product> checkEmptyProduct (Map<Integer,Product> map){
        if(map.isEmpty()){
            return initMapWithDefaultProduct();
        }else return map;
    }

    public Map<Integer,DiscountCard> checkEmptyDisc(Map<Integer,DiscountCard> map){
        if(map.isEmpty()){
            return initMapWithDefaultDiscount();
        }else return map;
    }
}
