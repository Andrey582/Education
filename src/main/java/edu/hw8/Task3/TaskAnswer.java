package edu.hw8.Task3;

import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TaskAnswer {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final int MAX_THREAD_COUNT = 8;

    @SuppressWarnings({"UncommentedMain", "MultipleStringLiterals"})
    public static void main(String[] args) {
        long startSingle = System.nanoTime();
        SingleBrute singleBrute = new SingleBrute(DataBase.dataBase);
        singleBrute.bruteDatabase();
        long endSingle = System.nanoTime();

        LOGGER.info("single thread brute - " + TimeUnit.NANOSECONDS.toMillis(endSingle - startSingle) + " millisec.");

        for (int i = 1; i <= MAX_THREAD_COUNT; i++) {
            long start = System.nanoTime();
            MultiBrute multiBrute = new MultiBrute(DataBase.dataBase);
            multiBrute.bruteDatabase(i);
            long end = System.nanoTime();

            LOGGER.info("multi " + i + " thread brute - " + TimeUnit.NANOSECONDS.toMillis(end - start) + " millisec.");
        }
    }

    private TaskAnswer() {
    }
}
