// September 12th, 2020
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class a {

    public static void main(String[] args) {

        List<int[]> discs = new ArrayList<>();

        try {

            File f = new File("input.txt");
            Scanner s = new Scanner(f);

            while (s.hasNextLine()) {
                String[] line = s.nextLine().split(" ");
                discs.add(new int[]{ Integer.parseInt(line[11].substring(0, line[11].length() - 1)), Integer.parseInt(line[3]) });
            }
            s.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        boolean b = false;
        int time = -1;
        while (!b) {
            time++;
            b = true;
            for (int i = 0; i < discs.size(); i++) {
                int[] disc = discs.get(i);
                if ((disc[0] + time + i + 1) % disc[1] != 0) {
                    b = false;
                    break;
                }
            }
        }

        System.out.println(time);

    }

}
