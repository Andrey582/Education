package edu.hw2;

import edu.hw2.Task4.Info;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {

    @Test
    void someClassMethodNameTest() {

        // given
        String expected = "someMethod";

        // when
        Info.CallingInfo callingInfo = SomeClass.someMethod();

        // then
        assertThat(callingInfo.methodName())
            .isEqualTo(expected);
    }

    @Test
    void someClassClassNameTest() {

        // given
        String expected = "edu.hw2.Task4Test$SomeClass";

        // when
        Info.CallingInfo callingInfo = SomeClass.someMethod();

        // then
        assertThat(callingInfo.className())
            .isEqualTo(expected);
    }

    @Test
    void anotherClassClassNameTest() {

        // given
        String expected = "edu.hw2.Task4Test$AnotherClass";

        // when
        Info.CallingInfo callingInfo = AnotherClass.anotherMethod();

        // then
        assertThat(callingInfo.className())
            .isEqualTo(expected);
    }

    @Test
    void anotherClassMethodNameTest() {

        // given
        String expected = "anotherMethod";

        // when
        Info.CallingInfo callingInfo = AnotherClass.anotherMethod();

        // then
        assertThat(callingInfo.methodName())
            .isEqualTo(expected);
    }








    class SomeClass {
        public static Info.CallingInfo someMethod() {
            return Info.callingInfo();
        }
    }

    class AnotherClass {
        public static Info.CallingInfo anotherMethod() {
            return Info.callingInfo();
        }
    }
}
