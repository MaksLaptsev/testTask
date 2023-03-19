package ru.clevertec;

import ru.clevertec.entity.Cart;
import ru.clevertec.utils.cartUtils.OperationsWithCart;
import ru.clevertec.utils.checkCreator.CreateRecipePdf;
import ru.clevertec.utils.checkCreator.DirToSaveCheck;
import ru.clevertec.utils.convertInputArgs.ConvertInputStr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args){

        Cart cart;

        if(args == null || args.length == 0){
            String[] aaa={"9-13"," 6-15"," 2-77", "4-9", "5-6","3-15", "card-2"};
            logger.info("add next arguments: 9-13, 6-15, 2-77, 4-9, 5-6, 3-15, card-2");
            cart = new ConvertInputStr().getCartFromInputArgs(aaa);
        }else {
            cart = new ConvertInputStr().getCartFromInputArgs(args);
        }

        cart = new OperationsWithCart(cart).getCartWithUpdPrices();
        CreateRecipePdf recipePdf = new CreateRecipePdf();
        DirToSaveCheck.checkPathInParams(args);

        try{
            recipePdf.createCashRecipe(cart);
        }catch (FileNotFoundException e){
            logger.info(e.getMessage());
            logger.info("error reading the file, close Recipe.pdf and try again");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}