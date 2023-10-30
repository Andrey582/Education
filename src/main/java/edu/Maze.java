package edu;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;

public class Maze implements MazeCreator {

    private Random random = new Random();
    private  Cell[][] maze;
    private Cell currentCell;
    private Cell start;
    private Cell exit;
    private Deque<Cell> stack = new ArrayDeque<>();


    public static void main(String[] args) {
        Maze maze = new Maze(30, 30);
        Solve solve = new Solve();
        solve.solve(maze);
        System.out.println();
        System.out.println();
        System.out.println();
        solve.print();
    }

    public Maze(int height, int width) {
        fillMaze(height, width);
        currentCell = maze[1][1];
        generate();
        print();
    }

    @Override
    public Cell[][] generate() {
        Cell next;
        int count = 0;
        currentCell.setType(Cell.Type.START);
        start = currentCell;
        do {
            visitCell(currentCell);
            List<Cell> neighbor = checkNeighbor();

            if (stack.size() > count) {
                count = stack.size();
                exit = stack.peek();
            }

            if (neighbor.isEmpty()) {
                next = stack.poll();
            } else {
                next = neighbor.get(random.nextInt(neighbor.size()));
                breakWall(currentCell, next);
            }
            currentCell = next;
        } while (!stack.isEmpty());
        exit.setType(Cell.Type.END);
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

    public void print() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                maze[i][j].printSelf();
            }
            System.out.println();
        }
    }

    private boolean checkCell(int y, int x) {
        return y >= 1 && y <= maze.length - 2 && x >= 1 && x <= maze[0].length - 2;
    }

    private List<Cell> checkNeighbor() {
        List<Cell> neighbor = new ArrayList<>();
        if (checkCell(currentCell.getY(), currentCell.getX() - 2)
            && !maze[currentCell.getY()][currentCell.getX() - 2].isVisited()) {
            neighbor.add(maze[currentCell.getY()][currentCell.getX() - 2]);
        }
        if (checkCell(currentCell.getY() + 2, currentCell.getX())
            && !maze[currentCell.getY() + 2][currentCell.getX()].isVisited()) {
            neighbor.add(maze[currentCell.getY() + 2][currentCell.getX()]);
        }
        if (checkCell(currentCell.getY(), currentCell.getX() + 2)
            && !maze[currentCell.getY()][currentCell.getX() + 2].isVisited()) {
            neighbor.add(maze[currentCell.getY()][currentCell.getX() + 2]);
        }
        if (checkCell(currentCell.getY() - 2, currentCell.getX())
            && !maze[currentCell.getY() - 2][currentCell.getX()].isVisited()) {
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
        }
        cell.setVisited(true);
    }

    public Cell getStart() {
        return start;
    }

    public Cell getExit() {
        return exit;
    }

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
