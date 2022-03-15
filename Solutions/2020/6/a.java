// December 8th, 2020
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class a {

    public static void main(String[] args) {

        try {

            File f = new File("input.txt");
            Scanner s = new Scanner(f);

            int total = 0;
            int[] arr = new int[26];
            while (s.hasNextLine()) {
                String str = s.nextLine();
                if (!str.equals("")) {
                    for (int i = 0; i < str.length(); i++) {
                        int x = str.charAt(i);
                        if (x >= 97 && x <= 122) arr[x - 97] = 1;
                    }
                } else {
                    for (int i = 0; i < 26; i++) {
                        if (arr[i] == 1) total++;
                        arr[i] = 0;
                    }
                }
            }
            for (int i = 0; i < 26; i++) {
                if (arr[i] == 1) total++;
            }

            System.out.println(total);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
