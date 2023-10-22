package edu.project1;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.stream.Stream;
import nl.altindag.log.LogCaptor;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HangmanTest {

    @Test
    void playerDefeatGameTest() {

        // given
        Dictionary dict = new Dictionary() {
            @Override
            public @NotNull String getRandomWord() {
                return "hello";
            }
        };

        String expected = "> You lost!";
        LogCaptor logCaptor = LogCaptor.forClass(Session.class);
        Session session = new Session(dict);

        String[] input = {"a", "b", "c", "d", "z"};

        InputStream in;

        // when
        for (String s : input) {
            in = new ByteArrayInputStream(s.getBytes());
            System.setIn(in);
            session.guess();
        }

        // then
        assertThat(logCaptor.getLogs())
            .contains(expected);
    }

    @Test
    void playerWinGameTest() {

        // given
        Dictionary dict = new Dictionary() {
            @Override
            public @NotNull String getRandomWord() {
                return "hello";
            }
        };

        String expected = "> You won!";
        LogCaptor logCaptor = LogCaptor.forClass(Session.class);
        Session session = new Session(dict);

        String[] input = {"h", "e", "l", "o"};

        InputStream in;

        // when
        for (String s : input) {
            in = new ByteArrayInputStream(s.getBytes());
            System.setIn(in);
            session.guess();
        }

        //then
        assertThat(logCaptor.getLogs())
            .contains(expected);
    }

    @Test
    void playerMisprintTest() {

        // given
        Dictionary dict = new Dictionary() {
            @Override
            public @NotNull String getRandomWord() {
                return "hello";
            }
        };

        String expected = "> You have a misprint. Please repeat your input.";
        LogCaptor logCaptor = LogCaptor.forClass(Session.class);
        Session session = new Session(dict);

        String[] input = {"hel\nh", "e", "l", "o"};

        InputStream in;

        // when
        for (String s : input) {
            in = new ByteArrayInputStream(s.getBytes());
            System.setIn(in);
            session.guess();
        }

        //then
        assertThat(logCaptor.getLogs())
            .contains(expected);
    }

    @Test
    void playerMistakeTest() {

        // given
        Dictionary dict = new Dictionary() {
            @Override
            public @NotNull String getRandomWord() {
                return "hello";
            }
        };

        String expected1 = "> Missed, mistake 1 out of 5.";
        String expected2 = "> Missed, mistake 2 out of 5.";
        String expected3 = "> Missed, mistake 3 out of 5.";
        String expected4 = "> Missed, mistake 4 out of 5.";
        String expected5 = "> Missed, mistake 5 out of 5.";

        LogCaptor logCaptor = LogCaptor.forClass(Session.class);
        Session session = new Session(dict);

        String[] input = {"a", "b", "c", "d", "p"};

        InputStream in;

        // when
        for (String s : input) {
            in = new ByteArrayInputStream(s.getBytes());
            System.setIn(in);
            session.guess();
        }


        //then
        assertThat(logCaptor.getLogs())
            .contains(expected1)
            .contains(expected2)
            .contains(expected3)
            .contains(expected4)
            .contains(expected5);
    }

    @Test
    void playerEnterUsedLetterTest() {

        // given
        Dictionary dict = new Dictionary() {
            @Override
            public @NotNull String getRandomWord() {
                return "hello";
            }
        };

        int expected = 2;
        LogCaptor logCaptor = LogCaptor.forClass(Session.class);
        Session session = new Session(dict);

        String[] input = {"h", "h", "e", "l", "l", "o"};

        InputStream in;

        // when
        for (String s : input) {
            in = new ByteArrayInputStream(s.getBytes());
            System.setIn(in);
            session.guess();
        }
        Stream<String> countMessage = logCaptor.getLogs().stream().filter(e -> e.equals("> The letter already used!"));

        //then
        assertThat(countMessage.count())
            .isEqualTo(2);
    }

    @Test()
    void wordLessThan2LetterTest() {

        // given
        Dictionary dict = new Dictionary() {
            @Override
            @NotNull
            public String getRandomWord() {
                return "f";
            }
        };

        String expected = "Answer length need to be greater, than 1";

        // when
        RuntimeException time = assertThrows(RuntimeException.class, () -> new Session(dict));
        // then
        assertThat(time.getMessage())
            .isEqualTo(expected);
    }
}
