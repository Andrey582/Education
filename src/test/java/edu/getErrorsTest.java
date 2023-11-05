package edu;

import edu.Validation.HeightError;
import edu.Validation.ValidationError;
import edu.Validation.WeightError;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class getErrorsTest {

    @Test
    void getErrors() {
        List<Animal> list = getListOfAnimals();
        Map<String, Set<ValidationError>> expected = expected();
        Boolean isEquals = true;

        Map<String, Set<ValidationError>> result = Methods.getErrorsSet(list);

        assertThat(result)
            .isEqualTo(expected);
    }

    List<Animal> getListOfAnimals() {
        return List.of(
            new Animal("macaw", Animal.Type.BIRD, Animal.Sex.M, 35, 48, 2020, false),
            new Animal("big golden retriever", Animal.Type.DOG, Animal.Sex.F, 9, 62, 31000, true),
            new Animal("piranha", Animal.Type.FISH, Animal.Sex.M, 20, 17, 2400, true),
            new Animal("cockatoo", Animal.Type.BIRD, Animal.Sex.M, 2, 28, 400, false),
            new Animal("bloodhound", Animal.Type.DOG, Animal.Sex.M, 4, 60, 42000, true)
        );
    }

    Map<String, Set<ValidationError>> expected() {
        return Map.of(
            "macaw", Set.of(
                new WeightError(new Animal("macaw", Animal.Type.BIRD, Animal.Sex.M, 35, 48, 2020, false))
            ),
            "big golden retriever", Set.of(
                new WeightError(new Animal("big golden retriever", Animal.Type.DOG, Animal.Sex.F, 9, 62, 31000, true)),
                new HeightError(new Animal("big golden retriever", Animal.Type.DOG, Animal.Sex.F, 9, 62, 31000, true))
            ),
            "bloodhound", Set.of(
                new WeightError(new Animal("bloodhound", Animal.Type.DOG, Animal.Sex.M, 4, 60, 42000, true))
            ),
            "cockatoo", Set.of(),
            "piranha", Set.of()
        );
    }
}
