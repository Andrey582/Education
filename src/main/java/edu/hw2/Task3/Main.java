package edu.hw2.Task3;

import edu.hw2.Task3.Connections.StableConnection;
import edu.hw2.Task3.Managers.DefaultConnectionManager;
import edu.hw2.Task3.Managers.FaultyConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    public static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        PopularCommandExecutor executor = new PopularCommandExecutor(new FaultyConnectionManager(), 2);
        try {
            executor.updatePackages();
        } catch (ConnectionException e) {
            LOGGER.info(e.getCause());
        }
    }
}
