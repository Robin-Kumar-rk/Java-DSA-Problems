import java.util.Arrays;

public class TwoDArray16 {
    public static void spiral(int matrix[][]) {
        int n = matrix.length, m = matrix[0].length;
        int sr = 0, er = n - 1, sc = 0, ec = m - 1; // s=start , r=row , e=end , c=coulmn
        while (sr <= er && sc <= ec) { // loop for boundries
            for (int j = sc; j <= ec; j++) { // loop for top of each boundry
                System.out.print(matrix[sr][j] + " ");
            }
            for (int i = sr + 1; i <= er; i++) { // right
                System.out.print(matrix[i][ec] + " ");
            }
            for (int j = ec - 1; j >= sc; j--) { // bottom
                if (sr == er) {
                    break;
                }
                System.out.print(matrix[er][j] + " ");
            }
            for (int i = er - 1; i >= sr + 1; i--) { // left
                if (sc == ec) {
                    break;
                }
                System.out.print(matrix[i][sc] + " ");
            }
            // updation
            sr++;
            sc++;
            ec--;
            er--;
        }
    }

    public static void diagonal(int matrix[][]) {
        int sum = 0;
        for (int i = 0, j = matrix[0].length - 1; i < matrix.length; i++, j--) {
            sum = sum + matrix[i][i]; // primary diagonal
            if (i != j) { // escaping from overlapping condition
                sum = sum + matrix[i][j]; // secondary diagonal
            }
        }
        System.out.println(sum);
    }

    public static void stairSearch(int matrix[][], int key) {
        int n = matrix.length, m = matrix[0].length;
        int row = 0, column = m - 1;
        while (row <= n && column >= 0) {
            if (key == matrix[row][column]) {
                System.out.print("Key found at Index : " + "(" + row + "," + column + ")");
                return;
            } else if (key > matrix[row][column]) {
                row++;
            } else {
                column--;
            }
        }
        System.out.println("Key is not found");
    }

    public static void transpose(int matrix[][]) {
        // reverse of matrix mean elements are changed row to coulmnans coulmn to row
        int transpose[][] = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                transpose[j][i] = matrix[i][j]; // transposing
            }
        }
        for (int[] row : transpose) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static void main(String[] input) {
        int matrix[][] = { { 1, 2, 9, 7, 0 },
                { 3, 5, 7, 2, 8 } };
        transpose(matrix);
    }
}
