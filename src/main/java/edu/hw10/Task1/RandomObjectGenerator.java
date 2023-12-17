package edu.hw10.Task1;

import edu.hw10.Task1.Annotation.Max;
import edu.hw10.Task1.Annotation.Min;
import edu.hw10.Task1.Annotation.NotNull;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

public class RandomObjectGenerator {

    public static final Random RANDOM = new Random();

    public <T> T nextObject(Class<T> classType) {

        Object object = getInstanceByConstructor(classType);

        try {
            setFields(classType, object);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        return (T) object;
    }

    public <T> T nextObject(Class<T> classType, String creationMethodName) {

        Object object = getInstanceByMethod(classType, creationMethodName);

        try {
            setFields(classType, object);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        return (T) object;
    }

    private void setFields(Class<?> clazz, Object object) throws IllegalAccessException, NoSuchFieldException {
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            int min = Integer.MIN_VALUE;
            int max = Integer.MAX_VALUE;

            if (field.isAnnotationPresent(Min.class)) {
                min = field.getAnnotation(Min.class).value();
            }
            if (field.isAnnotationPresent(Max.class)) {
                max = field.getAnnotation(Max.class).value();
            }
            if (field.isAnnotationPresent(NotNull.class)) {
                field.set(object, getRandomValue(field, min, max));
            }

        }
    }

    @SuppressWarnings("MagicNumber")
    private Object getRandomValue(Field field, int min, int max) {

        Object result = null;

        if (field.getType().equals(Integer.class) || field.getType().equals(int.class)) {
            result = RANDOM.nextInt(min, max);
        } else if (field.getType().equals(String.class)) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < RANDOM.nextInt(3, 10); i++) {
                sb.append((char) RANDOM.nextInt(97, 122));
            }
            result = sb.toString();
        }

        return result;
    }

    private Object getInstanceByConstructor(Class<?> clazz) {
        Constructor<?> constructor = clazz.getConstructors()[0];
        constructor.setAccessible(true);
        Object[] args = new Object[constructor.getParameterCount()];
        Object object = null;

        try {
            if (constructor.getParameterCount() == 0) {
                object = constructor.newInstance();
            } else {
                object = constructor.newInstance(args);
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return object;
    }

    private Object getInstanceByMethod(Class<?> clazz, String creationMethodName) {
        try {
            Method creationMethod = clazz.getMethod(creationMethodName);
            creationMethod.setAccessible(true);
            return creationMethod.invoke(null);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
