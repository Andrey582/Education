package edu.hw7;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("MagicNumber")
public class Task4 {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void task() {

        DecimalFormat df = new DecimalFormat("#.######");

        int iterations = 1000000;

        for (int i = 0; i < 3; i++) {

            iterations *= 10;

            long start1 = System.nanoTime();
            double singleResult = singleThreadMonteCarlo(iterations);
            long end1 = System.nanoTime();

            long start2 = System.nanoTime();
            double multiResult = multiThreadMonteCarlo(iterations, 4);
            long end2 = System.nanoTime();

            double singleTime = (double) (end1 - start1) / 1000000000;
            double multiTime = (double) (end2 - start2) / 1000000000;

            double singleDelta = Math.abs(Math.PI - singleResult);
            double multiDelta = Math.abs(Math.PI - multiResult);

            LOGGER.info("Погрешность и время при {} точек (single) : {} - {} сек",
                iterations,
                df.format(singleDelta),
                df.format(singleTime));
            LOGGER.info("Погрешность и время при {} точек (multi) : {} - {} сек",
                iterations,
                df.format(multiDelta),
                df.format(multiTime));
        }
    }

    public static double singleThreadMonteCarlo(int iteration) {
        Random random = new Random();

        int totalCount = iteration;
        int circleCount = 0;

        for (int i = 0; i < iteration; i++) {
            double x = random.nextDouble(10);
            double y = random.nextDouble(10);
            if ((x - 5) * (x - 5) + (y - 5) * (y - 5) <= 25) {
                circleCount++;
            }
        }

        return 4 * (circleCount / (double) totalCount);
    }

    public static double multiThreadMonteCarlo(int iteration, int threadCount) {

        int totalCount = iteration;
        int circleCount = 0;

        List<MyThread> threads = new ArrayList<>();

        for (int i = 0; i < threadCount; i++) {
            threads.add(new MyThread(iteration / threadCount));
            threads.get(i).start();
        }

        try {
            for (MyThread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (MyThread thread : threads) {
            circleCount += thread.getCircleCount();
        }

        return 4 * (circleCount / (double) totalCount);
    }

    private Task4() {
    }

    public static class MyThread extends Thread {

        private int circleCount;
        private int iteration;

        public MyThread(int iteration) {
            this.iteration = iteration;
        }

        @Override
        public void run() {
            for (int i = 0; i < iteration; i++) {
                double x = ThreadLocalRandom.current().nextDouble(10);
                double y = ThreadLocalRandom.current().nextDouble(10);
                if ((x - 5) * (x - 5) + (y - 5) * (y - 5) <= 25) {
                    circleCount++;
                }
            }
        }

        public int getCircleCount() {
            return circleCount;
        }
    }
}
