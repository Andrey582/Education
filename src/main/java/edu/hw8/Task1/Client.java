package edu.hw8.Task1;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class Client {

    public static void main(String[] args) {
        Client client = new Client();
        client.connect("личности", 8080);
    }

    public String connect(String msg, int port) {
        try (SocketChannel client = SocketChannel.open(new InetSocketAddress("localhost", port))) {

            ByteBuffer buffer = ByteBuffer.allocate(256);
            buffer.put(msg.getBytes());

            buffer.flip();
            client.write(buffer);

            buffer.clear();

            client.read(buffer);
            buffer.flip();

            return new String(buffer.array()).trim();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
