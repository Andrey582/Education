package edu.hw6.Task1;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiskMap {

    private final static int BUFFER_SIZE = 128;
    private Map<String, String> storage;
    private Path path;

    public DiskMap(Path path) {
        this.path = path;
        this.storage = new HashMap<>();
    }

    public void safeValueToFile() {

        try (FileChannel channel = FileChannel.open(
            path,
            StandardOpenOption.CREATE,
            StandardOpenOption.WRITE,
            StandardOpenOption.TRUNCATE_EXISTING)
        ) {

            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

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

            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

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
