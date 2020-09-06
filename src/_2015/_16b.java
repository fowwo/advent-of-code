// June 5th, 2020
package _2015;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class _16b {

    public static void main(String[] args) {

        try {

            File f = new File("input/2015/16.txt");
            Scanner s = new Scanner(f);

            while (s.hasNextLine()) {

                String[] line = s.nextLine().split(" ");
                String[] things = {line[2].substring(0, line[2].length() - 1), line[4].substring(0, line[4].length() - 1), line[6].substring(0, line[6].length() - 1)};
                int[] counts = {Integer.parseInt(line[3].substring(0, line[3].length() - 1)), Integer.parseInt(line[5].substring(0, line[5].length() - 1)), Integer.parseInt(line[7])};

                if (checkThing(things[0], counts[0]) && checkThing(things[1], counts[1]) && checkThing(things[2], counts[2])) {
                    System.out.println(line[1].substring(0, line[1].length() - 1));
                    return;
                }

            }
            s.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static boolean checkThing(String thing, int count) {

        if (thing.equals("children") && count != 3) return false;
        if (thing.equals("cats") && count <= 7) return false;
        if (thing.equals("samoyeds") && count != 2) return false;
        if (thing.equals("pomeranians") && count >= 3) return false;
        if (thing.equals("akitas") && count != 0) return false;
        if (thing.equals("vizslas") && count != 0) return false;
        if (thing.equals("goldfish") && count >= 5) return false;
        if (thing.equals("trees") && count <= 3) return false;
        if (thing.equals("cars") && count != 2) return false;
        if (thing.equals("perfumes") && count != 1) return false;

        return true;

    }

}
