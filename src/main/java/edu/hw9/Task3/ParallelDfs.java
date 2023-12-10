package edu.hw9.Task3;

import edu.Cell;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.RecursiveTask;

public class ParallelDfs extends RecursiveTask<Queue<Cell>> {

    private Cell currentCell;
    private Cell[][] maze;
    private Cell start;
    private Cell exit;

    public ParallelDfs(Cell[][] maze, int startX, int startY, int endX, int endY) {
        this.maze = maze;
        start = this.maze[startY][startX];
        exit = this.maze[endY][endX];
        exit.setType(Cell.Type.END);
    }

    @Override
    protected Queue<Cell> compute() {
        Queue<Cell> result = new ArrayDeque<>();

        List<ParallelDfs> subtask = new ArrayList<>();

        currentCell = start;
        visitCell(start);
        result.add(start);
        List<Cell> neighbor = checkNeighbor();


        do {

            if (neighbor.size() == 1) {
                currentCell = neighbor.get(0);
                visitCell(currentCell);
                result.add(currentCell);
            } else {
                for (Cell cell : neighbor) {
                    int currentX = cell.getX();
                    int currentY = cell.getY();
                    int endX = exit.getX();
                    int endY = exit.getY();
                    ParallelDfs dfs = new ParallelDfs(maze, currentX, currentY, endX, endY);
                    dfs.fork();
                    subtask.add(dfs);
                }
                break;
            }
            neighbor = checkNeighbor();
        } while (!neighbor.isEmpty() && currentCell.getType() != Cell.Type.END);

        for (var task : subtask) {
            result.addAll(task.join());
        }

        return result.contains(exit) ? result : new ArrayDeque<>();
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
            cell.setVisited(true);
        }
    }
}
