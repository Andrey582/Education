package edu.hw6.Task3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface AbstractFilter extends DirectoryStream.Filter<Path> {

    default AbstractFilter and(AbstractFilter filter) {
        return path -> accept(path) && filter.accept(path);
    }

    AbstractFilter REGULAR_FILE = Files::isRegularFile;

    AbstractFilter READABLE = Files::isReadable;

    static AbstractFilter largerThan(int size) {
        return path -> {
            try {
                return Files.size(path) > size;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }

    static AbstractFilter globalMatches(String fileExtension) {
        return path -> {
            Pattern pattern = Pattern.compile(fileExtension.replace("*", ".*") + "$");
            Matcher matcher = pattern.matcher(path.getFileName().toString());
            return matcher.find();
        };
    }

    static AbstractFilter regexContains(String fileRegex) {
        return path -> {
            Pattern pattern = Pattern.compile(fileRegex);
            Matcher matcher = pattern.matcher(path.getFileName().toString());
            return matcher.find();
        };
    }

    @SuppressWarnings("MultipleStringLiterals")
    static AbstractFilter magicNumber(int... chars) {

        return path -> {
            byte[] array = Files.readAllBytes(path);

            if (array.length < chars.length) {
                return false;
            }

            for (int i = 0; i < chars.length; i++) {
                String firstByte = String.format("%02x", chars[i]);
                String secondByte = String.format("%02x", array[i]);
                if (!firstByte.equals(secondByte)) {
                    return false;
                }
            }
            return true;
        };
    }
}
