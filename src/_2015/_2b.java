// May 5th, 2020
package _2015;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class _2b {

    public static void main(String[] args) {

        try {

            File f = new File("input/2015/2.txt");
            Scanner s = new Scanner(f);

            int total = 0;
            while (s.hasNextLine()) {
                String[] line = s.nextLine().split("x");
                int[] d = new int[3];
                d[0] = Integer.parseInt(line[0]);
                d[1] = Integer.parseInt(line[1]);
                d[2] = Integer.parseInt(line[2]);
                total += 2 * (d[0] + d[1] + d[2] - Math.max(Math.max(d[0], d[1]), d[2])) + d[0] * d[1] * d[2];
            }

            System.out.println(total);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}