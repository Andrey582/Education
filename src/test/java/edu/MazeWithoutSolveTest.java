package edu;

import edu.Creator.MazeCreator;
import edu.Creator.MazeDeepFirsSearch;
import edu.Solver.MazeSolver;
import edu.Solver.SolveDeepFirstSearch;
import edu.Solver.WallFollowerSolve;
import org.junit.jupiter.api.Test;
import java.util.Queue;
import static org.assertj.core.api.Assertions.assertThat;

public class MazeWithoutSolveTest {

    @Test
    void solveDFSWithoutSolve() {

        // given
        Cell[][] maze = DefaultValues.getMazeWithoutSolve(new Cell[11][11]);
        MazeCreator dfsMaze = new MazeDeepFirsSearch(maze);
        MazeSolver solveDeepFirstSearch = new SolveDeepFirstSearch();

        // when
        Queue<Cell> result = solveDeepFirstSearch.solve(dfsMaze, 0, 0, 4, 4);

        // then
        assertThat(result)
            .isNull();
    }

    @Test
    void solveWallFollowerWithoutSolve() {

        // given
        Cell[][] maze = DefaultValues.getMazeWithoutSolve(new Cell[11][11]);
        MazeCreator dfsMaze = new MazeDeepFirsSearch(maze);
        MazeSolver solveWallFollower = new WallFollowerSolve();

        // when
        Queue<Cell> result = solveWallFollower.solve(dfsMaze, 0, 0, 4, 4);

        // then
        assertThat(result)
            .isNull();
    }
}
