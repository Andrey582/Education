package edu;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {

    @ParameterizedTest
    @MethodSource("passwordProvider")
    void passwordCheck(String password, boolean expected) {

        boolean result = Task4.checkPassword(password);

        assertThat(result)
            .isEqualTo(expected);
    }

    public static Stream<Arguments> passwordProvider() {
        return Stream.of(
            Arguments.of("123!123", true),
            Arguments.of("f~!@#$%^&*|", true),
            Arguments.of("~!@#$%^&*|", true),
            Arguments.of("|asd", true),
            Arguments.of("as!dlfgh", true),
            Arguments.of("asd@lfgh", true),
            Arguments.of("asdl|fgh", true),
            Arguments.of("123123", false),
            Arguments.of("asdlfgh", false)
        );
    }
}
