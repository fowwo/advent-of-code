// September 13th, 2020
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class b {

    public static void main(String[] args) {

        int x = 0;
        int y = 0;
        Set<Location> set = new HashSet<>();

        try {

            File f = new File("input.txt");
            Scanner s = new Scanner(f);

            String[] line = s.nextLine().split(", ");
            int direction = 0;
            for (String dir : line) {
                int distance = Integer.parseInt(dir.substring(1));
                if (dir.charAt(0) == 'R') {
                    direction = (direction + 1) % 4;
                } else {
                    direction = (direction - 1 + 4) % 4;
                }
                switch (direction) {
                    case 0:
                        for (int i = 0; i < distance; i++) {
                            y--;
                            Location loc = new Location(x, y);
                            if (set.contains(loc)) {
                                System.out.println(Math.abs(x) + Math.abs(y));
                                System.exit(0);
                            }
                            set.add(loc);
                        }
                        break;
                    case 1:
                        for (int i = 0; i < distance; i++) {
                            x++;
                            Location loc = new Location(x, y);
                            if (set.contains(loc)) {
                                System.out.println(Math.abs(x) + Math.abs(y));
                                System.exit(0);
                            }
                            set.add(loc);
                        }
                        break;
                    case 2:
                        for (int i = 0; i < distance; i++) {
                            y++;
                            Location loc = new Location(x, y);
                            if (set.contains(loc)) {
                                System.out.println(Math.abs(x) + Math.abs(y));
                                System.exit(0);
                            }
                            set.add(loc);
                        }
                        break;
                    case 3:
                        for (int i = 0; i < distance; i++) {
                            x--;
                            Location loc = new Location(x, y);
                            if (set.contains(loc)) {
                                System.out.println(Math.abs(x) + Math.abs(y));
                                System.exit(0);
                            }
                            set.add(loc);
                         }
                        break;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
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

    }

}
