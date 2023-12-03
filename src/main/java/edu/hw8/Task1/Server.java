package edu.hw8.Task1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

public class Server {

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.startServer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ExecutorService service;
    private Selector serverSelector;
    private ServerSocketChannel serverChannel;
    private boolean isRun = true;

    private static final Map<String, String> answers = Map.of(
        "личности", "Не переходи на личности там, где их нет",
        "оскорбления", "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами",
        "глупый", "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
        "интеллект", "Чем ниже интеллект, тем громче оскорбления"
    );

    public static final int PORT = 8080;

    public void startServer() throws IOException {

        service = Executors.newFixedThreadPool(4);
        serverSelector = Selector.open();
        serverChannel = ServerSocketChannel.open();

        serverChannel.bind(new InetSocketAddress("localhost", PORT));
        serverChannel.configureBlocking(false);

        serverChannel.register(serverSelector, SelectionKey.OP_ACCEPT);

        while (isRun) {
            serverSelector.select();
            Set<SelectionKey> selectionKeys = serverSelector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                try {
                    service.submit(() -> {
                        SelectionKey key = iterator.next();

                        if (key.isAcceptable()) {
                            register(serverChannel, serverSelector);
                        }

                        if (key.isReadable()) {
                            writer(key);
                        }
                        iterator.remove();
                    }).get();
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    public void close() {
        isRun = false;
    }

    private void writer(SelectionKey key) {
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(256);
        try {
            client.read(buffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        buffer.flip();

        String answer = answers.get(new String(buffer.array()).trim().toLowerCase());
        buffer.flip();
        buffer.clear();
        if (answer == null) {
            answer = "такое сообщение не поддерживается";
        }
        buffer.put(answer.getBytes());
        buffer.flip();
        try {
            client.write(buffer);
            client.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        buffer.clear();

    }

    private void register(ServerSocketChannel serverChannel, Selector serverSelector) {
        try {
            SocketChannel client = serverChannel.accept();
            client.configureBlocking(false);
            client.register(serverSelector, SelectionKey.OP_READ);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
