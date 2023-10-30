package edu.hw2.Task3;

import edu.hw2.Task3.Connections.Connection;
import edu.hw2.Task3.Managers.ConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PopularCommandExecutor {
    private static final Logger LOGGER = LogManager.getLogger();
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() throws ConnectionException {
        tryExecute("apt update && apt upgrade -y");
    }

    private void tryExecute(String command) throws ConnectionException {
        for (int i = 0; i < maxAttempts; i++) {
            try (Connection connection = manager.getConnection()) {
                connection.execute(command);
            } catch (Exception e) {
                LOGGER.info(e.getMessage());

                if (i == maxAttempts - 1) {
                    throw new ConnectionException(e.getMessage(), e.getCause());
                }
                continue;
            }
            break;
        }
    }
}
