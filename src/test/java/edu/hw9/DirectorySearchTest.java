package edu.hw9;

import edu.hw9.Task2.FindDirectory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.File;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class DirectorySearchTest {

    @ParameterizedTest
    @MethodSource("fileProvider")
    void directorySearchTest(File startDirectory, int count, List<File> expected) {
        FindDirectory findDirectory = new FindDirectory(startDirectory, count);
        List<File> result;
        try (ForkJoinPool pool = new ForkJoinPool()) {
            result = pool.invoke(findDirectory);
        }

        assertThat(result)
            .containsAll(expected);
    }

    public static Stream<Arguments> fileProvider() {
        return Stream.of(
            Arguments.of(
                new File("src/main"),
                3,
                List.of(
                    new File("src/main/java/edu/Creator"),
                    new File("src/main/java/edu/Solver")
                )
            ),
            Arguments.of(
                new File("src/main"),
                2,
                List.of(
                    new File("src/main/java/edu/Creator"),
                    new File("src/main/java/edu/hw9/Task2"),
                    new File("src/main/java/edu/Solver")
                )
            )
        );
    }
}
