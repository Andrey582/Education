package edu.project1;

import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        run(new Package());
    }

    public static void run(Dictionary dictionary) {
        try {
            Session session = new Session(dictionary);
            Status status;

            do {
                status = session.guess();

            } while (!Arrays.asList(session.getENDGAME()).contains(status));
        } catch (RuntimeException e) {
            LOGGER.info(e.getMessage());
        }

        LOGGER.info("> Game over!");
    }

    private Main() {
    }
}
