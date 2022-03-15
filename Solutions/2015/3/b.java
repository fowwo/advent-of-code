// May 5th, 2020
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class b {

    public static void main(String[] args) {

        try {

            File f = new File("input.txt");
            Scanner s = new Scanner(f);
            String str = s.nextLine();
            s.close();

            int[][] matrix = new int[10001][10001];
            int x1 = 5000;
            int y1 = 5000;
            int x2 = 5000;
            int y2 = 5000;
            int total = 0;

            // Initialize matrix to 0
            for (int i = 0; i < 10001; i++) {
                for (int j = 0; j < 10001; j++) {
                    matrix[i][j] = 0;
                }
            }

            matrix[x1][y1]++;

            // Move
            for (int i = 0; i < str.length(); i += 2) {
                // Move Santa
                char c = str.charAt(i);
                if (c == '>') {
                    x1++;
                } else if (c == 'v') {
                    y1++;
                } else if (c == '<') {
                    x1--;
                } else if (c == '^') {
                    y1--;
                }
                matrix[x1][y1]++;

                // Move Robo-Santa
                c = str.charAt(i + 1);
                if (c == '>') {
                    x2++;
                } else if (c == 'v') {
                    y2++;
                } else if (c == '<') {
                    x2--;
                } else if (c == '^') {
                    y2--;
                }
                matrix[x2][y2]++;
            }

            // Count visited houses
            for (int i = 0; i < 10001; i++) {
                for (int j = 0; j < 10001; j++) {
                    if (matrix[i][j] > 0){
                        total++;
                    }
                }
            }

            System.out.println(total);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
