// September 13th, 2020
package _2016;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class _25a {

    public static void main(String[] args) {

        List<String[]> instructions = new ArrayList<>();
        Map<String, Integer> registers = new HashMap<>();
        try {

            File f = new File("input/2016/25.txt");
            Scanner s = new Scanner(f);

            while (s.hasNextLine()) instructions.add(s.nextLine().split(" "));
            s.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int index = 0;
        boolean b = false;
        while (!b) {
            index++;
            b = true;
            int alt = 0;
            registers.put("a", index);
            registers.put("b", 0);
            registers.put("c", 0);
            registers.put("d", 0);

            // Run instructions
            for (int i = 0; i < instructions.size();) {
                String[] s = instructions.get(i);
                switch (s[0]) {
                    case "cpy":
                        i = cpy(instructions, i, registers);
                        break;
                    case "inc":
                        i = inc(instructions, i, registers);
                        break;
                    case "dec":
                        i = dec(instructions, i, registers);
                        break;
                    case "jnz":
                        i = jnz(instructions, i, registers);
                        break;
                    case "out":
                        i++;
                        int value = getValue(s[1], registers);
                        if (value != alt % 2) {
                            b = false;
                            break;
                        }
                        alt++;
                        break;
                }
                if (!b || alt == 1000) break;
            }
        }

        System.out.println(index);

    }

    public static int cpy(List<String[]> instructions, int i, Map<String, Integer> registers) {
        String[] s = instructions.get(i);
        if (registers.get(s[2]) != null) registers.put(s[2], getValue(s[1], registers));
        return i + 1;
    }
    public static int inc(List<String[]> instructions, int i, Map<String, Integer> registers) {
        String[] s = instructions.get(i);
        registers.put(s[1], registers.get(s[1]) + 1);
        return i + 1;
    }
    public static int dec(List<String[]> instructions, int i, Map<String, Integer> registers) {
        String[] s = instructions.get(i);
        registers.put(s[1], registers.get(s[1]) - 1);
        return i + 1;
    }
    public static int jnz(List<String[]> instructions, int i, Map<String, Integer> registers) {
        String[] s = instructions.get(i);
        if (getValue(s[1], registers) != 0) return i + getValue(s[2], registers);
        return i + 1;
    }

    public static int getValue(String value, Map<String, Integer> registers) {
        if (registers.get(value) != null) {
            return registers.get(value);
        }
        return Integer.parseInt(value);
    }

}
