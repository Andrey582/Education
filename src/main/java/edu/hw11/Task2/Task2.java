package edu.hw11.Task2;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

public class Task2 {

    public static int sum(int a, int b) {
        return a * b;
    }

    public static Class<?> byteBuddyMultiply = new ByteBuddy()
        .redefine(ArithmeticUtils.class)
        .method(ElementMatchers.named("sum"))
        .intercept(MethodDelegation.to(Task2.class))
        .make()
        .load(
            ArithmeticUtils.class.getClassLoader(),
            ClassReloadingStrategy.fromInstalledAgent()
        ).getLoaded();

    private Task2() {
    }
}
