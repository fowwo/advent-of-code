// December 7th, 2020
package _2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class _5b {

    public static void main(String[] args) {

        try {

            File f = new File("input/2020/5.txt");
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
