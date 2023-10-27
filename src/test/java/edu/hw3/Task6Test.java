package edu.hw3;

import edu.hw3.Task6.Market;
import edu.hw3.Task6.Stock;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class Task6Test {

    @ParameterizedTest
    @MethodSource("stockProvider")
    void stcokTest(Market market, Stock expected) {

        // when
        Stock result = market.mostValuableStock();

        // then
        assertThat(result.getPrice())
            .isEqualTo(expected.getPrice());
    }

    public static Stream<Arguments> stockProvider() {
        return Stream.of(
            Arguments.of(new Market() {{
                add(new Stock("Microsoft", 3000));
                add(new Stock("Google", 7000));
                add(new Stock("Tesla", 5000));
            }},
                new Stock("Google", 7000)),
            Arguments.of(new Market() {{
                             add(new Stock("Microsoft", 9000));
                             add(new Stock("Google", 700));
                             add(new Stock("Tesla", 150000));
                         }},
                new Stock("Tesla", 150000)),
            Arguments.of(new Market() {{
                             add(new Stock("Microsoft", -30));
                             add(new Stock("Google", 70));
                             add(new Stock("Tesla", 500));
                         }},
                new Stock("Tesla", 500))
        );
    }
}
