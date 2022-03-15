// May 5th, 2020
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class a {

    public static void main(String[] args) {

        try {

            File f = new File("input.txt");
            Scanner s = new Scanner(f);

            int totalCharacters = 0;
            int totalMemory = 0;
            while (s.hasNextLine()) {

                String line = s.nextLine();
                for (int i = 1; i < line.length() - 1; i++) {
                    totalMemory++;
                    if (line.charAt(i) == '\\') {
                        i++;
                        if (line.charAt(i) == 'x') {
                            i += 2;
                        }
                    }
                }
                totalCharacters += line.length();

            }

            System.out.printf("%d - %d = %d\n", totalCharacters, totalMemory, totalCharacters - totalMemory);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
