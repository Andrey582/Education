package edu.project3.Parsers;

import edu.project3.Storage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLParser implements Parser {

    private final URI uri;
    private final Storage storage;
    private final LocalDate dateFrom;
    private final LocalDate dateTo;

    private final static Pattern LOG_PATTERN = Pattern.compile(
        "^(.*)"
            + "\\s-\\s.*\\s"
            + "\\[(.*?):.*\\]\\s"
            + "\"(.*)\\s(.*)\\s.*\"\\s"
            + "(\\d{3})\\s"
            + "(\\d{1,15})"
            + "\\s\".*\"\\s\".*\"$"
    );

    private Matcher matcher;

    public URLParser(String url, Storage storage, LocalDate dateFrom, LocalDate dateTo)
        throws URISyntaxException {

        this.uri = new URI(url);
        this.storage = storage;
        this.dateFrom = dateFrom == null ? LocalDate.MIN : dateFrom;
        this.dateTo = dateTo == null ? LocalDate.MAX : dateTo;
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

        HttpRequest request = HttpRequest
            .newBuilder(uri)
            .GET()
            .build();

        try {
            HttpResponse<String> response = HttpClient
                .newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());

            String[] logs = response.body().split("\n");

            for (String log : logs) {
                matcher = LOG_PATTERN.matcher(log);

                if (matcher.find()) {
                    parse(matcher);
                }
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        storage.setFiles(List.of(uri.getPath()));

    }
}
