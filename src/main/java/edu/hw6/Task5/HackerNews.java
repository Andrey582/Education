package edu.hw6.Task5;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HackerNews {

    public static void main(String[] args) {
        try {
            System.out.println(news(37570037));
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static long[] hackerNewsTopStories() {

        var request = HttpRequest.newBuilder()
            .uri(URI.create("https://hacker-news.firebaseio.com/v0/topstories.json"))
            .GET()
            .build();

        HttpResponse<String> client;

        try {
            client = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            return new long[]{};
        }

        String body = client.body().replace("\\[|\\]", "");
        String[] array = body.split(",");

        return Arrays.stream(array).mapToLong(Long::valueOf).toArray();
    }

    public static String news(long id) throws IOException, InterruptedException {
        var response = HttpRequest.newBuilder()
            .uri(URI.create("https://hacker-news.firebaseio.com/v0/item/" + id + ".json"))
            .GET()
            .build();

        var request = HttpClient.newHttpClient().send(response, HttpResponse.BodyHandlers.ofString());

        String body = request.body();

        Pattern pattern = Pattern.compile("^.*\"title\":\"(.*?)\".*$");
        Matcher matcher = pattern.matcher(body);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "";
        }

    }

}
