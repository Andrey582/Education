package edu;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SortByTypeThenBySexThenByNameTest {

    @Test
    void sortByTypeThenBySexThenByName() {

        List<Animal> list = getListOfAnimals();
        List<Animal> expected = expected();

        List<Animal> result = Methods.sortByTypeThenBySexThenByName(list);

        assertThat(result)
            .isEqualTo(expected);
    }

    List<Animal> getListOfAnimals() {
        return List.of(
            new Animal("macaw", Animal.Type.BIRD, Animal.Sex.M, 35, 48, 2000, false),
            new Animal("big golden retriever", Animal.Type.DOG, Animal.Sex.F, 9, 57, 31000, true),
            new Animal("piranha", Animal.Type.FISH, Animal.Sex.M, 20, 17, 2400, true),
            new Animal("cockatoo", Animal.Type.BIRD, Animal.Sex.M, 2, 28, 400, false),
            new Animal("bloodhound", Animal.Type.DOG, Animal.Sex.M, 4, 60, 42000, true)
        );
    }

    List<Animal> expected() {
        return List.of(
            new Animal("bloodhound", Animal.Type.DOG, Animal.Sex.M, 4, 60, 42000, true),
            new Animal("big golden retriever", Animal.Type.DOG, Animal.Sex.F, 9, 57, 31000, true),
            new Animal("cockatoo", Animal.Type.BIRD, Animal.Sex.M, 2, 28, 400, false),
            new Animal("macaw", Animal.Type.BIRD, Animal.Sex.M, 35, 48, 2000, false),
            new Animal("piranha", Animal.Type.FISH, Animal.Sex.M, 20, 17, 2400, true)
        );
    }
}
