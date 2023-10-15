package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task5Test {

    @ParameterizedTest
    @DisplayName("Проверяем, является ли число или любой из его потомков палиндромом")
    @CsvSource(value = {
        "11211230, true",
        "13001120, true",
        "23336014, true",
        "11, true",
        "123, true",
        "1234, false",
        "-123, false"
    })
    void isPalindromeDescendantTest(long given, boolean expected) {
        // when
        boolean result = Task5.isPalindromeDescendant(given);

        // then
        assertThat(result)
            .isEqualTo(expected);
    }
}
