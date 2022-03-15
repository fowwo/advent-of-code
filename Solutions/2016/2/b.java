// September 6th, 2020
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class b {

    public static void main(String[] args) {

        try {

            File f = new File("input.txt");
            Scanner s = new Scanner(f);

            int x = 0;
            int y = 0;
            char[][] keypad = new char[][]{
                    { '-', '-', '1', '-', '-' },
                    { '-', '2', '3', '4', '-' },
                    { '5', '6', '7', '8', '9' },
                    { '-', 'A', 'B', 'C', '-' },
                    { '-', '-', 'D', '-', '-' },
            };
            while (s.hasNextLine()) {
                String line = s.nextLine();
                for (int i = 0; i < line.length(); i++) {
                    switch (line.charAt(i)) {
                        case 'U':
                            if (y > 0) y--;
                            if (keypad[y][x] == '-') y++;
                            break;
                        case 'D':
                            if (y < 4) y++;
                            if (keypad[y][x] == '-') y--;
                            break;
                        case 'L':
                            if (x > 0) x--;
                            if (keypad[y][x] == '-') x++;
                            break;
                        case 'R':
                            if (x < 4) x++;
                            if (keypad[y][x] == '-') x--;
                            break;
                    }
                }
                System.out.print(keypad[y][x]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
