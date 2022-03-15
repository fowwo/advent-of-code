// December 8th, 2020
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class _6b {

    public static void main(String[] args) {

        try {

            File f = new File("input.txt");
            Scanner s = new Scanner(f);

            int total = 0;
            int count = 0;
            int[] arr = new int[26];
            while (s.hasNextLine()) {
                String str = s.nextLine();
                if (!str.equals("")) {
                    count++;
                    for (int i = 0; i < str.length(); i++) {
                        int x = str.charAt(i);
                        if (x >= 97 && x <= 122) arr[x - 97]++;
                    }
                } else {
                    for (int i = 0; i < 26; i++) {
                        if (arr[i] == count) total++;
                        arr[i] = 0;
                    }
                    count = 0;
                }
            }
            for (int i = 0; i < 26; i++) {
                if (arr[i] == count) total++;
            }

            System.out.println(total);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
