package edu;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task5Test {

    @ParameterizedTest
    @MethodSource("autoNumberSignProvider")
    void autoNumberSignCheck(String number, boolean expected) {

        boolean result = Task5.checkAutoNumberSign(number);

        assertThat(result)
            .isEqualTo(expected);
    }

    public static Stream<Arguments> autoNumberSignProvider() {
        return Stream.of(
            Arguments.of("А123ВЕ777", true),
            Arguments.of("О777ОО177", true),
            Arguments.of("123АВЕ777", false),
            Arguments.of("А123ВГ77", false),
            Arguments.of("А123ВЕ7777", false)
        );
    }
}
