package edu.hw9;

import edu.hw9.Task1.StatsCollector;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class StatsCollectorTest {

    @ParameterizedTest
    @MethodSource("statsProvider")
    void statsCollectorTest(
        String firstMetricName,
        double[] firstMetricValue,
        String secondMetricName,
        double[] secondMetricValue,
        Map<String, Map<String, Double>> expected
    ) {

        StatsCollector collector = new StatsCollector();

        Thread thread1 = new Thread(() -> collector.push(firstMetricName, firstMetricValue));
        Thread thread2 = new Thread(() -> collector.push(secondMetricName, secondMetricValue));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Set<Map.Entry<String, Map<String, Double>>> result = collector.stats();

        assertThat(result)
            .isEqualTo(expected.entrySet());

    }

    public static Stream<Arguments> statsProvider() {
        return Stream.of(
            Arguments.of(
                "firstMetric", new double[] {1.1, 2.2, 3.3, 4.4},
                "secondMetric", new double[] {5.5, 6.6, 7.7, 8.8},
                Map.of(
                    "firstMetric", Map.of(
                        "Sum", 11.0,
                        "Avg", 2.75,
                        "Max", 4.4,
                        "Min", 1.1
                    ),
                    "secondMetric", Map.of(
                        "Sum", 28.6,
                        "Avg", 7.15,
                        "Max", 8.8,
                        "Min", 5.5
                    )
                )),
            Arguments.of(
                "firstMetric", new double[] {1.2, 2.3, 3.4, 4.5},
                "secondMetric", new double[] {1.2, 3.4, 5.6, 7.8},
                Map.of(
                    "firstMetric", Map.of(
                        "Sum", 11.4,
                        "Avg", 2.85,
                        "Max", 4.5,
                        "Min", 1.2
                    ),
                    "secondMetric", Map.of(
                        "Sum", 18.0,
                        "Avg", 4.5,
                        "Max", 7.8,
                        "Min", 1.2
                    )
                ))
        );
    }

}
