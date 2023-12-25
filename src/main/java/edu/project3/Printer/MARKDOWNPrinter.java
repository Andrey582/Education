package edu.project3.Printer;

import edu.project3.Storage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("MultipleStringLiterals")
public class MARKDOWNPrinter implements Printer {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final int DEFAULT_COUNT_REQUEST = 100;
    private int countRequest = DEFAULT_COUNT_REQUEST;
    private final Storage storage;
    private final LocalDate dateFrom;
    private final LocalDate dateTo;

    public MARKDOWNPrinter(Storage storage, LocalDate dateFrom, LocalDate dateTo) {
        this.storage = storage;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    @Override
    public void print() {
        LOGGER.info(header());
        LOGGER.info(resources());
        LOGGER.info(answerCodes());
        LOGGER.info(ip(countRequest));
        LOGGER.info(dates());
    }

    @Override
    public void printToFile(Path path) throws IOException {
        if (!path.toFile().exists()) {
            Files.createFile(path);
        }
        Files.writeString(path, header());
        Files.writeString(path, resources(), StandardOpenOption.APPEND);
        Files.writeString(path, answerCodes(), StandardOpenOption.APPEND);
        Files.writeString(path, ip(countRequest), StandardOpenOption.APPEND);
        Files.writeString(path, dates(), StandardOpenOption.APPEND);
    }

    private String header() {
        StringBuilder sb = new StringBuilder();

        sb.append("#### Общая информация\n");
        sb.append("| Метрика | Значение |\n");
        sb.append("|:-:|-:|\n");
        sb.append("|Файл(-ы)| `");
        sb.append(storage.getFiles().get(0));
        sb.append("`|\n");
        for (int i = 1; i < storage.getFiles().size(); i++) {
            sb.append("|| `");
            sb.append(storage.getFiles().get(i));
            sb.append("` |\n");
        }

        if (dateFrom != null) {
            sb.append("|Начальная дата| ");
            sb.append(dateFrom.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
            sb.append(" |\n");
        } else {
            sb.append("|Начальная дата| - |\n");
        }

        if (dateTo != null) {
            sb.append("|Конечная дата| ");
            sb.append(dateTo.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
            sb.append(" |\n");
        } else {
            sb.append("|Конечная дата| - |\n");
        }

        sb.append("|Количество запросов| ");
        sb.append(storage.getRequestCount());
        sb.append(" |\n");

        long size = storage.getRequestCount() == 0 ? 0 : storage.getRequestSize() / storage.getRequestCount();
        sb.append("|Средний размер ответа| ");
        sb.append(size);
        sb.append("b |\n");
        return sb.toString();
    }

    private String resources() {
        StringBuilder sb = new StringBuilder();

        sb.append("#### Запрашиваемые ресурсы \n");
        sb.append("|Ресурс|Количество|\n");
        sb.append("|:-:|-:|\n");
        for (var resource : storage.getResources()) {
            sb.append("|`");
            sb.append(resource.getKey());
            sb.append("`| ");
            sb.append(resource.getValue());
            sb.append(" |\n");
        }
        return sb.toString();
    }

    private String answerCodes() {
        StringBuilder sb = new StringBuilder();

        sb.append("#### Коды ответа \n");
        sb.append("| Код | Имя | Количество |\n");
        sb.append("|:-:|:-:|-:|\n");
        for (var code : storage.getAnswerCode()) {
            sb.append("| ");
            sb.append(code.getKey());
            sb.append(" |");
            sb.append(getAnswerByCode(code.getKey()));
            sb.append(" | ");
            sb.append(code.getValue());
            sb.append(" |\n");
        }

        return sb.toString();
    }

    private String ip(int countRequests) {
        StringBuilder sb = new StringBuilder();

        sb.append("#### Запросы от адресов \n");
        sb.append("| IP | Количество |\n");
        sb.append("|:-:|-:|\n");
        for (var ip : storage.getAddress()) {
            if (ip.getValue() <= countRequests) {
                break;
            }
            sb.append("|");
            sb.append(ip.getKey());
            sb.append("| ");
            sb.append(ip.getValue());
            sb.append(" |\n");
        }

        return sb.toString();
    }

    private String dates() {
        StringBuilder sb = new StringBuilder();

        sb.append("#### Запросы по датам \n");
        sb.append("| Дата | Количество |\n");
        sb.append("|:-:|-:|\n");
        for (var data : storage.getDates()) {
            sb.append("|");
            sb.append(data.getKey());
            sb.append("| ");
            sb.append(data.getValue());
            sb.append(" |\n");
        }

        return sb.toString();
    }

    private String getAnswerByCode(String code) {
        return switch (code) {
            case "200" -> "Ok";
            case "206" -> "Partial Content";
            case "304" -> "Not Modified";
            case "403" -> "Forbidden";
            case "404" -> "Not Found";
            case "500" -> "Internal Server Error";
            default -> "Unknown";
        };
    }
}
