package com.maksru2009.operations;

import com.maksru2009.NotFoundIdInMap;
import com.maksru2009.entity.Cart;
import com.maksru2009.entity.DiscountCard;
import com.maksru2009.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateCartInf {
    private static final Logger logger = LoggerFactory.getLogger(UpdateCartInf.class);
    private Cart cart;
    private DiscountCard discountCard;

    //перевод строки типа:1-5 5-2 в обьект Product
    public Map<Product,Integer> convertStrInProduct(String args, Map<Integer,Product> productMap){
        Map<Product,Integer> products = new HashMap<>();
        String[] subStr;
        subStr = args.replace(" ","").split("-");
        try {

            try {
                if(productMap.get(Integer.parseInt(subStr[0]))!=null){
                    products.put(productMap.get(Integer.parseInt(subStr[0])),Integer.parseInt(subStr[1]));
                }else {
                    throw new NotFoundIdInMap("Product with id-"+subStr[0]+"not found");
                }

            }catch (NotFoundIdInMap e){
                logger.info(e.getMessage());
            }


        }catch (NumberFormatException e){
            logger.info("data entered incorrectly "+subStr.toString()+"\n example: 5-1 5-3 card-1234");
        }
        return products;
    }
    //добавление товара в сущ корзину
    public Map<Product,Integer>  convertStrInProduct(String args, Map<Integer,Product> bdWithProducts,Map<Product,Integer> productMap){
        convertStrInProduct(args,bdWithProducts).forEach((key,value) ->{
            if(productMap.containsKey(key)){
                productMap.put(key,productMap.get(key)+value);
            }else {
                productMap.put(key,value);
            }
        });

        return  productMap;
    }
    //перевод строки типа: card-1234 в объект DiscountCard
    public DiscountCard convertStrInCard(String args,Map<Integer,DiscountCard> discountCardMap){
        String[] subStr;
        subStr = args.replace(" ","").split("-");
        try {
                if(discountCardMap.get(Integer.parseInt(subStr[1]))!=null){
                    return discountCardMap.get(Integer.parseInt(subStr[1]));
                }else {
                    throw new NotFoundIdInMap("Skid.cards with id - "+subStr[1]+" "+" not found, the purchase will be made without a card");
                }
            }
        catch (NumberFormatException e){
            logger.info("card data entered incorrectly "+subStr.toString()+"\n example: 5-1 5-3 card-1234");
            return null;
        }
        catch (NotFoundIdInMap e){
            logger.info(e.getMessage());
            return null;
        }
    }
    //получение полностью обновленного объекта корзины в соответсвии с введенными данными
    public Cart getCartWithUpdProductAndPrices(String[] args,Map<Integer,DiscountCard> bDWithCard,Map<Integer,Product> bdWithProducts){
        cart = new Cart();
        discountCard = new DiscountCard();
        for (String str : args) {
            if(str.replace(" ","").split("-")[0].equals("card")){
                discountCard = convertStrInCard(str,bDWithCard);
                cart.setCard(discountCard);
            }else if (!str.replace(" ","").split("-")[0].equals("pathToSave"))
            {
                {
                    if(cart.getProductMap().isEmpty()){
                        cart.setProductMap(convertStrInProduct(str,bdWithProducts));
                    }else {
                        cart.setProductMap(convertStrInProduct(str,bdWithProducts,cart.getProductMap()));

                    }
                }
            }
        }
        return cart;
    }


}
