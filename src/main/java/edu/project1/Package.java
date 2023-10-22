package edu.project1;

import java.util.Random;
import org.jetbrains.annotations.NotNull;

public class Package implements Dictionary {
    private static final String[] DICTIONARY = {
        "hello",
        "world",
        "tinkoff",
        "intellij"
    };

    public @NotNull String getRandomWord() {
        return DICTIONARY[new Random().nextInt(DICTIONARY.length)];
    }
}
