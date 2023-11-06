package edu;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class MostHeavyAnimalEachTypeTest {

    @Test
    void mostHeavyAnimalEachType() {

        List<Animal> list = getListOfAnimals();
        Map<Animal.Type, Animal> expected = expected();

        Map<Animal.Type, Animal> result = Methods.mostHeavyAnimalEachType(list);

        assertThat(result)
            .isEqualTo(expected);
    }

    List<Animal> getListOfAnimals() {
        return List.of(
            new Animal("macaw", Animal.Type.BIRD, Animal.Sex.M, 35, 48, 2000, false),
            new Animal("golden retriever", Animal.Type.DOG, Animal.Sex.F, 9, 57, 31000, true),
            new Animal("piranha", Animal.Type.FISH, Animal.Sex.M, 20, 17, 2400, true),
            new Animal("cockatoo", Animal.Type.BIRD, Animal.Sex.M, 3, 28, 400, false),
            new Animal("bloodhound", Animal.Type.DOG, Animal.Sex.F, 5, 60, 42000, true)
        );
    }

    Map<Animal.Type, Animal> expected() {
        return Map.of(
            Animal.Type.BIRD, new Animal("macaw", Animal.Type.BIRD, Animal.Sex.M, 35, 48, 2000, false),
            Animal.Type.DOG, new Animal("bloodhound", Animal.Type.DOG, Animal.Sex.F, 5, 60, 42000, true),
            Animal.Type.FISH, new Animal("piranha", Animal.Type.FISH, Animal.Sex.M, 20, 17, 2400, true)
        );
    }
}
