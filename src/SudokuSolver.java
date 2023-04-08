public class SudokuSolver {

    private static final int GRID_SIZE = 9;

    // Check if number already exists in the row
    private static boolean isNumberInRow(int[][] board, int number, int row) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }
    // Check if number already exists in the column
    private static boolean isNumberInColumn(int[][] board, int number, int column) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][column] == number) {
                return true;
            }
        }
        return false;
    }

    // Check if number already exists in the 3X3 box
    private static boolean isNumberInBox(int[][] board, int number, int row, int column) {
        int localBoxRow = row - (row % 3);  // Top left corner of local box
        int localBoxColumn = column - (column % 3); // Top left corner of local box

        for (int i = localBoxRow; i < localBoxRow + 3; i++) {  // iterate through the 3 rows in the 3X3 box
            for (int j = localBoxColumn; j < localBoxColumn + 3; j++) { // for each row, iterate through the 3 columns in the 3X3 box
                if (board[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    // Encompassing method that checks row, column, and box for valid placement of input number
    private static boolean isValidPlacement(int[][] board, int number, int row, int column) {
        return !isNumberInRow(board, number, row) && !isNumberInColumn(board, number, column) && !isNumberInBox(board, number, row, column);
    }

    // For each empty spot in the Sudoku grid, check to see which number from 1 to 9 can be placed.
    // Backtracks to try a different number, each time solveBoard returns false
    private static boolean solveBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int column = 0; column < GRID_SIZE; column++) {
                if (board[row][column] == 0) {
                    for (int numberToTry = 0; numberToTry <= GRID_SIZE; numberToTry++) {
                        if (isValidPlacement(board, numberToTry, row, column)) {
                            board[row][column] = numberToTry;

                            // Because valid number has been placed on the board, go through the board again and find the first empty spot again
                            if (solveBoard(board)) {
                                return true;  // needed because it is the recursion base case
                            } else {
                                // clear out the placed numberToTry by setting the spot back to 0
                                // The for-loop this is in will now try the next number
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false;  // unable to solve board; can't put any number from 1 to 9 in a certain spot
                }
            }
        }
        return true;  // the Sudoku board has been solved successfully
    }

    public static void main(String[] args) {
        int[][] board = {
            {1,0,2,0,5,0,6,0,0},
            {0,0,0,0,0,3,0,0,0},
            {1,0,0,0,0,9,5,0,0},
            {8,0,0,0,0,0,0,9,0},
            {0,4,3,0,0,0,7,5,0},
            {0,9,0,0,0,0,0,0,8},
            {0,0,9,7,0,0,0,0,5},
            {0,0,0,2,0,0,0,0,0},
            {0,0,7,0,4,0,2,0,3}
        };

        if (solveBoard(board)) {
            System.out.println("Solved successfully!");
        } else {
            System.out.println("Unsolvable board!");
        }

        // Print out the whole board
        for (int row = 0; row < GRID_SIZE; row++) {
            if (row % 3 == 0 && row != 0) { // Print the hyphens at every third row, but not at the very first row
                System.out.println("-----------");
            }
            for (int column = 0; column < GRID_SIZE; column++) {
                if (column % 3 == 0 && column != 0) { // Print the pipes at every third column, but not at the very first column
                    System.out.print("|");
                }
                System.out.print(board[row][column]);  // notice it's not "println"
            }
            System.out.println();  // After 9 numbers (GRID_SIZE), print a new line
        }
    }
}