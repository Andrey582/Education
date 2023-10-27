package edu.hw3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.swing.table.AbstractTableModel;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {

    @ParameterizedTest
    @MethodSource("romanProvider")
    void convertToRomanTest(int input, String expected) {

        // when
        String result = Task4.convertToRoman(input);

        // then
        assertThat(result)
            .isEqualTo(expected);
    }

    public static Stream<Arguments> romanProvider() {
        return Stream.of(
            Arguments.of(2, "II"),
            Arguments.of(12, "XII"),
            Arguments.of(16, "XVI"),
            Arguments.of(345, "CCCXLV")
        );
    }
}
