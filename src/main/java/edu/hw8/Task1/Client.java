package edu.hw8.Task1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {

    private static final int BUFFER_SIZE = 256;

    public String connect(String msg, int port) {
        try (SocketChannel client = SocketChannel.open(new InetSocketAddress("localhost", port))) {

            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
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
