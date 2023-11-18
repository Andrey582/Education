package edu.hw6;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task2 {

    public static void main(String[] args) {
        try {
            cloneFile(Path.of("test.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void cloneFile(Path path) throws IOException {

        if (!path.toFile().isFile()) {
            throw new IllegalArgumentException();
        }

        int count = 0;

        Path tempPath = path;

        do {

            if (Files.exists(tempPath)) {

                String filePath = tempPath.toAbsolutePath().toString();

                tempPath = switch (count) {
                    case 0 -> Path.of(filePath.replace(".", " - копия."));
                    case 1 -> Path.of(filePath.replace("копия.", "копия (" + count + ")."));
                    default -> Path.of(filePath.replace("копия (" + (count - 1) + ").", "копия (" + count + ")."));
                };

                count++;

            } else {
                Files.copy(path, tempPath);
                break;
            }
        } while (true);


    }
}
