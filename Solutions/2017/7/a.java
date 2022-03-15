// September 18th, 2020
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class a {

    public static void main(String[] args) {

        Map<String, String> map = new HashMap<>();

        try {

            File f = new File("input.txt");
            Scanner s = new Scanner(f);

            while (s.hasNextLine()) {

                String[] line = s.nextLine().split("[ ]+");
                String from = line[0];

                for (int i = 3; i < line.length - 1; i++) map.put(line[i].substring(0, line[i].length() - 1), from);
                map.put(line[line.length - 1], from);

            }

            s.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Get a 'random' starting point
        String i = "";
        for (String s : map.keySet()) {
            i = s;
            break;
        }

        // Find first element
        while (map.get(i) != null) i = map.get(i);

        System.out.print(i);

    }

}
