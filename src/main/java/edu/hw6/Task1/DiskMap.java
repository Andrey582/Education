package edu.hw6.Task1;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiskMap {

    private Map<String, String> storage;
    private Path path;

    public DiskMap(String path) {
        this.path = Path.of(path);
        this.storage = new HashMap<>();
    }



    public void safeValueToFile() {

        try (FileChannel channel = FileChannel.open(
            path,
            StandardOpenOption.CREATE,
            StandardOpenOption.WRITE,
            StandardOpenOption.TRUNCATE_EXISTING)
        ) {

            ByteBuffer buffer = ByteBuffer.allocate(128);

            List<Map.Entry<String, String>> list = storage.entrySet().stream().toList();

            for (Map.Entry<String, String> item : list) {
                 String write = item.getKey() + ":" + item.getValue() + "\n";
                 buffer.put(write.getBytes());
                 buffer.flip();
                 channel.write(buffer);
                 buffer.flip();
                 buffer.clear();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadValueFromFile() {

        try (FileChannel channel = FileChannel.open(
            path,
            StandardOpenOption.CREATE,
            StandardOpenOption.READ)
        ) {

            ByteBuffer buffer = ByteBuffer.allocate(128);

            StringBuilder sb = new StringBuilder();

            while (channel.read(buffer) != -1) {

                buffer.flip();

                while (buffer.hasRemaining()) {

                    char ch = (char) buffer.get();
                    if (ch == '\n') {
                        String keyValue = sb.toString();
                        String[] splitedString = keyValue.split(":");
                        storage.put(splitedString[0], splitedString[1]);
                        sb = new StringBuilder();
                    } else {
                        sb.append(ch);
                    }
                }
                buffer.flip();
                buffer.clear();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addValue(String key, String value) {
        storage.put(key, value);
    }

    public void setMap(Map<String, String> map) {
        storage = map;
    }

    public Map<String, String> getValues() {
        return Map.copyOf(storage);
    }
}
