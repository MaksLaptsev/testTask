package com.maksru2009.operations;

import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

class RoundingPriceTest {

    @Test
    void checkRoundPriceCorrect() {
        double expected = 15.76;

        Assertions.assertThat(new RoundingPrice().roundPrice(15.7566666666)).isEqualTo(expected);
    }
    @Test
    void checkRoundPriceUnCorrect() {
        double expected = 15.75;

        Assertions.assertThat(new RoundingPrice().roundPrice(15.7566666666)).isNotEqualTo(expected);
    }
}