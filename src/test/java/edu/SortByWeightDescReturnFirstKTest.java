package edu;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SortByWeightDescReturnFirstKTest {

    @Test
    void sortByWeightDescReturnFirstK() {

        List<Animal> list = getListOfAnimals();
        List<Animal> expected = getSortedListOfAnimals();

        List<Animal> result = Methods.sortByWeightDescReturnFirstK(list, 2);

        assertThat(result)
            .isEqualTo(expected);
    }

    List<Animal> getListOfAnimals() {
        return List.of(
            new Animal("macaw", Animal.Type.BIRD, Animal.Sex.M, 35, 48, 2000, false),
            new Animal("golden retriever", Animal.Type.DOG, Animal.Sex.M, 9, 57, 31000, true),
            new Animal("piranha", Animal.Type.FISH, Animal.Sex.M, 20, 17, 2400, true)
        );
    }

    List<Animal> getSortedListOfAnimals() {
        return List.of(
            new Animal("golden retriever", Animal.Type.DOG, Animal.Sex.M, 9, 57, 31000, true),
            new Animal("piranha", Animal.Type.FISH, Animal.Sex.M, 20, 17, 2400, true)
        );
    }
}
