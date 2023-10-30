package edu;

import java.util.Arrays;

public interface MazeCreator {
    Cell[][] generate();
    void fillMaze(int height, int width);
}
