// December 7th, 2020
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class b {

    public static void main(String[] args) {

        /*
            This code is not entirely correct, although it (luckily) returned the same answer.
            On searching a second half, the code should split the seats like:
                    [ 1  2  3  4 ]
                                   [ 5  6  7  8 ]
            However, I have the seats being split like:
                    [ 1  2  3  4 ]
                             [ 4  5  6  7  8 ]
            As a result, the code fails to find boarding passes for the eighth column and
            sometimes matches two boarding passes to the same seat. Somehow, this implementation
            still lead me to correct answer for both parts.
         */

        try {

            File f = new File("input.txt");
            Scanner s = new Scanner(f);
            int[][] matrix = new int[128][8];

            while (s.hasNextLine()) {
                String str = s.nextLine();
                int[] m = new int[]{ 0, 127, 0, 7 };
                for (int i = 0; i < 6; i++) {
                    int x = (m[0] + m[1]) / 2;
                    if (str.charAt(i) == 'F') m[1] = x;
                    else m[0] = x;
                }
                int row = m[0];
                if (str.charAt(6) == 'B') row++;
                for (int i = 7; i < 9; i++) {
                    int x = (m[2] + m[3]) / 2;
                    if (str.charAt(i) == 'L') m[3] = x;
                    else m[2] = x;
                }
                int column = m[2];
                if (str.charAt(9) == 'R') column++;
                matrix[row][column] = 1;
            }

            for (int r = 0; r < 128; r++) {
                System.out.printf("%3d ", r + 1);
                for (int c = 0; c < 8; c++) {
                    if (matrix[r][c] == 0) System.out.print("-");
                    else System.out.print("#");
                }
                System.out.print("\n");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
