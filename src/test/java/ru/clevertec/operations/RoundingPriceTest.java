package ru.clevertec.operations;

import ru.clevertec.utils.checkCreator.RoundingPrice;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class RoundingPriceTest {

    @ParameterizedTest
    @MethodSource("argsRoundPrice")
    void checkRoundPriceCorrect(double expected, double test) {
        Assertions.assertThat(new RoundingPrice().roundPrice(test)).isEqualTo(expected);
    }

    static Stream<Arguments> argsRoundPrice(){
        return Stream.of(
                Arguments.of(
                        15.76,15.75555
                ),
                Arguments.of(
                        18.99,18.988888
                ),
                Arguments.of(
                        956.13,956.1267423
                )
        );
    }
    @Test
    void checkRoundPriceUnCorrect() {
        double expected = 15.75;

        Assertions.assertThat(new RoundingPrice().roundPrice(15.7566666666)).isNotEqualTo(expected);
    }
}