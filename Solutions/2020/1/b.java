// December 3rd, 2020
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class b {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();

        try {

            File f = new File("input.txt");
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) list.add(Integer.parseInt(s.nextLine()));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < list.size() - 2; i++) {
            for (int j = i + 1; j < list.size() - 1; j++) {
                for (int k = j + 1; k < list.size(); k++) {
                    if (list.get(i) + list.get(j) + list.get(k) == 2020) {
                        System.out.println(list.get(i) * list.get(j) * list.get(k));
                        System.exit(0);
                    }
                }
            }
        }

    }

}
