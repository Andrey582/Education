package edu;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class longestNameAnimalTest {

    @Test
    void longestNameAnimal() {

        List<Animal> list = getListOfAnimals();
        Animal expected = expected();

        Animal result = Methods.longestNameAnimal(list);

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

    Animal expected() {
        return new Animal("golden retriever", Animal.Type.DOG, Animal.Sex.M, 9, 57, 31000, true);
    }
}
