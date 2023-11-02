package edu;

import javax.swing.text.StyledEditorKit;
import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Methods {

    public static List<Animal> Task1(Collection<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .toList();
    }

    public static List<Animal> Task2(Collection<Animal> animals, int k) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::weight).reversed())
            .limit(Math.max(k, 0))
            .toList();
    }

    public static Map<Animal.Type, Integer> Task3(Collection<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::type,
                Collectors.collectingAndThen(Collectors.counting(), Long::intValue)));
    }

    public static Animal Task4(Collection<Animal> animals) {
        return animals.stream()
            .max(Comparator.comparing(Animal::name))
            .get();
    }

    public static Animal.Sex Task5(Collection<Animal> animals) {
        return animals.stream()
            .max(Comparator.comparing(Animal::sex))
            .get()
            .sex();
    }

    /*public static Map<Animal.Type, Animal> Task6(Collection<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::type,
                Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(Animal::weight)))))
    }*/

    public static Animal Task7(Collection<Animal> animals) {
        return animals.stream()
            .max(Comparator.comparing(Animal::age))
            .get();
    }

    public static Optional<Animal> Task8(Collection<Animal> animals, int k) {
        return animals.stream()
            .filter(e -> e.height() < k)
            .max(Comparator.comparing(Animal::weight));
    }

    public static Integer Task9(Collection<Animal> animals) {
        return animals.stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    public static List<Animal> Task10(Collection<Animal> animals) {
        return animals.stream()
            .filter(e -> e.paws() != e.age())
            .toList();
    }

    public static List<Animal> Task11(Collection<Animal> animals) {
        return animals.stream()
            .filter(e -> e.bites() && e.height() > 100)
            .toList();
    }

    public static Integer Task12(Collection<Animal> animals) {
        return Math.toIntExact(animals.stream()
            .filter(e -> e.weight() > e.height())
            .count());
    }

    public static List<Animal> Task13(Collection<Animal> animals) {
        return animals.stream()
            .filter(e -> e.name().split(" ").length > 2)
            .toList();
    }

    public static Boolean Task14(Collection<Animal> animals, int k) {
        return animals.stream()
            .anyMatch(e -> e.type() == Animal.Type.DOG && e.height() > k);
    }

    public static Map<Animal.Type, Integer> Task15(Collection<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(Animal::weight)));
    }

    public static List<Animal> Task16(Collection<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name)).toList();
    }

    /*public static Boolean Task17(Collection<Animal> animals) {
        return animals.stream()
            .
    }*/

    public static Animal Task18(Collection<Animal> ... animals) {
        return Stream.of(animals).flatMap(Collection::stream)
            .filter(e -> e.type() == Animal.Type.FISH)
            .max(Comparator.comparing(Animal::weight))
            .get();
    }

    //public static Map<>
}
