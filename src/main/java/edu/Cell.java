package edu;

public class Cell {

    private static final String WHITE_CELL = "\u001B[47m\u001B[37m...\u001B[0m";
    private static final String BLACK_CELL = "\u001B[40m\u001B[30m...\u001B[0m";
    private static final String START_CELL = "\u001B[42m\u001B[32m...\u001B[0m";
    private static final String END_CELL = "\u001B[41m\u001B[31m...\u001B[0m";
    private static final String SOLVE_CELL = "\u001B[43m\u001B[33m...\u001B[0m";


    private boolean visited = false;
    private Type type;
    private final int x;
    private final int y;

    public Cell(Type type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isVisited() {
        return visited;
    }

    public Type getType() {
        return type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @SuppressWarnings("RegexpSinglelineJava")
    public void printSelf() {
        switch (type) {
            case WALL -> System.out.print(WHITE_CELL);
            case PASSAGE -> System.out.print(BLACK_CELL);
            case START -> System.out.print(START_CELL);
            case END -> System.out.print(END_CELL);
            case SOlVE -> System.out.print(SOLVE_CELL);
            default -> {
                // nothing
            }
        }
    }

    public enum Type {
        WALL,
        PASSAGE,
        START,
        END,
        SOlVE
   }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Cell cell) {

            return x == cell.getX()
                && y == cell.getY()
                && type == cell.getType();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }
}
