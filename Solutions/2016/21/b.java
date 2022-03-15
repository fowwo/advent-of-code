// September 13th, 2020
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class b {

    public static void main(String[] args) {

        char[] chars = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        char[][] permutations = createPermutation(chars);
        List<String[]> instructions = new ArrayList<>();

        try {

            File f = new File("input.txt");
            Scanner s = new Scanner(f);

            while (s.hasNextLine()) instructions.add(s.nextLine().split(" "));
            s.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (char[] string : permutations) {
            char[] copy = Arrays.copyOf(string, string.length);
            for (String[] line : instructions) {
                switch (line[0]) {
                    case "swap":
                        switch (line[1]) {
                            case "position":
                                int ai = Integer.parseInt(line[2]);
                                int bi = Integer.parseInt(line[5]);
                                char temp = string[ai];
                                string[ai] = string[bi];
                                string[bi] = temp;
                                break;
                            case "letter":
                                char a = line[2].charAt(0);
                                char b = line[5].charAt(0);
                                for (int i = 0; i < string.length; i++) {
                                    if (string[i] == a) {
                                        string[i] = b;
                                    } else if (string[i] == b) {
                                        string[i] = a;
                                    }
                                }
                                break;
                        }
                        break;
                    case "rotate":
                        int rotate = 0;
                        char[] temp = Arrays.copyOf(string, string.length);
                        switch (line[1]) {
                            case "left":
                            case "right":
                                rotate = Integer.parseInt(line[2]);
                                if (line[1].equals("left")) rotate *= -1;
                                for (int i = 0; i < string.length; i++)
                                    string[((i + rotate) % string.length + string.length) % string.length] = temp[i];
                                break;
                            case "based":
                                while (string[rotate] != line[6].charAt(0)) rotate++;
                                if (rotate >= 4) rotate++;
                                rotate++;
                                for (int i = 0; i < string.length; i++)
                                    string[((i + rotate) % string.length + string.length) % string.length] = temp[i];
                                break;
                        }
                        break;
                    case "reverse":
                        int ai = Integer.parseInt(line[2]);
                        int bi = Integer.parseInt(line[4]);
                        for (int i = 0; i < (bi - ai + 1) >> 1; i++) {
                            char tmp = string[ai + i];
                            string[ai + i] = string[bi - i];
                            string[bi - i] = tmp;
                        }
                        break;
                    case "move":
                        ai = Integer.parseInt(line[2]);
                        bi = Integer.parseInt(line[5]);
                        char tmp = string[ai];
                        if (ai < bi) {
                            while (ai < bi) {
                                string[ai] = string[ai + 1];
                                ai++;
                            }
                        } else {
                            while (ai > bi) {
                                string[ai] = string[ai - 1];
                                ai--;
                            }
                        }
                        string[bi] = tmp;
                        break;
                }
            }
            if (new String(string).equals("fbgdceah")) {
                System.out.println(new String(copy));
                break;
            }
        }

    }

    private static char[][] createPermutation(char[] list) {

        List<List<Character>> permutations = new ArrayList<>();
        permutations.add(new ArrayList<>());
        for (char item : list) {
            permutations = addPermutation(item, permutations);
        }
        char[][] ap = new char[factorial(list.length)][];
        for (int i = 0; i < permutations.size(); i++) {
            Character[] ugh = permutations.get(i).toArray(new Character[0]);
            char[] temp = new char[ugh.length];
            for (int j = 0; j < ugh.length; j++) temp[j] = ugh[j];
            ap[i] = temp;
        }
        return ap;

    }

    private static List<List<Character>> addPermutation(char item, List<List<Character>> list) {
        List<List<Character>> permutations = new ArrayList<>();
        for (List<Character> permutation : list) {
            for (int i = 0; i < permutation.size() + 1; i++) {
                List<Character> copy = new ArrayList<>(permutation);
                copy.add(i, item);
                permutations.add(copy);
            }
        }
        return permutations;
    }

    private static int factorial(int x) {
        if (x <= 1) return 1;
        return x * factorial(x - 1);
    }

}
