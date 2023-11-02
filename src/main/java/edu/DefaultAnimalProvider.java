package edu;

import java.util.List;

public class DefaultAnimalProvider {
    public static List<Animal> getList = List.of(
        new Animal("golden retriever", Animal.Type.DOG, Animal.Sex.M, 9, 57, 31000, true),
        new Animal("shiba inu", Animal.Type.DOG, Animal.Sex.M, 5, 40, 12000, true),
        new Animal("bloodhound", Animal.Type.DOG, Animal.Sex.F, 5, 60, 42000, true),
        new Animal("sphynx", Animal.Type.CAT, Animal.Sex.M, 7, 32, 4000, true),
        new Animal("persian", Animal.Type.CAT, Animal.Sex.F, 12, 26, 4000, true),
        new Animal("siamese", Animal.Type.CAT, Animal.Sex.F, 9, 26, 5000, true),
        new Animal("macaw", Animal.Type.BIRD, Animal.Sex.M, 35, 48, 2000, false),
        new Animal("cockatoo", Animal.Type.BIRD, Animal.Sex.M, 3, 28, 400, false),
        new Animal("budgerigar", Animal.Type.BIRD, Animal.Sex.F, 4, 17, 40, false),
        new Animal("bream", Animal.Type.FISH, Animal.Sex.M, 7, 30, 1500, false),
        new Animal("roach", Animal.Type.FISH, Animal.Sex.F, 17, 25, 450, false),
        new Animal("piranha", Animal.Type.FISH, Animal.Sex.M, 20, 17, 2400, true),
        new Animal("black widow", Animal.Type.SPIDER, Animal.Sex.F, 3, 1, 1, true),
        new Animal("karakurt", Animal.Type.SPIDER, Animal.Sex.F, 2, 2, 1, true),
        new Animal("tarantula", Animal.Type.SPIDER, Animal.Sex.M, 12, 7, 130, true)
    );
}
