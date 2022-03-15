// December 8th, 2020
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class _7a {

    public static void main(String[] args) {

        try {

            File f = new File("input.txt");
            Scanner s = new Scanner(f);
            Map<String, Set<String>> into = new HashMap<>();

            while (s.hasNextLine()) {
                String[] str = s.nextLine().split(" ");
                if (str.length != 7) {
                    for (int i = 5; i < str.length; i += 4) {
                        if (!into.containsKey(str[i] + str[i + 1])) into.put(str[i] + str[i + 1], new HashSet<>());
                        into.get(str[i] + str[i + 1]).add(str[0] + str[1]);
                    }
                }
            }
            System.out.println(getAllOuterCases("shinygold", into).size());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static Set<String> getAllOuterCases(String c, Map<String, Set<String>> map) {
        Set<String> all = new HashSet<>();
        if (map.containsKey(c)) {
            Set<String> current = new HashSet<>(Collections.singleton(c));
            while (current.size() != 0) {
                Set<String> temp = new HashSet<>();
                for (String c2 : current) {
                    temp.addAll(getOuterCases(c2, map));
                }
                current = temp;
                all.addAll(current);
            }
        }
        return all;
    }
    public static Set<String> getOuterCases(String c, Map<String, Set<String>> map) {
        if (map.containsKey(c)) {
            return map.get(c);
        } else {
            return new HashSet<>();
        }
    }

}
