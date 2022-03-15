// September 6th, 2020
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class b {

    public static void main(String[] args) {

        int[][] matrix = new int[6][50];
        try {

            File f = new File("input.txt");
            Scanner s = new Scanner(f);

            while (s.hasNextLine()) {
                String[] line = s.nextLine().split(" ");
                switch (line[0]) {
                    case "rect":
                        String[] split = line[1].split("x");
                        for (int column = 0; column < Integer.parseInt(split[0]); column++) {
                            for (int row = 0; row < Integer.parseInt(split[1]); row++) {
                                matrix[row][column] = 1;
                            }
                        }
                        break;
                    case "rotate":
                        int pos = Integer.parseInt(line[2].split("=")[1]);
                        int amount = Integer.parseInt(line[4]);
                        int[] temp;
                        switch (line[1]) {
                            case "row":
                                temp = Arrays.copyOfRange(matrix[pos], 50 - amount, 50);
                                for (int i = 49; i >= amount; i--) {
                                    matrix[pos][i] = matrix[pos][i - amount];
                                }
                                for (int i = 0; i < amount; i++) {
                                    matrix[pos][i] = temp[i];
                                }
                                break;
                            case "column":
                                temp = new int[amount];
                                for (int i = 0; i < amount; i++) {
                                    temp[i] = matrix[6 - amount + i][pos];
                                }
                                for (int i = 5; i >= amount; i--) {
                                    matrix[i][pos] = matrix[i - amount][pos];
                                }
                                for (int i = 0; i < amount; i++) {
                                    matrix[i][pos] = temp[i];
                                }
                                break;
                        }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int row = 0; row < 6; row++) {
            for (int column = 0; column < 50; column++) {
                if (matrix[row][column] == 1) {
                    System.out.print("# ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.print("\n");
        }

    }

}
