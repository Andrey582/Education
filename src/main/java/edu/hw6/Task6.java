package edu.hw6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.SocketException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings({"MultipleStringLiterals", "MagicNumber", "EmptyBlock"})
public class Task6 {

    private final static Logger LOGGER = LogManager.getLogger();

    public static void checkPort() {
        int startPort = 0;
        int finalPort = 49151;

        LOGGER.info("Протокол\tПорт\tСервис");

        for (int i = startPort; i < finalPort; i++) {

            try (ServerSocket serverSocket = new ServerSocket(i)) {

            } catch (IOException e) {
                LOGGER.info("TCP\t\t\t" + i + "\t\t" + getServiceTCP(i));
            }

            try (DatagramSocket datagramSocket = new DatagramSocket(i)) {

            } catch (SocketException e) {
                LOGGER.info("UDP\t\t\t" + i + "\t\t" + getServiceUDP(i));
            }
        }
    }

    private static String getServiceTCP(int port) {
        return switch (port) {
            case 135 -> "EPMAP";
            case 139 -> "Служба сеансов NetBIOS";
            case 445 -> "Microsoft-DS Active Directory";
            case 843 -> "Adobe Flash";
            case 17500 -> "Dropbox";
            case 27017 -> "MongoDB";
            default -> "";
        };
    }

    private static String getServiceUDP(int port) {
        return switch (port) {
            case 137 -> "Служба имен NetBIOS";
            case 138 -> "Служба датаграмм NetBIOS";
            case 1900 -> "Simple Service Discovery Protocol (SSDP)";
            case 3702 -> "Динамическое обнаружение веб-служб";
            case 5353 -> "Многоадресный DNS";
            case 5355 -> "Link-Local Multicast Name Resolution (LLMNR)";
            case 17500 -> "Dropbox";
            default -> "";
        };
    }

    private Task6() {
    }
}
