// June 5th, 2020
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class a {

    public static void main(String[] args) {

        try {

            File f = new File("input.txt");
            Scanner s = new Scanner(f);

            ArrayList<Integer> containers = new ArrayList<>();
            ArrayList<Integer> eggnog = new ArrayList<>();
            while (s.hasNextLine()) {
                containers.add(Integer.parseInt(s.nextLine()));
                eggnog.add(0);
            }
            s.close();

            int count = 0;

            int i = 0;
            while (i != containers.size()) {
                if (eggnog.get(i) == 0) {
                    eggnog.set(i, 1);
                    i = 0;
                    if (total(containers, eggnog) == 150) count++;
                } else {
                    eggnog.set(i, 0);
                    i++;
                }
            }

            System.out.println(count);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static int total(ArrayList<Integer> a, ArrayList<Integer> b) {
        int total = 0;
        for (int i = 0; i < a.size(); i++) {
            total += a.get(i) * b.get(i);
        }
        return total;
    }

}
