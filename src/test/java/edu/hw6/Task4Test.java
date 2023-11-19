package edu.hw6;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {

    @AfterAll
    public static void clear() {
        List<Path> paths = List.of(
            Path.of("test.txt")
        );
        for (Path path : paths) {
            path.toFile().delete();
        }
    }

    @ParameterizedTest
    @MethodSource("fileTextProvider")
    void writeToFileTest(String fileName, String text) throws IOException {

        Task4.writeToFile(fileName, text);

        String result = Files.readString(Path.of(fileName));

        assertThat(result)
            .isEqualTo(text);
    }

    public static Stream<Arguments> fileTextProvider() {
       return Stream.of(
           Arguments.of("test.txt", "Hello world!"),
           Arguments.of("test.txt", "Another text to file.")
       );
    }
}
