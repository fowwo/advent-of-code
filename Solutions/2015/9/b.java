// September 1st, 2020
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class b {

    public static void main(String[] args) {

        Map<Set<String>, Integer> map = new HashMap<>();
        List<String> locations = new ArrayList<>();

        try {

            File f = new File("input.txt");
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String[] line = s.nextLine().split(" ");
                Set<String> pair = new HashSet<>();
                pair.add(line[0]);
                pair.add(line[2]);
                map.put(pair, Integer.parseInt(line[4]));
                if (!locations.contains(line[0])) {
                    locations.add(line[0]);
                }
                if (!locations.contains(line[2])) {
                    locations.add(line[2]);
                }
            }
            s.close();

            // Go through all permutations
            int max = 0;
            List<List<String>> permutations = Permutation.create(locations);
            for (List<String> permutation : permutations) {
                int total = 0;
                for (int i = 0; i < permutation.size() - 1; i++) {
                    Set<String> pair = new HashSet<>();
                    pair.add(permutation.get(i));
                    pair.add(permutation.get(i + 1));
                    total += map.get(pair);
                }
                if (total > max) {
                    max = total;
                }
            }

            System.out.println(max);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
