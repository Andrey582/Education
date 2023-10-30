package edu.hw3;

import java.util.HashMap;
import java.util.Map;

public class Task1 {
    private static final int ALPHABET_LENGTH = 26;

    public static String atbash(String input) {
        Map<Character, Character> alphabet = new HashMap<>();
        StringBuilder result = new StringBuilder();
        fillHashMap(alphabet);
        char temp;

        for (char elem : input.toCharArray()) {
            temp = elem;

            if (alphabet.containsKey(elem)) {
                temp = alphabet.get(elem);
            }
            result.append(temp);
        }

        return result.toString();
    }

    private static void fillHashMap(Map<Character, Character> map) {
        for (int i = 0; i < ALPHABET_LENGTH; i++) {
            map.put(Character.toChars('A' + i)[0], Character.toChars('Z' - i)[0]);
            map.put(Character.toChars('a' + i)[0], Character.toChars('z' - i)[0]);
        }
    }

    private Task1() {
    }
}
