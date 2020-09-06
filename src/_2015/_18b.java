// June 5th, 2020
package _2015;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class _18b {

    public static void main(String[] args) {

        try {

            File f = new File("input/2015/18.txt");
            Scanner s = new Scanner(f);

            int[][] matrix = new int[100][100];

            int lineCount = 0;
            while (s.hasNextLine()) {
                String line = s.nextLine();
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == '#') {
                        matrix[lineCount][i] = 1;
                    } else {
                        matrix[lineCount][i] = 0;
                    }
                }
                lineCount++;
            }
            s.close();

            matrix[0][0] = 1;
            matrix[0][99] = 1;
            matrix[99][0] = 1;
            matrix[99][99] = 1;

            int[][] copy = new int[100][100];
            for (int i = 0; i < 100; i++) {

                // Make deep copy
                for (int j = 0; j < matrix.length; j++) {
                    copy[j] = Arrays.copyOf(matrix[j], 100);
                }

                for (int x = 0; x < 100; x++) {
                    for (int y = 0; y < 100; y++) {

                        if ((x == 0 & y == 0) || (x == 0 & y == 99) || (x == 99 & y == 0) || (x == 99 & y == 99)) continue;

                        // Count neighbors
                        int count = 0;
                        if (x != 0 && y != 0) count += matrix[x - 1][y - 1];
                        if (x != 0 && y != 99) count += matrix[x - 1][y + 1];
                        if (x != 99 && y != 0) count += matrix[x + 1][y - 1];
                        if (x != 99 && y != 99) count += matrix[x + 1][y + 1];
                        if (x != 0) count += matrix[x - 1][y];
                        if (x != 99) count += matrix[x + 1][y];
                        if (y != 0) count += matrix[x][y - 1];
                        if (y != 99) count += matrix[x][y + 1];

                        if (matrix[x][y] == 0 && count == 3) {
                            copy[x][y] = 1;
                        } else if (matrix[x][y] == 1 && (count < 2 || count > 3)) {
                            copy[x][y] = 0;
                        }

                    }
                }

                // Update matrix
                for (int j = 0; j < matrix.length; j++) {
                    matrix[j] = Arrays.copyOf(copy[j], 100);
                }

            }

            int total = 0;
            for (int x = 0; x < 100; x++) {
                for (int y = 0; y < 100; y++) {
                    if (matrix[x][y] == 1) total++;
                }
            }

            System.out.println(total);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
