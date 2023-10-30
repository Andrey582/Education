package edu.hw2;

import edu.hw2.Task3.ConnectionException;
import edu.hw2.Task3.Connections.FaultyConnection;
import edu.hw2.Task3.Connections.StableConnection;
import edu.hw2.Task3.Managers.DefaultConnectionManager;
import edu.hw2.Task3.Managers.FaultyConnectionManager;
import edu.hw2.Task3.PopularCommandExecutor;
import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

public class Task3Test {

    @Test
    void faultyConnectionMessageTest() {

        // given
        String expected = "Command failed";

        // when
        ConnectionException exception = assertThrows(ConnectionException.class, () -> {
            PopularCommandExecutor executor = new PopularCommandExecutor(new FaultyConnectionManager(), 2);
            executor.updatePackages();
        });

        //then
        assertThat(expected)
            .isEqualTo(exception.getMessage());
    }

    @Test
    void faultyConnectionCauseTest() {

        // given
        String expected = "java.lang.Throwable: The faulty connection couldn't execute the command";

        // when
        ConnectionException exception = assertThrows(ConnectionException.class, () -> {
            PopularCommandExecutor executor = new PopularCommandExecutor(new FaultyConnectionManager(), 3);
            executor.updatePackages();
        });

        //then
        assertThat(expected)
            .isEqualTo(exception.getCause().toString());
    }

    @Test
    void faultyConnectionGetGoodResultTest() {

        // given
        LogCaptor logCaptor = LogCaptor.forClass(FaultyConnection.class);
        PopularCommandExecutor executor = new PopularCommandExecutor(new FaultyConnectionManager(), 7);
        String expected = "apt update && apt upgrade -y. Execute successfully";

        // when
        executor.updatePackages();

        // then
        assertThat(logCaptor.getLogs())
            .contains("apt update && apt upgrade -y. Execute successfully");

    }

    @Test
    void DefaultConnectionManagerReturnFaultyConnectionTest() {

        // given
        String expected = "java.lang.Throwable: The faulty connection couldn't execute the command";

        // when
        ConnectionException exception = assertThrows(ConnectionException.class, () -> {
            PopularCommandExecutor executor = new PopularCommandExecutor(new DefaultConnectionManager(), 3);
            executor.updatePackages();
        });

        //then
        assertThat(expected)
            .isEqualTo(exception.getCause().toString());

    }

    @Test
    void DefaultConnectionGetGoodResultTest() {

        // given
        LogCaptor logCaptor = LogCaptor.forClass(StableConnection.class);
        PopularCommandExecutor executor = new PopularCommandExecutor(new DefaultConnectionManager(), 6);
        String expected = "apt update && apt upgrade -y. Execute successfully";

        // when
        executor.updatePackages();

        // then
        assertThat(logCaptor.getLogs())
            .contains("apt update && apt upgrade -y. Execute successfully");

    }
}
