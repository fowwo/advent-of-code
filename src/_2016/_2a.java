// September 6th, 2020
package _2016;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class _2a {

    public static void main(String[] args) {

        try {

            File f = new File("input/2016/2.txt");
            Scanner s = new Scanner(f);

            int value = 5;
            while (s.hasNextLine()) {
                String line = s.nextLine();
                for (int i = 0; i < line.length(); i++) {
                    switch (line.charAt(i)) {
                        case 'U':
                            if (value >= 4) value -= 3;
                            break;
                        case 'D':
                            if (value <= 6) value += 3;
                            break;
                        case 'L':
                            if (value % 3 != 1) value -= 1;
                            break;
                        case 'R':
                            if (value % 3 != 0) value += 1;
                            break;
                    }
                }
                System.out.print(value);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
