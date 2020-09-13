// September 13th, 2020
package _2016;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class _22b {

    public static void main(String[] args) {

        int[][][] matrix = new int[35][29][2];

        try {

            File f = new File("input/2016/22.txt");
            Scanner s = new Scanner(f);

            // Skip first two lines
            s.nextLine();
            s.nextLine();

            while (s.hasNextLine()) {
                String[] line = s.nextLine().split("[ ]+");
                String[] pos = line[0].split("-");
                int x = Integer.parseInt(pos[1].substring(1));
                int y = Integer.parseInt(pos[2].substring(1));
                matrix[x][y] = new int[]{ Integer.parseInt(line[2].substring(0, line[2].length() - 1)), Integer.parseInt(line[1].substring(0, line[1].length() - 1)) };
            }
            s.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.printf("%7s ", matrix[j][i][0] + "/" + matrix[j][i][1]);
            }
            System.out.print('\n');
        }

        /*
            It takes 26 moves to create an empty node to the left of the goal data.
            It takes  1 move  to move the goal data to the empty data.
            It takes  5 moves to create an empty node to the left of the goal data and to move the goal data.
            It takes 33 iterations of these 5 moves to move the goal data to the target node.

            26 + 1 + 33 * 5 = 192.
            It takes at least 192 moves to move the goal data to the target node.
        */

    }

}
