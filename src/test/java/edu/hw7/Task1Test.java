package edu.hw7;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {

    @ParameterizedTest
    @MethodSource("counterProvider")
    void counterTest(int first, int second, int third, int expected) {

        int result = Task1.counterAdding(first, second, third);

        assertThat(result)
            .isEqualTo(expected);
    }

    public static Stream<Arguments> counterProvider() {
        return Stream.of(
            Arguments.of(1000, 2000, 3000, 6000),
            Arguments.of(1500, 2500, 3500, 7500),
            Arguments.of(123, 456, 789, 1368)
        );
    }
}
