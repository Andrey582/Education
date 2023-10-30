package edu;

import java.util.Deque;
import java.util.Queue;
import java.util.Stack;

public interface MazeSolver {

    public Queue<Cell> solve(Maze maze);
}
