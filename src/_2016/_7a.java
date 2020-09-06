// September 6th, 2020
package _2016;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class _7a {

    public static void main(String[] args) {

        try {

            File f = new File("input/2016/7.txt");
            Scanner s = new Scanner(f);

            int total = 0;
            while (s.hasNextLine()) {
                String line = s.nextLine();
                boolean valid = false;
                for (int i = 0; i < line.length() - 3; i++) {
                    if (line.charAt(i) == line.charAt(i + 3) && line.charAt(i + 1) == line.charAt(i + 2) && line.charAt(i) != line.charAt(i + 1)) {
                        valid = true;
                        for (int j = i - 1; j >= 0; j--) {
                            if (line.charAt(j) == ']') break;
                            if (line.charAt(j) == '[') {
                                valid = false;
                                break;
                            }
                        }
                        if (!valid) break;
                    }
                }
                if (valid) total++;
            }

            System.out.println(total);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
