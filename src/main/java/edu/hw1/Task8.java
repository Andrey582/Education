package edu.hw1;

public class Task8 {
    private final static int BOARD_SIZE = 8;
    private final static int BIG_STEP = 2;
    private final static int SMALL_STEP = 1;

    public static boolean knightBoardCapture(int[][] board) {
        if (board == null) {
            return false;
        }

        if (board.length != BOARD_SIZE || board[0].length != BOARD_SIZE) {
            return false;
        }

        int[] stepX = new int[] {-BIG_STEP, -SMALL_STEP, SMALL_STEP, BIG_STEP};
        int[] stepY = new int[] {SMALL_STEP, BIG_STEP, BIG_STEP, SMALL_STEP};

        int x;
        int y;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 1) {
                    for (int k = 0; k < stepX.length; k++) {
                        x = i + stepX[k];
                        y = j + stepY[k];

                        if (x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE) {
                            if (board[x][y] == 1) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    private Task8() {
    }
}
