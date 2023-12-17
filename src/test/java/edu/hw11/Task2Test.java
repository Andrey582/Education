package edu.hw11;

import edu.hw11.Task2.ArithmeticUtils;
import edu.hw11.Task2.Task2;
import net.bytebuddy.agent.ByteBuddyAgent;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {

    @ParameterizedTest
    @MethodSource("multiplyProvider")
    void testByteBuddyDelegate(int first, int second, int expected) throws InstantiationException, IllegalAccessException {
        ByteBuddyAgent.install();
        ArithmeticUtils arithmeticUtils = (ArithmeticUtils) Task2.byteBuddyMultiply.newInstance();
        int result = arithmeticUtils.sum(first, second);

        assertThat(result)
            .isEqualTo(expected);
    }

    public static Stream<Arguments> multiplyProvider() {
        return Stream.of(
            Arguments.of(3, 3, 9),
            Arguments.of(10, 20, 200),
            Arguments.of(15, 15, 225)
        );
    }
}
