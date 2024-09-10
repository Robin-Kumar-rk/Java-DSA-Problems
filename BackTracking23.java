import java.util.Arrays;

public class BackTracking23 {
    public static void findSubSets(String st, StringBuilder ans, int i) {
        // base case
        if (st.length() == i) {
            if (ans.length() == 0) {
                System.out.print("Null");
            } else {
                System.out.print(ans + " ");
            }
            return;
        }
        // yes choice
        findSubSets(st, ans.append(st.charAt(i)), i + 1);
        ans.deleteCharAt(ans.length() - 1);
        // no choice
        findSubSets(st, ans, i + 1);
    }

    public static void permutation(String st, StringBuilder ans) {
        if (st.length() == 0) {
            System.out.print(ans + " ");
            return;
        }
        // placing all character at 1st place
        for (int i = 0; i < st.length(); i++) {
            String newst = st.substring(0, i) + st.substring(i + 1);
            permutation(newst, ans.append(st.charAt(i)));
            ans.deleteCharAt(ans.length() - 1);
        }
    }

    static int nQueenSolution = 0; 
    // we are finding only one sol in code below

    public static boolean nQueens(char[][] board, int row) {
        if (row == board.length) {
            return true;
        }

        for (int j = 0; j < board.length; j++) {
            if (isSafe(board, row, j)) {
                board[row][j] = 'Q';
                if (nQueens(board, row + 1)) {
                    return true;
                } else {
                    board[row][j] = 'X';
                }

            }
        }
        return false;
    }

    public static boolean isSafe(char[][] board, int row, int col) {
        for (int i = row - 1; i >= 0; i--) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    public static void printBoard(char[][] board) {
        System.out.println("------Board-------------");
        for (char[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static void nQueenProblem(int n) {
        char[][] board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, 'X');
        }
        if (nQueens(board, 0)) {
            System.out.println("Solution exists");
            printBoard(board);
        } else {
            System.out.println("Solution does not exist");
        }
    }

    public static int gridWays(int n, int m, int i, int j) {
        if (i == n - 1 || j == m - 1) {
            return 1;
        }
        return gridWays(n, m, i + 1, j) + gridWays(n, m, i, j + 1);
    }

    
    public int gridWays2(int m, int n) { // DP
        int[][] dp = new int[m][n];

        // Initialize the bottom row and rightmost column to 1
        for (int i = 0; i < m; i++) {
            dp[i][n - 1] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[m - 1][j] = 1;
        }

        // Iterate over the grid to fill in the number of ways to reach each cell
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                dp[i][j] = dp[i + 1][j] + dp[i][j + 1];
            }
        }

        // Return the number of ways to reach the bottom-right cell
        return dp[0][0];
    }
    
    

    public static boolean sudokuSolver(int[][] sudoku, int row, int col) {
        // base case
        if (row == 9) {
            return true;
        }
        // finding nextRow and nextCoulmn for recursion
        int nextRow = row, nextCol = col + 1;
        if (nextCol == 9) {
            nextRow = row + 1;
            nextCol = 0;
        }
        // check if sudoku[row][col] is empty or not
        if (sudoku[row][col] != 0) {
            return sudokuSolver(sudoku, nextRow, nextCol);
        }
        for (int i = 1; i <= 9; i++) {
            if (isSafe(sudoku, row, col, i)) {
                sudoku[row][col] = i;
                if (sudokuSolver(sudoku, nextRow, nextCol)) { // solution exists
                    return true;
                }
                sudoku[row][col] = 0;
            }
        }
        return false;
    }

    public static boolean isSafe(int[][] sudoku, int row, int col, int digit) {
        // checking in coulmn
        for (int i = 0; i < 9; i++) {
            if (sudoku[i][col] == digit) {
                return false;
            }
        }
        // checking in row
        for (int j = 0; j < 9; j++) {
            if (sudoku[row][j] == digit) {
                return false;
            }
        }
        // checking in grid
        int sr = (row / 3) * 3, sc = (col / 3) * 3;
        for (int i = sr; i < sr + 3; i++) {
            for (int j = sc; j < sc + 3; j++) {
                if (sudoku[i][j] == digit) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void printSudoku(int[][] sudoku, int row, int col) {
        if (sudokuSolver(sudoku, row, col)) {
            System.out.println("----Solved Sudoku----");
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(sudoku[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("Solution does not exist");
        }
    }

    public static void sudokuCall() {
        int[][] sudoku = {  { 3, 0, 6, 5, 0, 8, 4, 0, 0 },
                            { 5, 2, 0, 0, 0, 0, 0, 0, 0 },
                            { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
                            { 0, 0, 3, 0, 1, 0, 0, 8, 0 },
                            { 9, 0, 0, 8, 6, 3, 0, 0, 5 },
                            { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
                            { 1, 3, 0, 0, 0, 0, 2, 5, 0 },
                            { 0, 0, 0, 0, 0, 0, 0, 7, 4 },
                            { 0, 0, 5, 2, 0, 6, 3, 0, 0 } };
        printSudoku(sudoku, 0, 0);
    }

    public static void printMaze(int[][] maze) {
        System.out.println("----Solution path-----");
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze.length; j++) {
                if (maze[i][j] == 2) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
    }

    /*
     * A utility function to check
     * if x, y is valid index for N*N maze
     */
    public static boolean isSafe(int maze[][], int row, int col) {
        int n = maze.length;
        // if (x, y outside maze) return false
        return (row >= 0 && col >= 0 && row < n && col < n && maze[row][col] == 1);
    }

    public static void ratInMaze(int[][] maze, int x, int y) {
        if (x == maze.length - 1 && y == maze.length - 1 && maze[x][y] == 1) {
            maze[x][y] = 2;
            printMaze(maze);
            maze[x][y] = 1;
            return;
        }

        if (isSafe(x, y, maze)) {
            maze[x][y] = 2;
            ratInMaze(maze, x - 1, y);
            ratInMaze(maze, x, y + 1);
            ratInMaze(maze, x + 1, y);
            ratInMaze(maze, x, y - 1);
            maze[x][y] = 1;
        }
    }

    public static void ratInMazeCall() {
        int[][] maze = { { 1, 0, 1, 1 },
                { 1, 1, 1, 0 },
                { 0, 1, 1, 1 },
                { 1, 1, 1, 1 }, };

        ratInMaze(maze, 0, 0);
    }

    // second ans of rat in maze question it gives only one possible solution
    /*
     * A utility function to print
     * solution matrix sol[N][N]
     */
    public static void printMaze2(int sol[][]) {
        for (int i = 0; i < sol.length; i++) {
            for (int j = 0; j < sol.length; j++) {
                System.out.print(sol[i][j] + " ");
            }
            System.out.println();
        }
    }

    /*
     * This function solves the Maze problem using
     * Backtracking. It mainly uses ratInMazeUtil2()
     * to solve the problem. It returns false if no
     * path is possible, otherwise return true and
     * prints the path in the form of 1s. Please note
     * that there may be more than one solutions, this
     * function prints one of the feasible(possible) solutions.
     */
    public static void ratInMaze2call() {
        int[][] maze = { { 1, 0, 1, 1 },
                { 1, 1, 1, 0 },
                { 0, 1, 1, 1 },
                { 1, 1, 1, 1 }, };
        int sol[][] = new int[maze.length][maze.length];
        if (ratInMazeutil2(maze, sol, 0, 0)) {
            System.out.println("Slution path -----");
            printMaze2(sol);
        } else {
            System.out.println("Solution does not exists");
        }

    }

    /*
     * A recursive utility function to solve Maze
     * problem
     */
    public static boolean ratInMazeutil2(int maze[][], int sol[][], int row, int col) {
        // if (x, y is goal) return true
        if (row == maze.length - 1 && col == maze.length - 1) {
            sol[row][col] = 1;
            return true;
        }
        // Check if maze[x][y] is valid
        if (isSafe(maze, row, col)) {
            // Check if the current block is already part of solution path.
            if (sol[row][col] == 1) {
                return false;
            }
            // mark x, y as part of solution path
            sol[row][col] = 1;
            if (ratInMazeutil2(maze, sol, row, col + 1)) {
                return true;
            }
            if (ratInMazeutil2(maze, sol, row + 1, col)) {
                return true;
            }
            if (ratInMazeutil2(maze, sol, row, col - 1)) {
                return true;
            }
            if (ratInMazeutil2(maze, sol, row - 1, col)) {
                return true;
            }
            /*
             * If none of the above movements works then
             * BACKTRACK: unmark x, y as part of solution
             * path
             */
            sol[row][col] = 0;
            return false;
        }
        return false;
    }

    // third ans of rat in maze question without ceating solution matrix .it gives
    // only one possible solution
    public static boolean ratInMaze3(int maze[][], int row, int col) {
        if (row == maze.length - 1 && col == maze.length - 1) {
            maze[row][col] = 2;
            return true;
        }
        if (isSafe(maze, row, col)) {
            if (maze[row][col] == 2) {
                return false;
            }
            maze[row][col] = 2;
            if (ratInMaze3(maze, row, col + 1)) {
                return true;
            }
            if (ratInMaze3(maze, row + 1, col)) {
                return true;
            }
            if (ratInMaze3(maze, row, col - 1)) {
                return true;
            }
            if (ratInMaze3(maze, row - 1, col)) {
                return true;
            }
            maze[row][col] = 1;
            return false;
        }
        return false;

    }

    public static void ratInMaze3call() {
        int[][] maze = { { 1, 0, 1, 1 },
                { 1, 1, 1, 0 },
                { 0, 1, 1, 1 },
                { 1, 1, 1, 1 }, };
        if (ratInMaze3(maze, 0, 0)) {
            System.out.println("Slution path -----");
            printMaze2(maze);
        } else {
            System.out.println("Solution does not exists");
        }

    }

    static String[] digitChar = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    // Using String
    public static void keypadCombination(String st, String ans, int idx) {
        if (idx == st.length()) {
            System.out.print(ans + " ");
            return;
        }
        String com = digitChar[st.charAt(idx) - '2'];
        for (int i = 0; i < com.length(); i++) {
            keypadCombination(st, ans + com.charAt(i), idx + 1);
        }
    }

    // Using StringBuilder
    public static void keypadCombination2(String st, int idx, StringBuilder sb) { 
        if (idx == st.length()) {
            System.out.print(sb + " ");
            return;
        }
        String s = digitChar[st.charAt(idx) - '2'];
        for (int i = 0; i < s.length(); i++) {
            keypadCombination2(st, idx + 1, sb.append(s.charAt(i)));
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    /*
     * This function solves the Knight Tour problem
     * using Backtracking. In this code we
     * think about next cell. This function mainly
     * uses KTUtil() to solve the problem. It
     * returns false if no complete tour is possible,
     * otherwise return true and prints the tour.
     * Please note that there may be more than one
     * solutions, this function prints one of the
     * feasible solutions.
     */
    public static boolean knightTour1(int n) {

        /* Initialization of solution matrix */
        int board[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = -1;
            }
        }

        // Since the Knight is initially at the first block
        board[0][0] = 0;

        /*
         * xMove[] and yMove[] define next move of Knight.
         * xMove[] is for next value of x coordinate
         * yMove[] is for next value of y coordinate
         */
        int xMove[] = { -2, -1, 1, 2, 2, 1, -1, -2 };
        int yMove[] = { 1, 2, 2, 1, -1, -2, -2, -1 };
        if (kTUtil1(0, 0, 1, xMove, yMove, board)) {
            ptintBoard(board);
            return true;
        } else {
            System.out.println("Solution doesn't exist");
            return false;
        }
    }
    // A utility function to check if i,j are valid indexes for N*N chessboard
    public static boolean isSafe(int x, int y, int[][] board) {
        return (x >= 0 && y >= 0 && x < board.length && y < board.length && board[x][y] == -1);
    }
    // A recursive utility function to solve Knight Tour problem
    public static boolean kTUtil1(int x, int y, int move, int[] xMove, int[] yMove, int[][] board) {
        if (move == board.length * board.length) {
            return true;
        }
        // Try all next moves from the current coordinate x, y
        int nextx, nexty;
        for (int k = 0; k < 8; k++) {
            nextx = x + xMove[k];
            nexty = y + yMove[k];
            if (isSafe(nextx, nexty, board)) {
                board[nextx][nexty] = move;
                if (kTUtil1(nextx, nexty, move + 1, xMove, yMove, board)) {
                    return true;
                } else {
                    board[nextx][nexty] = -1; // backtracking
                }
            }
        }
        return false;
    }

    /*
     * this is the second answer of knightTour problem. In this code we think about
     * cuurent cell.
     */
    public static void knightTour2(int n) {
        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = -1;
            }
        }
        int[] rmove = { -2, -1, 1, 2, 2, 1, -1, -2 };
        int[] cmove = { 1, 2, 2, 1, -1, -2, -2, -1 };
        if (knightTourUtil2(board, 0, 0, 0, rmove, cmove)) {
            ptintBoard(board);
        } else {
            System.out.println("Solution does not exist");
        }
    }

    public static boolean knightTourUtil2(int[][] board, int row, int col, int move, int[] rmove, int[] cmove) {
        if (move == board.length * board.length) {
            return true;
        }
        if (isSafe(board, row, col)) {
            board[row][col] = move;
            int nextRow, nextCol;
            for (int i = 0; i < 8; i++) {
                nextCol = col + cmove[i];
                nextRow = row + rmove[i];
                if (knightTourUtil2(board, nextRow, nextCol, move + 1, rmove, cmove)) {
                    return true;
                }
            }
            board[row][col] = -1;
            return false;
        }
        return false;
    }

    /*
     * A utility function to print solution
     * matrix sol[N][N]
     */
    public static void ptintBoard(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
       nQueenProblem(4);
    }
}
