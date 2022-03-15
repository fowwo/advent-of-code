// December 8th, 2020
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class _7b {

    public static void main(String[] args) {

        try {

            File f = new File("input.txt");
            Scanner s = new Scanner(f);
            Map<String, List<Bags>> contain = new HashMap<>();

            while (s.hasNextLine()) {
                String[] str = s.nextLine().split(" ");
                if (str.length != 7) {
                    for (int i = 5; i < str.length; i += 4) {
                        if (!contain.containsKey(str[0] + str[1])) contain.put(str[0] + str[1], new ArrayList<>());
                        contain.get(str[0] + str[1]).add(new Bags(str[i] + str[i + 1], Integer.parseInt(str[i - 1])));
                    }
                }
            }
            System.out.println(getAllInnerBags("shinygold", contain));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static int getAllInnerBags(String c, Map<String, List<Bags>> map) {
        int total = 0;
        if (map.containsKey(c)) {
            for (Bags b : map.get(c)) {
                total += b.getCount() * (getAllInnerBags(b.getBag(), map) + 1);
            }
        }
        return total;
    }

    static class Bags {

        private String bag;
        private int count;

        public Bags(String bag, int count) {
            this.bag = bag;
            this.count = count;
        }

        public String getBag() { return bag; }
        public int getCount() { return count; }

    }

}
