// September 12th, 2020
package _2016;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _20b {

    public static void main(String[] args) {

        List<long[]> ranges = new ArrayList<>();

        try {

            File f = new File("input/2016/20.txt");
            Scanner s = new Scanner(f);

            while (s.hasNextLine()) {
                String[] line = s.nextLine().split("-");
                ranges.add(new long[]{ Long.parseLong(line[0]), Long.parseLong(line[1]) });
            }
            s.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Merge ranges
        ranges.sort((a, b) -> { return Long.compare(a[0], b[0]); });
        for (int i = 0; i < ranges.size() - 1; i++) {
            if (ranges.get(i)[1] + 1 >= ranges.get(i + 1)[0]) {
                ranges.get(i)[1] = Math.max(ranges.get(i)[1], ranges.get(i + 1)[1]);
                ranges.remove(i + 1);
                i--;
            }
        }

        int total = 0;
        for (int i = 0; i < ranges.size() - 1; i++) total += ranges.get(i + 1)[0] - ranges.get(i)[1] - 1;
        total += 4294967295L - ranges.get(ranges.size() - 1)[1]; // ...any value from 0 through 4294967295, inclusive.
        System.out.println(total);

    }

}
