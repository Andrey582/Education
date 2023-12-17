package edu.hw10.Task2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CacheProxy implements InvocationHandler {

    private Object clazz;
    private final Map<String, Object> cache;
    private final Set<String> methodCalls;

    private CacheProxy(Object clazz) {
        cache = new HashMap<>();
        methodCalls = new HashSet<>();
        this.clazz = clazz;
    }

    public static  <T> T create(Object clazz, Class<?> classType) {
        return (T) Proxy.newProxyInstance(
            classType.getClassLoader(),
            new Class<?>[]{classType},
            new CacheProxy(clazz)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (method.isAnnotationPresent(Cache.class)) {
            String key = getKey(method, args);

            if (methodCalls.contains(key)) {
                Object result;
                if (method.getAnnotation(Cache.class).persist()) {
                    result = readDataFromDisk(key);
                } else {
                    result = cache.get(key);
                }
                return result;

            } else {
                Object result = method.invoke(clazz, args);

                if (method.getAnnotation(Cache.class).persist()) {
                    writeDataToDisk(key, result);
                } else {
                    cache.put(key, result);
                }
                methodCalls.add(key);
                return result;
            }
        } else {
            return method.invoke(clazz, args);
        }
    }

    private String getKey(Method method, Object[] args) {
        return method.getName() + Arrays.hashCode(args);
    }

    private void writeDataToDisk(String key, Object result) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(key))) {
            outputStream.writeObject(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Object readDataFromDisk(String key) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(key))) {
            return inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
