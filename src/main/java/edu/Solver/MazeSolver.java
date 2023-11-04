package edu.Solver;

import edu.Cell;
import edu.Creator.MazeCreator;
import java.util.Queue;

public interface MazeSolver {

    Queue<Cell> solve(MazeCreator maze, int startX, int startY, int endX, int endY);
}
