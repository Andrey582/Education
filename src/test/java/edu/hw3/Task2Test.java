package edu.hw3;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {

    @ParameterizedTest
    @MethodSource("clusterizeProvider")
    void clusterizeTest(String sequence, List<String> expected) {

        // when
        List<String> result = Task2.clusterize(sequence);

        // then
        assertThat(result)
            .isEqualTo(expected);
    }

    public static Stream<Arguments> clusterizeProvider() {
        return Stream.of(
            Arguments.of("()()()", Arrays.asList("()", "()", "()")),
            Arguments.of("((()))", Arrays.asList("((()))")),
            Arguments.of("((()))(())()()(()())", Arrays.asList("((()))", "(())", "()", "()", "(()())")),
            Arguments.of("((())())(()(()()))", Arrays.asList("((())())", "(()(()()))"))
        );
    }
}
