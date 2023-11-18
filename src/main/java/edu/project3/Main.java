package edu.project3;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static String path;
    private static String dateFrom;
    private static String dateTo;
    private static String format;
    private static final Logger LOGGER = LogManager.getLogger();

    @SuppressWarnings("InnerAssignment")
    public static void main(String[] args) throws RuntimeException {

        if (args.length < 2 || args.length % 2 != 0) {
           throw new IllegalArgumentException("Invalid arguments");
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
            throw new IllegalArgumentException("Need path");
        }

        LogStats logStats = new LogStats(path, dateFrom, dateTo, format);

        try {

            logStats.parse();
            logStats.printToFile(Path.of("file.adoc"));
            logStats.print();

        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("Cannot parse by path");
        }
    }

    private Main() {
    }
}
