package com.maksru2009.operations;

import com.maksru2009.entity.DiscountCard;
import com.maksru2009.entity.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class DefaultMapsTest {
    private DefaultMaps defaultMaps;
    private Map<Integer,Product> prod;
    private Map<Integer,DiscountCard> disc;

    @BeforeEach
    void setUp() {
        defaultMaps = new DefaultMaps();
        prod = new HashMap();
        disc = new HashMap();
        prod.put(1,new Product(1,"Purrfect Treats","unknown",0.0,0.0,0.0,0.0,100.0,true ));
        disc.put(1,new DiscountCard(1,20));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void checkEmptyProductCorrect() {
        Assertions.assertThat(defaultMaps.checkEmptyProduct(prod)).isEqualTo(prod);
    }

    @Test
    void checkEmptyDiscCorrect() {
        Assertions.assertThat(defaultMaps.checkEmptyDisc(disc)).isEqualTo(disc);
    }
}