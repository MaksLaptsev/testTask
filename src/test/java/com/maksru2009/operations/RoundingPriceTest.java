package com.maksru2009.operations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoundingPriceTest {

    @Test
    void testRoundPrice() {
        double d = 15.76;
        Assertions.assertEquals(d,new RoundingPrice().roundPrice(15.7566666666));
    }
}