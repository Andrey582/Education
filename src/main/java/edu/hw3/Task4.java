package edu.hw3;

public class Task4 {

    private static final int MINIMUM_NUMBER = 0;
    private static final int MAXIMUM_NUMBER = 4000;
    private static final int TEN = 10;
    private static final int HUNDRED = 100;
    private static final int THOUSAND = 1000;

    private static final String[] ONES = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
    private static final String[] TENS = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    private static final String[] HUNDREDS = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    private static final String[] THOUSANDS = {"", "M", "MM", "MMM"};

    public static String convertToRoman(int num) throws IllegalArgumentException {

        if (num <= MINIMUM_NUMBER || num >= MAXIMUM_NUMBER) {
            throw new IllegalArgumentException("The number must be greater than 0 and less than 4000");
        }

        StringBuilder result = new StringBuilder();

        result.append(THOUSANDS[num / THOUSAND]);
        result.append(HUNDREDS[num % THOUSAND / HUNDRED]);
        result.append(TENS[num % HUNDRED / TEN]);
        result.append(ONES[num % TEN]);

        return result.toString();
    }

    private Task4() {
    }
}
