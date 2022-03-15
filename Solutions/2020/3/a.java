// December 3rd, 2020
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _3a {

    public static void main(String[] args) {

        int[][] matrix = new int[0][];

        try {

            File f = new File("input.txt");
            Scanner s = new Scanner(f);
            List<String> list = new ArrayList<>();
            while (s.hasNextLine()) list.add(s.nextLine());

            matrix = new int[list.size()][list.get(0).length()];
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (list.get(i).charAt(j) == '.') matrix[i][j] = 0;
                    else matrix[i][j] = 1;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int x = 0;
        int y = 0;
        int total = 0;

        while (y < matrix.length) {
            if (matrix[y][x] == 1) total++;
            x = (x + 3) % matrix[0].length;
            y++;
        }

        System.out.println(total);

    }

}
