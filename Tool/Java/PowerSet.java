// September 1st, 2020 - Unused (created for 24a but this method could not solve the problem in a reasonable amount of time)
import java.util.HashSet;
import java.util.Set;

public class PowerSet {

    public static <T> Set<Set<T>> create(Set<T> list) {

        Set<Set<T>> powerSet = new HashSet<>();
        powerSet.add(new HashSet<>());
        for (T item : list) {
            powerSet = add(item, powerSet);
        }
        return powerSet;

    }

    private static <T> Set<Set<T>> add(T item, Set<Set<T>> sets) {
        Set<Set<T>> powerSet = new HashSet<>();
        for (Set<T> set : sets) {
            Set<T> copy = new HashSet<T>(set);
            copy.add(item);
            powerSet.add(copy);
        }
        powerSet.addAll(sets);
        return powerSet;
    }

    public static Set<Set<Integer>> createFiltered(Set<Integer> set, int value) {

        Set<Set<Integer>> powerSet = new HashSet<>();
        powerSet.add(new HashSet<>());
        int i = 0;
        for (Integer item : set) {
            i++;
            System.out.println(item + " (" + i + "/" + set.size() + ") (" + powerSet.size() + ")");
            powerSet = addFiltered(item, powerSet, value);
        }
        powerSet.removeIf(x -> sum(x) != value);
        System.out.println("Removing elements");
        return powerSet;

    }

    private static Set<Set<Integer>> addFiltered(Integer item, Set<Set<Integer>> sets, int max) {
        Set<Set<Integer>> powerSet = new HashSet<>();
        for (Set<Integer> set : sets) {
            Set<Integer> copy = new HashSet<Integer>(set);
            copy.add(item);
            if (sum(copy) <= max) {
                powerSet.add(copy);
            }
        }
        powerSet.addAll(sets);
        return powerSet;
    }

    private static int sum(Set<Integer> set) {
        int sum = 0;
        for (int number : set) {
            sum += number;
        }
        return sum;
    }

}
