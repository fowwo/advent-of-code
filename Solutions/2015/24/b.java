// September 2nd, 2020
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class b {

    public static void main(String[] args) {

        List<Integer> weights = new ArrayList<>();

        try {

            File f = new File("input.txt");
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) weights.add(Integer.parseInt(s.nextLine()));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int sum = 0;
        for (int x : weights) {
            sum += x;
        }

        // ...separate the packages into four groups instead of three
        List<List<Integer>> combinations = findAllCombinationsWithSum(weights, sum / 4);
        System.out.println(combinations.size() + " total combination" + (combinations.size() == 1 ? "" : "s"));

        if (combinations.size() == 0) System.exit(0);

        // Find all combinations with the fewest packages
        List<List<Integer>> remaining = new ArrayList<>();
        int minimum = combinations.get(0).size();
        for (List<Integer> combination : combinations) {
            if (combination.size() < minimum) {
                minimum = combination.size();
                remaining.clear();
                remaining.add(combination);
            } else if (combination.size() == minimum) {
                remaining.add(combination);
            }
        }
        System.out.println(remaining.size() + " combination" + (remaining.size() == 1 ? "" : "s") + " of lowest cardinality");

        // Find all combinations where a second combination of the same sum exists (turns out all remaining combinations are already valid).
        /*
        for (int i = 0; i < remaining.size(); i++) {
            List<Integer> combination = remaining.get(i);
            List<Integer> copy = new ArrayList<>(weights);
            copy.removeIf(combination::contains);
            if (findAllCombinationsWithSum(copy, sum / 3).size() == 0) {
                remaining.remove(i);
                i--;
            }
        }
        System.out.println(remaining.size() + " valid combination" + (remaining.size() == 1 ? "" : "s") + " of lowest cardinality");
         */

        // Find minimum quantum entanglement
        long qe = Long.MAX_VALUE;
        int ci = -1;
        for (int i = 0; i < remaining.size(); i++) {
            long product = 1;
            for (Integer x : remaining.get(i)) {
                product *= x;
            }
            if (product < qe) {
                qe = product;
                ci = i;
            }
        }
        System.out.println("Minimum quantum entanglement: " + qe + " " + remaining.get(ci));

    }

    public static List<List<Integer>> findAllCombinationsWithSum(List<Integer> list, int targetSum) {

        int sum = 0;
        List<Integer> ai = new ArrayList<>();
        List<List<Integer>> c = new ArrayList<>();
        for (int i = 0; ai.size() != 0 || i != list.size(); i++) {

            // Show iteration
            /*
            for (int j = 0; j < list.size(); j++) {
                if (ai.contains(j)) {
                    System.out.printf("[%d] ", list.get(j));
                } else if (j == i) {
                    System.out.printf("{%d} ", list.get(j));
                } else {
                    System.out.printf(" %d  ", list.get(j));
                }
            }
            if (i == list.size()) {
                System.out.print("{ } ");
            }
            System.out.print("\n");
            */

            // Is 'i' out of bounds?
            if (i == list.size()) {

                // Go to last addend and remove
                i = ai.get(ai.size() - 1);
                sum -= list.get(ai.remove(ai.size() - 1));

            }

            // Would adding this number stay on or below the target?
            else if (list.get(i) + sum <= targetSum) {

                // Add addend
                sum += list.get(i);
                ai.add(i);

                // Is the target reached?
                if (sum == targetSum) {

                    // Add combination to list
                    List<Integer> copy = new ArrayList<>();
                    for (Integer x : ai) {
                        copy.add(list.get(x));
                    }
                    c.add(copy);

                    // Remove last element
                    i = ai.get(ai.size() - 1);
                    sum -= list.get(ai.remove(ai.size() - 1));

                }

            }

        }
        return c;

    }

}
