package edu;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MostWeightFishInListsTest {

    @Test
    void mostWeightFishInLists() {

        List<Animal> firstList = getFirstList();
        List<Animal> secondList = getSecondList();
        List<Animal> thirdList = getThirdList();
        Animal expected = expected();

        Animal result = Methods.mostWeightFishInLists(firstList, secondList, thirdList);

        assertThat(result)
            .isEqualTo(expected);
    }

    List<Animal> getFirstList() {
        return List.of(
            new Animal("bream", Animal.Type.FISH, Animal.Sex.M, 7, 30, 1500, false),
            new Animal("roach", Animal.Type.FISH, Animal.Sex.F, 17, 25, 4500, false),
            new Animal("piranha", Animal.Type.FISH, Animal.Sex.M, 20, 17, 2400, true)
        );
    }

    List<Animal> getSecondList() {
        return List.of(
            new Animal("bream", Animal.Type.FISH, Animal.Sex.M, 7, 30, 1700, false),
            new Animal("roach", Animal.Type.FISH, Animal.Sex.F, 17, 25, 450, false),
            new Animal("piranha", Animal.Type.FISH, Animal.Sex.M, 20, 17, 2300, true)
        );
    }

    List<Animal> getThirdList() {
        return List.of(
            new Animal("bream", Animal.Type.FISH, Animal.Sex.M, 7, 30, 500, false),
            new Animal("roach", Animal.Type.FISH, Animal.Sex.F, 17, 25, 2500, false),
            new Animal("piranha", Animal.Type.FISH, Animal.Sex.M, 20, 17, 5000, true)
        );
    }

    Animal expected() {
        return new Animal("piranha", Animal.Type.FISH, Animal.Sex.M, 20, 17, 5000, true);
    }
}
