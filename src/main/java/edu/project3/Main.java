package edu.project3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.net.*;

public class Main {

    private static String path;
    private static String dateFrom;
    private static String dateTo;
    private static String format;
    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) throws RuntimeException {

        if (args.length < 2 || args.length % 2 != 0) {
           throw new IllegalArgumentException();
        }

        for (int i = 0; i < args.length; i += 2) {
            switch (args[i]) {
                case "--path" -> path = args[i + 1];
                case "--from" -> dateFrom = args[i + 1];
                case "--to" -> dateTo = args[i + 1];
                case "--format" -> format = args[i + 1];
                default -> LOGGER.info(args[i] + " unknown flag");
            }
        }

        if (path == null) {
            throw new IllegalArgumentException();
        }

        LogStats logStats = new LogStats(path, dateFrom, dateTo, format);

        try {

            logStats.parse();
            logStats.print();

        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
