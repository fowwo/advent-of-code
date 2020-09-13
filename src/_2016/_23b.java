// September 13th, 2020
package _2016;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class _23b {

    public static void main(String[] args) {

        List<String[]> instructions = new ArrayList<>();
        Map<String, Integer> registers = new HashMap<>();
        registers.put("a", 12); // Now you count 12.
        registers.put("b", 0);
        registers.put("c", 0);
        registers.put("d", 0);
        try {

            File f = new File("input/2016/23.txt");
            Scanner s = new Scanner(f);

            while (s.hasNextLine()) instructions.add(s.nextLine().split(" "));
            s.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Convert loops to mlt
        for (int i = 0; i < instructions.size(); i++) {
            String[] s = instructions.get(i);
            boolean valid = true;
            if (s[0].equals("jnz")) {
                int span = -getValue(s[2], registers);
                if (span > 0) {

                    // Check for jnz or cpy inside loop
                    for (int j = i - span; j < i; j++) {
                        String[] s2 = instructions.get(j);
                        if (s2[0].equals("jnz") || s2[0].equals("cpy")) {
                            valid = false;
                            break;
                        }
                    }
                    if (!valid) continue;

                    // Check for jnz jump into loop
                    for (int j = 0; j < instructions.size(); j++) {
                        if (j >= i - span && j <= i) continue;
                        String[] s2 = instructions.get(j);
                        if (s2[0].equals("jnz")) {
                            int to = getValue(s2[2], registers);
                            if (j + to >= i - span && j + to <= i) {
                                valid = false;
                                break;
                            }
                        }
                    }

                    if (valid) {
                        int[] abcd = new int[4];
                        for (int j = i - span; j < i; j++) {
                            String[] instruction = instructions.get(j);
                            switch (instruction[0]) {
                                case "inc":
                                    switch (instruction[1]) {
                                        case "a":
                                            abcd[0]++;
                                            break;
                                        case "b":
                                            abcd[1]++;
                                            break;
                                        case "c":
                                            abcd[2]++;
                                            break;
                                        case "d":
                                            abcd[3]++;
                                            break;
                                    }
                                    break;
                                case "dec":
                                    switch (instruction[1]) {
                                        case "a":
                                            abcd[0]--;
                                            break;
                                        case "b":
                                            abcd[1]--;
                                            break;
                                        case "c":
                                            abcd[2]--;
                                            break;
                                        case "d":
                                            abcd[3]--;
                                            break;
                                    }
                                    break;
                            }
                        }
                        int repl = 0;
                        if (abcd[0] != 0) {
                            instructions.set(i - span + repl, new String[]{ "aml", "a", Integer.toString(abcd[0]), s[1] });
                            repl++;
                        }
                        if (abcd[1] != 0) {
                            instructions.set(i - span + repl, new String[]{ "aml", "b", Integer.toString(abcd[1]), s[1] });
                            repl++;
                        }
                        if (abcd[2] != 0) {
                            instructions.set(i - span + repl, new String[]{ "aml", "c", Integer.toString(abcd[2]), s[1] });
                            repl++;
                        }
                        if (abcd[3] != 0) {
                            instructions.set(i - span + repl, new String[]{ "aml", "d", Integer.toString(abcd[3]), s[1] });
                            repl++;
                        }
                        for (int j = repl; j < 4; j++) instructions.set(i - span + repl, new String[]{ "aml", "a", "0", "0" }); // This keeps the instruction count the same
                    }
                }
            }
        }

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
                case "tgl":
                    i = tgl(instructions, i, registers);
                    break;
                case "aml":
                    i = aml(instructions, i, registers);
                    break;
            }
        }

        System.out.println(registers.get("a"));

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
    public static int tgl(List<String[]> instructions, int i, Map<String, Integer> registers) {
        String[] s = instructions.get(i);
        int value = getValue(s[1], registers);
        if (i + value < instructions.size()) {
            String[] instruction = instructions.get(i + value);
            switch (instruction[0]) {
                case "cpy":
                    instruction[0] = "jnz";
                    break;
                case "inc":
                    instruction[0] = "dec";
                    break;
                case "dec":
                case "tgl":
                    instruction[0] = "inc";
                    break;
                case "jnz":
                    instruction[0] = "cpy";
                    break;
            }
        }
        return i + 1;
    }

    public static int getValue(String value, Map<String, Integer> registers) {
        if (registers.get(value) != null) {
            return registers.get(value);
        }
        return Integer.parseInt(value);
    }
    public static int aml(List<String[]> instructions, int i, Map<String, Integer> registers) {
        String[] s = instructions.get(i);
        int a = getValue(s[2], registers);
        int b = getValue(s[3], registers);
        registers.put(s[1], registers.get(s[1]) + a * b);
        return i + 1;
    }

}
