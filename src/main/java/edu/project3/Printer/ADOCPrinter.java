package edu.project3.Printer;

import edu.project3.Storage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ADOCPrinter implements Printer {

    private final static Logger LOGGER = LogManager.getLogger();
    private final Storage storage;
    private final LocalDate dateFrom;
    private final LocalDate dateTo;

    public ADOCPrinter(Storage storage, LocalDate dateFrom, LocalDate dateTo) {
        this.storage = storage;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    @Override
    public void print() {
        printHeader();
        printResources();
        printAnswerCodes();
        printIP(100);
        printDates();
    }

    private void printHeader() {
        LOGGER.info("==== Общая информация\n");
        LOGGER.info("[width=\"100%\", options=\"header\", cols=\"^,>\"]");
        LOGGER.info("|===");
        LOGGER.info("|Метрика|Значение");
        LOGGER.info("|Файл(-ы)|_" + storage.getFiles().get(0) + "_");
        for (int i = 1; i < storage.getFiles().size(); i++) {
            LOGGER.info("|| _" + storage.getFiles().get(i) + "_");
        }

        if (dateFrom != null) {
            LOGGER.info("|Начальная дата|" + dateFrom.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        } else {
            LOGGER.info("|Начальная дата|-");
        }

        if (dateTo != null) {
            LOGGER.info("|Конечная дата|" + dateTo.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        } else {
            LOGGER.info("|Конечная дата|-");
        }

        LOGGER.info("|Количество запросов|" + storage.getRequestCount());
        LOGGER.info("|Средний размер ответа|" + storage.getRequestSize() / storage.getRequestCount() + "b");
        LOGGER.info("|===");
    }

    private void printResources() {
        LOGGER.info("==== Запрашиваемые ресурсы \n");
        LOGGER.info("[width=\"100%\", options=\"header\", cols=\"^,>\"]");
        LOGGER.info("|===");
        LOGGER.info("|Ресурс|Количество");
        for (var resource : storage.getResources()) {
            LOGGER.info("|_" + resource.getKey() + "_|" + resource.getValue());
        }
        LOGGER.info("|===");
    }

    private void printAnswerCodes() {
        LOGGER.info("==== Коды ответа \n");
        LOGGER.info("[width=\"100%\", options=\"header\", cols=\"^,^,>\"]");
        LOGGER.info("|===");
        LOGGER.info("|Код|Имя|Количество");
        for (var code : storage.getAnswerCode()) {
            LOGGER.info("|" + code.getKey() + "|" + getAnswerByCode(code.getKey()) + "|" + code.getValue());
        }
        LOGGER.info("|===");
    }

    private void printIP(int countRequests) {
        LOGGER.info("==== Запросы от адресов \n");
        LOGGER.info("[width=\"100%\", options=\"header\", cols=\"^,>\"]");
        LOGGER.info("|===");
        LOGGER.info("|IP|Количество");
        for (var IP : storage.getAddress()) {
            if (IP.getValue() <= countRequests) {
                break;
            }
            LOGGER.info("|" + IP.getKey() + "|" + IP.getValue());
        }
        LOGGER.info("|===");
    }

    private void printDates() {
        LOGGER.info("==== Запросы по датам \n");
        LOGGER.info("[width=\"100%\", options=\"header\", cols=\"^,>\"]");
        LOGGER.info("|===");
        LOGGER.info("|Дата|Количество");
        for (var data : storage.getDates()) {
            LOGGER.info("|" + data.getKey() + "|" + data.getValue());
        }
        LOGGER.info("|===");
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
