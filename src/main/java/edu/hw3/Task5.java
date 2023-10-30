package edu.hw3;

import java.util.Arrays;

public class Task5 {

    public static Contact[] parseContacts(String[] contacts, String orderBy) {

        if (contacts == null) {
            return new Contact[] {};
        }

        Contact[] sortedArray = new Contact[contacts.length];
        Contact newContact;
        String[] parsedFullName;

        for (int i = 0; i < contacts.length; i++) {

            parsedFullName = contacts[i].split(" ");

            if (parsedFullName.length == 2) {
                newContact = new Contact(parsedFullName[0], parsedFullName[1]);
            } else {
                newContact = new Contact(parsedFullName[0], null);
            }

            sortedArray[i] = newContact;
        }

        switch (orderBy.toUpperCase()) {
            case "ASC" -> Arrays.sort(sortedArray, Contact::compareAsc);
            case "DESC" -> Arrays.sort(sortedArray, Contact::compareDesc);
            default -> throw new IllegalArgumentException("Wrong order");
        }

        return sortedArray;
    }

    private Task5() {
    }

    public record Contact(String name, String surname) {
        public int compare(Contact another) {
            String firstStringToCompare = this.surname == null ? this.name : this.surname;
            String secondStringToCompare = another.surname == null ? another.name : another.surname;
            return firstStringToCompare.compareTo(secondStringToCompare);
        }

        public static int compareAsc(Contact firstContact, Contact secondContact) {
            String first = firstContact.surname == null ? firstContact.name : firstContact.surname;
            String second = secondContact.surname == null ? secondContact.name : secondContact.surname;

            for (int i = 0; i < first.length() && i < second.length(); i++) {
                if (first.charAt(i) != second.charAt(i)) {
                    return first.charAt(i) - second.charAt(i);
                }
            }

            return first.length() - second.length();
        }

        public static int compareDesc(Contact firstContact, Contact secondContact) {
            String first = firstContact.surname == null ? firstContact.name : firstContact.surname;
            String second = secondContact.surname == null ? secondContact.name : secondContact.surname;

            for (int i = 0; i < first.length() && i < second.length(); i++) {
                if (first.charAt(i) != second.charAt(i)) {
                    return second.charAt(i) - first.charAt(i);
                }
            }

            return second.length() - first.length();
        }
    }
}
