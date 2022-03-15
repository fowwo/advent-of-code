// December 7th, 2020
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class a {

    public static void main(String[] args) {

        try {

            File f = new File("input.txt");
            Scanner s = new Scanner(f);
            int total = 0;

            while (s.hasNextLine()) {
                String[] str = s.nextLine().split(" ");
                int c = 0;
                for (int i = 0; i < str[2].length(); i++) {
                    if (str[2].charAt(i) == str[1].charAt(0)) c++;
                }
                String[] split = str[0].split("-");
                if (c >= Integer.parseInt(split[0]) && c <= Integer.parseInt(split[1])) total++;
            }

            System.out.println(total);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
