package edu.hw8.Task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SingleBrute {

    private final Map<String, String> dataBase;
    private final Map<String, String> outputDataBase;

    private final List<Character> alphabet;
    private StringBuilder lastPassword;

    public SingleBrute(List<String> inputDataBase) {
        this.dataBase = new HashMap<>();
        for (String string : inputDataBase) {
            String[] values = string.split(" ");
            this.dataBase.put(values[1], values[0]);
        }
        alphabet = new ArrayList<>();
        fillAlphabet();
        lastPassword = new StringBuilder();
        outputDataBase = new HashMap<>();
    }

    public Map<String, String> bruteDatabase() {
        for (int i = 0; i < dataBase.size(); i++) {
            String password = nextPassword();
            String login = dataBase.get(MD5.MD5Hash(password));
            outputDataBase.put(login, password);
        }
        return outputDataBase;
    }

    public String nextPassword() {
        StringBuilder password = lastPassword;
        do {
            getNextWord(password);
        } while (!dataBase.containsKey(MD5.MD5Hash(password.toString())));
        lastPassword = password;
        return password.toString();
    }

    private void getNextWord(StringBuilder string) {
        int index = string.length() - 1;

        if (index == -1) {
            string.append(alphabet.getFirst());
            return;
        }

        if (string.charAt(index) == alphabet.getLast()) {
            while (index >= 0 && string.charAt(index) == alphabet.getLast()) {
                string.setCharAt(index, alphabet.getFirst());
                if (index == 0) {
                    string.append(alphabet.getFirst());
                } else {
                    index--;
                    int alphabetIndex = alphabet.indexOf(string.charAt(index));
                    string.setCharAt(index, alphabet.get(alphabetIndex + 1));
                }
            }
        } else {
            int alphabetIndex = alphabet.indexOf(string.charAt(index));
            string.setCharAt(index, alphabet.get(alphabetIndex + 1));
        }
    }

    private void fillAlphabet() {
        /*for (char i = 'A'; i <= 'Z'; i++) {
            ALPHABET.add(i);
        }*/
        for (char i = 'a'; i <= 'z'; i++) {
            alphabet.add(i);
        }
        for (char i = '0'; i <= '9'; i++) {
            alphabet.add(i);
        }
    }
}
