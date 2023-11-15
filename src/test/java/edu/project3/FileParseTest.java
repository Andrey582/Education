package edu.project3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class FileParseTest {

    @ParameterizedTest
    @MethodSource("nameFileProvider")
    void nameFileParseTest(String path, List<String> expected) {

        LogStats logStats = new LogStats(path, null, null, null);
        try {
            logStats.parse();
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

        List<String> result = logStats.getStorage().getFiles();

        assertThat(result)
            .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("datesFileProvider")
    void datesFileParseTest(String path, String dateFrom, String dateTo, List<Map.Entry<LocalDate, Integer>> expected) {

        LogStats logStats = new LogStats(path, dateFrom, dateTo, null);
        try {
            logStats.parse();
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

        List<Map.Entry<LocalDate, Integer>> result = logStats.getStorage().getDates();

        assertThat(result)
            .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("resourcesFileProvider")
    void resourcesFileParseTest(String path, String dateFrom, String dateTo, List<Map.Entry<String, Integer>> expected) {

        LogStats logStats = new LogStats(path, dateFrom, dateTo, null);
        try {
            logStats.parse();
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

        List<Map.Entry<String, Integer>> result = logStats.getStorage().getResources();

        assertThat(result)
            .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("answerCodeFileProvider")
    void answerCodeFileParseTest(String path, String dateFrom, String dateTo, List<Map.Entry<String, Integer>> expected) {

        LogStats logStats = new LogStats(path, dateFrom, dateTo, null);
        try {
            logStats.parse();
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

        List<Map.Entry<String, Integer>> result = logStats.getStorage().getAnswerCode();

        assertThat(result)
            .isEqualTo(expected);
    }

    public static Stream<Arguments> nameFileProvider() {
        return Stream.of(
            Arguments.of("test/logs.txt", List.of("logs.txt")),
            Arguments.of("test/**/logs2.txt", List.of( "logs2.txt", "logs2.txt")),
            Arguments.of("test/*", List.of("logs.txt", "logs2.txt", "logs2.txt"))
        );
    }

    public static Stream<Arguments> datesFileProvider() {
        return Stream.of(
            Arguments.of("test/logs.txt", "2015-05-18", null,
                Map.of(LocalDate.of(2015, 5, 18), 141).entrySet().stream().toList()),
            Arguments.of("test/*", "2015-05-18", "2015-05-20",
                Map.of(
                    LocalDate.of(2015, 5, 18), 257,
                    LocalDate.of(2015, 5, 19), 1384
                ).entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByKey())
                    .toList())
        );
    }

    public static Stream<Arguments> resourcesFileProvider() {
        return Stream.of(
            Arguments.of("test/logs.txt", "2015-05-18", null,
                Map.of(
                    "/downloads/product_1", 81,
                    "/downloads/product_2", 60)
                    .entrySet()
                    .stream()
                    .toList()),
            Arguments.of("test/*", "2015-05-18", "2015-05-20",
                Map.of(
                        "/downloads/product_1", 941,
                        "/downloads/product_2", 698,
                        "/downloads/product_3", 2
                    ).entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .toList()
                    .reversed())
        );
    }

    public static Stream<Arguments> answerCodeFileProvider() {
        return Stream.of(
            Arguments.of("test/logs.txt", "2015-05-18", null,
                Map.of(
                        "404", 117,
                        "304", 22,
                        "200", 2)
                    .entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .toList()
                    .reversed()),
            Arguments.of("test/*", "2015-05-18", "2015-05-20",
                Map.of(
                        "404", 1081,
                        "304", 406,
                        "200", 152,
                        "403", 2
                    ).entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .toList()
                    .reversed())
        );
    }
}
