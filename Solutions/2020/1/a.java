// December 3rd, 2020
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _1a {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();

        try {

            File f = new File("input.txt");
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) list.add(Integer.parseInt(s.nextLine()));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i) + list.get(j) == 2020) {
                    System.out.println(list.get(i) * list.get(j));
                    System.exit(0);
                }
            }
        }

    }

}
