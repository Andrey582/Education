package edu.hw8.Task2;

public class FixedThreadPool implements ThreadPool {

    private Thread[] threads;

    public static ThreadPool create(int threadCount) {
        return new FixedThreadPool(threadCount);
    }

    private FixedThreadPool(int countThreads) {
        threads = new Thread[countThreads];
    }

    @Override
    public void start() {
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(runnable);
        }
    }

    @Override
    public void close() throws Exception {
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
    }
}
