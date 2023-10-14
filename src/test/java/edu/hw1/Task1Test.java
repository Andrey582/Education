package edu.hw1;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class Task1Test {

    @ParameterizedTest
    @DisplayName("Конвертация времени в секудны")
    @CsvSource(value = {
        "01:00, 60",
        "13:56, 836",
        "10:60, -1",
        "-10:59, -1",
        "999:59, 59999",
        "999:9O, -1"
    })
    void minuteToSecondTest(String given, long expected) {
        // when
        long seconds = Task1.minuteToSeconds(given);

        // then
        assertThat(seconds)
            .isEqualTo(expected);

    }

}
