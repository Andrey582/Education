package edu.hw9;

import edu.hw9.Task2.FindByPredicate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class FileSearchTest {

    public static Predicate<File> predicate = e -> e.toString().contains("Test");
    public static Predicate<File> predicate2 = e -> e.toString().contains("Maze");

    @ParameterizedTest
    @MethodSource("filesProvider")
    void findByPredicateTest(File startDirectory, Predicate<File> predicate, List<File> expected) {

        FindByPredicate findByPredicate = new FindByPredicate(startDirectory, predicate);
        List<File> result;
        try (ForkJoinPool pool = new ForkJoinPool()) {
             result = pool.invoke(findByPredicate);
        }

        assertThat(result)
            .isEqualTo(expected);

    }

    public static Stream<Arguments> filesProvider() {
        return Stream.of(
            Arguments.of(
                new File("src/test/java/edu/hw9"),
                predicate,
                List.of(
                    new File("src/test/java/edu/hw9/DirectorySearchTest.java"),
                    new File("src/test/java/edu/hw9/FileSearchTest.java"),
                    new File("src/test/java/edu/hw9/ParallelDfsTest.java"),
                    new File("src/test/java/edu/hw9/StatsCollectorTest.java")
                )),
            Arguments.of(
                new File("src/main"),
                predicate2,
                List.of(
                    new File("src/main/java/edu/Creator/MazeCreator.java"),
                    new File("src/main/java/edu/Creator/MazeDeepFirsSearch.java"),
                    new File("src/main/java/edu/Creator/MazeEller.java"),
                    new File("src/main/java/edu/Solver/MazeSolver.java")
                ))
        );
    }
}
