// September 9th, 2020
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class b {

    public static void main(String[] args) {

        List<String[]> instructions = new ArrayList<>();
        Map<String, Integer> registers = new HashMap<>();
        registers.put("a", 0);
        registers.put("b", 0);
        registers.put("c", 1); // If you instead initialize register 'c' to be '1'
        registers.put("d", 0);
        try {

            File f = new File("input.txt");
            Scanner s = new Scanner(f);

            while (s.hasNextLine()) instructions.add(s.nextLine().split(" "));
            s.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < instructions.size();) {
            String[] s = instructions.get(i);
            switch (s[0]) {
                case "cpy":
                    if (registers.get(s[1]) != null) {
                        registers.put(s[2], registers.get(s[1]));
                    } else {
                        registers.put(s[2], Integer.parseInt(s[1]));
                    }
                    i++;
                    break;
                case "inc":
                    registers.put(s[1], registers.get(s[1]) + 1);
                    i++;
                    break;
                case "dec":
                    registers.put(s[1], registers.get(s[1]) - 1);
                    i++;
                    break;
                case "jnz":
                    int value;
                    if (registers.get(s[1]) != null) {
                        value = registers.get(s[1]);
                    } else {
                        value = Integer.parseInt(s[1]);
                    }
                    if (value != 0) {
                        if (registers.get(s[2]) != null) {
                            i += registers.get(s[2]);
                        } else {
                            i += Integer.parseInt(s[2]);
                        }
                    } else {
                        i++;
                    }
                    break;
            }
        }

        System.out.println(registers.get("a"));

    }

}
