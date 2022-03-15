// September 2nd, 2020 - Does not run in a reasonable amount of time
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class b2 {

    public static void main(String[] args) {

        Set<String> set = new HashSet<>();
        Map<String, String> map = new HashMap<>();
        String input = "";

        try {

            File f = new File("input.txt");
            Scanner s = new Scanner(f);

            String line = s.nextLine();
            while (!line.equals("")) {
                String[] split = line.split(" => ");
                map.put(split[1], split[0]); // No molecule can be created by two different molecules
                set.add(split[1]);
                line = s.nextLine();
            }
            input = s.nextLine();
            s.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Set<String> molecules = new HashSet<>(Collections.singleton(input));
        int replacements = 0;
        while (!molecules.contains("e")) {
            Set<String> temp = new HashSet<>();
            for (String bigMolecule : molecules) {
                for (int i = 0; i < bigMolecule.length(); i++) {
                    for (String molecule : set) {
                        if (!bigMolecule.contains("e") && i + molecule.length() <= bigMolecule.length() && molecule.equals(bigMolecule.substring(i, i + molecule.length()))) {
                            temp.add(bigMolecule.substring(0, i) + map.get(molecule) + bigMolecule.substring(i + molecule.length()));
                        }
                    }
                }
            }
            molecules = temp;
            replacements++;
        }
        System.out.println(replacements);

    }

}
