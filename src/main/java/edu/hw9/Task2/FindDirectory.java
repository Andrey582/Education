package edu.hw9.Task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FindDirectory extends RecursiveTask<List<File>> {

    private final File startDirectory;
    private int counter;
    private final int needCount;

    public FindDirectory(File startDirectory, int needCount) {
        this.startDirectory = startDirectory;
        counter = 0;
        this.needCount = needCount;
    }

    @Override
    protected List<File> compute() {
        List<FindDirectory> subtask = new ArrayList<>();
        List<File> result = new ArrayList<>();

        File[] files = startDirectory.listFiles();
        if (files != null) {
            for (File file : files) {

                if (file.isDirectory()) {
                    FindDirectory sub = new FindDirectory(file, needCount);
                    sub.fork();
                    subtask.add(sub);
                } else {
                    counter++;
                }
            }
        }

        if (counter >= needCount) {
            result.add(startDirectory);
        }

        for (var sub : subtask) {
            result.addAll(sub.join());
        }
        return result;
    }
}
