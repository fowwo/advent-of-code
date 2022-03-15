// September 8th, 2020
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class a {

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

        for (int i = 0; i < input.length() - 4; i++) {
            if (input.charAt(i) == '(') {
                int x = 4;
                while (input.charAt(i + x) != ')') {
                    x++;
                }
                String[] split = input.substring(i + 1, i + x).split("x");
                int copySize = Integer.parseInt(split[0]);
                int copyCount = Integer.parseInt(split[1]);
                String repeat = input.substring(i + x + 1, i + x + 1 + copySize);

                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < copyCount; j++) {
                    sb.append(repeat);
                }
                input = input.substring(0, i) + sb.toString() + input.substring(i + x + 1 + copySize);
                i += copyCount * copySize - 1;
            }
        }

        System.out.println(input.length());

    }

}
