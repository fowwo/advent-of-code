// September 5th, 2020
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class b4 {

    public static void main(String[] args) {

        Map<String, List<String>> replacementMap = new HashMap<>();
        Map<String, String> breakdown = new HashMap<>();
        List<String> molecules = new ArrayList<>();
        String input = "";

        try {

            File f = new File("input.txt");
            Scanner s = new Scanner(f);

            String line = s.nextLine();
            while (!line.equals("")) {
                String[] split = line.split(" => ");
                if (!replacementMap.containsKey(split[0])) {
                    replacementMap.put(split[0], new ArrayList<>());
                }
                replacementMap.get(split[0]).add(split[1]);
                breakdown.put(split[1], split[0]); // No molecule can be created by two different molecules
                molecules.add(split[1]);
                line = s.nextLine();
            }
            input = s.nextLine();

            s.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        /* Important Notes:
            1. Irreplaceable elements: [Ar, C, Y, Rn]
        *** 2. Every 'Rn' is followed by an 'Ar' and every 'Ar' is preceded by an 'Rn'
                -> RnFAr, RnFYFAr, RnFYFYFAr, RnMgAr, RnFYMgAr, RnMgYFAr, RnAlAr
            2. All 'C's are immediately followed by 'Rn'
            4. All 'Y's have an 'RnF' or 'RnMg' immediately before and an 'FAr' or 'MgAr' immediately after except for 'YFY', in which case treat 'YFY' as 'Y'
                -> RnFYFAr, RnMgYFAr, RnFYMgAr, RnMgYMgAr, RnFYFYFAr, RnMgYFYFAr, RnFYFYMgAr, RnMgYFYMgAr
            5. First step is e -> NAl */

        // Breakdown
        int totalDepth = 0;

        // Every 'Rn' is followed by an 'Ar', and every 'Ar' is preceded by an 'Rn'
        String[] not = new String[]{ "F", "FYF", "FYFYF", "Mg", "FYMg", "MgYF", "Al" };
        int[] biArr = new int[not.length];
        int[] x = findFromTo(input, "Rn", "Ar", not);
        String temp = ""; // Temporarily removing the first Rn...Ar (very long simplification)
        while (x != null) {
            if (x[0] == 3 && x[1] == 243) {
                // This is the first 'Rn...Ar'
                temp = input.substring(x[0] - 2, x[1] + 2);
                input = input.substring(0, x[0] - 2) + input.substring(x[1] + 2);
                x = findFromTo(input, "Rn", "Ar", not);
                continue;
            }
            System.out.println(input);
            System.out.println(Arrays.toString(x));
            for (int i = 0; i < biArr.length; i++) biArr[i] = breakdownInto(input.substring(x[0], x[1]), not[i], breakdown, molecules);

            // Check for exclusive-or
            boolean allfalse = true;
            for (int i = 0; i < biArr.length; i++) {
                if (biArr[i] != -1) {
                    allfalse = false;
                    boolean ex = true;
                    for (int j = 0; j < biArr.length; j++) {
                        if (i != j && biArr[j] != -1) {
                            ex = false;
                            break;
                        }
                    }
                    if (ex) {
                        totalDepth += biArr[i];
                        input = input.substring(0, x[0]) + not[i] + input.substring(x[1]);
                    } else {
                        System.out.println(input.substring(x[0], x[1]) + " can be broken down into multiple things");
                        System.exit(0);
                    }
                    break;
                }
            }
            if (allfalse) {
                System.out.println(input.substring(x[0], x[1]) + " cannot be broken down (should not be possible)");
                System.exit(0);
            }
            x = findFromTo(input, "Rn", "Ar", not);
        }

        // Adding back the removed 'Rn...Ar'
        String left = input.substring(0, 1) + temp; // Left is 'CRn...Ar' (Should simplify to one element)
        String right = input.substring(1); // Right is the rest (Should simplify to one element)
        System.out.println(input);
        System.out.println(totalDepth);

        // Splitting the problem into two
        String[] possibleLeft  = new String[]{ "H", "N", "O" };
        String[] possibleRight = new String[]{ "F", "Al", "Mg" };

        // Solving the right side
        int[] piArr = new int[possibleRight.length];
        for (int i = 0; i < piArr.length; i++) piArr[i] = breakdownInto(right, possibleRight[i], breakdown, molecules);

        // Check for exclusive-or (not necessary)
        for (int i = 0; i < piArr.length; i++) {
            if (piArr[i] != -1) {
                boolean ex = true;
                for (int j = 0; j < piArr.length; j++) {
                    if (i != j && piArr[j] != -1) {
                        ex = false;
                        break;
                    }
                }
                if (ex) {
                    totalDepth += piArr[i];
                    System.out.println(left + possibleRight[i]);
                    System.out.println(totalDepth);

                    // Solving the left side
                    totalDepth += breakdownInto(left, possibleLeft[i], breakdown, molecules);
                    System.out.println(possibleLeft[i] + possibleRight[i]);
                    System.out.println(totalDepth);
                    totalDepth += 1; // Last conversion to 'e'
                    System.out.println("e");
                    System.out.println(totalDepth);
                } else {
                    System.out.println(right + " can be broken down into multiple things");
                    System.exit(0);
                }
                break;
            }
        }

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

    public static int breakdownInto(String input, String into, Map<String, String> breakdown, List<String> molecules) {
        int depth = -1;
        Set<String> memo = new HashSet<>();
        List<String> moleculeList = new ArrayList<>(Collections.singleton(input));
        List<Integer> moleculeIndexList = new ArrayList<>(Collections.singleton(0));
        List<Integer> stringIndexList = new ArrayList<>(Collections.singleton(0));

        while (moleculeList.size() > 0) {

            // Is target?
            if (getLastElement(moleculeList).equals(into)) {
                depth = moleculeList.size() - 1;

                // Remove last element
                removeLastElement(moleculeList);
                removeLastElement(moleculeIndexList);
                removeLastElement(stringIndexList);

                // Afternote: I could have inserted a break here. "My request for the "fewest number of steps" is a decoy - there is only one number of steps possible..."
            }

            // Contains e?
            else if (getLastElement(moleculeList).contains("e")) {

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
            else if (depth == -1 || moleculeList.size() < depth) {
                boolean exit = false;
                for (int i = getLastElement(stringIndexList); i < getLastElement(moleculeList).length(); i++) {
                    for (int j = getLastElement(moleculeIndexList); j < molecules.size(); j++) {
                        String molecule = molecules.get(j);
                        String bigMolecule = getLastElement(moleculeList);
                        if (i + molecule.length() <= bigMolecule.length() && bigMolecule.substring(i, i + molecule.length()).equals(molecule)) {

                            // Increment last
                            incrementLastInteger(moleculeIndexList);

                            // Add new molecule
                            String newMolecule = bigMolecule.substring(0, i) + breakdown.get(molecule) + bigMolecule.substring(i + molecule.length());
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
        return depth;
    }

    public static int[] findFromTo(String input, String from, String to, List<String> not) {
        int last = -1;
        boolean skip = false;
        for (int i = from.length(); i < input.length() - 1; i++) {
            if (input.substring(i - from.length(), i).equals(from)) {
                boolean valid = true;
                for (String notE : not) {
                    if (input.substring(i, i + notE.length()).equals(notE) && input.substring(i + notE.length(), i + notE.length() + to.length()).equals(to)) {
                        valid = false;
                        skip = true;
                        break;
                    }
                }
                if (valid) {
                    last = i;
                    skip = false;
                }
            }
            else if (last != -1 && input.substring(i, i + to.length()).equals(to)) {
                if (skip) {
                    skip = false;
                } else if (!not.contains(input.substring(last, i))) {
                    return new int[]{ last, i };
                }
            }
        }
        return null;
    }
    public static int[] findFromTo(String input, String from, String to, String[] not) {
        return findFromTo(input, from, to, Arrays.asList(not));
    }
    public static int[] findFromTo(String input, String from, String to, String not) {
        return findFromTo(input, from, to, new ArrayList<>(Collections.singleton(not)));
    }
    public static int[] findFromTo(String input, String from, String to) {
        return findFromTo(input, from, to, "");
    }

}
