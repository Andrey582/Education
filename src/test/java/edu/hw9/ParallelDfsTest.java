package edu.hw9;

import edu.Cell;
import edu.Creator.MazeCreator;
import edu.Creator.MazeDeepFirsSearch;
import edu.Solver.MazeSolver;
import edu.Solver.SolveDeepFirstSearch;
import edu.hw9.Task3.ParallelDfs;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import java.util.Queue;
import java.util.concurrent.ForkJoinPool;

import static org.assertj.core.api.Assertions.assertThat;

public class ParallelDfsTest {

    @Test
    @RepeatedTest(10)
    void dfsTest() {
        MazeCreator mazeCreator = new MazeDeepFirsSearch(5, 5);
        mazeCreator.generate();
        MazeSolver solver = new SolveDeepFirstSearch();
        Queue<Cell> expected = solver.solve(mazeCreator, 0, 0, 4, 4);
        mazeCreator.clean();
        ParallelDfs dfs = new ParallelDfs(mazeCreator.getMaze(), 1, 1, 9, 9);
        Queue<Cell> result;
        try (ForkJoinPool pool = new ForkJoinPool()) {
            result = pool.invoke(dfs);
        }

        assertThat(result)
            .containsAll(expected);

    }
}
