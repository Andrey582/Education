package edu.hw2;

import edu.hw2.Task2.Rectangle;
import edu.hw2.Task2.Square;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {

    static Arguments[] rectangles() {
        return new Arguments[]{
            Arguments.of(new Rectangle()),
            Arguments.of(new Square())
        };
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    void rectangleArea(Rectangle rect) {

        // when
        rect = rect.setWidth(20);
        rect = rect.setHeight(10);

        // then
        assertThat(rect.area()).isEqualTo(200.0);
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    void reactangleWithDifferentWidthAndHeight(Rectangle rect) {
        // given
        String result = "class edu.hw2.Task2.Rectangle";

        // when
        rect = rect.setWidth(20);
        rect = rect.setHeight(10);

        // then
        assertThat(rect.getClass().toString())
            .isEqualTo(result);
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    void reactangleWithSameWidthAndHeight(Rectangle rect) {
        // given
        String result = "class edu.hw2.Task2.Square";

        // when
        rect = rect.setWidth(20);
        rect = rect.setHeight(20);

        // then
        assertThat(rect.getClass().toString())
            .isEqualTo(result);
    }
}
