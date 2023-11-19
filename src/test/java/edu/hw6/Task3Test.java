package edu.hw6;

import edu.hw6.Task3.AbstractFilter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class Task3Test {

    @BeforeAll
    public static void create() throws IOException {
        List<Path> paths = List.of(
            Path.of("test.txt"),
            Path.of("test2.txt"),
            Path.of("another.txt"),
            Path.of("largeFile.txt")
        );

        for (Path path : paths) {
            Files.createFile(path);
        }

        Files.writeString(Path.of("largeFile.txt"),
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. " +
                "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. " +
                "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
    }

    @AfterAll
    public static void clear() {
        List<Path> paths = List.of(
            Path.of("test.txt"),
            Path.of("test2.txt"),
            Path.of("another.txt"),
            Path.of("largeFile.txt")
        );
        for (Path path : paths) {
            path.toFile().delete();
        }
    }

    @ParameterizedTest
    @MethodSource("filterProvider")
    void filterTest(Path path, AbstractFilter filter, List<Path> expected) {

        boolean isEquals = true;
        int count = 0;

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(path, filter)) {

            for (Path entry : entries) {
                if (!expected.contains(entry)) {
                    isEquals = false;
                    break;
                } else {
                    count++;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        isEquals = expected.size() == count ? isEquals : false;

        assertThat(isEquals).isTrue();
    }

    public static Stream<Arguments> filterProvider() {
        return Stream.of(
            Arguments.of(
                Path.of(""),
                AbstractFilter.READABLE
                    .and(AbstractFilter.REGULAR_FILE)
                    .and(AbstractFilter.globalMatches("*.txt")),
                List.of(
                    Path.of("test.txt"),
                    Path.of("test2.txt"),
                    Path.of("another.txt"),
                    Path.of("largeFile.txt")
                )),
            Arguments.of(
                Path.of(""),
                AbstractFilter.READABLE
                    .and(AbstractFilter.REGULAR_FILE)
                    .and(AbstractFilter.globalMatches("*.txt"))
                    .and(AbstractFilter.largerThan(200)),
                List.of(
                    Path.of("largeFile.txt")
                )),
            Arguments.of(
                Path.of(""),
                AbstractFilter.READABLE
                    .and(AbstractFilter.REGULAR_FILE)
                    .and(AbstractFilter.globalMatches("*.txt"))
                    .and(AbstractFilter.regexContains("test.*")),
                List.of(
                    Path.of("test.txt"),
                    Path.of("test2.txt")
                )),
            Arguments.of(
                Path.of(""),
                AbstractFilter.READABLE
                    .and(AbstractFilter.REGULAR_FILE)
                    .and(AbstractFilter.magicNumber(0x89, 'P', 'N', 'G')),
                List.of(
                    Path.of("file.png")
                )));
    }
}
