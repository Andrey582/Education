package edu.Creator;

import edu.Cell;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MazeEller implements MazeCreator {

    private final static Random RANDOM = new Random();
    private final List<Integer> line = new ArrayList<>();
    private Cell[][] maze;
    private int counter = 0;
    private final int height;
    private final int width;

    public MazeEller(Cell[][] maze) {
        this.maze = maze;
        height = maze.length;
        width = maze[0].length;
    }

    public MazeEller(int height, int width) throws IllegalArgumentException {
        if (height < 1 || width < 1) {
            throw new IllegalArgumentException("height and width need be greater than 1");
        }
        this.width = width;
        this.height = height;
        generate();
    }

    @Override
    public Cell[][] generate() {
        fillMaze(height, width);
        fillEmptyValue();
        for (int i = 0; i < height - 1; i++) {
            uniqueSet();
            addVerticalWall(i);
            addHorizontalWall(i);
            checkHorizontalWall(i);
            preparatingNewLine(i);
        }
        addEndLine();
        return maze;
    }

    public void fillMaze(int height, int width) {
        maze = new Cell[height * 2 + 1][width * 2 + 1];
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (i == 0 || i == maze.length - 1 || j == 0 || j == maze[0].length - 1) {
                    maze[i][j] = new Cell(Cell.Type.WALL, j, i);
                } else {
                    maze[i][j] = new Cell(Cell.Type.PASSAGE, j, i);
                }
            }
        }
    }

    @Override
    public Cell[][] getMaze() {
        return maze;
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

    private void fillEmptyValue() {
        for (int i = 0; i < width; i++) {
            line.add(0);
        }
    }

    private void uniqueSet() {
        for (int i = 0; i < width; i++) {
            if (line.get(i) == 0) {
                line.set(i, counter);
                counter++;
            }
        }
    }

    private void setRightWall(Cell cell) {
        maze[cell.getY() - 1][cell.getX() + 1].setType(Cell.Type.WALL);
        maze[cell.getY()][cell.getX() + 1].setType(Cell.Type.WALL);
        maze[cell.getY() + 1][cell.getX() + 1].setType(Cell.Type.WALL);
    }

    private void setDownWall(Cell cell) {
        maze[cell.getY() + 1][cell.getX() - 1].setType(Cell.Type.WALL);
        maze[cell.getY() + 1][cell.getX()].setType(Cell.Type.WALL);
        maze[cell.getY() + 1][cell.getX() + 1].setType(Cell.Type.WALL);
    }

    private void removeDownWall(Cell cell) {
        maze[cell.getY() + 1][cell.getX()].setType(Cell.Type.PASSAGE);
    }

    private void removeVerticalWall(Cell cell) {
        maze[cell.getY()][cell.getX() + 1].setType(Cell.Type.PASSAGE);
    }

    private void merge(int index, int element) {
        int set = line.get(index + 1);
        for (int i = 0; i < width; i++) {
            if (line.get(i) == set) {
                line.set(i, element);
            }
        }
    }

    private void addVerticalWall(int row) {
        for (int i = 0; i < width - 1; i++) {
            if (RANDOM.nextInt() % 2 == 0 || line.get(i).equals(line.get(i + 1))) {
                setRightWall(maze[row * 2 + 1][i * 2 + 1]);
            } else {
                merge(i, line.get(i));
            }
        }
    }

    private void addHorizontalWall(int row) {
        for (int i = 0; i < width; i++) {
            if (countElementInSet(line.get(i)) != 1 && RANDOM.nextInt() % 2 == 0) {
                setDownWall(maze[row * 2 + 1][i * 2 + 1]);
            }
        }
    }

    private int countElementInSet(int element) {
        return Math.toIntExact(line.stream().filter(e -> e == element).count());
    }

    private void checkHorizontalWall(int row) {
        for (int i = 0; i < width; i++) {
            if (countHorizontalWall(line.get(i), row * 2 + 1) == 0) {
                removeDownWall(maze[row * 2 + 1][i * 2 + 1]);
            }
        }
    }

    private int countHorizontalWall(int element, int row) {
        int count = 0;
        for (int i = 0; i < width; i++) {
            if (line.get(i) == element && maze[row + 1][i * 2 + 1].getType() == Cell.Type.PASSAGE) {
                count++;
            }
        }
        return count;
    }

    private void preparatingNewLine(int row) {
        for (int i = 0; i < width; i++) {
            if (maze[row * 2 + 2][i * 2 + 1].getType() == Cell.Type.WALL) {
                line.set(i, 0);
            }
        }
    }

    private void addEndLine() {
        uniqueSet();
        addVerticalWall(height - 1);
        checkEndLine();
    }

    private void checkEndLine() {
        for (int i = 0; i < width - 1; i++) {
            if (!line.get(i).equals(line.get(i + 1))) {
                removeVerticalWall(maze[height * 2 - 1][i * 2 + 1]);
                merge(i, line.get(i));
            }
        }
    }
}
