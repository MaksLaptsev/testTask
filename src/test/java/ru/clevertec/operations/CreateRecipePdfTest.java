package ru.clevertec.operations;

import ru.clevertec.entity.Cart;
import ru.clevertec.entity.DiscountCard;
import ru.clevertec.entity.Product;
import ru.clevertec.utils.checkCreator.CreateRecipePdf;
import ru.clevertec.utils.checkCreator.DirToSaveCheck;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

class CreateRecipePdfTest {

    private CreateRecipePdf recipePdf;
    private Cart cart;
    @BeforeEach
    void setUp() {
        recipePdf = new CreateRecipePdf();
        HashMap<Product,Integer> map = new HashMap<>();
        map.put(new Product(1,"Purrfect Treats","unknown",0.0,0.0,0.0,0.0,100.0,true ),7);
        cart = new Cart();
        cart.setCard(new DiscountCard(1,20));
        cart.setProductMap(map);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void checkCreateCashRecipe() throws IOException {
        DirToSaveCheck.path = System.getProperty("user.dir")+"\\src\\test\\resources";
        Path path = Paths.get(DirToSaveCheck.path+"\\RecipeFolder\\Recipe.pdf");

        recipePdf.createCashRecipe(cart);

        Assertions.assertTrue(Files.deleteIfExists(path));

    }
}