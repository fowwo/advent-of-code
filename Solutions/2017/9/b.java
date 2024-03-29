// November 8th, 2020
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class b {

    public static void main(String[] args) {

        String line = "";

        try {

            File f = new File("input.txt");
            Scanner s = new Scanner(f);
            line = s.nextLine();
            s.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Remove garbage
        int total = 0;
        int a = -1;
        for (int i = 0; i < line.length(); i++) {
            switch (line.charAt(i)) {
                case '!':
                    line = line.substring(0, i) + line.substring(i + 2);
                    i--;
                    break;
                case '<':
                    if (a == -1) a = i;
                    break;
                case '>':
                    line = line.substring(0, a) + line.substring(i + 1);
                    total += i - a - 1;
                    i = a - 1;
                    a = -1;
                    break;
            }
        }

        System.out.println(total);

    }

}
