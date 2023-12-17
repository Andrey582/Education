package edu.hw11;

import edu.hw11.Task2.ArithmeticUtils;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {

    public static int sum (int a, int b) {
        return a * b;
    }

    @ParameterizedTest
    @MethodSource("multiplyProvider")
    void testByteBuddyDelegate(int first, int second, int expected) {

        ByteBuddyAgent.install();

        new ByteBuddy()
            .redefine(ArithmeticUtils.class)
            .method(ElementMatchers.named("sum"))
            .intercept(MethodDelegation.to(Task2Test.class))
            .make()
            .load(
                ArithmeticUtils.class.getClassLoader(),
                ClassReloadingStrategy.fromInstalledAgent()
            );


        int result = ArithmeticUtils.sum(first, second);

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
