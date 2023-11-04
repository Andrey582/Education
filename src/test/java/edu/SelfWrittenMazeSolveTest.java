package edu;

import edu.Creator.MazeCreator;
import edu.Creator.MazeDeepFirsSearch;
import edu.Creator.MazeEller;
import edu.Solver.MazeSolver;
import edu.Solver.SolveDeepFirstSearch;
import edu.Solver.WallFollowerSolve;
import java.util.Queue;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SelfWrittenMazeSolveTest {

    @Test
    void solveDFSMaze() {

        // given
        Cell[][] maze = DefaultValues.getMazeWithoutCycle(new Cell[11][11]);
        MazeCreator dfsMaze = new MazeDeepFirsSearch(maze);
        MazeSolver solveDeepFirstSearch = new SolveDeepFirstSearch();
        boolean isEquals = true;

        // when
        Queue<Cell> result = solveDeepFirstSearch.solve(dfsMaze, 0, 0, 4, 4);
        Queue<Cell> expected = DefaultValues.getFirstSolve();

        while (!result.isEmpty()) {
            if (!result.poll().equals(expected.poll())) {
                isEquals = false;
                break;
            }
        }

        isEquals = expected.isEmpty() ? isEquals : false;

        // then
        assertThat(isEquals)
            .isTrue();
    }


    @Test
    void solveWallFollowerTest() {

        // given
        Cell[][] maze = DefaultValues.getMazeWithoutCycle(new Cell[11][11]);
        MazeCreator dfsMaze = new MazeEller(maze);
        MazeSolver solveWallFollower = new WallFollowerSolve();
        boolean isEquals = true;

        // when
        Queue<Cell> result = solveWallFollower.solve(dfsMaze, 0, 0, 4, 4);
        Queue<Cell> expected = DefaultValues.getFirstSolve();

        while (!result.isEmpty()) {
            if (!result.poll().equals(expected.poll())) {
                isEquals = false;
                break;
            }
        }

        isEquals = expected.isEmpty() ? isEquals : false;

        // then
        assertThat(isEquals)
            .isTrue();
    }


}
