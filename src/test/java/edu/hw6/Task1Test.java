package edu.hw6;

import edu.hw6.Task1.DiskMap;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Task1Test {

    @AfterAll
    public static void clear() {
        List<Path> paths = List.of(
            Path.of("test.txt"),
            Path.of("test2.txt")
        );
        for (Path path : paths) {
            path.toFile().delete();
        }
    }

    @ParameterizedTest
    @MethodSource("saveValueProvider")
    @Order(1)
    void diskMapSaveValueTest(Map<String, String> map, Path filePath) {

        DiskMap diskMap = new DiskMap(filePath);

        diskMap.setMap(map);
        diskMap.safeValueToFile();

        assertThat(filePath.toFile().exists()).isTrue();

    }

    @ParameterizedTest
    @MethodSource("loadValueProvider")
    @Order(2)
    void diskMapLoadValueTest(Path filePath, Map<String, String> expected) {
        DiskMap diskMap = new DiskMap(filePath);

        diskMap.loadValueFromFile();
        Map<String, String> result = diskMap.getValues();

        assertThat(result)
            .isEqualTo(expected);
    }

    public static Stream<Arguments> saveValueProvider() {
        return Stream.of(
            Arguments.of(Map.of(
                "asd", "123",
                "fgh", "456",
                "jkl", "789"
            ), Path.of("test.txt")),
            Arguments.of(Map.of(
                "123", "asd",
                "456", "fgh",
                "789", "jkl"
            ), Path.of("test2.txt"))
        );
    }

    public static Stream<Arguments> loadValueProvider() {
        return Stream.of(
            Arguments.of(Path.of("test.txt"), Map.of(
                "asd", "123",
                "fgh", "456",
                "jkl", "789"
            )),
            Arguments.of(Path.of("test2.txt"), Map.of(
                "123", "asd",
                "456", "fgh",
                "789", "jkl"
            ))
        );
    }
}
