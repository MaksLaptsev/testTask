package ru.clevertec.operations;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.clevertec.entity.DiscountCard;
import ru.clevertec.entity.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;


class ObjectToJsonTest {
    private ObjectToJson toJson;

    @BeforeEach
    void setUp() {
        toJson = new ObjectToJson();

    }

    @AfterEach
    void tearDown() {
    }

    @ParameterizedTest
    @MethodSource("argsObjToJson")
    void checkObjToJsonStrCorrect(String expected,Product product) throws JsonProcessingException {

        Assertions.assertThat(toJson.objToJsonStr(product)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("argsObjToJsonUnCorrect")
    void checkObjToJsonStrUnCorrect(String expected,Product product) throws JsonProcessingException {

        Assertions.assertThat(toJson.objToJsonStr(product)).isNotEqualTo(expected);
    }

    static Stream<Arguments> argsObjToJson(){
        return Stream.of(
                Arguments.of(
                        "{\"id\":1,\"name\":\"Purrfect Treats\",\"maker\":\"unknown\",\"height\":0.0," +
                                "\"width\":0.0,\"length\":0.0,\"weight\":0.0,\"price\":100.0,\"discount\":true}",
                        new Product(1,"Purrfect Treats","unknown",0.0,0.0,0.0,
                                0.0,100.0,true )
                ),
                Arguments.of(
                        "{\"id\":2,\"name\":\"Furry Friends\",\"maker\":\"unknown\",\"height\":0.0," +
                                "\"width\":0.0,\"length\":0.0,\"weight\":0.0,\"price\":175.0,\"discount\":false}",
                        new Product(2,"Furry Friends","unknown",0.0,0.0,0.0,
                                0.0,175.0,false )
                )
        );
    }
    static Stream<Arguments> argsObjToJsonUnCorrect(){
        return Stream.of(
                Arguments.of(
                        "{\"id\":1,\"name\":\"Purrfect Treats\",\"maker\":\"unknown\",\"height\":0.0," +
                                "\"width\":0.0,\"length\":0.0,\"weight\":0.0,\"price\":100.0,\"discount\":true}",
                        new Product(4,"Purrfect Treats","unknown",4.0,0.0,0.0,
                                0.0,100.0,true )
                ),
                Arguments.of(
                        "{\"id\":2,\"name\":\"Furry Friends\",\"maker\":\"unknown\",\"height\":0.0," +
                                "\"width\":0.0,\"length\":0.0,\"weight\":0.0,\"price\":175.0,\"discount\":false}",
                        new Product(3,"Furry Friends","unknown",5.0,0.0,0.0,
                                0.0,175.0,false )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("argsProductMapFromFile")
    void checkProductMapFromFileCorrect(String fileName,Map<Integer,Product> map) {
        Assertions.assertThat(toJson.productMapFromFile(fileName,ObjectToJsonTest.class)).isEqualTo(map);
    }

    static Stream<Arguments> argsProductMapFromFile(){
        return Stream.of(
                Arguments.of(
                        "jsonTestProd.txt",
                        Map.of(1,new Product(1,"Purrfect Treats","unknown",0.0,0.0,
                                0.0,0.0,100.0,true ))
                ),
                Arguments.of(
                        "jsonTestProd1.txt",
                        Map.of(2,new Product(2,"Furry Friends","unknown",0.0,0.0,
                                0.0,0.0,175.0,false),
                                3,new Product(3,"Cuddlr","unknown",0.0,0.0,
                                        0.0,0.0,1000.0,false))
                )
        );
    }

    @Test
    void checkProductMapFromFileUnCorrectFileName() {
        //expected - not null map + loginfo about null and load default map
        String unCorrectFileName = "ajskfbjahn";

        Assertions.assertThat(toJson.productMapFromFile(unCorrectFileName,ObjectToJsonTest.class)).isNotNull();
    }

    @ParameterizedTest
    @MethodSource("argsDiscountMapFromFile")
    void checkDiscountMapFromFileCorrect(String filename, Map<Integer,DiscountCard> map) {
        Assertions.assertThat(toJson.discountMapFromFile(filename,ObjectToJsonTest.class)).isEqualTo(map);
    }

    static Stream<Arguments> argsDiscountMapFromFile(){
        return Stream.of(
                Arguments.of(
                        "jsonTestDisc.txt",
                        Map.of(1234,new DiscountCard(1234,5))
                ),
                Arguments.of(
                        "jsonTestDisc1.txt",
                        Map.of(154,new DiscountCard(154,10),
                                26, new DiscountCard(26,6))
                )
        );
    }
    @Test
    void checkDiscountMapFromFileUnCorrectFileName() {
        //expected - not null map + loginfo about null and load default map
        String unCorrectFileName = "ajskfbjahn";

        Assertions.assertThat(toJson.discountMapFromFile(unCorrectFileName,ObjectToJsonTest.class)).isNotNull();
    }


}