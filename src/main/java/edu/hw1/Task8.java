package edu.hw1;

public class Task8 {
    public static boolean knightBoardCapture(int[][] board) {
        if (board == null) {
            return false;
        }

        if (board.length != 8 || board[0].length != 8) {
            return false;
        }

        int[] stepX = new int[] {-2, -1, 1, 2};
        int[] stepY = new int[] {1, 2, 2, 1};

        int x, y;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 1) {
                    for (int k = 0; k < stepX.length; k++) {
                        x = i + stepX[k];
                        y = j + stepY[k];

                        if (x >= 0 && x < 8 && y >= 0 && y < 8){
                            if (board[x][y] == 1){
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
