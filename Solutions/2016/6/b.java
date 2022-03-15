// September 6th, 2020
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class b {

    public static void main(String[] args) {

        try {

            File f = new File("input.txt");
            Scanner s = new Scanner(f);

            String line = s.nextLine();
            int wordLength = line.length();
            int[][] matrix = new int[wordLength][26];

            for (int i = 0; i < wordLength; i++) {
                matrix[i][line.charAt(i) - 97]++;
            }
            while (s.hasNextLine()) {
                line = s.nextLine();
                for (int i = 0; i < wordLength; i++) {
                    matrix[i][line.charAt(i) - 97]++;
                }
            }

            for (int i = 0; i < wordLength; i++) {
                int mi = 0;
                for (int j = 1; j < 26; j++) {
                    if (matrix[i][j] < matrix[i][mi]) {
                        mi = j;
                    }
                }
                System.out.print((char) (mi + 97));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
