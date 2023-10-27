package edu.hw3.Task7;

import java.util.Comparator;
import java.util.TreeMap;

public class TreeWithAvailableNullKey {
    public final TreeMap<String, String> tree;

    public TreeWithAvailableNullKey() {
        tree = new TreeMap<>(Comparator.nullsLast(String::compareTo));
    }

    public TreeMap<String, String> getTree() {
        return tree;
    }
}
