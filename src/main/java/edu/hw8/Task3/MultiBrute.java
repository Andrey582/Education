package edu.hw8.Task3;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MultiBrute {

    private final Map<String, String> dataBase;
    private final Map<String, String> outputDataBase;
    private final List<Character> ALPHABET;
    private CountDownLatch cdl;
    private final ReentrantReadWriteLock rwLock;
    private int counter = 0;

    public MultiBrute(List<String> inputDataBase) {
        this.dataBase = new HashMap<>();
        for (String string : inputDataBase) {
            String[] values = string.split(" ");
            this.dataBase.put(values[1], values[0]);
        }
        ALPHABET = new ArrayList<>();
        fillAlphabet();
        rwLock = new ReentrantReadWriteLock();
        outputDataBase = new HashMap<>();
    }

    public Map<String, String> bruteDatabase(int countThreads) {
        List<Thread> threads = new ArrayList<>();
        cdl = new CountDownLatch(countThreads);
        for (int i = 0; i < countThreads; i++) {
            threads.add(new Thread(this::nextPassword));
            threads.get(i).start();
        }
        try {
            for (int i = 0; i < countThreads; i++) {
                threads.get(i).join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return outputDataBase;
    }

    private void nextPassword() {

        int selfIndex = getSelfIndex();
        cdl.countDown();
        try {
            cdl.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        int size = ALPHABET.size() / counter;
        int start = selfIndex == 1 ? 0 : size * (selfIndex - 1) + 1;
        int end = selfIndex == counter ? ALPHABET.size() - 1 : size * selfIndex;
        StringBuilder password = new StringBuilder();

        while (dataBase.size() > outputDataBase.size()) {
            do {
                getNextWord(password, start, end);

            } while (!dataBase.containsKey(MD5.MD5Hash(password.toString())) &&
                dataBase.size() > outputDataBase.size());

            if (dataBase.containsKey(MD5.MD5Hash(password.toString()))) {
                rwLock.writeLock().lock();
                outputDataBase.put(dataBase.get(MD5.MD5Hash(password.toString())), password.toString());
                rwLock.writeLock().unlock();
            }
        }
    }

    private void getNextWord(StringBuilder string, int start, int end) {
        int index = string.length() - 1;

        if (index == -1) {
            string.append(ALPHABET.get(start));
            return;
        }
        if (index == 0) {

            if (string.charAt(index) == ALPHABET.get(end)) {
                string.setCharAt(index, ALPHABET.get(start));
                string.append(ALPHABET.getFirst());
            } else {
                string.setCharAt(index, getNextChar(string.charAt(index)));
            }

        } else if (string.charAt(index) == ALPHABET.getLast()) {

            while (index > 0 && string.charAt(index) == ALPHABET.getLast()) {
                string.setCharAt(index, ALPHABET.getFirst());
                index--;
            }
            if (index == 0) {
                if (string.charAt(index) == ALPHABET.get(end)) {
                    string.append(ALPHABET.getFirst());
                    string.setCharAt(index, ALPHABET.get(start));
                } else {
                    string.setCharAt(index, getNextChar(string.charAt(index)));
                }
            } else {
                string.setCharAt(index, getNextChar(string.charAt(index)));
            }
        } else {
            string.setCharAt(index, getNextChar(string.charAt(index)));
        }
    }

    private char getNextChar(char ch) {
        return ALPHABET.get(ALPHABET.indexOf(ch) + 1);
    }
    private void fillAlphabet() {
        /*for (char i = 'A'; i <= 'Z'; i++) {
            ALPHABET.add(i);
        }*/
        for (char i = 'a'; i <= 'z'; i++) {
            ALPHABET.add(i);
        }
        for (char i = '0'; i <= '9'; i++) {
            ALPHABET.add(i);
        }
    }

    private synchronized int getSelfIndex() {
        return ++counter;
    }
}
