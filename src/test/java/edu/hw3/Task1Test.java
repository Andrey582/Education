package edu.hw3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {

    @ParameterizedTest
    @MethodSource("atbshProvider")
    void atbashTest(String input, String expected) {

        //when
        String result = Task1.atbash(input);

        assertThat(result)
            .isEqualTo(expected);
    }

    public static Stream<Arguments> atbshProvider() {
        return Stream.of(
            Arguments.of("Hello world!", "Svool dliow!"),
            Arguments.of("Any fool can write code that a computer can understand. " +
                    "Good programmers write code that humans can understand. ― Martin Fowler",
                    "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. " +
                    "Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi")
        );
    }
}
