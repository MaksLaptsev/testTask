package com.maksru2009.operations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.maksru2009.entity.DiscountCard;
import com.maksru2009.entity.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

import java.util.HashMap;
import java.util.Map;


class ObjectToJsonTest {
    private ObjectToJson toJson;
    private Map<Integer,Product> productMap;
    private Map<Integer, DiscountCard> discountCardMap;

    @BeforeEach
    void setUp() {
        toJson = new ObjectToJson();
        productMap = new HashMap<>();
        discountCardMap = new HashMap<>();

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void checkObjToJsonStrCorrect() throws JsonProcessingException {
        String expected = "{\"id\":1,\"name\":\"Purrfect Treats\",\"maker\":\"unknown\",\"height\":0.0,\"width\":0.0,\"length\":0.0,\"weight\":0.0,\"price\":100.0,\"discount\":true}";
        Product product = new Product(1,"Purrfect Treats","unknown",0.0,0.0,0.0,0.0,100.0,true );

        Assertions.assertThat(toJson.objToJsonStr(product)).isEqualTo(expected);
    }

    @Test
    void checkObjToJsonStrUnCorrect() throws JsonProcessingException {
        String expected = "{\"id\":2,\"name\":\"Purrfect Treat\",\"maker\":\"unknown\",\"height\":0.0,\"width\":0.0,\"length\":0.0,\"weight\":0.0,\"price\":100.0,\"discount\":true}";
        Product product = new Product(1,"Purrfect Treats","unknown",0.0,0.0,0.0,0.0,100.0,true );

        Assertions.assertThat(toJson.objToJsonStr(product)).isNotEqualTo(expected);
    }

    //так и не понял, как нужно правильно писать тест для таких методов, где идет чтение файла, и преобазование в мапу с обьектами
    @Test
    void checkProductMapFromFileCorrect() {
        //expected
        productMap.put(1,new Product(1,"Purrfect Treats","unknown",0.0,0.0,0.0,0.0,100.0,true ));

        Assertions.assertThat(toJson.productMapFromFile("jsonTestProd.txt",ObjectToJsonTest.class)).isEqualTo(productMap);
    }

    @Test
    void checkProductMapFromFileUnCorrectFileName() {
        //expected - not null map
        String unCorrectFileName = "ajskfbjahn";

        Assertions.assertThat(toJson.productMapFromFile(unCorrectFileName,ObjectToJsonTest.class)).isNotNull();
    }

    @Test
    void checkDiscountMapFromFileCorrect() {
        //expected
        discountCardMap.put(1234,new DiscountCard(1234,5));

        Assertions.assertThat(toJson.discountMapFromFile("jsonTestDisc.txt",ObjectToJsonTest.class)).isEqualTo(discountCardMap);
    }
    @Test
    void checkDiscountMapFromFileUnCorrectFileName() {
        //expected - not null map
        String unCorrectFileName = "ajskfbjahn";

        Assertions.assertThat(toJson.discountMapFromFile(unCorrectFileName,ObjectToJsonTest.class)).isNotNull();
    }


}