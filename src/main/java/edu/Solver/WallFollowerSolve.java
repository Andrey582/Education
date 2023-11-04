package edu.Solver;

import edu.Cell;
import edu.Creator.MazeCreator;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class WallFollowerSolve implements MazeSolver {

    private Cell[][] maze;
    private Direction currentDirection = Direction.RIGHT;
    private Cell currentCell;
    private Deque<Cell> deque;

    @Override
    public Queue<Cell> solve(MazeCreator maze, int startX, int startY, int endX, int endY)
        throws IllegalArgumentException {

        this.maze = maze.getMaze();
        if (!isCoordinateInMaze(startX * 2 + 1, startY * 2 + 1, endX * 2 + 1, endY * 2 + 1)) {
            throw new IllegalArgumentException("Coordinates must be greater than 0 and less than height/width");
        }

        deque = new ArrayDeque<>();

        Cell start = this.maze[startY * 2 + 1][startX * 2 + 1];
        Cell exit = this.maze[endY * 2 + 1][endX * 2 + 1];

        currentCell = start;

        while (!currentCell.equals(exit)) {

            if (canRotateRight()) {
                rightRotate();
            } else if (!checkForwardCell() && canRotateLeft()) {
                leftRotate();
            } else if (!checkForwardCell()) {
                rightRotate();
                rightRotate();
                currentCell.setVisited(true);
                deque.addLast(currentCell);
            }

            visitCell(currentCell);

            currentCell = moveForward();

            if (currentCell.equals(start)) {
                return null;
            }
        }
        deque.addLast(exit);
        start.setType(Cell.Type.START);
        exit.setType(Cell.Type.END);

        return deque;
    }

    private void rightRotate() {
        currentDirection = switch (currentDirection) {
            case UP -> Direction.RIGHT;
            case RIGHT -> Direction.DOWN;
            case DOWN -> Direction.LEFT;
            case LEFT ->  Direction.UP;
        };
    }

    private void leftRotate() {
        currentDirection = switch (currentDirection) {
            case UP -> Direction.LEFT;
            case LEFT -> Direction.DOWN;
            case DOWN -> Direction.RIGHT;
            case RIGHT ->  Direction.UP;
        };
    }

    private boolean canRotateRight() {
        return switch (currentDirection) {
            case UP -> maze[currentCell.getY()][currentCell.getX() + 1].getType() != Cell.Type.WALL;
            case RIGHT -> maze[currentCell.getY() + 1][currentCell.getX()].getType() != Cell.Type.WALL;
            case DOWN -> maze[currentCell.getY()][currentCell.getX() - 1].getType() != Cell.Type.WALL;
            case LEFT -> maze[currentCell.getY() - 1][currentCell.getX()].getType() != Cell.Type.WALL;
        };
    }

    private boolean canRotateLeft() {
        return switch (currentDirection) {
            case UP -> maze[currentCell.getY()][currentCell.getX() - 1].getType() != Cell.Type.WALL;
            case RIGHT -> maze[currentCell.getY() - 1][currentCell.getX()].getType() != Cell.Type.WALL;
            case DOWN -> maze[currentCell.getY()][currentCell.getX() + 1].getType() != Cell.Type.WALL;
            case LEFT -> maze[currentCell.getY() + 1][currentCell.getX()].getType() != Cell.Type.WALL;
        };
    }

    private boolean checkForwardCell() {
        return switch (currentDirection) {
            case UP -> maze[currentCell.getY() - 1][currentCell.getX()].getType() != Cell.Type.WALL;
            case RIGHT -> maze[currentCell.getY()][currentCell.getX() + 1].getType() != Cell.Type.WALL;
            case DOWN -> maze[currentCell.getY() + 1][currentCell.getX()].getType() != Cell.Type.WALL;
            case LEFT -> maze[currentCell.getY()][currentCell.getX() - 1].getType() != Cell.Type.WALL;
        };
    }

    private Cell moveForward() {
        return switch (currentDirection) {
            case UP -> maze[currentCell.getY() - 1][currentCell.getX()];
            case RIGHT -> maze[currentCell.getY()][currentCell.getX() + 1];
            case DOWN -> maze[currentCell.getY() + 1][currentCell.getX()];
            case LEFT -> maze[currentCell.getY()][currentCell.getX() - 1];
        };
    }

    private boolean isCoordinateInMaze(int x1, int y1, int x2, int y2) {
        return x1 >= 0 && x1 < maze[0].length
            && y1 >= 0 && y1 < maze.length
            && x2 >= 0 && x2 < maze[0].length
            && y2 >= 0 && y2 < maze.length;
    }

    private void visitCell(Cell cell) {
        if (!currentCell.isVisited()) {
            deque.addLast(currentCell);
            currentCell.setVisited(true);
            currentCell.setType(Cell.Type.SOlVE);
        } else {
            if (moveForward().isVisited()) {
                deque.pollLast();
                currentCell.setType(Cell.Type.PASSAGE);
            }
        }
    }

    enum Direction {
        UP,
        RIGHT,
        DOWN,
        LEFT
    }
}
