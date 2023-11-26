package edu.hw7;

public class Task1 {

    private static int counter = 0;

    private static synchronized void counterAdd() {
        counter++;
    }

    public static int counterAdding(int firstInterval, int secondInterval, int thirdInterval) {

        counter = 0;

        Thread th1 = new Thread(() -> {
            for (int i = 0; i < firstInterval; i++) {
                counterAdd();
            }
        });

        Thread th2 = new Thread(() -> {
            for (int i = 0; i < secondInterval; i++) {
                counterAdd();
            }
        });

        Thread th3 = new Thread(() -> {
            for (int i = 0; i < thirdInterval; i++) {
                counterAdd();
            }
        });

        th1.start();
        th2.start();
        th3.start();

        try {
            th1.join();
            th2.join();
            th3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return counter;
    }

    private Task1() {
    }
}
