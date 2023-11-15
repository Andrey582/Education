package edu;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {

    @ParameterizedTest
    @MethodSource("dateTimeProvider")
    void calculateTime(String[] dateTime, String expected) {

        String result = Task1.calculateTime(dateTime);

        assertThat(result)
            .isEqualTo(expected);
    }

    public static Stream<Arguments> dateTimeProvider() {
        return Stream.of(
            Arguments.of(new String[] {
                "2022-03-12, 20:20 - 2022-03-12, 23:50",
                "2022-04-01, 21:30 - 2022-04-02, 01:20",
            }, "3ч 40м")
        );
    }
}
