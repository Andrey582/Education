package edu.hw6.Task3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Predicate;

public interface AbstractFilter extends DirectoryStream.Filter<Path> {

    default AbstractFilter and(AbstractFilter filter) {
        return path -> accept(path) && filter.accept(path);
    }
}
