package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task7Test {

    @ParameterizedTest
    @DisplayName("Циклический сдвиг вправо")
    @CsvSource(value = {
        "8, 1, 4",
        "16, 3, 2",
        "8, 4, 8",
        "5, 1, 6",
        "-12, 2, -1"
    })
    void rotateRightTest(int number, int step, int expected) {
        // when
        int result = Task7.rotateRight(number, step);

        // then
        assertThat(result)
            .isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("Циклический сдвиг влево")
    @CsvSource(value = {
        "9, 1, 3",
        "16, 1, 1",
        "17, 2, 6",
        "11, 4, 11",
        "-12, 2, -1"
    })
    void rotateLeftTest(int number, int step, int expected) {
        // when
        int result = Task7.rotateLeft(number, step);

        // then
        assertThat(result)
            .isEqualTo(expected);
    }
}
