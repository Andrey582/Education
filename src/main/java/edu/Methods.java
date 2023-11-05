package edu;

import edu.Validation.AgeError;
import edu.Validation.HeightError;
import edu.Validation.ValidationError;
import edu.Validation.WeightError;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Methods {

    public static List<Animal> sortByHeight(Collection<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .toList();
    }

    public static List<Animal> sortByWeightDescReturnFirstK(Collection<Animal> animals, int k) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::weight).reversed())
            .limit(Math.max(k, 0))
            .toList();
    }

    public static Map<Animal.Type, Integer> countGroupingByType(Collection<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::type,
                Collectors.collectingAndThen(Collectors.counting(), Long::intValue)));
    }

    public static Animal longestNameAnimal(Collection<Animal> animals) {
        return animals.stream()
            .max(Comparator.comparing(o -> o.name().length()))
            .get();
    }

    public static Animal.Sex whichAnimalSexMore(Collection<Animal> animals) {
        return animals.stream()
            .collect(
                Collectors.groupingBy(Animal::sex,
                Collectors.counting())
            ).entrySet().stream()
            .max(Comparator.comparing(Map.Entry::getValue))
            .get()
            .getKey();
    }

    public static Map<Animal.Type, Animal> mostHeavyAnimalEachType(Collection<Animal> animals) {
        return animals.stream()
            .collect(Collectors
                .groupingBy(Animal::type,
                    Collectors.maxBy(Comparator.comparing(Animal::weight))))
            .entrySet()
            .stream().collect(Collectors.toMap(Map.Entry::getKey,
                e -> e.getValue().get()));
    }

    public static Animal oldestAnimal(Collection<Animal> animals) {
        return animals.stream()
            .max(Comparator.comparing(Animal::age))
            .get();
    }

    public static Optional<Animal> mostHeavyAnimalBelowK(Collection<Animal> animals, int k) {
        return animals.stream()
            .filter(e -> e.height() < k)
            .max(Comparator.comparing(Animal::weight));
    }

    public static Integer sumPaws(Collection<Animal> animals) {
        return animals.stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    public static List<Animal> ageNotEqualPawsCount(Collection<Animal> animals) {
        return animals.stream()
            .filter(e -> e.paws() != e.age())
            .toList();
    }

    @SuppressWarnings("MagicNumber")
    public static List<Animal> listOfAnimalThatCanBytesAndHigherThanHundred(Collection<Animal> animals) {
        return animals.stream()
            .filter(e -> e.bites() && e.height() > 100)
            .toList();
    }

    public static Integer weightMoreThanHeight(Collection<Animal> animals) {
        return Math.toIntExact(animals.stream()
            .filter(e -> e.weight() > e.height())
            .count());
    }

    public static List<Animal> nameWordCountMoreThanTwo(Collection<Animal> animals) {
        return animals.stream()
            .filter(e -> e.name().split(" ").length > 2)
            .toList();
    }

    public static Boolean containDogHigherThanK(Collection<Animal> animals, int k) {
        return animals.stream()
            .anyMatch(e -> e.type() == Animal.Type.DOG && e.height() > k);
    }

    public static Map<Animal.Type, Integer> sumWeightGroupingByType(Collection<Animal> animals, int k, int l) {
        return animals.stream()
            .filter(e -> k <= e.age() && e.age() <= l)
            .collect(Collectors.groupingBy(Animal::type,
                Collectors.summingInt(Animal::weight)));
    }

    public static List<Animal> sortByTypeThenBySexThenByName(Collection<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name)).toList();
    }

    public static Boolean spiderBytesOftenThanDog(Collection<Animal> animals) {
        return animals.stream()
            .filter(e -> e.type() == Animal.Type.SPIDER && e.bites()).count() > animals.stream()
            .filter(e -> e.type() == Animal.Type.DOG && e.bites()).count();
    }

    public static Animal mostWeightFishInLists(Collection<Animal>... animals) {
        return Stream.of(animals).flatMap(Collection::stream)
            .filter(e -> e.type() == Animal.Type.FISH)
            .max(Comparator.comparing(Animal::weight))
            .get();
    }

     public static Map<String, Set<ValidationError>> getErrorsSet(Collection<Animal> animals) {
        return animals.stream()
            .collect(Collectors.toMap(
                Animal::name,
                e -> {
                    Set<ValidationError> set = new HashSet<>();
                    WeightError weightError = new WeightError(e);
                    HeightError heightError = new HeightError(e);
                    AgeError ageError = new AgeError(e);
                    if (weightError.getErrorInfo() != null) {
                        set.add(weightError);
                    }
                    if (heightError.getErrorInfo() != null) {
                        set.add(heightError);
                    }
                    if (ageError.getErrorInfo() != null) {
                        set.add(ageError);
                    }
                    return set;
                }
            ));
    }

    public static Map<String, String> getErrorsString(Collection<Animal> animals) {
        return animals.stream()
            .collect(Collectors.toMap(
                Animal::name,
                e -> {
                    StringBuilder errors = new StringBuilder();
                    WeightError weightError = new WeightError(e);
                    HeightError heightError = new HeightError(e);
                    AgeError ageError = new AgeError(e);
                    if (weightError.getErrorInfo() != null) {
                        errors.append("weight ").append(weightError.getErrorInfo()).append("\n");
                    }
                    if (heightError.getErrorInfo() != null) {
                        errors.append("height ").append(heightError.getErrorInfo()).append("\n");
                    }
                    if (ageError.getErrorInfo() != null) {
                        errors.append("age ").append(ageError.getErrorInfo()).append("\n");
                    }
                    return errors.toString();
                }
            ));
    }

    private Methods() {
    }
}
