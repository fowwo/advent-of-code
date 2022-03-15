// September 6th, 2020
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class _5b {

    public static void main(String[] args) {

        try {

            File f = new File("input.txt");
            Scanner s = new Scanner(f);
            String input = s.nextLine();
            s.close();

            int[] minArr = new int[26];
            for (int j = 0; j < 26; j++) {
                String temp = input.replaceAll(String.valueOf((char) (97 + j)), "").replaceAll(String.valueOf((char) (65 + j)), "");
                for (int i = 0; i < temp.length() - 1; i++) {
                    if (Character.toLowerCase(temp.charAt(i)) == Character.toLowerCase(temp.charAt(i + 1)) && temp.charAt(i) != temp.charAt(i + 1)) {
                        temp = temp.substring(0, i) + temp.substring(i + 2);
                        i = Math.max(i - 2, -1);
                    }
                }
                minArr[j] = temp.length();
            }

            int min = minArr[0];
            for (int i = 1; i < 26; i++) {
                if (minArr[i] < min) min = minArr[i];
            }
            System.out.println(min);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
