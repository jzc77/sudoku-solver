public class SudokuSolver {

    private static final int GRID_SIZE = 9;

    // Check if number already exists in the row
    private static boolean isNumberInRow(int[][] board, int number, int row) {
        for (int i=0; i<GRID_SIZE; i++) {
            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }
    // Check if number already exists in the column
    private static boolean isNumberInColumn(int[][] board, int number, int column) {
        for (int i=0; i<GRID_SIZE; i++) {
            if (board[i][column] == number) {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        int[][] board = {
            {7,0,2,0,5,0,6,0,0},
            {0,0,0,0,0,3,0,0,0},
            {1,0,0,0,0,9,5,0,0},
            {8,0,0,0,0,0,0,9,0},
            {0,4,3,0,0,0,7,5,0},
            {0,9,0,0,0,0,0,0,8},
            {0,0,9,7,0,0,0,0,5},
            {0,0,0,2,0,0,0,0,0},
            {0,0,7,0,4,0,2,0,3}
        };
    }
}