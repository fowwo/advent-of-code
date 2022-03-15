// September 2nd, 2020 - Does not run in a reasonable amount of time
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class b3 {

    public static void main(String[] args) {

        List<String> molecules = new ArrayList<>();
        Map<String, String> replacementMap = new HashMap<>();
        String input = "";

        try {

            File f = new File("input.txt");
            Scanner s = new Scanner(f);

            String line = s.nextLine();
            while (!line.equals("")) {
                String[] split = line.split(" => ");
                replacementMap.put(split[1], split[0]); // No molecule can be created by two different molecules
                molecules.add(split[1]);
                line = s.nextLine();
            }
            input = s.nextLine();
            s.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        molecules.sort((b, a) -> Integer.compare(a.length(), b.length()));

        int maxDepth = 10000;
        Set<String> memo = new HashSet<>();
        List<String> moleculeList = new ArrayList<>(Collections.singleton(input));
        List<Integer> moleculeIndexList = new ArrayList<>(Collections.singleton(0));
        List<Integer> stringIndexList = new ArrayList<>(Collections.singleton(0));

        while (moleculeList.size() > 0) {

            System.out.println(getLastElement(moleculeList));
            // System.out.println(moleculeList);
            // System.out.println(stringIndexList);
            // System.out.println(moleculeIndexList);

            // Contains e?
            if (getLastElement(moleculeList).contains("e")) {
                if (getLastElement(moleculeList).equals("e")) { maxDepth = Math.min(maxDepth, moleculeList.size() - 1); }

                // Remove "e"
                removeLastElement(moleculeList);
                removeLastElement(moleculeIndexList);
                removeLastElement(stringIndexList);

            }

            // Is 'i' out of bounds of the current molecule?
            else if (getLastElement(stringIndexList) == getLastElement(moleculeList).length()) {

                // Revert to last element
                removeLastElement(moleculeList);
                removeLastElement(moleculeIndexList);
                removeLastElement(stringIndexList);

            }

            // Can we go deeper?
            else if (moleculeList.size() < maxDepth) {
                boolean exit = false;
                for (int i = getLastElement(stringIndexList); i < getLastElement(moleculeList).length(); i++) {
                    for (int j = getLastElement(moleculeIndexList); j < molecules.size(); j++) {
                        String molecule = molecules.get(j);
                        String bigMolecule = getLastElement(moleculeList);
                        if (i + molecule.length() <= bigMolecule.length() && bigMolecule.substring(i, i + molecule.length()).equals(molecule)) {

                            // Increment last
                            incrementLastInteger(moleculeIndexList);

                            // Add new molecule
                            String newMolecule = bigMolecule.substring(0, i) + replacementMap.get(molecule) + bigMolecule.substring(i + molecule.length());
                            if (!memo.contains(newMolecule)) {
                                memo.add(newMolecule);
                                moleculeList.add(newMolecule);
                                moleculeIndexList.add(0);
                                stringIndexList.add(0);

                                exit = true;
                                break;
                            }
                        }
                        incrementLastInteger(moleculeIndexList);
                    }
                    if (exit) break;
                    setLastElement(moleculeIndexList, 0);
                    incrementLastInteger(stringIndexList);
                }
                if (exit) continue;

                // No new molecules can be created
                removeLastElement(moleculeList);
                removeLastElement(moleculeIndexList);
                removeLastElement(stringIndexList);

                if (moleculeIndexList.size() > 0) incrementLastInteger(moleculeIndexList);

            }

            // The element is too deep
            else {
                removeLastElement(moleculeList);
                removeLastElement(moleculeIndexList);
                removeLastElement(stringIndexList);
            }

        }

        System.out.println(maxDepth);

    }

    public static void removeLastElement(List<?> list) {
        list.remove(list.size() - 1);
    }
    public static <T> T getLastElement(List<T> list) {
        return list.get(list.size() - 1);
    }
    public static <T> void setLastElement(List<T> list, T x) {
        list.set(list.size() - 1, x);
    }
    public static void incrementLastInteger(List<Integer> list) {
        list.set(list.size() - 1, getLastElement(list) + 1);
    }

}
