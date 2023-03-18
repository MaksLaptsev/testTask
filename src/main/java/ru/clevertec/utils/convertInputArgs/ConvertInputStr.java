package ru.clevertec.utils.convertInputArgs;

import ru.clevertec.NotFoundIdInMap;
import ru.clevertec.entity.Cart;
import ru.clevertec.entity.Product;
import ru.clevertec.service.CartService;
import ru.clevertec.service.DiscountCardService;
import ru.clevertec.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;

public class ConvertInputStr {
    private static final Logger logger = LoggerFactory.getLogger(ConvertInputStr.class);
    private CartService cartService;
    private DiscountCardService cardService;
    private ProductService productService;

    private Integer cartID;

    public ConvertInputStr(){
        cartService = new CartService();
        cartID = cartService.saveAndGetId(Cart.builder().build());
        cardService = new DiscountCardService();
        productService = new ProductService();
    }

    public Cart getCartFromInputArgs(String[] args){
        for (String str : args) {
            if(str.replace(" ","").split("-")[0].equals("card")){
                convertStrInCard(str);
            }else if (!str.replace(" ","").split("-")[0].equals("pathToSave")) {
                convertStrInProduct(str);
            }
        }
        return cartService.get(cartID);
    }

    private void convertStrInCard(String args){
        String[] subStr;
        Cart cart = cartService.get(cartID);
        subStr = args.replace(" ","").split("-");
        try {
            if(cardService.get(Integer.parseInt(subStr[1])).getPercentDiscount()!=0){
                cart.setDiscountCard(cardService.get(Integer.parseInt(subStr[1])));
                cartService.update(cart);
            }else {
                throw new NotFoundIdInMap("Skid.cards with id - "+subStr[1]+" "+" not found, the purchase will be made without a card");
            }
        }
        catch (NumberFormatException e){
            logger.info("card data entered incorrectly "+ Arrays.toString(subStr) +"\n example: 5-1 5-3 card-1234");
        }
        catch (NotFoundIdInMap e){
            logger.info(e.getMessage());
        }
    }

    private void convertStrInProduct(String args){
        Cart cart = cartService.get(cartID);
        List<Product> list = cart.getListProduct();
        String[] subStr;
        subStr = args.replace(" ","").split("-");
        try {

            try {
                if(productService.get(Integer.parseInt(subStr[0])).getId()!=0){
                    for(int i =0; i < Integer.parseInt(subStr[1]); i++){
                        list.add(productService.get(Integer.parseInt(subStr[0])));
                    }
                    cart.setListProduct(list);
                    cartService.update(cart);
                }else {
                    throw new NotFoundIdInMap("Product with id-"+subStr[0]+"not found");
                }

            }catch (NotFoundIdInMap e){
                logger.info(e.getMessage());
            }
        }catch (NumberFormatException e){
            logger.info("data entered incorrectly "+ Arrays.toString(subStr) +"\n example: 5-1 5-3 card-1234");
        }
    }

}
