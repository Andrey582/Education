package edu.hw3;

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
            case "ASC" -> sortAsc(sortedArray);
            case "DESC" -> sortDesc(sortedArray);
            default -> throw new IllegalArgumentException("Wrong order");
        }

        return sortedArray;
    }

    private static void sortAsc(Contact[] array) {
        Contact contactToSwap;
        int indexToSwap;

        for (int i = 0; i < array.length; i++) {
            contactToSwap = array[i];
            indexToSwap = i;

            for (int j = i; j < array.length; j++) {

                if (contactToSwap.compare(array[j]) > 0) {
                    contactToSwap = array[j];
                    indexToSwap = j;
                }
            }

            var temp = contactToSwap;
            array[indexToSwap] = array[i];
            array[i] = temp;
        }
    }

    private static void sortDesc(Contact[] array) {
        Contact contactToSwap;
        int indexToSwap;

        for (int i = 0; i < array.length; i++) {
            contactToSwap = array[i];
            indexToSwap = i;

            for (int j = i; j < array.length; j++) {

                if (contactToSwap.compare(array[j]) < 0) {
                    contactToSwap = array[j];
                    indexToSwap = j;
                }
            }

            var temp = contactToSwap;
            array[indexToSwap] = array[i];
            array[i] = temp;
        }
    }

    private Task5() {
    }

    public record Contact(String name, String surname) {
        public int compare(Contact another) {
            String firstStringToCompare = this.surname == null ? this.name : this.surname;
            String secondStringToCompare = another.surname == null ? another.name : another.surname;
            return firstStringToCompare.compareTo(secondStringToCompare);
        }
    }
}
