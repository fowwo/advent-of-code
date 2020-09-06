// June 1st, 2020
package _2015;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class _7b {

    public static void main(String[] args) {

        Map<String, Integer> map = new HashMap<>();
        ArrayList<String> lines = new ArrayList<>();

        map.put("b", 46065); // Now, take the signal you got on wire a, override wire b to that signal...

        try {

            File f = new File("input/2015/7.txt");
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                lines.add(s.nextLine());
            }
            s.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int i = -1;
        while (lines.size() != 0) {

            i++;
            if (i == lines.size()) {
                i = 0;
            }

            // Read line
            String[] line = lines.get(i).split(" -> ");
            String[] left = line[0].split(" ");

            if (left.length == 1) {

                if (line[1].equals("b")) { // override wire b
                    lines.remove(i);
                    i--;
                    continue;
                }

                int a;

                try {
                    a = Integer.parseInt(left[0]);
                } catch (NumberFormatException e) {
                    if (map.containsKey(left[0])) {
                        a = map.get(left[0]);
                    } else {
                        continue;
                    }
                }

                map.put(line[1], a);
                lines.remove(i);
                i--;

            } else if (left[1].equals("AND")) {

                int a;
                int b;

                try {
                    a = Integer.parseInt(left[0]);
                } catch (NumberFormatException e) {
                    if (map.containsKey(left[0])) {
                        a = map.get(left[0]);
                    } else {
                        continue;
                    }
                }

                try {
                    b = Integer.parseInt(left[2]);
                } catch (NumberFormatException e) {
                    if (map.containsKey(left[2])) {
                        b = map.get(left[2]);
                    } else {
                        continue;
                    }
                }

                map.put(line[1], a & b);
                lines.remove(i);
                i--;

            } else if (left[1].equals("OR")) {

                int a;
                int b;

                try {
                    a = Integer.parseInt(left[0]);
                } catch (NumberFormatException e) {
                    if (map.containsKey(left[0])) {
                        a = map.get(left[0]);
                    } else {
                        continue;
                    }
                }

                try {
                    b = Integer.parseInt(left[2]);
                } catch (NumberFormatException e) {
                    if (map.containsKey(left[2])) {
                        b = map.get(left[2]);
                    } else {
                        continue;
                    }
                }

                map.put(line[1], a | b);
                lines.remove(i);
                i--;

            } else if (left[1].equals("LSHIFT")) {

                int a;
                int b;

                try {
                    a = Integer.parseInt(left[0]);
                } catch (NumberFormatException e) {
                    if (map.containsKey(left[0])) {
                        a = map.get(left[0]);
                    } else {
                        continue;
                    }
                }

                try {
                    b = Integer.parseInt(left[2]);
                } catch (NumberFormatException e) {
                    if (map.containsKey(left[2])) {
                        b = map.get(left[2]);
                    } else {
                        continue;
                    }
                }

                map.put(line[1], a << b);
                lines.remove(i);
                i--;

            } else if (left[1].equals("RSHIFT")) {

                int a;
                int b;

                try {
                    a = Integer.parseInt(left[0]);
                } catch (NumberFormatException e) {
                    if (map.containsKey(left[0])) {
                        a = map.get(left[0]);
                    } else {
                        continue;
                    }
                }

                try {
                    b = Integer.parseInt(left[2]);
                } catch (NumberFormatException e) {
                    if (map.containsKey(left[2])) {
                        b = map.get(left[2]);
                    } else {
                        continue;
                    }
                }

                map.put(line[1], a >> b);
                lines.remove(i);
                i--;

            } else if (left[0].equals("NOT")) {

                int a;

                try {
                    a = Integer.parseInt(left[1]);
                } catch (NumberFormatException e) {
                    if (map.containsKey(left[1])) {
                        a = map.get(left[1]);
                    } else {
                        continue;
                    }
                }

                map.put(line[1], 65535 - a);
                lines.remove(i);
                i--;

            } else {

                System.out.printf("idk what this is: %s\n", lines.get(i));
                System.exit(1);

            }

        }

        System.out.printf("a: %d\n", map.get("a"));

    }

}
