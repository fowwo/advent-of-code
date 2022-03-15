// September 18th, 2020
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class b {

    public static void main(String[] args) {

        String bottom = "veboyvy"; // 7a solution
        Map<String, List<String>> discs = new HashMap<>();
        Map<String, Integer> weights = new HashMap<>();

        try {

            File f = new File("input.txt");
            Scanner s = new Scanner(f);

            while (s.hasNextLine()) {

                String[] line = s.nextLine().split(" -> ");
                String[] split = line[0].split("[ ]+\\(");

                String from = split[0];
                int weight = Integer.parseInt(split[1].substring(0, split[1].length() - 1));
                weights.put(from, weight);
                discs.put(from, new ArrayList<>());

                if (line.length == 2) {
                    split = line[1].split("[ ]+");

                    for (int i = 0; i < split.length - 1; i++) discs.get(from).add(split[i].substring(0, split[i].length() - 1));
                    discs.get(from).add(split[split.length - 1]);
                }

            }

            s.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Find unbalanced disc
        Map<String, Integer> memo = new HashMap<>();
        int target;
        if (getTotalWeight(discs.get(bottom).get(0), discs, weights, memo) == getTotalWeight(discs.get(bottom).get(1), discs, weights, memo)) {
            target = getTotalWeight(discs.get(bottom).get(0), discs, weights, memo);
        } else if (getTotalWeight(discs.get(bottom).get(0), discs, weights, memo) == getTotalWeight(discs.get(bottom).get(2), discs, weights, memo)) {
            target = getTotalWeight(discs.get(bottom).get(0), discs, weights, memo);
        } else {
            target = getTotalWeight(discs.get(bottom).get(1), discs, weights, memo);
        }
        target = weights.get(bottom) + target * discs.get(bottom).size();
        getUnbalancedProgram(bottom, target, discs, weights, memo);

    }

    private static int getTotalWeight(String disc, Map<String, List<String>> discs, Map<String, Integer> weights, Map<String, Integer> memo) {
        if (memo.get(disc) != null) return memo.get(disc);
        int total = weights.get(disc);
        for (String sub : discs.get(disc)) {
            total += getTotalWeight(sub, discs, weights, memo);
        }
        memo.put(disc, total);
        return total;
    }
    private static String getUnbalancedProgram(String disc, int target, Map<String, List<String>> discs, Map<String, Integer> weights, Map<String, Integer> memo) {
        if (!isBalanced(disc, discs, weights, memo)) {
            target = (target - weights.get(disc)) / discs.get(disc).size();
            if (discs.get(disc).size() == 1) return getUnbalancedProgram(discs.get(disc).get(0), target, discs, weights, memo);
            for (String d : discs.get(disc)) {
                if (getTotalWeight(d, discs, weights, memo) != target) {
                    return getUnbalancedProgram(d, target, discs, weights, memo);
                }
            }
        }
        System.out.println(target - discs.get(disc).size() * getTotalWeight(discs.get(disc).get(0), discs, weights, memo));
        return disc;
    }
    private static boolean isBalanced(String disc, Map<String, List<String>> discs, Map<String, Integer> weights, Map<String, Integer> memo) {
        if (discs.get(disc).size() != 0) {
            int x = getTotalWeight(discs.get(disc).get(0), discs, weights, memo);
            for (String sub : discs.get(disc)) {
                if (getTotalWeight(sub, discs, weights, memo) != x) {
                    return false;
                }
            }
        }
        return true;
    }

}
