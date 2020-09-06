// May 5th, 2020
package _2015;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class _5a {

    public static void main(String[] args) {

        try {

            File f = new File("input/2015/5.txt");
            Scanner s = new Scanner(f);

            int total = 0;
            List<Character> vowels = new ArrayList<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
            List<String> pairs = new ArrayList<>(Arrays.asList("ab", "cd", "pq", "xy"));
            while (s.hasNextLine()) {

                String line = s.nextLine();

                int vowelCount = 0;
                boolean twice = false;
                boolean pair = false;

                for (int i = 0; i < line.length() - 1; i++) {

                    if (vowels.contains(line.charAt(i))) {
                        vowelCount++;
                    }
                    if (line.charAt(i) == line.charAt(i + 1)) {
                        twice = true;
                    }
                    if (pairs.contains("" + line.charAt(i) + line.charAt(i + 1))) {
                        pair = true;
                    }

                }
                if (vowels.contains(line.charAt(line.length() - 1))) {
                    vowelCount++;
                }

                if (vowelCount >= 3 && twice && !pair) {
                    total++;
                }

            }

            System.out.println(total);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
