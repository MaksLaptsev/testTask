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
import java.util.Arrays;

class CreateRecipePdfTest {

    private CreateRecipePdf recipePdf;
    private Cart cart;
    @BeforeEach
    void setUp() {
        recipePdf = new CreateRecipePdf();
        cart = new Cart();
        cart.setDiscountCard(DiscountCard.builder().id(1).percentDiscount(20).percentDiscountInDouble(0.8).build());
        cart.setListProduct(Arrays.asList(Product.builder().id(1).name("name").maker("unknown").isDiscount(true).price(100).build(),
                Product.builder().id(2).name("name").maker("unknown").isDiscount(false).price(123).build()));
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