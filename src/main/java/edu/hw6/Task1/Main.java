package edu.hw6.Task1;

import java.util.Map;

public class Main {

    public static void main(String[] args) {
        DiskMap map = new DiskMap("test.txt");
        map.loadValueFromFile();
        Map<String, String> storage = map.getValues();
        for (var temp : storage.entrySet()) {
            System.out.println(temp.getKey() + " - " + temp.getValue());
        }
    }
}
