package edu.hw3;

import edu.hw3.Task8.BackwardIterator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class Task8Test<T> {

    @ParameterizedTest
    @MethodSource("bacwarIteratorProvider")
    void backwardIteratorTest(Collection<T> collection, Collection<T> expected) {

        // given
        boolean equals = true;

        // when
        BackwardIterator<T> iterator = new BackwardIterator<>(collection);
        Iterator<T> expectedIterator = expected.iterator();

        while (iterator.hasNext()) {
            if (!iterator.next().equals(expectedIterator.next())) {
                equals = false;
            }
        }

        equals = expectedIterator.hasNext() ? false : equals;

        // then
        assertThat(equals)
            .isTrue();
    }

    public static Stream<Arguments> bacwarIteratorProvider() {
        return Stream.of(
            Arguments.of(List.of(1, 2, 3), List.of(3, 2, 1)),
            Arguments.of(List.of('a', 'b', 'c'), List.of('c', 'b', 'a')),
            Arguments.of(List.of("abc", "def", "zxc"), List.of("zxc", "def", "abc"))
        );
    }

}
