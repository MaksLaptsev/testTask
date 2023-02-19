package com.maksru2009.operations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maksru2009.entity.DiscountCard;
import com.maksru2009.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectToJson {
    private static final Logger logger = LoggerFactory.getLogger(ObjectToJson.class);


    public String objToJsonStr(Product product) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String objStr = objectMapper.writeValueAsString(product);
        return objStr;
    }

    //загрузка из файла листа с json Product
    public Map<Integer,Product> productMapFromFile(String fileName,Class<?> c){
        Map<Integer,Product> map = new HashMap<>();
        try{
            ClassLoader classLoader = c.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(fileName);
            if (inputStream == null)throw new IllegalArgumentException();
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(streamReader);
            String line = reader.readLine();
            while (line != null){
                map.put(fromJsonToProduct(line).getId(),fromJsonToProduct(line));
                line = reader.readLine();
            }
            reader.close();
        }catch (IOException e){
            logger.info(e.getMessage()+"\n read file jsonProduct error");
            logger.info("load default productMap");
            return map;
        }catch (IllegalArgumentException e){
            logger.info(e.getMessage());
            logger.info("load file jsonProduct.txt error");
            logger.info("load default productMap");
            return map;
        }
        return map;
    }

    //конвертация в Product
    private Product fromJsonToProduct(String s){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            Product product = objectMapper.readValue(s,Product.class);
            return product;
        }catch (JsonProcessingException e){
            logger.info(e.getMessage()+"\n json conversion error");
            return new Product();
        }

    }

    //загрузка из файла листа с json DiscountCard
    public Map<Integer,DiscountCard> discountMapFromFile(String fileName,Class<?> c){
        Map<Integer,DiscountCard> map = new HashMap<>();
        try{
            ClassLoader classLoader = c.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(fileName);
            if (inputStream == null)throw new IllegalArgumentException();
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(streamReader);
            String line = reader.readLine();
            while (line != null){
                map.put(fromJsonToDiscount(line).getId(),fromJsonToDiscount(line));
                line = reader.readLine();
            }
            reader.close();
        }catch (IOException e){
            logger.info(e.getMessage()+"\n read file jsonDiscount error");
            logger.info("load default DiscountCardMap");
            return map;
        }catch (IllegalArgumentException e){
            logger.info(e.getMessage());
            logger.info("load file error jsonDiscount.txt");
            logger.info("load default DiscountCardMap");
            return map;
        }
        return map;
    }
    //конвертация в DiscountCard
    private DiscountCard fromJsonToDiscount(String s) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        DiscountCard discountCard = objectMapper.readValue(s,DiscountCard.class);
        return discountCard;
    }


}
