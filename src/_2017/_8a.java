// November 8th, 2020
package _2017;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class _8a {

    public static void main(String[] args) {

        Map<String, Integer> registers = new HashMap<>();

        try {

            File f = new File("input/2017/8.txt");
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
                }

            }

            s.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Find largest value in register
        int max = Integer.MIN_VALUE;
        for (int x : registers.values()) {
            if (x > max) max = x;
        }
        System.out.println(max);

    }

}
