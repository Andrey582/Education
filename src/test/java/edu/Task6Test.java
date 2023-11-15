package edu;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task6Test {

    @ParameterizedTest
    @MethodSource("stringProvider")
    void checkSubsequence(String find, String original, boolean expected) {

        boolean result = Task6.checkSubsequence(find, original);

        assertThat(result)
            .isEqualTo(expected);
    }

    public static Stream<Arguments> stringProvider() {
        return Stream.of(
            Arguments.of("abc", "kjhlkjhlkejhrflabcskdjfhlkjrh", true),
            Arguments.of("123", "kjhlkj123ejhrflabcskdjfhlkjrh", true),
            Arguments.of("123", "kjhl1jhlkej2rflabcs3djfhlkjrh", true),
            Arguments.of("abc", "kjhlkjhlkejhrflskdjfhlkjrh", false),
            Arguments.of("123", "kjhl1jh3kej2rflabcsdjfhlkjrh", false)
        );
    }
}
