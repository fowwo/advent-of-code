// September 1st, 2020 - Used in 9a, 9b, 13a, and 13b
package _2015;

import java.util.ArrayList;
import java.util.List;

public class Permutation {

    public static <T> List<List<T>> create(List<T> list) {

        List<List<T>> permutations = new ArrayList<List<T>>();
        permutations.add(new ArrayList<T>());
        for (T item : list) {
            permutations = add(item, permutations);
        }
        return permutations;

    }

    private static <T> List<List<T>> add(T item, List<List<T>> list) {
        List<List<T>> permutations = new ArrayList<List<T>>();
        for (List<T> permutation : list) {
            for (int i = 0; i < permutation.size() + 1; i++) {
                List<T> copy = new ArrayList<T>(permutation);
                copy.add(i, item);
                permutations.add(copy);
            }
        }
        return permutations;
    }

}
