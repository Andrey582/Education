package edu;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task7Test {

    @ParameterizedTest
    @MethodSource("firstProvider")
    void moreOrEqualsThreeAndZeroIsThird(String input, boolean expected) {

        boolean result = Task7.moreOrEqualsThreeAndZeroIsThird(input);

        assertThat(result)
            .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("secondProvider")
    void startAndEndWithSameSymbol(String input, boolean expected) {

        boolean result = Task7.startAndEndWithSameSymbol(input);

        assertThat(result)
            .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("thirdProvider")
    void lengthMoreOrEqualOneAndLessOrEqualThree(String input, boolean expected) {

        boolean result = Task7.lengthMoreOrEqualOneAndLessOrEqualThree(input);

        assertThat(result)
            .isEqualTo(expected);
    }

    public static Stream<Arguments> firstProvider() {
        return Stream.of(
            Arguments.of("110", true),
            Arguments.of("110111", true),
            Arguments.of("110112", false),
            Arguments.of("11", false),
            Arguments.of("011", false)
        );
    }

    public static Stream<Arguments> secondProvider() {
        return Stream.of(
            Arguments.of("10001", true),
            Arguments.of("1111111", true),
            Arguments.of("0111110", true),
            Arguments.of("1111110", false),
            Arguments.of("1112111", false)
        );
    }

    public static Stream<Arguments> thirdProvider() {
        return Stream.of(
            Arguments.of("111", true),
            Arguments.of("11", true),
            Arguments.of("1", true),
            Arguments.of("0", true),
            Arguments.of("010", true),
            Arguments.of("0101", false),
            Arguments.of("", false)
        );
    }
}
