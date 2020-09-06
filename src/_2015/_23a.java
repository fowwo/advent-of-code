// September 1st, 2020
package _2015;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class _23a {

    public static void main(String[] args) {

        List<String> instructions = new ArrayList<>();
        Map<String, Integer> registers = new HashMap<>();
        registers.put("a", 0);
        registers.put("b", 0);

        try {

            File f = new File("input/2015/23.txt");
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) instructions.add(s.nextLine());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < instructions.size(); i++) {
            String cmd = instructions.get(i).substring(0, 3);
            String arg = instructions.get(i).substring(4);
            switch (cmd) {
                case "hlf":
                    registers.put(arg, registers.get(arg) / 2);
                    break;
                case "tpl":
                    registers.put(arg, registers.get(arg) * 3);
                    break;
                case "inc":
                    registers.put(arg, registers.get(arg) + 1);
                    break;
                case "jmp":
                    i += Integer.parseInt(arg) - 1;
                    break;
                case "jie":
                    String[] arg2 = arg.split(", ");
                    if (registers.get(arg2[0]) % 2 == 0) {
                        i += Integer.parseInt(arg2[1]) - 1;
                    }
                    break;
                case "jio":
                    String[] arg3 = arg.split(", ");
                    if (registers.get(arg3[0]) == 1) {
                        i += Integer.parseInt(arg3[1]) - 1;
                    }
                    break;
            }
        }

        System.out.println(registers.get("b"));

    }

}
