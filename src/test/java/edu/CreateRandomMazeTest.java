package edu;

import edu.Creator.MazeCreator;
import edu.Creator.MazeDeepFirsSearch;
import edu.Creator.MazeEller;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateRandomMazeTest {

    @Test
    void generateDFSMaze() {

        // given
        MazeCreator firstMaze = new MazeDeepFirsSearch(10, 10);
        MazeCreator secondMaze = new MazeDeepFirsSearch(10, 10);

        // when
        Cell[][] firstArrayMaze = firstMaze.getMaze();
        Cell[][] secondArrayMaze = secondMaze.getMaze();

        // then
        assertThat(firstArrayMaze)
            .isNotEqualTo(secondArrayMaze);
    }

    @Test
    void generateEllerMaze() {

        // given
        MazeCreator firstMaze = new MazeEller(10, 10);
        MazeCreator secondMaze = new MazeEller(10, 10);

        // when
        Cell[][] firstArrayMaze = firstMaze.getMaze();
        Cell[][] secondArrayMaze = secondMaze.getMaze();

        // then
        assertThat(firstArrayMaze)
            .isNotEqualTo(secondArrayMaze);
    }
}
