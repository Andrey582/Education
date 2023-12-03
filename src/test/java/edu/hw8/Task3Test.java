package edu.hw8;

import edu.hw8.Task3.MultiBrute;
import edu.hw8.Task3.SingleBrute;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task3Test {

    @ParameterizedTest
    @MethodSource("dataBaseProvider")
    void singleThreadTest(List<String> dataBase, Map<String, String> expected) {

        SingleBrute singleBrute = new SingleBrute(dataBase);
        Map<String, String> result = singleBrute.bruteDatabase();

        assertThat(result)
            .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("dataBaseProvider")
    void multiThreadTest(List<String> dataBase, Map<String, String> expected) {

        MultiBrute multiBrute = new MultiBrute(dataBase);
        Map<String, String> result = multiBrute.bruteDatabase(4);

        assertThat(result)
            .isEqualTo(expected);
    }

    public static Stream<Arguments> dataBaseProvider() {
        return Stream.of(
                Arguments.of(List.of("first 81dc9bdb52d04dc20036dbd8313ed055",
                "second a384b6463fc216a5f8ecb6670f86456a",
                "test 098f6bcd4621d373cade4e832627b4f6"),
                Map.of("first", "1234",
                    "second", "qwert",
                    "test", "test")),
            Arguments.of(List.of("first e2fc714c4727ee9395f324cd2e7f331f",
                    "second 5d41402abc4b2a76b9719d911017c592",
                    "test 7d793037a0760186574b0282f2f435e7"),
                Map.of("first", "abcd",
                    "second", "hello",
                    "test", "world"))
        );
    }
}
