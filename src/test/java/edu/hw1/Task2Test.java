package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {

    @ParameterizedTest
    @DisplayName("Подсчет цифр в числе")
    @CsvSource(value = {
        "12345, 5",
        "123456789, 9",
        "-9837413, 7",
        "0, 1"
    })
    void countDigitsTest(long given, int expected) {
        // when
        int countDigits = Task2.countDigits(given);

        // then
        assertThat(countDigits)
            .isEqualTo(expected);

    }
}
