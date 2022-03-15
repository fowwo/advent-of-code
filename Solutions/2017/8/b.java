// November 8th, 2020
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class b {

    public static void main(String[] args) {

        Map<String, Integer> registers = new HashMap<>();
        int max = Integer.MIN_VALUE;

        try {

            File f = new File("input.txt");
            Scanner s = new Scanner(f);

            while (s.hasNextLine()) {

                String[] line = s.nextLine().split(" ");

                if (!registers.containsKey(line[0])) registers.put(line[0], 0);
                if (!registers.containsKey(line[4])) registers.put(line[4], 0);

                int value = Integer.parseInt(line[6]);
                boolean b = false;
                switch (line[5]) {
                    case ">":
                        if (registers.get(line[4]) > value) b = true;
                        break;
                    case "<":
                        if (registers.get(line[4]) < value) b = true;
                        break;
                    case ">=":
                        if (registers.get(line[4]) >= value) b = true;
                        break;
                    case "<=":
                        if (registers.get(line[4]) <= value) b = true;
                        break;
                    case "==":
                        if (registers.get(line[4]) == value) b = true;
                        break;
                    case "!=":
                        if (registers.get(line[4]) != value) b = true;
                        break;
                }

                if (b) {
                    switch (line[1]) {
                        case "inc":
                            registers.put(line[0], registers.get(line[0]) + Integer.parseInt(line[2]));
                            break;
                        case "dec":
                            registers.put(line[0], registers.get(line[0]) - Integer.parseInt(line[2]));
                            break;
                    }
                    if (registers.get(line[0]) > max) max = registers.get(line[0]);
                }

            }

            s.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(max);

    }

}
