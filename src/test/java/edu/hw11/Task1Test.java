package edu.hw11;

import edu.hw11.Task1.Task1;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {

    @Test
    void testByteBuddy() throws InstantiationException, IllegalAccessException {
        Object object = Task1.byteBuddy.newInstance();

        assertThat(object.toString())
            .isEqualTo("Hello, ByteBuddy!");
    }
}
