package edu;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class countGroupingByTypeTest {

    @Test
    void countGroupingByType() {

        List<Animal> list = getListOfAnimals();
        Map<Animal.Type, Integer> expected = getExpected();

        Map<Animal.Type, Integer> result = Methods.countGroupingByType(list);

        assertThat(result)
            .isEqualTo(expected);
    }

    List<Animal> getListOfAnimals() {
        return List.of(
            new Animal("macaw", Animal.Type.BIRD, Animal.Sex.M, 35, 48, 2000, false),
            new Animal("golden retriever", Animal.Type.DOG, Animal.Sex.M, 9, 57, 31000, true),
            new Animal("piranha", Animal.Type.FISH, Animal.Sex.M, 20, 17, 2400, true),
            new Animal("cockatoo", Animal.Type.BIRD, Animal.Sex.M, 3, 28, 400, false)
        );
    }

    Map<Animal.Type, Integer> getExpected() {
        return Map.of(
            Animal.Type.BIRD, 2,
            Animal.Type.DOG, 1,
            Animal.Type.FISH, 1
        );
    }
}
