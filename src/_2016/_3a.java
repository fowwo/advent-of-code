// September 6th, 2020
package _2016;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class _3a {

    public static void main(String[] args) {

        try {

            File f = new File("input/2016/3.txt");
            Scanner s = new Scanner(f);

            int count = 0;
            while (s.hasNextLine()) {
                String[] line = s.nextLine().trim().split("[ ]+");
                int[] sides = new int[3];
                for (int i = 0; i < 3; i++) {
                    sides[i] = Integer.parseInt(line[i]);
                }
                Arrays.sort(sides);
                if (sides[0] + sides[1] > sides[2]) count++;
            }

            System.out.println(count);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
