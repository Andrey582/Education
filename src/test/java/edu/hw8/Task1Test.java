package edu.hw8;

import edu.hw8.Task1.Client;
import edu.hw8.Task1.Server;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {

    private static Server server;

    @BeforeAll
    public static void startServer() {
        server = new Server();
        Thread thread = new Thread(() -> {
            try {
                server.startServer();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
    }

    @AfterAll
    public static void closeServer() {
        server.close();
    }

    @ParameterizedTest
    @MethodSource("serverMessageProvider")
    void messageTest(String msg, String expected) {

        Client client = new Client();
        String result;


        try (ExecutorService service = Executors.newSingleThreadExecutor()) {
            Thread.sleep(100);
            result = service.submit(() -> client.connect(msg, 8080)).get();

        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        assertThat(result)
            .isEqualTo(expected);
    }

    public static Stream<Arguments> serverMessageProvider() {
        return Stream.of(
            Arguments.of("личности", "Не переходи на личности там, где их нет"),
            Arguments.of("оскорбления", "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами"),
            Arguments.of("глупый", "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма."),
            Arguments.of("интеллект", "Чем ниже интеллект, тем громче оскорбления"),
            Arguments.of("неизвестное сообщение", "такое сообщение не поддерживается")
        );
    }
}
