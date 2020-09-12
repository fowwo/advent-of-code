// September 12th, 2020 - Does not run in reasonable time.
package _2016;

import java.util.LinkedList;
import java.util.List;

public class _19a {

    public static void main(String[] args) {

        List<int[]> elves = new LinkedList<>();
        for (int i = 0; i < 3004953; i++) elves.add(new int[]{ i + 1, 1 });

        for (int i = 0; elves.size() != 1; i = (i + 1) % (elves.size() + 1)) {
            elves.get(i)[1] += elves.get((i + 1) % elves.size())[1];
            elves.remove((i + 1) % elves.size());
        }

        System.out.println(elves.get(0)[0]);

    }

}
