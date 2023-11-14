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

    private final Storage storage;
    private final LocalDate dateFrom;
    private final LocalDate dateTo;
    private final String path;
    private List<Path> files;

    private final static Pattern LOG_PATTERN = Pattern.compile(
        "^(\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3})" +
            "\\s-\\s-\\s" +
            "\\[(.*?):.*\\]" +
            "\\s" +
            "\"(.*)\\s(.*)\\s.*\"" +
            "\\s" +
            "(\\d{3})" +
            "\\s" +
            "(\\d{1,8})" +
            "\\s\"-\"\\s\".*\"$"
    );

    private Matcher matcher;

    public FileParser(String path, Storage storage, LocalDate dateFrom, LocalDate dateTo) {
        this.path = path;
        this.storage = storage;
        this.dateFrom = dateFrom == null ? LocalDate.MIN : dateFrom;
        this.dateTo = dateTo == null ? LocalDate.MAX : dateTo;
        this.files = new ArrayList<>();
    }

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
        storage.setFiles(files.stream().map(Path::getFileName).map(Path::toString).toList());
        for (Path file : files) {
            read(file);
        }
    }

    private void read(Path path) throws IOException {
        StringBuilder sb = new StringBuilder();

        try(FileChannel fileChannel = FileChannel.open(path)) {
            ByteBuffer buffer = ByteBuffer.allocate(128);

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

    private void getAllFilesByPath() throws IOException {
        String[] files = path.split("\\*{2}");

        if (files.length == 1) {

            if (files[0].charAt(files[0].length() - 1) == '*') {
                Path currentPath = Path.of(files[0].replace("*", ""));
                walker(currentPath);
            } else {
                this.files.add(Path.of(files[0]));
            }
        } else {
            Path currentPath = Path.of(files[0]);
            walker(currentPath);
            this.files = this.files.stream().filter(
                e -> {
                    StringBuilder filePattern = new StringBuilder();
                    for (int i = 0; i < files.length - 1; i ++) {
                        filePattern.append(files[i]).append(".*");
                    }
                    filePattern.append(files[files.length - 1]);
                    String filePath = e.toAbsolutePath().toString();
                    Pattern pattern = Pattern.compile(filePattern.toString());
                    Matcher matcher = pattern.matcher(filePath);
                    return matcher.find();
                }
            ).toList();
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
}
