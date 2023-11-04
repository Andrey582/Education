package edu;

import edu.Creator.MazeCreator;
import edu.Creator.MazeDeepFirsSearch;
import edu.Creator.MazeEller;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SelfWrittenMazeTest {

    @Test
    void selfWrittenMazeDFS() {
        // given
        Cell[][] expected = DefaultValues.getMazeWithoutCycle(new Cell[11][11]);

        // when
        MazeCreator result = new MazeDeepFirsSearch(expected);

        // then
        assertThat(result.getMaze())
            .isEqualTo(expected);
    }

    @Test
    void selfWrittenMazeEller() {
        // given
        Cell[][] expected = DefaultValues.getMazeWithoutCycle(new Cell[11][11]);

        // when
        MazeCreator result = new MazeEller(expected);

        // then
        assertThat(result.getMaze())
            .isEqualTo(expected);
    }
}
