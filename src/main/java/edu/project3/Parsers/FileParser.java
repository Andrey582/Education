package edu.project3.Parsers;

import edu.project3.Storage;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileParser implements Parser {

    public static final int BUFFER_SIZE = 128;
    public static final Pattern LOG_PATTERN = Pattern.compile(
        "^(.*)"
            + "\\s-\\s.*\\s"
            + "\\[(.*?):.*\\]\\s"
            + "\"(.*)\\s(.*)\\s.*\"\\s"
            + "(\\d{3})\\s"
            + "(\\d{1,15})"
            + "\\s\".*\"\\s\".*\"$"
    );
    private final Storage storage;
    private final LocalDate dateFrom;
    private final LocalDate dateTo;
    private final String path;
    private List<Path> files;
    private Matcher matcher;

    public FileParser(String path, Storage storage, LocalDate dateFrom, LocalDate dateTo) {
        this.path = path;
        this.storage = storage;
        this.dateFrom = dateFrom == null ? LocalDate.MIN : dateFrom;
        this.dateTo = dateTo == null ? LocalDate.MAX : dateTo;
        this.files = new ArrayList<>();
    }

    @SuppressWarnings("MagicNumber")
    private void parse(Matcher matcher) {
        LocalDate date = LocalDate.parse(
            matcher.group(2), DateTimeFormatter.ofPattern("dd/MMM/yyyy", Locale.ENGLISH)
        );

        if ((dateFrom.isBefore(date) || dateFrom.isEqual(date)) && (dateTo.isAfter(date) || dateTo.isEqual(date))) {
            storage.increaseRequestCount();
            storage.addAddress(matcher.group(1));
            storage.addDate(date);
            storage.addResource(matcher.group(4));
            storage.addAnswerCode(matcher.group(5));
            storage.addRequestSize(Long.valueOf(matcher.group(6)));
        }
    }

    @Override
    public void readData() throws IOException {
        getAllFilesByPath();

        if (files.isEmpty()) {
            throw new IOException("Cannot find file");
        }

        storage.setFiles(files.stream().map(Path::getFileName).map(Path::toString).toList());
        for (Path file : files) {
            read(file);
        }
    }

    private void read(Path path) throws IOException {
        StringBuilder sb = new StringBuilder();

        try (FileChannel fileChannel = FileChannel.open(path)) {
            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

            int bytesRead = fileChannel.read(buffer);

            while (bytesRead != -1) {
                buffer.flip();

                while (buffer.hasRemaining()) {
                    char ch = (char) buffer.get();

                    if (ch != '\n') {
                        sb.append(ch);

                    } else {
                        matcher = LOG_PATTERN.matcher(sb.toString());
                        if (matcher.find()) {
                            parse(matcher);
                        }
                        sb = new StringBuilder();
                    }
                }

                buffer.clear();
                bytesRead = fileChannel.read(buffer);
            }
        }
    }

    @SuppressWarnings({"MultipleStringLiterals", "HiddenField"})
    private void getAllFilesByPath() throws IOException {
        String[] filesStringArray = path.split("\\*{2}");

        if (filesStringArray.length == 1) {

            if (filesStringArray[0].charAt(filesStringArray[0].length() - 1) == '*') {
                Path currentPath = Path.of(filesStringArray[0].replace("*", ""));
                walker(currentPath);
            } else {
                this.files.add(Path.of(filesStringArray[0]));
            }
        } else {
            Path currentPath = Path.of(filesStringArray[0]);
            Path fileName = Path.of(filesStringArray[1]);
            walker(currentPath, fileName);
        }
    }

    private void walker(Path path) throws IOException {
        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (!Files.isDirectory(file)) {
                    files.add(file.toAbsolutePath());
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }

    private void walker(Path path, Path filename) throws IOException {
        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (!Files.isDirectory(file) && file.toFile().getName().equals(filename.toFile().getName())) {
                    files.add(file.toAbsolutePath());
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
