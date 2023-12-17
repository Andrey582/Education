package edu.hw11;

import edu.hw11.Task3.Task3;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class Task3Test {

    @ParameterizedTest
    @MethodSource("fibonacciProvider")
    void fibonacciTest(long num, long expected)
        throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        long result = (long) Task3.fibonacci.getMethod("fib", long.class).invoke(null, num);

        assertThat(result)
            .isEqualTo(expected);
    }

    public static Stream<Arguments> fibonacciProvider() {
        return Stream.of(
            Arguments.of(5, 5),
            Arguments.of(6, 8),
            Arguments.of(7, 13),
            Arguments.of(8, 21),
            Arguments.of(16, 987)
        );
    }

}

