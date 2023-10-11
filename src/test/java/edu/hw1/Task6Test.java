package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task6Test {

    @ParameterizedTest
    @DisplayName("Ищем необходимое количество шагов для получения постоянной Капрекара")
    @CsvSource(value = {
        "3524, 3",
        "6621, 5",
        "6554, 4",
        "1234, 3",
        "0001, 0",
        "6174, 0",
        "-1234, 0",
        "12345, 0",
        "1111, 0"
    })
    void kaprekarTest(int given, int expected) {
        // when
        int result = Task6.kaprekar(given);

        // then
        assertThat(result)
            .isEqualTo(expected);
    }
}
