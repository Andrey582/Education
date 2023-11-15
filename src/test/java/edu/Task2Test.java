package edu;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.Temporal;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {

    @ParameterizedTest
    @MethodSource("dateProvider")
    void getAllFridayThirteen(int year, String[] expected) {

        String[] result = Task2.getAllFridayThirteen(year);

        assertThat(result)
            .containsExactly(expected);
    }

    @ParameterizedTest
    @MethodSource("datesProvider")
    void getClosestFridayThirteen(Temporal date, Temporal expected) {

        Temporal result = Task2.getNextClosestFridayThirteen(date);

        assertThat(result)
            .isEqualTo(expected);
    }

    public static Stream<Arguments> dateProvider() {
        return Stream.of(
            Arguments.of(1925, new String[] {"1925-02-13", "1925-03-13", "1925-11-13"}),
            Arguments.of(2024, new String[] {"2024-09-13", "2024-12-13"})
        );
    }

    public static Stream<Arguments> datesProvider() {
        return Stream.of(
            Arguments.of(LocalDate.now(), LocalDate.of(2024, Month.SEPTEMBER, 13)),
            Arguments.of(LocalDate.of(2023, Month.OCTOBER, 12), LocalDate.of(2023, Month.OCTOBER, 13))
        );
    }
}
