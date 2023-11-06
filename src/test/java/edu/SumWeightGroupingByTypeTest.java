package edu;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class SumWeightGroupingByTypeTest {

    @Test
    void sumWeightGroupingByType() {

        List<Animal> list = getListOfAnimals();
        Map<Animal.Type, Integer> expected = expected();

        Map<Animal.Type, Integer> result = Methods.sumWeightGroupingByType(list, 3, 50);

        assertThat(result)
            .isEqualTo(expected);
    }

    List<Animal> getListOfAnimals() {
        return List.of(
            new Animal("macaw", Animal.Type.BIRD, Animal.Sex.M, 35, 48, 2000, false),
            new Animal("big golden retriever", Animal.Type.DOG, Animal.Sex.F, 9, 57, 31000, true),
            new Animal("piranha", Animal.Type.FISH, Animal.Sex.M, 20, 17, 2400, true),
            new Animal("cockatoo", Animal.Type.BIRD, Animal.Sex.M, 2, 28, 400, false),
            new Animal("bloodhound", Animal.Type.DOG, Animal.Sex.F, 4, 60, 42000, true)
        );
    }

    Map<Animal.Type, Integer> expected() {
        return Map.of(
            Animal.Type.BIRD, 2000,
            Animal.Type.DOG, 73000,
            Animal.Type.FISH, 2400
        );
    }
}
