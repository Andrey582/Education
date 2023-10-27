package edu.hw3;

import java.util.HashMap;
import java.util.List;

public class Task3 {

    public static <T> HashMap<T, Integer> freqDict(List<T> input) {
        HashMap<T, Integer> hashMap = new HashMap<>();
        for (var val : input) {

            if (hashMap.containsKey(val)) {
                hashMap.put(val, hashMap.get(val) + 1);
            } else {
                hashMap.put(val, 1);
            }
        }
        return hashMap;
    }

    private Task3() {
    }
}
