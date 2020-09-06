// June 1st, 2020
package _2015;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class _14a {

    public static void main(String[] args) {

        try {

            File f = new File("input/2015/14.txt");
            Scanner s = new Scanner(f);

            int max = 0;
            while (s.hasNextLine()) {

                String[] line = s.nextLine().split(" ");

                int left = 2503;
                int speed = Integer.parseInt(line[3]);
                int duration = Integer.parseInt(line[6]);
                int rest = Integer.parseInt(line[13]);

                int distance = 0;
                while (left > 0) {
                    distance += Math.min(duration, left) * speed;
                    left -= (duration + rest);
                }
                if (max < distance) {
                    max = distance;
                }

            }
            s.close();

            System.out.println("Distance: " + max);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
