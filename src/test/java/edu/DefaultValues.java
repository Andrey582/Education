package edu;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class DefaultValues {

    public static Cell[][] getMazeWithoutCycle(Cell[][] maze) {
        return List.of(
            List.of(
                new Cell(Cell.Type.WALL, 0, 0),
                new Cell(Cell.Type.WALL, 1, 0),
                new Cell(Cell.Type.WALL, 2, 0),
                new Cell(Cell.Type.WALL, 3, 0),
                new Cell(Cell.Type.WALL, 4, 0),
                new Cell(Cell.Type.WALL, 5, 0),
                new Cell(Cell.Type.WALL, 6, 0),
                new Cell(Cell.Type.WALL, 7, 0),
                new Cell(Cell.Type.WALL, 8, 0),
                new Cell(Cell.Type.WALL, 9, 0),
                new Cell(Cell.Type.WALL, 10, 0)
            ).toArray(maze[0]),
            List.of(
                new Cell(Cell.Type.WALL, 0, 1),
                new Cell(Cell.Type.PASSAGE, 1, 1),
                new Cell(Cell.Type.PASSAGE, 2, 1),
                new Cell(Cell.Type.PASSAGE, 3, 1),
                new Cell(Cell.Type.WALL, 4, 1),
                new Cell(Cell.Type.PASSAGE, 5, 1),
                new Cell(Cell.Type.PASSAGE, 6, 1),
                new Cell(Cell.Type.PASSAGE, 7, 1),
                new Cell(Cell.Type.WALL, 8, 1),
                new Cell(Cell.Type.PASSAGE, 9, 1),
                new Cell(Cell.Type.WALL, 10, 1)
            ).toArray(maze[1]),
            List.of(
                new Cell(Cell.Type.WALL, 0, 2),
                new Cell(Cell.Type.WALL, 1, 2),
                new Cell(Cell.Type.WALL, 2, 2),
                new Cell(Cell.Type.PASSAGE, 3, 2),
                new Cell(Cell.Type.WALL, 4, 2),
                new Cell(Cell.Type.WALL, 5, 2),
                new Cell(Cell.Type.WALL, 6, 2),
                new Cell(Cell.Type.PASSAGE, 7, 2),
                new Cell(Cell.Type.WALL, 8, 2),
                new Cell(Cell.Type.PASSAGE, 9, 2),
                new Cell(Cell.Type.WALL, 10, 2)
            ).toArray(maze[2]),
            List.of(
                new Cell(Cell.Type.WALL, 0, 3),
                new Cell(Cell.Type.PASSAGE, 1, 3),
                new Cell(Cell.Type.PASSAGE, 2, 3),
                new Cell(Cell.Type.PASSAGE, 3, 3),
                new Cell(Cell.Type.PASSAGE, 4, 3),
                new Cell(Cell.Type.PASSAGE, 5, 3),
                new Cell(Cell.Type.PASSAGE, 6, 3),
                new Cell(Cell.Type.PASSAGE, 7, 3),
                new Cell(Cell.Type.WALL, 8, 3),
                new Cell(Cell.Type.PASSAGE, 9, 3),
                new Cell(Cell.Type.WALL, 10, 3)
            ).toArray(maze[3]),
            List.of(
                new Cell(Cell.Type.WALL, 0, 4),
                new Cell(Cell.Type.PASSAGE, 1, 4),
                new Cell(Cell.Type.WALL, 2, 4),
                new Cell(Cell.Type.PASSAGE, 3, 4),
                new Cell(Cell.Type.WALL, 4, 4),
                new Cell(Cell.Type.WALL, 5, 4),
                new Cell(Cell.Type.WALL, 6, 4),
                new Cell(Cell.Type.PASSAGE, 7, 4),
                new Cell(Cell.Type.WALL, 8, 4),
                new Cell(Cell.Type.PASSAGE, 9, 4),
                new Cell(Cell.Type.WALL, 10, 4)
            ).toArray(maze[4]),
            List.of(
                new Cell(Cell.Type.WALL, 0, 5),
                new Cell(Cell.Type.PASSAGE, 1, 5),
                new Cell(Cell.Type.WALL, 2, 5),
                new Cell(Cell.Type.PASSAGE, 3, 5),
                new Cell(Cell.Type.PASSAGE, 4, 5),
                new Cell(Cell.Type.PASSAGE, 5, 5),
                new Cell(Cell.Type.WALL, 6, 5),
                new Cell(Cell.Type.PASSAGE, 7, 5),
                new Cell(Cell.Type.PASSAGE, 8, 5),
                new Cell(Cell.Type.PASSAGE, 9, 5),
                new Cell(Cell.Type.WALL, 10, 5)
            ).toArray(maze[5]),
            List.of(
                new Cell(Cell.Type.WALL, 0, 6),
                new Cell(Cell.Type.WALL, 1, 6),
                new Cell(Cell.Type.WALL, 2, 6),
                new Cell(Cell.Type.PASSAGE, 3, 6),
                new Cell(Cell.Type.WALL, 4, 6),
                new Cell(Cell.Type.PASSAGE, 5, 6),
                new Cell(Cell.Type.WALL, 6, 6),
                new Cell(Cell.Type.PASSAGE, 7, 6),
                new Cell(Cell.Type.WALL, 8, 6),
                new Cell(Cell.Type.PASSAGE, 9, 6),
                new Cell(Cell.Type.WALL, 10, 6)
            ).toArray(maze[6]),
            List.of(
                new Cell(Cell.Type.WALL, 0, 7),
                new Cell(Cell.Type.PASSAGE, 1, 7),
                new Cell(Cell.Type.PASSAGE, 2, 7),
                new Cell(Cell.Type.PASSAGE, 3, 7),
                new Cell(Cell.Type.WALL, 4, 7),
                new Cell(Cell.Type.PASSAGE, 5, 7),
                new Cell(Cell.Type.WALL, 6, 7),
                new Cell(Cell.Type.PASSAGE, 7, 7),
                new Cell(Cell.Type.WALL, 8, 7),
                new Cell(Cell.Type.PASSAGE, 9, 7),
                new Cell(Cell.Type.WALL, 10, 7)
            ).toArray(maze[7]),
            List.of(
                new Cell(Cell.Type.WALL, 0, 8),
                new Cell(Cell.Type.PASSAGE, 1, 8),
                new Cell(Cell.Type.WALL, 2, 8),
                new Cell(Cell.Type.WALL, 3, 8),
                new Cell(Cell.Type.WALL, 4, 8),
                new Cell(Cell.Type.WALL, 5, 8),
                new Cell(Cell.Type.WALL, 6, 8),
                new Cell(Cell.Type.PASSAGE, 7, 8),
                new Cell(Cell.Type.WALL, 8, 8),
                new Cell(Cell.Type.PASSAGE, 9, 8),
                new Cell(Cell.Type.WALL, 10, 8)
            ).toArray(maze[8]),
            List.of(
                new Cell(Cell.Type.WALL, 0, 9),
                new Cell(Cell.Type.PASSAGE, 1, 9),
                new Cell(Cell.Type.PASSAGE, 2, 9),
                new Cell(Cell.Type.PASSAGE, 3, 9),
                new Cell(Cell.Type.PASSAGE, 4, 9),
                new Cell(Cell.Type.PASSAGE, 5, 9),
                new Cell(Cell.Type.WALL, 6, 9),
                new Cell(Cell.Type.PASSAGE, 7, 9),
                new Cell(Cell.Type.WALL, 8, 9),
                new Cell(Cell.Type.PASSAGE, 9, 9),
                new Cell(Cell.Type.WALL, 10, 9)
            ).toArray(maze[9]),
            List.of(
                new Cell(Cell.Type.WALL, 0, 10),
                new Cell(Cell.Type.WALL, 1, 10),
                new Cell(Cell.Type.WALL, 2, 10),
                new Cell(Cell.Type.WALL, 3, 10),
                new Cell(Cell.Type.WALL, 4, 10),
                new Cell(Cell.Type.WALL, 5, 10),
                new Cell(Cell.Type.WALL, 6, 10),
                new Cell(Cell.Type.WALL, 7, 10),
                new Cell(Cell.Type.WALL, 8, 10),
                new Cell(Cell.Type.WALL, 9, 10),
                new Cell(Cell.Type.WALL, 10, 10)
            ).toArray(maze[10])).toArray(maze);
    }

    public static Cell[][] getMazeWithCycle(Cell[][] maze) {
        return List.of(
            List.of(
                new Cell(Cell.Type.WALL, 0, 0),
                new Cell(Cell.Type.WALL, 1, 0),
                new Cell(Cell.Type.WALL, 2, 0),
                new Cell(Cell.Type.WALL, 3, 0),
                new Cell(Cell.Type.WALL, 4, 0),
                new Cell(Cell.Type.WALL, 5, 0),
                new Cell(Cell.Type.WALL, 6, 0),
                new Cell(Cell.Type.WALL, 7, 0),
                new Cell(Cell.Type.WALL, 8, 0),
                new Cell(Cell.Type.WALL, 9, 0),
                new Cell(Cell.Type.WALL, 10, 0)
            ).toArray(maze[0]),
            List.of(
                new Cell(Cell.Type.WALL, 0, 1),
                new Cell(Cell.Type.PASSAGE, 1, 1),
                new Cell(Cell.Type.PASSAGE, 2, 1),
                new Cell(Cell.Type.PASSAGE, 3, 1),
                new Cell(Cell.Type.PASSAGE, 4, 1),
                new Cell(Cell.Type.PASSAGE, 5, 1),
                new Cell(Cell.Type.PASSAGE, 6, 1),
                new Cell(Cell.Type.PASSAGE, 7, 1),
                new Cell(Cell.Type.WALL, 8, 1),
                new Cell(Cell.Type.PASSAGE, 9, 1),
                new Cell(Cell.Type.WALL, 10, 1)
            ).toArray(maze[1]),
            List.of(
                new Cell(Cell.Type.WALL, 0, 2),
                new Cell(Cell.Type.WALL, 1, 2),
                new Cell(Cell.Type.WALL, 2, 2),
                new Cell(Cell.Type.PASSAGE, 3, 2),
                new Cell(Cell.Type.WALL, 4, 2),
                new Cell(Cell.Type.WALL, 5, 2),
                new Cell(Cell.Type.WALL, 6, 2),
                new Cell(Cell.Type.PASSAGE, 7, 2),
                new Cell(Cell.Type.WALL, 8, 2),
                new Cell(Cell.Type.PASSAGE, 9, 2),
                new Cell(Cell.Type.WALL, 10, 2)
            ).toArray(maze[2]),
            List.of(
                new Cell(Cell.Type.WALL, 0, 3),
                new Cell(Cell.Type.PASSAGE, 1, 3),
                new Cell(Cell.Type.PASSAGE, 2, 3),
                new Cell(Cell.Type.PASSAGE, 3, 3),
                new Cell(Cell.Type.PASSAGE, 4, 3),
                new Cell(Cell.Type.PASSAGE, 5, 3),
                new Cell(Cell.Type.PASSAGE, 6, 3),
                new Cell(Cell.Type.PASSAGE, 7, 3),
                new Cell(Cell.Type.WALL, 8, 3),
                new Cell(Cell.Type.PASSAGE, 9, 3),
                new Cell(Cell.Type.WALL, 10, 3)
            ).toArray(maze[3]),
            List.of(
                new Cell(Cell.Type.WALL, 0, 4),
                new Cell(Cell.Type.PASSAGE, 1, 4),
                new Cell(Cell.Type.WALL, 2, 4),
                new Cell(Cell.Type.PASSAGE, 3, 4),
                new Cell(Cell.Type.WALL, 4, 4),
                new Cell(Cell.Type.WALL, 5, 4),
                new Cell(Cell.Type.WALL, 6, 4),
                new Cell(Cell.Type.PASSAGE, 7, 4),
                new Cell(Cell.Type.WALL, 8, 4),
                new Cell(Cell.Type.PASSAGE, 9, 4),
                new Cell(Cell.Type.WALL, 10, 4)
            ).toArray(maze[4]),
            List.of(
                new Cell(Cell.Type.WALL, 0, 5),
                new Cell(Cell.Type.PASSAGE, 1, 5),
                new Cell(Cell.Type.WALL, 2, 5),
                new Cell(Cell.Type.PASSAGE, 3, 5),
                new Cell(Cell.Type.PASSAGE, 4, 5),
                new Cell(Cell.Type.PASSAGE, 5, 5),
                new Cell(Cell.Type.WALL, 6, 5),
                new Cell(Cell.Type.PASSAGE, 7, 5),
                new Cell(Cell.Type.PASSAGE, 8, 5),
                new Cell(Cell.Type.PASSAGE, 9, 5),
                new Cell(Cell.Type.WALL, 10, 5)
            ).toArray(maze[5]),
            List.of(
                new Cell(Cell.Type.WALL, 0, 6),
                new Cell(Cell.Type.WALL, 1, 6),
                new Cell(Cell.Type.WALL, 2, 6),
                new Cell(Cell.Type.PASSAGE, 3, 6),
                new Cell(Cell.Type.WALL, 4, 6),
                new Cell(Cell.Type.PASSAGE, 5, 6),
                new Cell(Cell.Type.WALL, 6, 6),
                new Cell(Cell.Type.PASSAGE, 7, 6),
                new Cell(Cell.Type.WALL, 8, 6),
                new Cell(Cell.Type.PASSAGE, 9, 6),
                new Cell(Cell.Type.WALL, 10, 6)
            ).toArray(maze[6]),
            List.of(
                new Cell(Cell.Type.WALL, 0, 7),
                new Cell(Cell.Type.PASSAGE, 1, 7),
                new Cell(Cell.Type.PASSAGE, 2, 7),
                new Cell(Cell.Type.PASSAGE, 3, 7),
                new Cell(Cell.Type.WALL, 4, 7),
                new Cell(Cell.Type.PASSAGE, 5, 7),
                new Cell(Cell.Type.WALL, 6, 7),
                new Cell(Cell.Type.PASSAGE, 7, 7),
                new Cell(Cell.Type.WALL, 8, 7),
                new Cell(Cell.Type.PASSAGE, 9, 7),
                new Cell(Cell.Type.WALL, 10, 7)
            ).toArray(maze[7]),
            List.of(
                new Cell(Cell.Type.WALL, 0, 8),
                new Cell(Cell.Type.PASSAGE, 1, 8),
                new Cell(Cell.Type.WALL, 2, 8),
                new Cell(Cell.Type.WALL, 3, 8),
                new Cell(Cell.Type.WALL, 4, 8),
                new Cell(Cell.Type.WALL, 5, 8),
                new Cell(Cell.Type.WALL, 6, 8),
                new Cell(Cell.Type.PASSAGE, 7, 8),
                new Cell(Cell.Type.WALL, 8, 8),
                new Cell(Cell.Type.PASSAGE, 9, 8),
                new Cell(Cell.Type.WALL, 10, 8)
            ).toArray(maze[8]),
            List.of(
                new Cell(Cell.Type.WALL, 0, 9),
                new Cell(Cell.Type.PASSAGE, 1, 9),
                new Cell(Cell.Type.PASSAGE, 2, 9),
                new Cell(Cell.Type.PASSAGE, 3, 9),
                new Cell(Cell.Type.PASSAGE, 4, 9),
                new Cell(Cell.Type.PASSAGE, 5, 9),
                new Cell(Cell.Type.WALL, 6, 9),
                new Cell(Cell.Type.PASSAGE, 7, 9),
                new Cell(Cell.Type.WALL, 8, 9),
                new Cell(Cell.Type.PASSAGE, 9, 9),
                new Cell(Cell.Type.WALL, 10, 9)
            ).toArray(maze[9]),
            List.of(
                new Cell(Cell.Type.WALL, 0, 10),
                new Cell(Cell.Type.WALL, 1, 10),
                new Cell(Cell.Type.WALL, 2, 10),
                new Cell(Cell.Type.WALL, 3, 10),
                new Cell(Cell.Type.WALL, 4, 10),
                new Cell(Cell.Type.WALL, 5, 10),
                new Cell(Cell.Type.WALL, 6, 10),
                new Cell(Cell.Type.WALL, 7, 10),
                new Cell(Cell.Type.WALL, 8, 10),
                new Cell(Cell.Type.WALL, 9, 10),
                new Cell(Cell.Type.WALL, 10, 10)
            ).toArray(maze[10])).toArray(maze);
    }

    public static Cell[][] getMazeWithoutSolve(Cell[][] maze) {
        return List.of(
            List.of(
                new Cell(Cell.Type.WALL, 0, 0),
                new Cell(Cell.Type.WALL, 1, 0),
                new Cell(Cell.Type.WALL, 2, 0),
                new Cell(Cell.Type.WALL, 3, 0),
                new Cell(Cell.Type.WALL, 4, 0),
                new Cell(Cell.Type.WALL, 5, 0),
                new Cell(Cell.Type.WALL, 6, 0),
                new Cell(Cell.Type.WALL, 7, 0),
                new Cell(Cell.Type.WALL, 8, 0),
                new Cell(Cell.Type.WALL, 9, 0),
                new Cell(Cell.Type.WALL, 10, 0)
            ).toArray(maze[0]),
            List.of(
                new Cell(Cell.Type.WALL, 0, 1),
                new Cell(Cell.Type.PASSAGE, 1, 1),
                new Cell(Cell.Type.PASSAGE, 2, 1),
                new Cell(Cell.Type.WALL, 3, 1),
                new Cell(Cell.Type.PASSAGE, 4, 1),
                new Cell(Cell.Type.PASSAGE, 5, 1),
                new Cell(Cell.Type.PASSAGE, 6, 1),
                new Cell(Cell.Type.PASSAGE, 7, 1),
                new Cell(Cell.Type.WALL, 8, 1),
                new Cell(Cell.Type.PASSAGE, 9, 1),
                new Cell(Cell.Type.WALL, 10, 1)
            ).toArray(maze[1]),
            List.of(
                new Cell(Cell.Type.WALL, 0, 2),
                new Cell(Cell.Type.WALL, 1, 2),
                new Cell(Cell.Type.WALL, 2, 2),
                new Cell(Cell.Type.PASSAGE, 3, 2),
                new Cell(Cell.Type.WALL, 4, 2),
                new Cell(Cell.Type.WALL, 5, 2),
                new Cell(Cell.Type.WALL, 6, 2),
                new Cell(Cell.Type.PASSAGE, 7, 2),
                new Cell(Cell.Type.WALL, 8, 2),
                new Cell(Cell.Type.PASSAGE, 9, 2),
                new Cell(Cell.Type.WALL, 10, 2)
            ).toArray(maze[2]),
            List.of(
                new Cell(Cell.Type.WALL, 0, 3),
                new Cell(Cell.Type.PASSAGE, 1, 3),
                new Cell(Cell.Type.PASSAGE, 2, 3),
                new Cell(Cell.Type.PASSAGE, 3, 3),
                new Cell(Cell.Type.PASSAGE, 4, 3),
                new Cell(Cell.Type.PASSAGE, 5, 3),
                new Cell(Cell.Type.PASSAGE, 6, 3),
                new Cell(Cell.Type.PASSAGE, 7, 3),
                new Cell(Cell.Type.WALL, 8, 3),
                new Cell(Cell.Type.PASSAGE, 9, 3),
                new Cell(Cell.Type.WALL, 10, 3)
            ).toArray(maze[3]),
            List.of(
                new Cell(Cell.Type.WALL, 0, 4),
                new Cell(Cell.Type.PASSAGE, 1, 4),
                new Cell(Cell.Type.WALL, 2, 4),
                new Cell(Cell.Type.PASSAGE, 3, 4),
                new Cell(Cell.Type.WALL, 4, 4),
                new Cell(Cell.Type.WALL, 5, 4),
                new Cell(Cell.Type.WALL, 6, 4),
                new Cell(Cell.Type.PASSAGE, 7, 4),
                new Cell(Cell.Type.WALL, 8, 4),
                new Cell(Cell.Type.PASSAGE, 9, 4),
                new Cell(Cell.Type.WALL, 10, 4)
            ).toArray(maze[4]),
            List.of(
                new Cell(Cell.Type.WALL, 0, 5),
                new Cell(Cell.Type.PASSAGE, 1, 5),
                new Cell(Cell.Type.WALL, 2, 5),
                new Cell(Cell.Type.PASSAGE, 3, 5),
                new Cell(Cell.Type.PASSAGE, 4, 5),
                new Cell(Cell.Type.PASSAGE, 5, 5),
                new Cell(Cell.Type.WALL, 6, 5),
                new Cell(Cell.Type.PASSAGE, 7, 5),
                new Cell(Cell.Type.PASSAGE, 8, 5),
                new Cell(Cell.Type.PASSAGE, 9, 5),
                new Cell(Cell.Type.WALL, 10, 5)
            ).toArray(maze[5]),
            List.of(
                new Cell(Cell.Type.WALL, 0, 6),
                new Cell(Cell.Type.WALL, 1, 6),
                new Cell(Cell.Type.WALL, 2, 6),
                new Cell(Cell.Type.PASSAGE, 3, 6),
                new Cell(Cell.Type.WALL, 4, 6),
                new Cell(Cell.Type.PASSAGE, 5, 6),
                new Cell(Cell.Type.WALL, 6, 6),
                new Cell(Cell.Type.PASSAGE, 7, 6),
                new Cell(Cell.Type.WALL, 8, 6),
                new Cell(Cell.Type.PASSAGE, 9, 6),
                new Cell(Cell.Type.WALL, 10, 6)
            ).toArray(maze[6]),
            List.of(
                new Cell(Cell.Type.WALL, 0, 7),
                new Cell(Cell.Type.PASSAGE, 1, 7),
                new Cell(Cell.Type.PASSAGE, 2, 7),
                new Cell(Cell.Type.PASSAGE, 3, 7),
                new Cell(Cell.Type.WALL, 4, 7),
                new Cell(Cell.Type.PASSAGE, 5, 7),
                new Cell(Cell.Type.WALL, 6, 7),
                new Cell(Cell.Type.PASSAGE, 7, 7),
                new Cell(Cell.Type.WALL, 8, 7),
                new Cell(Cell.Type.PASSAGE, 9, 7),
                new Cell(Cell.Type.WALL, 10, 7)
            ).toArray(maze[7]),
            List.of(
                new Cell(Cell.Type.WALL, 0, 8),
                new Cell(Cell.Type.PASSAGE, 1, 8),
                new Cell(Cell.Type.WALL, 2, 8),
                new Cell(Cell.Type.WALL, 3, 8),
                new Cell(Cell.Type.WALL, 4, 8),
                new Cell(Cell.Type.WALL, 5, 8),
                new Cell(Cell.Type.WALL, 6, 8),
                new Cell(Cell.Type.PASSAGE, 7, 8),
                new Cell(Cell.Type.WALL, 8, 8),
                new Cell(Cell.Type.PASSAGE, 9, 8),
                new Cell(Cell.Type.WALL, 10, 8)
            ).toArray(maze[8]),
            List.of(
                new Cell(Cell.Type.WALL, 0, 9),
                new Cell(Cell.Type.PASSAGE, 1, 9),
                new Cell(Cell.Type.PASSAGE, 2, 9),
                new Cell(Cell.Type.PASSAGE, 3, 9),
                new Cell(Cell.Type.PASSAGE, 4, 9),
                new Cell(Cell.Type.PASSAGE, 5, 9),
                new Cell(Cell.Type.WALL, 6, 9),
                new Cell(Cell.Type.PASSAGE, 7, 9),
                new Cell(Cell.Type.WALL, 8, 9),
                new Cell(Cell.Type.PASSAGE, 9, 9),
                new Cell(Cell.Type.WALL, 10, 9)
            ).toArray(maze[9]),
            List.of(
                new Cell(Cell.Type.WALL, 0, 10),
                new Cell(Cell.Type.WALL, 1, 10),
                new Cell(Cell.Type.WALL, 2, 10),
                new Cell(Cell.Type.WALL, 3, 10),
                new Cell(Cell.Type.WALL, 4, 10),
                new Cell(Cell.Type.WALL, 5, 10),
                new Cell(Cell.Type.WALL, 6, 10),
                new Cell(Cell.Type.WALL, 7, 10),
                new Cell(Cell.Type.WALL, 8, 10),
                new Cell(Cell.Type.WALL, 9, 10),
                new Cell(Cell.Type.WALL, 10, 10)
            ).toArray(maze[10])).toArray(maze);
    }

    public static Queue<Cell> getFirstSolve() {
        Queue<Cell> result = new ArrayDeque<>();
        result.add(new Cell(Cell.Type.START, 1, 1));
        result.add(new Cell(Cell.Type.SOlVE, 2, 1));
        result.add(new Cell(Cell.Type.SOlVE, 3, 1));
        result.add(new Cell(Cell.Type.SOlVE, 3, 2));
        result.add(new Cell(Cell.Type.SOlVE, 3, 3));
        result.add(new Cell(Cell.Type.SOlVE, 4, 3));
        result.add(new Cell(Cell.Type.SOlVE, 5, 3));
        result.add(new Cell(Cell.Type.SOlVE, 6, 3));
        result.add(new Cell(Cell.Type.SOlVE, 7, 3));
        result.add(new Cell(Cell.Type.SOlVE, 7, 4));
        result.add(new Cell(Cell.Type.SOlVE, 7, 5));
        result.add(new Cell(Cell.Type.SOlVE, 8, 5));
        result.add(new Cell(Cell.Type.SOlVE, 9, 5));
        result.add(new Cell(Cell.Type.SOlVE, 9, 6));
        result.add(new Cell(Cell.Type.SOlVE, 9, 7));
        result.add(new Cell(Cell.Type.SOlVE, 9, 8));
        result.add(new Cell(Cell.Type.END, 9, 9));
        return result;
    }

    public static Queue<Cell> getSecondSolve() {
        Queue<Cell> result = new ArrayDeque<>();
        result.add(new Cell(Cell.Type.START, 1, 1));
        result.add(new Cell(Cell.Type.SOlVE, 2, 1));
        result.add(new Cell(Cell.Type.SOlVE, 3, 1));
        result.add(new Cell(Cell.Type.SOlVE, 4, 1));
        result.add(new Cell(Cell.Type.SOlVE, 5, 1));
        result.add(new Cell(Cell.Type.SOlVE, 6, 1));
        result.add(new Cell(Cell.Type.SOlVE, 7, 1));
        result.add(new Cell(Cell.Type.SOlVE, 7, 2));
        result.add(new Cell(Cell.Type.SOlVE, 7, 3));
        result.add(new Cell(Cell.Type.SOlVE, 7, 4));
        result.add(new Cell(Cell.Type.SOlVE, 7, 5));
        result.add(new Cell(Cell.Type.SOlVE, 8, 5));
        result.add(new Cell(Cell.Type.SOlVE, 9, 5));
        result.add(new Cell(Cell.Type.SOlVE, 9, 6));
        result.add(new Cell(Cell.Type.SOlVE, 9, 7));
        result.add(new Cell(Cell.Type.SOlVE, 9, 8));
        result.add(new Cell(Cell.Type.END, 9, 9));
        return result;
    }
}
