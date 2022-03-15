// September 9th, 2020
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class a {

    public static void main(String[] args) {

        int input = 1352;
        List<Integer> start = new ArrayList<>();
        start.add(1);
        start.add(1);
        List<Integer> target = new ArrayList<>();
        target.add(31);
        target.add(39);

        Set<List<Integer>> checked = new HashSet<>();
        Set<List<Integer>> newSpots = new HashSet<>();
        newSpots.add(start);

        int depth = 0;
        while (!newSpots.contains(target)) {
            Set<List<Integer>> tempSpots = new HashSet<>();
            for (List<Integer> location : newSpots) {
                int x = location.get(0);
                int y = location.get(1);

                // Right
                if (isOpen(x + 1, y, input)) {
                    List<Integer> copy = new ArrayList<>();
                    copy.add(x + 1);
                    copy.add(y);
                    if (!checked.contains(copy)) {
                        checked.add(copy);
                        tempSpots.add(copy);
                    }
                }

                // Down
                if (isOpen(x, y + 1, input)) {
                    List<Integer> copy = new ArrayList<>();
                    copy.add(x);
                    copy.add(y + 1);
                    if (!checked.contains(copy)) {
                        checked.add(copy);
                        tempSpots.add(copy);
                    }
                }

                // Left
                if (x > 0 && isOpen(x - 1, y, input)) {
                    List<Integer> copy = new ArrayList<>();
                    copy.add(x - 1);
                    copy.add(y);
                    if (!checked.contains(copy)) {
                        checked.add(copy);
                        tempSpots.add(copy);
                    }
                }

                // Up
                if (y > 0 && isOpen(x, y - 1, input)) {
                    List<Integer> copy = new ArrayList<>();
                    copy.add(x);
                    copy.add(y - 1);
                    if (!checked.contains(copy)) {
                        checked.add(copy);
                        tempSpots.add(copy);
                    }
                }
            }
            newSpots = tempSpots;
            depth++;
        }

        System.out.println(depth);

    }

    public static boolean isOpen(int x, int y, int favorite) { return numberOfOneBits(x * x + 3 * x + 2 * x * y + y + y * y + favorite) % 2 == 0; }
    public static int numberOfOneBits(int x) {
        int ones = 0;
        while (x != 0) {
            if (x % 2 != 0) ones++;
            x = x >> 1;
        }
        return ones;
    }

}
