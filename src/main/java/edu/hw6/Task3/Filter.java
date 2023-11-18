package edu.hw6.Task3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Filter {

    public static final AbstractFilter regular = Files::isRegularFile;
    public static final AbstractFilter readable = Files::isReadable;

    public static DirectoryStream.Filter<Path> filter = regular
        .and(readable)
        .and(magicNumber(0x89, 'P', 'N', 'G'));

    public static AbstractFilter largerThan(int size) {
        return path -> {
            try {
                return Files.size(path) > size;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static AbstractFilter globalMatches(String fileExtension) {
        return path -> {
            Pattern pattern = Pattern.compile(fileExtension.replace("*", ".*") + "$");
            Matcher matcher = pattern.matcher(path.getFileName().toString());
            return matcher.find();
        };
    }

    public static AbstractFilter regexContains(String fileRegex) {
        return path -> {
            Pattern pattern = Pattern.compile(fileRegex);
            Matcher matcher = pattern.matcher(path.getFileName().toString());
            return matcher.find();
        };
    }

    public static AbstractFilter magicNumber(int... chars) {

        return path -> {
            byte[] array = Files.readAllBytes(path);
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
