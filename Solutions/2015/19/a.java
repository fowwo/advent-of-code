// June 13th, 2020
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class a {

    public static void main(String[] args) {

        try {

            File f = new File("input.txt");
            Scanner s = new Scanner(f);
            HashMap<String, ArrayList<String>> map = new HashMap<>();

            String line = s.nextLine();
            while (!line.equals("")) {
                String[] split = line.split(" => ");
                if (!map.containsKey(split[0])) {
                    map.put(split[0], new ArrayList<>());
                }
                map.get(split[0]).add(split[1]);
                line = s.nextLine();
            }

            String input = s.nextLine();
            s.close();

            HashSet<String> set = new HashSet<>();

            // Single replacements
            for (int i = 0; i < input.length(); i++) {
                String key = "" + input.charAt(i);
                if (map.containsKey(key)) {
                    for (String x : map.get(key)) {
                        set.add(input.substring(0, i) + x + input.substring(i + 1));
                    }
                }
            }

            // Double replacements
            for (int i = 0; i < input.length() - 1; i++) {
                String key = "" + input.charAt(i) + input.charAt(i + 1);
                if (map.containsKey(key)) {
                    for (String x : map.get(key)) {
                        set.add(input.substring(0, i) + x + input.substring(i + 2));
                    }
                }
            }

            System.out.println(set.size());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
