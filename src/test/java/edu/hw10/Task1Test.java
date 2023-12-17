package edu.hw10;

import edu.hw10.Task1.RandomObjectGenerator;
import edu.hw10.Task1.SimplePersonClass;
import edu.hw10.Task1.SimplePersonRecord;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {

    @RepeatedTest(10)
    void randomGeneratorTestConstructor() {
        RandomObjectGenerator generator = new RandomObjectGenerator();
        SimplePersonClass person1 = generator.nextObject(SimplePersonClass.class);
        SimplePersonClass person2 = generator.nextObject(SimplePersonClass.class);

        assertThat(person1)
            .isNotEqualTo(person2);
    }

    @RepeatedTest(10)
    void randomGeneratorTestCreationMethod() {
        RandomObjectGenerator generator = new RandomObjectGenerator();
        SimplePersonClass person1 = generator.nextObject(SimplePersonClass.class, "create");
        SimplePersonClass person2 = generator.nextObject(SimplePersonClass.class, "create");

        assertThat(person1)
            .isNotEqualTo(person2);
    }
}
