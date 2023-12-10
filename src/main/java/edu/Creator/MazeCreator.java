package edu.Creator;

import edu.Cell;

public interface MazeCreator {

    Cell[][] generate();

    void fillMaze(int height, int width);

    Cell[][] getMaze();

    void print();

    void clean();
}
