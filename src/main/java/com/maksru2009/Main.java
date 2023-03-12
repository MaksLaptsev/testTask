package com.maksru2009;

import com.maksru2009.entity.Cart;
import com.maksru2009.operations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileNotFoundException;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args){

        Cart cart;
        DefaultMaps defaultMaps = new DefaultMaps();



        if(args == null || args.length == 0){
            String[] aaa={"7-5"," 2-4"," 10-9", "4-2", "7-1","5-5", "card-26"};
            logger.info("add next arguments: 7-5, 2-4, 10-9, 4-2, 7-1, 5-5, card-26");
            cart = new UpdateCartInf()
                    .getCartWithUpdProductAndPrices
                            (aaa,defaultMaps.checkEmptyDisc(new ObjectToJson().discountMapFromFile("jsonDiscount.txt",ObjectToJson.class))
                                    ,defaultMaps.checkEmptyProduct(new ObjectToJson().productMapFromFile("jsonProduct.txt", ObjectToJson.class)));
        }else {
            cart = new UpdateCartInf()
                    .getCartWithUpdProductAndPrices
                            (args,defaultMaps.checkEmptyDisc(new ObjectToJson().discountMapFromFile("jsonDiscount.txt",ObjectToJson.class))
                                    ,defaultMaps.checkEmptyProduct(new ObjectToJson().productMapFromFile("jsonProduct.txt",ObjectToJson.class)));
        }

        cart = new OperationsWithCart(cart).getCartWithUpdPrices();
        CreateRecipePdf recipePdf = new CreateRecipePdf();
        DirToSaveCheck.checkPathInParams(args);

        try{
            recipePdf.createCashRecipe(cart);
        }catch (FileNotFoundException e){
            logger.info(e.getMessage());
            logger.info("error reading the file, close Recipe.pdf and try again");
        }

    }
}