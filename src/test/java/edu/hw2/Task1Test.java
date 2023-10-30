package edu.hw2;

import edu.hw2.Task1.Expr;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {

    @ParameterizedTest
    @MethodSource("additionProvider")
    void additionTest(Expr.Constant first, Expr.Constant second, double expected) {

        // when
        double result = new Expr.Addition(first, second).evalute();

        // then
        assertThat(result)
            .isEqualTo(expected);
    }

    public static Stream<Arguments> additionProvider() {
        return Stream.of(
            Arguments.of(new Expr.Constant(1), new Expr.Constant(2), 3.0),
            Arguments.of(new Expr.Constant(8), new Expr.Constant(12), 20.0),
            Arguments.of(new Expr.Constant(-7), new Expr.Constant(3), -4.0),
            Arguments.of(new Expr.Constant(206), new Expr.Constant(44), 250.0)
            );
    }

    @ParameterizedTest
    @MethodSource("negateProvider")
    void negateTest(Expr.Constant constant, double expected) {

        // when
        double result = new Expr.Negate(constant).evalute();

        // then
        assertThat(result)
            .isEqualTo(expected);
    }

    public static Stream<Arguments> negateProvider() {
        return Stream.of(
            Arguments.of(new Expr.Constant(1), -1.0),
            Arguments.of(new Expr.Constant(8), -8.0),
            Arguments.of(new Expr.Constant(-7), 7.0),
            Arguments.of(new Expr.Constant(206), -206.0)
        );
    }

    @ParameterizedTest
    @MethodSource("exponentProvider")
    void exponentTest(Expr.Constant constant, int power, double expected) {

        // when
        double result = new Expr.Exponent(constant, power).evalute();

        // then
        assertThat(result)
            .isEqualTo(expected);
    }

    public static Stream<Arguments> exponentProvider() {
        return Stream.of(
            Arguments.of(new Expr.Constant(1), 7, 1.0),
            Arguments.of(new Expr.Constant(8), 4, 4096.0),
            Arguments.of(new Expr.Constant(-7), 3, -343.0),
            Arguments.of(new Expr.Constant(206), 2, 42436.0)
        );
    }

    @ParameterizedTest
    @MethodSource("multiplicationProvider")
    void multiplicationTest(Expr.Constant first, Expr.Constant second, double expected) {

        // when
        double result = new Expr.Multiplication(first, second).evalute();

        // then
        assertThat(result)
            .isEqualTo(expected);
    }

    public static Stream<Arguments> multiplicationProvider() {
        return Stream.of(
            Arguments.of(new Expr.Constant(1), new Expr.Constant(1), 1.0),
            Arguments.of(new Expr.Constant(8), new Expr.Constant(3), 24.0),
            Arguments.of(new Expr.Constant(-7), new Expr.Constant(7), -49.0),
            Arguments.of(new Expr.Constant(206), new Expr.Constant(2), 412.0)
        );
    }
}
