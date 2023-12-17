package edu.hw11.Task1;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import static net.bytebuddy.matcher.ElementMatchers.named;

public class Task1 {
    public static Class<?> byteBuddy = new ByteBuddy()
        .subclass(Object.class)
        .method(named("toString"))
        .intercept(FixedValue.value("Hello, ByteBuddy!"))
        .make()
        .load(Task1.class.getClassLoader())
        .getLoaded();

    private Task1() {
    }
}
