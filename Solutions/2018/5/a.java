// September 6th, 2020
package _2018;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class _5a {

    public static void main(String[] args) {

        try {

            File f = new File("input.txt");
            Scanner s = new Scanner(f);
            String input = s.nextLine();
            s.close();

            for (int i = 0; i < input.length() - 1; i++) {
                if (Character.toLowerCase(input.charAt(i)) == Character.toLowerCase(input.charAt(i + 1)) && input.charAt(i) != input.charAt(i + 1)) {
                    input = input.substring(0, i) + input.substring(i + 2);
                    i = Math.max(i - 2, -1);
                }
            }

            System.out.println(input.length());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
