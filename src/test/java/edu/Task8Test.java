package edu;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task8Test {

    @ParameterizedTest
    @MethodSource("oddProvider")
    void isOdd(String input, boolean expected) {

        boolean result = Task8.isOdd(input);

        assertThat(result)
            .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("oddAndOneStartProvider")
    void isOddAndStartWithZeroOrEvenAndStartWithOne(String input, boolean expected) {

        boolean result = Task8.isOddAndStartWithZeroOrEvenAndStartWithOne(input);

        assertThat(result)
            .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("zeroCountMultipleOfThreeProvider")
    void zeroCountMultipleOfThree(String input, boolean expected) {

        boolean result = Task8.zeroCountMultipleOfThree(input);

        assertThat(result)
            .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("everyOddElementEqualsOneProvider")
    void everyOddElementEqualsOne(String input, boolean expected) {

        boolean result = Task8.everyOddElementEqualsOne(input);

        assertThat(result)
            .isEqualTo(expected);
    }

    public static Stream<Arguments> oddProvider() {
        return Stream.of(
            Arguments.of("1", true),
            Arguments.of("101", true),
            Arguments.of("10111", true),
            Arguments.of("1011", false),
            Arguments.of("", false),
            Arguments.of("11", false)
        );
    }

    public static Stream<Arguments> oddAndOneStartProvider() {
        return Stream.of(
            Arguments.of("11", true),
            Arguments.of("1101", true),
            Arguments.of("011", true),
            Arguments.of("0", true),
            Arguments.of("01", false),
            Arguments.of("100", false)
        );
    }
    public static Stream<Arguments> zeroCountMultipleOfThreeProvider() {
        return Stream.of(
          Arguments.of("1101110110", true),
          Arguments.of("000", true),
          Arguments.of("000000", true),
          Arguments.of("000000000", true),
          Arguments.of("00101010001010", true),
          Arguments.of("101", false),
          Arguments.of("101110", false),
          Arguments.of("101110111", false),
          Arguments.of("", false)
        );
    }

    public static Stream<Arguments> everyOddElementEqualsOneProvider() {
        return Stream.of(
            Arguments.of("1", true),
            Arguments.of("11", true),
            Arguments.of("111", true),
            Arguments.of("1111", true),
            Arguments.of("11111", true),
            Arguments.of("11101", true),
            Arguments.of("10101", true),
            Arguments.of("101010", true),
            Arguments.of("1010100", false),
            Arguments.of("", false),
            Arguments.of("0", false)
        );
    }
}
