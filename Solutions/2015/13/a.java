// September 1st, 2020
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class a {

    public static void main(String[] args) {

        HashMap<String, Integer> map = new HashMap<>();
        ArrayList<String> people = new ArrayList<>();

        try {

            File f = new File("input.txt");
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String[] line = s.nextLine().split(" ");
                int multiplier = 1;
                if (line[2].equals("lose")) {
                    multiplier = -1;
                }
                map.put(line[0] + line[10].substring(0, line[10].length() - 1), multiplier * Integer.parseInt(line[3]));
                if (!people.contains(line[0])) {
                    people.add(line[0]);
                }
            }
            s.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Go through all permutations
        int maxScore = 0;
        List<List<String>> permutations = Permutation.create(people);
        for (List<String> permutation : permutations) {
            int score = 0;
            for (int i = 0; i < permutation.size() - 1; i++) {
                score += map.get(permutation.get(i) + permutation.get(i + 1));
                score += map.get(permutation.get(i + 1) + permutation.get(i));
            }
            score += map.get(permutation.get(0) + permutation.get(permutation.size() - 1));
            score += map.get(permutation.get(permutation.size() - 1) + permutation.get(0));

            if (score > maxScore) {
                maxScore = score;
            }
        }

        System.out.println(maxScore);

    }

}
