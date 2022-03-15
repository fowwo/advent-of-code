// September 9th, 2020
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class b {

    public static void main(String[] args) {

        String input = "";

        try {

            File f = new File("input.txt");
            Scanner s = new Scanner(f);
            input = s.nextLine();
            s.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(solve(input));

    }

    public static long solve(String s) {

        int value = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                int x = 4;
                while (s.charAt(i + x) != ')') {
                    x++;
                }
                String[] split = s.substring(i + 1, i + x).split("x");
                int copySize = Integer.parseInt(split[0]);
                int copyCount = Integer.parseInt(split[1]);
                return value + copyCount * solve(s.substring(i + x + 1, i + x + 1 + copySize)) + solve(s.substring(i + x + 1 + copySize));
            } else {
                value++;
            }
        }
        return value;

    }

}
