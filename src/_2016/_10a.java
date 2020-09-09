// September 9th, 2020
package _2016;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class _10a {

    public static void main(String[] args) {

        List<Integer> find = new ArrayList<>(Arrays.asList(61, 17));
        List<String[]> instructions = new ArrayList<>();
        Map<Integer, List<Integer>> bots = new HashMap<>();
        Map<Integer, List<Integer>> output = new HashMap<>();
        try {

            File f = new File("input/2016/10.txt");
            Scanner s = new Scanner(f);

            while (s.hasNextLine()) {
                String[] line = s.nextLine().split(" ");
                if (line[0].equals("value")) {
                    int value = Integer.parseInt(line[1]);
                    int bot = Integer.parseInt(line[5]);

                    if (bots.get(bot) == null) bots.put(bot, new ArrayList<>());
                    bots.get(bot).add(value);

                } else {
                    instructions.add(line);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (instructions.size() > 0) {
            for (int i = 0; i < instructions.size(); i++) {
                String[] s = instructions.get(i);

                int from = Integer.parseInt(s[1]);
                if (bots.get(from) == null) bots.put(from, new ArrayList<>());
                if (bots.get(from).size() < 2) continue;

                int low = Integer.parseInt(s[6]);
                int high = Integer.parseInt(s[11]);

                bots.get(from).sort(Integer::compareTo);

                if (s[5].equals("bot")) {
                    if (bots.get(low) == null) bots.put(low, new ArrayList<>());
                    bots.get(low).add(bots.get(from).remove(0));
                } else {
                    if (output.get(low) == null) output.put(low, new ArrayList<>());
                    output.get(low).add(bots.get(from).remove(0));
                }

                if (bots.get(low).equals(find)) {
                    System.out.println(low);
                    System.exit(0);
                }

                if (s[10].equals("bot")) {
                    if (bots.get(high) == null) bots.put(high, new ArrayList<>());
                    bots.get(high).add(bots.get(from).remove(0));
                } else {
                    if (output.get(high) == null) output.put(high, new ArrayList<>());
                    output.get(high).add(bots.get(from).remove(0));
                }

                if (bots.get(high).equals(find)) {
                    System.out.println(high);
                    System.exit(0);
                }

                instructions.remove(i);
                i--;
            }
        }

    }

}
