// September 13th, 2020
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class a {

    public static void main(String[] args) {

        int[][][] matrix = new int[35][29][2];

        try {

            File f = new File("input.txt");
            Scanner s = new Scanner(f);

            // Skip first two lines
            s.nextLine();
            s.nextLine();

            while (s.hasNextLine()) {
                String[] line = s.nextLine().split("[ ]+");
                String[] pos = line[0].split("-");
                int x = Integer.parseInt(pos[1].substring(1));
                int y = Integer.parseInt(pos[2].substring(1));
                matrix[x][y] = new int[]{ Integer.parseInt(line[2].substring(0, line[2].length() - 1)), Integer.parseInt(line[1].substring(0, line[1].length() - 1)) };
            }
            s.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int total = 0;
        for (int x1 = 0; x1 < matrix.length; x1++) {
            for (int y1 = 0; y1 < matrix[x1].length; y1++) {
                for (int x2 = 0; x2 < matrix.length; x2++) {
                    for (int y2 = 0; y2 < matrix[x2].length; y2++) {
                        if (x1 == x2 && y1 == y2) continue;
                        if (matrix[x1][y1][0] != 0 && matrix[x1][y1][0] <= matrix[x2][y2][1] - matrix[x2][y2][0]) total++;
                    }
                }
            }
        }

        System.out.println(total);

    }

}
