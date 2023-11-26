package edu.hw7;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {

    @ParameterizedTest
    @MethodSource("factorialProvider")
    void factorialTest(int factorial, int expected) {

        int result = Task2.factorial(factorial);

        assertThat(result)
            .isEqualTo(expected);
    }

    public static Stream<Arguments> factorialProvider() {
        return Stream.of(
            Arguments.of(2, 2),
            Arguments.of(3, 6),
            Arguments.of(4, 24),
            Arguments.of(10, 3628800)
        );
    }
}
