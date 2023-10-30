package edu;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Deque;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class Solve implements MazeSolver {

    private Cell start;
    private Cell exit;
    private Deque<Cell> deque;
    private Cell currentCell;
    private Cell[][] maze;
    private Random random = new Random();

    @Override
    public Deque<Cell> solve(Maze maze) {
        start = maze.getStart();
        exit = maze.getExit();
        deque = new ArrayDeque<>();
        this.maze = maze.getMaze();
        Cell next;
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
            //print();
        } while (!currentCell.equals(exit));
        //print(deque);
        return deque;
    }

    private List<Cell> checkNeighbor() {
        List<Cell> neighbor = new ArrayList<>();
        if (!maze[currentCell.getY()][currentCell.getX() - 1].isVisited()
            && maze[currentCell.getY()][currentCell.getX() - 1].getType() != Cell.Type.WALL) {
            neighbor.add(maze[currentCell.getY()][currentCell.getX() - 1]);
        }
        if (!maze[currentCell.getY() + 1][currentCell.getX()].isVisited()
            && maze[currentCell.getY() + 1][currentCell.getX()].getType() != Cell.Type.WALL) {
            neighbor.add(maze[currentCell.getY() + 1][currentCell.getX()]);
        }
        if (!maze[currentCell.getY()][currentCell.getX() + 1].isVisited()
            && maze[currentCell.getY()][currentCell.getX() + 1].getType() != Cell.Type.WALL) {
            neighbor.add(maze[currentCell.getY()][currentCell.getX() + 1]);
        }
        if (!maze[currentCell.getY() - 1][currentCell.getX()].isVisited()
            && maze[currentCell.getY() - 1][currentCell.getX()].getType() != Cell.Type.WALL) {
            neighbor.add(maze[currentCell.getY() - 1][currentCell.getX()]);
        }
        return neighbor;
    }

    private boolean checkCell(int y, int x) {
        return y >= 1 && y <= maze.length - 2 && x >= 1 && x <= maze[0].length - 2;
    }

    private void visitCell(Cell cell) {
        if (!cell.isVisited()) {
            deque.add(cell);
        }
        cell.setVisited(true);
    }

    public void print() {
        for (int i = 0; i < maze.length; i++) {
            if (i < 10) {
                System.out.print(" " + i + " ");
            } else {
                System.out.print(" " + i);
            }
        }
        System.out.println();
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                maze[i][j].printSelf();
            }
            System.out.println();
        }
    }

    public void print(Deque<Cell> deque) {
        while (!deque.isEmpty()) {
            Cell temp = deque.poll();
            System.out.println(temp.getX() + " | " + temp.getY());
        }
    }

}
