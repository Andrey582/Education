package edu;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import javax.swing.text.StyledEditorKit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class spiderBytesOftenThanDogTest {

    @Test
    void spiderBytesOftenThanDog() {

        List<Animal> list = getListOfAnimals();
        Boolean expected = true;

        Boolean result = Methods.spiderBytesOftenThanDog(list);

        assertThat(result)
            .isEqualTo(expected);
    }

    List<Animal> getListOfAnimals() {
        return List.of(
            new Animal("macaw", Animal.Type.BIRD, Animal.Sex.M, 35, 48, 2000, false),
            new Animal("big golden retriever", Animal.Type.DOG, Animal.Sex.F, 9, 57, 31000, true),
            new Animal("piranha", Animal.Type.FISH, Animal.Sex.M, 20, 17, 2400, true),
            new Animal("cockatoo", Animal.Type.BIRD, Animal.Sex.M, 2, 28, 400, false),
            new Animal("bloodhound", Animal.Type.DOG, Animal.Sex.M, 4, 60, 42000, true),
            new Animal("black widow", Animal.Type.SPIDER, Animal.Sex.F, 3, 1, 1, true),
            new Animal("karakurt", Animal.Type.SPIDER, Animal.Sex.F, 2, 2, 1, true),
            new Animal("tarantula", Animal.Type.SPIDER, Animal.Sex.M, 12, 7, 130, true)
        );
    }
}
