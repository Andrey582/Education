package edu.hw6;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {

    @AfterAll
    public static void clear() {
        List<Path> paths = List.of(
            Path.of("test.txt"),
            Path.of("test - копия.txt"),
            Path.of("test - копия (1).txt"),
            Path.of("test - копия (2).txt")
        );
        for (Path path : paths) {
            path.toFile().delete();
        }
    }

    @ParameterizedTest
    @MethodSource("filePathProvider")
    void createCopyTest(Path filePath, Path expected) throws IOException {

        Task2.cloneFile(filePath);

        assertThat(Files.exists(expected))
            .isTrue();
    }

    public static Stream<Arguments> filePathProvider() {
        return Stream.of(
            Arguments.of(Path.of("test.txt"), Path.of("test.txt")),
            Arguments.of(Path.of("test.txt"), Path.of("test - копия.txt")),
            Arguments.of(Path.of("test.txt"), Path.of("test - копия (1).txt")),
            Arguments.of(Path.of("test.txt"), Path.of("test - копия (2).txt"))
        );
    }
}
