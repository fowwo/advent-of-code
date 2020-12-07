// December 7th, 2020
package _2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class _2b {

    public static void main(String[] args) {

        try {

            File f = new File("input/2020/2.txt");
            Scanner s = new Scanner(f);
            int total = 0;

            while (s.hasNextLine()) {
                String[] str = s.nextLine().split(" ");
                String[] split = str[0].split("-");
                char a = str[2].charAt(Integer.parseInt(split[0]) - 1);
                char b = str[2].charAt(Integer.parseInt(split[1]) - 1);
                if ((a == str[1].charAt(0) || b == str[1].charAt(0)) && a != b) total++;
            }

            System.out.println(total);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
