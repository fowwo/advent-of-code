// May 5th, 2020
package _2015;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class _8b {

    public static void main(String[] args) {

        try {

            File f = new File("input/2015/8.txt");
            Scanner s = new Scanner(f);

            int totalNew = 0;
            int totalCharacters = 0;
            while (s.hasNextLine()) {

                String line = s.nextLine();
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == '\\' || line.charAt(i) == '"') {
                        totalNew += 2;
                    } else {
                        totalNew++;
                    }
                }
                totalNew += 2;
                totalCharacters += line.length();

            }

            System.out.printf("%d - %d = %d\n", totalNew, totalCharacters, totalNew - totalCharacters);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
