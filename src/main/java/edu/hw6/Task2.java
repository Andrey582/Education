package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Task2 {

    @SuppressWarnings("MultipleStringLiterals")
    public static void cloneFile(Path path) throws IOException {

        if (Files.isDirectory(path)) {
            throw new IllegalArgumentException("Need file path");
        }

        if (!Files.exists(path)) {
            Files.createFile(path);
            return;
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

    private Task2() {
    }
}
