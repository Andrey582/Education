package edu.project3;

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

public class SiteParseTest {

    @ParameterizedTest
    @MethodSource("nameSiteProvider")
    void nameSiteParseTest(String path, List<String> expected) {

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
    @MethodSource("datesSiteProvider")
    void datesSiteParseTest(String path, String dateFrom, String dateTo, List<Map.Entry<LocalDate, Integer>> expected) {

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
    @MethodSource("resourcesSiteProvider")
    void resourcesSiteParseTest(String path, String dateFrom, String dateTo, List<Map.Entry<String, Integer>> expected) {

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
    @MethodSource("answerCodeSiteProvider")
    void answerCodeSiteParseTest(String path, String dateFrom, String dateTo, List<Map.Entry<String, Integer>> expected) {

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

    public static Stream<Arguments> nameSiteProvider() {
        return Stream.of(
            Arguments.of("https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs",
                List.of("/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs"))
        );
    }

    public static Stream<Arguments> datesSiteProvider() {
        return Stream.of(
            Arguments.of("https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs",
                "2015-05-18", "2015-05-25",
                Map.of(
                    LocalDate.of(2015, 5, 18), 2855,
                    LocalDate.of(2015, 5, 19), 2831,
                    LocalDate.of(2015, 5, 20), 2851,
                    LocalDate.of(2015, 5, 21), 2881,
                    LocalDate.of(2015, 5, 22), 2879,
                    LocalDate.of(2015, 5, 23), 2892,
                    LocalDate.of(2015, 5, 24), 2853,
                    LocalDate.of(2015, 5, 25), 2839
                )
                    .entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByKey())
                    .toList())
        );
    }

    public static Stream<Arguments> resourcesSiteProvider() {
        return Stream.of(
            Arguments.of("https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs",
                "2015-05-18", "2015-05-25",
                Map.of(
                        "/downloads/product_1", 13661,
                        "/downloads/product_2", 9186,
                        "/downloads/product_3", 34)
                    .entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .toList()
                    .reversed())
        );
    }

    public static Stream<Arguments> answerCodeSiteProvider() {
        return Stream.of(
            Arguments.of("https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs",
                "2015-05-18", "2015-05-25",
                Map.of(
                        "404", 14896,
                        "304", 5966,
                        "200", 1913,
                        "206", 93,
                        "403", 11,
                        "416", 2)
                    .entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .toList()
                    .reversed())
        );
    }
}
