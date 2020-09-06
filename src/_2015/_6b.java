// May 5th, 2020
package _2015;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class _6b {

    public static void main(String[] args) {

        try {

            File f = new File("input/2015/6.txt");
            Scanner s = new Scanner(f);

            int[][] matrix = new int[1000][1000];

            // Initialize matrix to 0
            for (int i = 0; i < 1000; i++) {
                for (int j = 0; j < 1000; j++) {
                    matrix[i][j] = 0;
                }
            }

            while (s.hasNextLine()) {

                String[] line = s.nextLine().split(" ");

                if (line[1].equals("on")) {

                    String[] c1 = line[2].split(",");
                    String[] c2 = line[4].split(",");

                    for (int i = Integer.parseInt(c1[0]); i <= Integer.parseInt(c2[0]); i++) {
                        for (int j = Integer.parseInt(c1[1]); j <= Integer.parseInt(c2[1]); j++) {
                            matrix[i][j]++;
                        }
                    }

                } else if (line[1].equals("off")) {

                    String[] c1 = line[2].split(",");
                    String[] c2 = line[4].split(",");

                    for (int i = Integer.parseInt(c1[0]); i <= Integer.parseInt(c2[0]); i++) {
                        for (int j = Integer.parseInt(c1[1]); j <= Integer.parseInt(c2[1]); j++) {
                            matrix[i][j] = Math.max(matrix[i][j] - 1, 0);
                        }
                    }

                } else {

                    String[] c1 = line[1].split(",");
                    String[] c2 = line[3].split(",");

                    for (int i = Integer.parseInt(c1[0]); i <= Integer.parseInt(c2[0]); i++) {
                        for (int j = Integer.parseInt(c1[1]); j <= Integer.parseInt(c2[1]); j++) {
                            matrix[i][j] += 2;
                        }
                    }

                }

            }

            int total = 0;
            for (int i = 0; i < 1000; i++) {
                for (int j = 0; j < 1000; j++) {
                    total += matrix[i][j];
                }
            }

            System.out.println(total);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
