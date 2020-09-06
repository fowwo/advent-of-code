// June 13th, 2020 - Does not run in a reasonable amount of time
package _2015;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class _19b {

    public static void main(String[] args) {

        try {

            File f = new File("input/2015/19.txt");
            Scanner s = new Scanner(f);
            HashMap<String, ArrayList<ArrayList<String>>> map = new HashMap<>();

            String line = s.nextLine();
            while (!line.equals("")) {
                String[] split = line.split(" => ");
                if (!map.containsKey(split[0])) {
                    map.put(split[0], new ArrayList<>());
                }
                map.get(split[0]).add(splitMolecule(split[1]));
                line = s.nextLine();
            }

            ArrayList<String> input = new ArrayList<>(splitMolecule(s.nextLine()));
            System.out.printf("Medicine Molecule size: %d elements\n", input.size());
            s.close();

            HashSet<ArrayList<String>> set = new HashSet<ArrayList<String>>(Collections.singleton(new ArrayList<String>(Collections.singletonList("e"))));

            int steps = 0;
            int highestSize = 1;
            while (!set.contains(input)) {
                System.out.printf("Stage %d, %d molecules, Largest Molecule: %d elements\n", steps, set.size(), highestSize);

                HashSet<ArrayList<String>> newSet = new HashSet<>();
                for (ArrayList<String> molecule : set) {
                    for (int i = 0; i < molecule.size(); i++) {
                        String element = molecule.get(i);
                        if (map.containsKey(element)) {
                            for (ArrayList<String> replacement : map.get(element)) {
                                ArrayList<String> newMolecule = (ArrayList<String>) molecule.clone();
                                newMolecule.set(i, replacement.get(0));
                                for (int j = 1; j < replacement.size(); j++) {
                                    newMolecule.add(i + j, replacement.get(j));
                                }
                                if (newMolecule.size() <= input.size()) { // The molecule always has more elements after replacement.
                                    newSet.add(newMolecule);
                                    highestSize = Math.max(newMolecule.size(), highestSize);
                                }
                            }
                        }

                    }
                }
                set = newSet;
                steps++;

            }
            System.out.println(steps);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<String> splitMolecule(String molecule) {
        return new ArrayList<String>(Arrays.asList(Pattern.compile("[A-Z][a-z]?+")
                .matcher(molecule)
                .results()
                .map(MatchResult::group)
                .toArray(String[]::new)));
    }

}
