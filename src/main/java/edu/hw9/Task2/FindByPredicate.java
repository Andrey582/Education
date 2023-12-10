package edu.hw9.Task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;

public class FindByPredicate extends RecursiveTask<List<File>> {

    private final Predicate<File> predicate;
    private final File startDirectory;

    public FindByPredicate(File startDirectory, Predicate<File> predicate) {
        this.predicate = predicate;
        this.startDirectory = startDirectory;
    }

    @Override
    protected List<File> compute() {
        List<File> result = new ArrayList<>();
        List<FindByPredicate> subtask = new ArrayList<>();

        File[] files = startDirectory.listFiles();

        if (files != null) {
            for (File file : files) {

                if (file.isDirectory()) {

                    FindByPredicate sub = new FindByPredicate(file, predicate);
                    sub.fork();
                    subtask.add(sub);

                } else {
                    if (predicate.test(file)) {
                        result.add(file);
                    }
                }

            }
        }

        for (var sub : subtask) {
            result.addAll(sub.join());
        }

        return result;
    }
}
