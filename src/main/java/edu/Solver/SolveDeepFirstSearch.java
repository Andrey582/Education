package edu.Solver;

import edu.Cell;
import edu.Creator.MazeCreator;
import edu.Creator.MazeEller;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class SolveDeepFirstSearch implements MazeSolver {

    private final Random random = new Random();
    private Deque<Cell> deque;
    private Cell currentCell;
    private Cell[][] maze;

    @Override
    public Queue<Cell> solve(MazeCreator maze, int startX, int startY, int endX, int endY) {
        deque = new ArrayDeque<>();
        this.maze = maze.getMaze();

        if (!isCoordinateInMaze(startX * 2 + 1, startY * 2 + 1, endX * 2 + 1, endY * 2 + 1)) {
            throw new IllegalArgumentException();
        }

        Cell next;
        Cell exit = this.maze[endY * 2 + 1][endX * 2 + 1];
        Cell start = this.maze[startY * 2 + 1][startX * 2 + 1];
        currentCell = start;

        do {
            visitCell(currentCell);

            List<Cell> neighbor = checkNeighbor();
            currentCell.setType(Cell.Type.SOlVE);

            if (neighbor.isEmpty()) {
                next = deque.pollLast();
                currentCell.setType(Cell.Type.PASSAGE);
            } else {
                if (deque.peekLast() != currentCell) {
                    deque.addLast(currentCell);
                }
                next = neighbor.get(random.nextInt(neighbor.size()));
            }
            currentCell = next;

            if (currentCell.equals(start)) {
                return null;
            }

        } while (!currentCell.equals(exit));
        deque.addLast(exit);
        exit.setType(Cell.Type.END);
        start.setType(Cell.Type.START);
        return deque;
    }

    private List<Cell> checkNeighbor() {
        List<Cell> neighbor = new ArrayList<>();
        if (checkCell(currentCell.getY(), currentCell.getX() - 1)) {
            neighbor.add(maze[currentCell.getY()][currentCell.getX() - 1]);
        }
        if (checkCell(currentCell.getY() + 1, currentCell.getX())) {
            neighbor.add(maze[currentCell.getY() + 1][currentCell.getX()]);
        }
        if (checkCell(currentCell.getY(), currentCell.getX() + 1)) {
            neighbor.add(maze[currentCell.getY()][currentCell.getX() + 1]);
        }
        if (checkCell(currentCell.getY() - 1, currentCell.getX())) {
            neighbor.add(maze[currentCell.getY() - 1][currentCell.getX()]);
        }
        return neighbor;
    }

    private boolean checkCell(int y, int x) {
        return !maze[y][x].isVisited()
            && maze[y][x].getType() != Cell.Type.WALL;
    }

    private void visitCell(Cell cell) {
        if (!cell.isVisited()) {
            deque.add(cell);
            cell.setVisited(true);
        }
    }

    @SuppressWarnings("RegexpSinglelineJava")
    public void print() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                maze[i][j].printSelf();
            }
            System.out.println();
        }
    }

    private boolean isCoordinateInMaze(int x1, int y1, int x2, int y2) {
        return x1 >= 0 && x1 < maze[0].length
        && y1 >= 0 && y1 < maze.length
        && x2 >= 0 && x2 < maze[0].length
        && y2 >= 0 && y2 < maze.length;
    }
}
