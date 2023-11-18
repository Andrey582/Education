package edu.project3.Printer;

import java.io.IOException;
import java.nio.file.Path;

public interface Printer {
    void print();
    void printToFile(Path path) throws IOException;
}
