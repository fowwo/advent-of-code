// May 5th, 2020
package _2015;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class _3a {

    public static void main(String[] args) {

        try {

            File f = new File("input/2015/3.txt");
            Scanner s = new Scanner(f);
            String str = s.nextLine();
            s.close();

            int[][] matrix = new int[10001][10001];
            int x = 5000;
            int y = 5000;
            int total = 0;

            // Initialize matrix to 0
            for (int i = 0; i < 10001; i++) {
                for (int j = 0; j < 10001; j++) {
                    matrix[i][j] = 0;
                }
            }

            matrix[x][y]++;

            // Move
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (c == '>') {
                    x++;
                } else if (c == 'v') {
                    y++;
                } else if (c == '<') {
                    x--;
                } else if (c == '^') {
                    y--;
                }
                matrix[x][y]++;
            }

            // Count visited houses
            for (int i = 0; i < 10001; i++) {
                for (int j = 0; j < 10001; j++) {
                    if (matrix[i][j] > 0){
                        total++;
                    }
                }
            }

            System.out.println(total);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
