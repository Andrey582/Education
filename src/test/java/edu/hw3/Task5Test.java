package edu.hw3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

public class Task5Test {

    @ParameterizedTest
    @MethodSource("contactProvider")
    void parseContactsTest(String[] array, String orderBy, Task5.Contact[] expected) {

        // when
        Task5.Contact[] result = Task5.parseContacts(array, orderBy);

        //then
        assertThat(result)
            .containsExactly(expected);
    }

    public static Stream<Arguments> contactProvider() {
        return Stream.of(
            Arguments.of(
                new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"},
                "ASC",
                new Task5.Contact[] {
                    new Task5.Contact("Thomas", "Aquinas"),
                    new Task5.Contact("Rene", "Descartes"),
                    new Task5.Contact("David", "Hume"),
                    new Task5.Contact("John", "Locke")
                }),
            Arguments.of(
                new String[] {"Paul Erdos", "Leonhard Euler", "Carl Gauss"},
                "DESC",
                new Task5.Contact[] {
                    new Task5.Contact("Carl", "Gauss"),
                    new Task5.Contact("Leonhard", "Euler"),
                    new Task5.Contact("Paul", "Erdos")
                }
            ),
            Arguments.of(new String[] {}, "DESC", new Task5.Contact[] {}),
            Arguments.of(null, "ASC", new Task5.Contact[] {}),
            Arguments.of(
                new String[] {"Rodrigo", "Andrew Clark", "Raphael"},
                "ASC",
                new Task5.Contact[] {
                    new Task5.Contact("Andrew", "Clark"),
                    new Task5.Contact("Raphael", null),
                    new Task5.Contact("Rodrigo", null)
                }
            )
        );
    }
}
