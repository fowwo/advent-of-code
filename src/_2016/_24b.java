// September 9th, 2020
package _2016;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class _24b {

    public static void main(String[] args) {

        char[][] matrix = new char[181][43];
        Location start = null;
        List<Location> points = new ArrayList<>();

        try {

            File f = new File("input/2016/24.txt");
            Scanner s = new Scanner(f);

            int y = 0;
            while (s.hasNextLine()) {
                String line = s.nextLine();
                for (int x = 0; x < line.length(); x++) {
                    matrix[x][y] = line.charAt(x);
                    if (line.charAt(x) == '0') start = new Location(x, y);
                    else if (line.charAt(x) >= '0' && line.charAt(x) <= '9') points.add(new Location(x, y));
                }
                y++;
            }
            s.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Set<State> checked = new HashSet<>(Collections.singleton(new State(start, new HashSet<>(points))));
        Set<State> currentStates = new HashSet<>(Collections.singleton(new State(start, new HashSet<>(points))));

        int depth = 0;
        boolean b = false;
        while (!b) {
            Set<State> tempStates = new HashSet<>();
            for (State state : currentStates) {
                int x = state.getPosition().getX();
                int y = state.getPosition().getY();

                // Right
                if (isOpen(x + 1, y, matrix)) {
                    State copy = new State(x + 1, y, state.getPoints());
                    copy.removePoint(copy.getPosition());
                    if (!checked.contains(copy)) {
                        checked.add(copy);
                        tempStates.add(copy);
                        if (copy.getPoints().size() == 0 && copy.getPosition().equals(start)) {
                            b = true;
                            break;
                        }
                    }
                }

                // Down
                if (isOpen(x, y + 1, matrix)) {
                    State copy = new State(x, y + 1, state.getPoints());
                    copy.removePoint(copy.getPosition());
                    if (!checked.contains(copy)) {
                        checked.add(copy);
                        tempStates.add(copy);
                        if (copy.getPoints().size() == 0 && copy.getPosition().equals(start)) {
                            b = true;
                            break;
                        }
                    }
                }

                // Left
                if (isOpen(x - 1, y, matrix)) {
                    State copy = new State(x - 1, y, state.getPoints());
                    copy.removePoint(copy.getPosition());
                    if (!checked.contains(copy)) {
                        checked.add(copy);
                        tempStates.add(copy);
                        if (copy.getPoints().size() == 0 && copy.getPosition().equals(start)) {
                            b = true;
                            break;
                        }
                    }
                }

                // Up
                if (isOpen(x, y - 1, matrix)) {
                    State copy = new State(x, y - 1, state.getPoints());
                    copy.removePoint(copy.getPosition());
                    if (!checked.contains(copy)) {
                        checked.add(copy);
                        tempStates.add(copy);
                        if (copy.getPoints().size() == 0 && copy.getPosition().equals(start)) {
                            b = true;
                            break;
                        }
                    }
                }
            }
            currentStates = tempStates;
            depth++;
        }

        System.out.println(depth);

    }

    public static boolean isOpen(int x, int y, char[][] matrix) { return matrix[x][y] != '#'; }

    private static class State {

        private Location position;
        private Set<Location> points;

        public State(Location start, Set<Location> points) {
            this.position = start;
            this.points = new HashSet<>(points);
        }
        public State(int[] start, Set<Location> points) {
            this.position = new Location(start[0], start[1]);
            this.points = new HashSet<>(points);
        }
        public State(int x, int y, Set<Location> points) {
            this.position = new Location(x, y);
            this.points = new HashSet<>(points);
        }

        public Location getPosition() { return position; }
        public Set<Location> getPoints() { return points; }

        public boolean removePoint(Location loc) { return points.remove(loc); }
        public boolean removePoint(int x, int y) { return removePoint(new Location(x, y)); }

        @Override
        public String toString() {
            return position + " " + points;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return Objects.equals(position, state.position) &&
                    Objects.equals(points, state.points);
        }

        @Override
        public int hashCode() {
            return Objects.hash(position, points);
        }

    }
    private static class Location {

        private int x;
        private int y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() { return x; }
        public int getY() { return y; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Location location = (Location) o;
            return x == location.x &&
                    y == location.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }

    }

}
