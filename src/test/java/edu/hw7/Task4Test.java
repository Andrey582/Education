package edu.hw7;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {

    @ParameterizedTest
    @MethodSource("PIProvider")
    void countSpeedTest(int iteration, int countThread) {

        long startSingleThread = System.nanoTime();
        Task4.singleThreadMonteCarlo(iteration);
        long endSingleThread = System.nanoTime();

        long startMultiThread = System.nanoTime();
        Task4.multiThreadMonteCarlo(iteration, countThread);
        long endMultiThread = System.nanoTime();

        long singleTime = endSingleThread - startSingleThread;
        long multiTime = endMultiThread - startMultiThread;

        assertThat(singleTime)
            .isGreaterThan(multiTime);
    }

    public static Stream<Arguments> PIProvider() {
        return Stream.of(
            Arguments.of(1000000, 4),
            Arguments.of(10000000, 4),
            Arguments.of(100000000, 4),
            Arguments.of(1000000000, 4)
        );
    }
}
