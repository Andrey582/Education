package edu.project1;

import java.util.Arrays;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Session {

    private final static Logger LOGGER = LogManager.getLogger();
    private final String answer;
    private final char[] userAnswer;
    private final StringBuilder availableLetters = new StringBuilder(
        "a b c d e f g h i j k l m n o p q r s t u v w x y z "
    );
    private final int maxAttempts = 5;
    private int attempts = 0;
    private static final String EXIT = "exit";
    private static final Status[] ENDGAME = {
        Status.WIN,
        Status.DEFEAT,
        Status.GIVEUP
    };


    public Session(Dictionary dictionary) throws RuntimeException {
        answer = dictionary.getRandomWord().toLowerCase();
        if (answer.length() < 2) {
            throw new RuntimeException("Answer length need to be greater, than 1");
        }
        userAnswer = new char[answer.length()];
        Arrays.fill(userAnswer, '_');
        printRule();
    }

    public Status guess() {
        printGuess();
        String input = input().toLowerCase();
        Status status;

        if (input.equals(EXIT)) {
            return Status.GIVEUP;
        }

        if (availableLetters.indexOf(input) != -1) {
            int indexUsed = availableLetters.indexOf(input);
            availableLetters.delete(indexUsed, indexUsed + 2);
        } else {
            printResult(Status.ALREADYUSED);
            return Status.ALREADYUSED;
        }

        if (answer.contains(input)) {
            openLetters(input.charAt(0));
            status = Arrays.toString(userAnswer).contains("_") ? Status.SUCCESS : Status.WIN;
        } else {
            attempts++;
            status = maxAttempts == attempts ? Status.DEFEAT : Status.FAILED;
        }

        if (Arrays.asList(ENDGAME).contains(status)) {
            printEndGame(status);
        } else {
            printResult(status);
        }

        return status;
    }

    private void openLetters(char ch) {
        for (int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) == ch) {
                userAnswer[i] = ch;
            }
        }
    }

    private String input() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (input.length() != 1 && !input.equals(EXIT)) {
            printError();
            input = scanner.nextLine();
        }

        return input;
    }

    private void printEndGame(Status status) {
        printResult(status == Status.WIN ? Status.SUCCESS : Status.FAILED);
        switch (status) {
            case WIN -> LOGGER.info("> You won!");
            case DEFEAT -> LOGGER.info("> You lost!");
            default -> {
                // do nothing
            }
        }
    }

    private void printResult(Status status) {
        switch (status) {
            case SUCCESS -> LOGGER.info("> Hit!");
            case FAILED -> LOGGER.info("> Missed, mistake " + attempts + " out of " + maxAttempts + ".");
            case ALREADYUSED -> LOGGER.info("> The letter already used!");
            default -> {
                // do nothing
            }
        }
        LOGGER.info("> ");
        LOGGER.info("> The word: " + new String(userAnswer));
        LOGGER.info("> Available letters: " + availableLetters.toString());
        LOGGER.info("> ");

    }

    private void printRule() {
        LOGGER.info("> Welcome to hangman game!");
        LOGGER.info("> You need to guess all the letters in the word.");
        LOGGER.info("> You will have 5 attempts.");
        LOGGER.info("> If you want to exit, write `exit`.");
    }

    private void printError() {
        LOGGER.info("> You have a misprint. Please repeat your input.");
        LOGGER.info("> ");
    }

    private void printGuess() {
        LOGGER.info("> Guess a letter: ");
        LOGGER.info("> ");
    }

    public Status[] getENDGAME() {
        return ENDGAME;
    }
}
