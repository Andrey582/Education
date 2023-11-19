package edu.hw6;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.Checksum;

public class Task4 {

    public static void writeToFile(String fileName, String textToWrite) {

        byte[] bytes = textToWrite.getBytes();
        Checksum sum = new CRC32();
        sum.update(bytes, 0, bytes.length);

        try {
            Path path = Path.of(fileName);
            if (!Files.exists(path)) {
                Files.createFile(path);
            }

            try (
                OutputStream outputStream = new FileOutputStream(fileName);
                CheckedOutputStream checkedOutputStream = new CheckedOutputStream(outputStream, sum);
                BufferedOutputStream bos = new BufferedOutputStream(checkedOutputStream);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(bos, StandardCharsets.UTF_8);
                PrintWriter writer = new PrintWriter(outputStreamWriter);
            ) {

                writer.write(textToWrite);

            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private Task4() {
    }
}
