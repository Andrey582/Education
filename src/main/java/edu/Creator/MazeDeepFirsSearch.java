package edu.Creator;

import edu.Cell;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;

public class MazeDeepFirsSearch implements MazeCreator {

    private final static Random RANDOM = new Random();
    private final Deque<Cell> stack = new ArrayDeque<>();

    private  Cell[][] maze;
    private Cell currentCell;
    private int height;
    private int width;

    public MazeDeepFirsSearch(Cell[][] maze) {
        this.maze = maze;
    }

    public MazeDeepFirsSearch(int height, int width) throws IllegalArgumentException {
        if (height < 1 || width < 1) {
            throw new IllegalArgumentException("height and width need be greater than 1");
        }
        this.height = height;
        this.width = width;
        generate();
    }

    @Override
    public Cell[][] generate() {
        fillMaze(height, width);
        currentCell = maze[1][1];
        Cell next;

        do {
            visitCell(currentCell);
            List<Cell> neighbor = checkNeighbor();

            if (neighbor.isEmpty()) {
                next = stack.poll();
            } else {
                next = neighbor.get(RANDOM.nextInt(neighbor.size()));
                breakWall(currentCell, next);
            }
            currentCell = next;

        } while (!stack.isEmpty());

        clearVisited();
        return maze;
    }

    @Override
    public void fillMaze(int height, int width) {
        maze = new Cell[height * 2 + 1][width * 2 + 1];
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (i == 0 || i == maze.length - 1 || j == 0 || j == maze[0].length - 1) {
                    maze[i][j] = new Cell(Cell.Type.WALL, j, i);
                } else if (i % 2 == j % 2 && i % 2 == 1) {
                    maze[i][j] = new Cell(Cell.Type.PASSAGE, j, i);
                } else {
                    maze[i][j] = new Cell(Cell.Type.WALL, j, i);
                }
            }
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

    @Override
    public void clean() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                maze[i][j].setVisited(false);
            }
        }
    }

    private boolean checkCell(int y, int x) {
        return y >= 1
            && y <= maze.length - 2
            && x >= 1
            && x <= maze[0].length - 2
            && !maze[y][x].isVisited();
    }

    private List<Cell> checkNeighbor() {
        List<Cell> neighbor = new ArrayList<>();
        if (checkCell(currentCell.getY(), currentCell.getX() - 2)) {
            neighbor.add(maze[currentCell.getY()][currentCell.getX() - 2]);
        }
        if (checkCell(currentCell.getY() + 2, currentCell.getX())) {
            neighbor.add(maze[currentCell.getY() + 2][currentCell.getX()]);
        }
        if (checkCell(currentCell.getY(), currentCell.getX() + 2)) {
            neighbor.add(maze[currentCell.getY()][currentCell.getX() + 2]);
        }
        if (checkCell(currentCell.getY() - 2, currentCell.getX())) {
            neighbor.add(maze[currentCell.getY() - 2][currentCell.getX()]);
        }
        return neighbor;
    }

    private void breakWall(Cell first, Cell second) {
        if (first.getX() == second.getX()) {
            if (first.getY() > second.getY()) {
                maze[first.getY() - 1][first.getX()].setType(Cell.Type.PASSAGE);
            } else {
                maze[second.getY() - 1][second.getX()].setType(Cell.Type.PASSAGE);
            }
        } else {
            if (first.getX() > second.getX()) {
                maze[first.getY()][first.getX() - 1].setType(Cell.Type.PASSAGE);
            } else {
                maze[second.getY()][second.getX() - 1].setType(Cell.Type.PASSAGE);
            }
        }
    }

    private void visitCell(Cell cell) {
        if (!cell.isVisited()) {
            stack.addFirst(cell);
            cell.setVisited(true);
        }
    }

    @Override
    public Cell[][] getMaze() {
        return maze;
    }

    public void clearVisited() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                maze[i][j].setVisited(false);
            }
        }
    }
}
