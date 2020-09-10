// September 9th, 2020
package _2016;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _11a {

    public static void main(String[] args) {

        /*
            0 - Pm    [0] - G
            1 - Pu    [1] - M
            2 - Ru
            3 - Sr
            4 - Tm
         */

        List<List<Integer>> input = new ArrayList<>();
        input.add(new ArrayList<>());
        input.add(new ArrayList<>());
        input.add(new ArrayList<>());
        input.add(new ArrayList<>());
        input.add(new ArrayList<>());
        input.add(new ArrayList<>());
        input.get(0).add(2);
        input.get(0).add(2);
        input.get(1).add(0);
        input.get(1).add(1);
        input.get(2).add(2);
        input.get(2).add(2);
        input.get(3).add(0);
        input.get(3).add(1);
        input.get(4).add(0);
        input.get(4).add(0);
        input.get(5).add(0);

        List<List<Integer>> target = new ArrayList<>();
        for (int i = 0; i < input.size() - 1; i++) {
            target.add(new ArrayList<>());
            target.get(target.size() - 1).add(3);
            target.get(target.size() - 1).add(3);
        }
        target.add(new ArrayList<>());
        target.get(target.size() - 1).add(3);

        int depth = 0;
        Set<List<List<Integer>>> checkedStates = new HashSet<>();
        Set<List<List<Integer>>> newStates = new HashSet<>(Collections.singleton(input));
        while (!newStates.contains(target) && newStates.size() > 0) {
            Set<List<List<Integer>>> tempStates = new HashSet<>();
            for (List<List<Integer>> state : newStates) {

                // Find all items on elevator floor
                int e = state.get(state.size() - 1).get(0);
                List<int[]> sameFloor = new ArrayList<>();
                for (int i = 0; i < state.size() - 1; i++) {
                    for (int j = 0; j < 2; j++) {
                        if (state.get(i).get(j) == e) sameFloor.add(new int[]{ i, j });
                    }
                }

                // Create new states
                List<List<Integer>> copy;
                for (int i = 0; i < sameFloor.size(); i++) {

                    // Move one item
                    int[] x = sameFloor.get(i);
                    if (e > 0) {
                        copy = copyState(state);
                        copy.get(x[0]).set(x[1], copy.get(x[0]).get(x[1]) - 1);
                        copy.get(state.size() - 1).set(0, copy.get(state.size() - 1).get(0) - 1);
                        if (!checkedStates.contains(copy) && isValidState(copy)) {
                            tempStates.add(copy);
                            checkedStates.add(copy);
                        }
                    }
                    if (e < 3) {
                        copy = copyState(state);
                        copy.get(x[0]).set(x[1], copy.get(x[0]).get(x[1]) + 1);
                        copy.get(state.size() - 1).set(0, copy.get(state.size() - 1).get(0) + 1);
                        if (!checkedStates.contains(copy) && isValidState(copy)) {
                            tempStates.add(copy);
                            checkedStates.add(copy);
                        }
                    }

                    // Move two items
                    for (int j = i + 1; j < sameFloor.size(); j++) {
                        int[] y = sameFloor.get(j);
                        if (e > 0) {
                            copy = copyState(state);
                            copy.get(x[0]).set(x[1], copy.get(x[0]).get(x[1]) - 1);
                            copy.get(y[0]).set(y[1], copy.get(y[0]).get(y[1]) - 1);
                            copy.get(state.size() - 1).set(0, copy.get(state.size() - 1).get(0) - 1);
                            if (!checkedStates.contains(copy) && isValidState(copy)) {
                                tempStates.add(copy);
                                checkedStates.add(copy);
                            }
                        }
                        if (e < 3) {
                            copy = copyState(state);
                            copy.get(x[0]).set(x[1], copy.get(x[0]).get(x[1]) + 1);
                            copy.get(y[0]).set(y[1], copy.get(y[0]).get(y[1]) + 1);
                            copy.get(state.size() - 1).set(0, copy.get(state.size() - 1).get(0) + 1);
                            if (!checkedStates.contains(copy) && isValidState(copy)) {
                                tempStates.add(copy);
                                checkedStates.add(copy);
                            }
                        }
                    }

                }

            }
            newStates = tempStates;
            depth++;
        }

        System.out.println(depth);

    }

    public static <T> List<List<T>> copyState(List<List<T>> state) {
        List<List<T>> copy = new ArrayList<>();
        for (List<T> list : state) {
            copy.add(new ArrayList<>(list));
        }
        return copy;
    }
    public static boolean isValidState(List<List<Integer>> state) {
        boolean valid = true;
        for (int i = 0; i < state.size() - 1; i++) {
            for (int j = 0; j < state.size() - 1; j++) {
                if (i == j) continue;
                if (state.get(i).get(1).equals(state.get(j).get(0)) && !state.get(i).get(1).equals(state.get(i).get(0))) {
                    return false;
                }
            }
        }
        return true;
    }

}
