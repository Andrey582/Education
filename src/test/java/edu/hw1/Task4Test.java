package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {
    @ParameterizedTest
    @DisplayName("Исрпавляем поврежденную строку")
    @CsvSource(value = {
        "123456, 214365",
        "hTsii  s aimex dpus rtni.g, This is a mixed up string.",
        "badce, abcde"
    })
    void fixStringTest(String given, String expected) {
        // when
        String result = Task4.fixString(given);

        // then
        assertThat(result)
            .isEqualTo(expected);
    }
}
