package edu.hw3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.rmi.MarshalledObject;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

public class Task3Test<T> {

    @ParameterizedTest
    @MethodSource("freqDictProvider")
    void freqDictTest(List<T> input, HashMap<T, Integer> expected) {

        // when
        HashMap<T, Integer> result = Task3.freqDict(input);

        // then
        assertThat(result)
            .isEqualTo(expected);
    }

    public static Stream<Arguments> freqDictProvider() {
        return Stream.of(
            Arguments.of(Arrays.asList("a", "bb", "a", "bb"), new HashMap<>() {{
                put("a", 2);
                put("bb", 2);
            }}),
            Arguments.of(Arrays.asList("this", "and", "that", "and"), new HashMap<>() {{
                put("this", 1);
                put("and", 2);
                put("that", 1);
            }}),
            Arguments.of(Arrays.asList("код", "код", "код", "bug"), new HashMap<>() {{
                put("код", 3);
                put("bug", 1);
            }}),
            Arguments.of(Arrays.asList(1, 1, 2, 2), new HashMap<>() {{
                put(1, 2);
                put(2, 2);
            }})
        );
    }
}
