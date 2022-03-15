// May 5th, 2020
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class b {

    public static void main(String[] args) {

        try {

            File f = new File("input.txt");
            Scanner s = new Scanner(f);

            int total = 0;
            while (s.hasNextLine()) {

                String line = s.nextLine();

                // It contains a pair of any two letters that appears at least twice in the string without
                boolean twice = false;
                for (int i = 0; i < line.length() - 3; i++) {
                    for (int j = i + 2; j < line.length() - 1; j++) {
                        if (line.charAt(i) == line.charAt(j) && line.charAt(i + 1) == line.charAt(j + 1)) {
                            twice = true;
                            break;
                        }
                    }
                }

                if (twice) {

                    // It contains at least one letter which repeats with exactly one letter between them
                    for (int i = 2; i < line.length(); i++) {
                        if (line.charAt(i) == line.charAt(i - 2)) {
                            total++;
                            break;
                        }
                    }

                }

            }

            System.out.println(total);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
