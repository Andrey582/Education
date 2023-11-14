package edu.project3;

import edu.project3.Parsers.FileParser;
import edu.project3.Parsers.URLParser;
import edu.project3.Printer.ADOCPrinter;
import edu.project3.Printer.MARKDOWNPrinter;
import edu.project3.Types.FormatType;
import edu.project3.Types.PathType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;

public class LogStats {

    private final String path;
    private final PathType pathType;
    private final LocalDate dateFrom;
    private final LocalDate dateTo;
    private final FormatType formatType;
    private final Storage storage;
    private static final Logger LOGGER = LogManager.getLogger();

    public LogStats(String path, String dateFrom, String dateTo, String format) {
        this.dateFrom = DataParser.parseDate(dateFrom);
        this.dateTo = DataParser.parseDate(dateTo);
        this.pathType = DataParser.getPathType(path);
        this.path = path;
        this.formatType = DataParser.getFormatType(format);
        this.storage = new Storage();
    }

    public void parse() throws IOException, URISyntaxException {
        switch (pathType) {
            case FILE -> new FileParser(path, storage, dateFrom, dateTo).readData();
            case URL -> new URLParser(path, storage, dateFrom, dateTo).readData();
        }
    }

    public void print() {
        if (formatType == null || formatType == FormatType.MARKDOWN) {
            new MARKDOWNPrinter(storage, dateFrom, dateTo).print();
        } else {
            new ADOCPrinter(storage, dateFrom, dateTo).print();
        }
    }
}
