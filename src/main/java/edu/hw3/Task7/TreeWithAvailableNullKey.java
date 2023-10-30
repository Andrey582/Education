package edu.hw3.Task7;

import java.util.TreeMap;

public class TreeWithAvailableNullKey {
    public final TreeMap<String, String> tree;

    public TreeWithAvailableNullKey() {
        tree = new TreeMap<>(TreeWithAvailableNullKey::compare);
    }

    public TreeMap<String, String> getTree() {
        return tree;
    }

    public static int compare(String firstString, String secondString) {
        if (firstString == null) {
            return secondString == null ? 0 : 1;
        }

        if (secondString == null) {
            return -1;
        }

        for (int i = 0; i < firstString.length() && i < secondString.length(); i++) {
            if (firstString.charAt(i) != secondString.charAt(i)) {
                return firstString.charAt(i) - secondString.charAt(i);
            }
        }

        return firstString.length() - secondString.length();
    }
}
