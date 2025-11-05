import java.util.Scanner;

public class NQueens {

    static int N;
    static int[][] board;
    static int fixedRow = -1;
    static int fixedCol = -1;

    // Function to print the board 
    //Iterates every row i and every column j, printing the integer in board[i][j] followed by a space
    static void printBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(); // move to next line (after printing one row)
    }

    // Function to check if placing a queen at (row, col) is safe
    static boolean isSafe(int row, int col) {
        // Check column
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1)
                return false;
        }

        // Check upper-left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1)
                return false;
        }

        // Check upper-right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < N; i--, j++) {
            if (board[i][j] == 1)
                return false;
        }

        return true;
    }

    // place queens row by row, starting from the top to bottom
    static boolean solveNQueens(int row) {
        // solution found no more rows left to process.
        if (row == N) {
            return true;
        }

        // If current row already has the fixed queen, skip it move on to the next row
        if (row == fixedRow) {
            return solveNQueens(row + 1);
        }

        // Try placing a queen in each column (Each row can only have one queen)
        for (int col = 0; col < N; col++) {
            if (isSafe(row, col)) {
                board[row][col] = 1; // if safe Place queen

                // Recurse for next row
                if (solveNQueens(row + 1))
                    return true;

                // Backtrack
                board[row][col] = 0;
            }
        }

        // No valid column found
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size of board (N): ");
        N = sc.nextInt();
        board = new int[N][N];

        // Take fixed queen input
        System.out.print("Enter fixed queen row (1-based): ");
        fixedRow = sc.nextInt() - 1;
        System.out.print("Enter fixed queen column (1-based): ");
        fixedCol = sc.nextInt() - 1;  //If user enters 2, internally it becomes 1 (second column).

        // Place the first queen
        board[fixedRow][fixedCol] = 1;

        // Start solving
        boolean solved = solveNQueens(0);

        // Output result
        if (solved) {
            System.out.println("\nFinal N-Queens Matrix:");
            printBoard();
        } else {
            System.out.println("No solution exists for this placement.");
        }

        sc.close();
    }
}
