// September 6th, 2020
package _2016;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class _3b {

    public static void main(String[] args) {

        try {

            File f = new File("input/2016/3.txt");
            Scanner s = new Scanner(f);

            int count = 0;
            List<int[]> list = new ArrayList<>();
            while (s.hasNextLine()) {
                String[] line = s.nextLine().trim().split("[ ]+");
                int[] sides = new int[3];
                for (int i = 0; i < 3; i++) {
                    sides[i] = Integer.parseInt(line[i]);
                }
                list.add(sides);
            }

            for (int i = 0; i < 3; i++) {
                for (int j = 2; j < list.size(); j += 3) {
                    int[] sides = new int[]{ list.get(j - 2)[i], list.get(j - 1)[i], list.get(j)[i] };
                    Arrays.sort(sides);
                    if (sides[0] + sides[1] > sides[2]) count++;
                }
            }
            System.out.println(count);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
