// September 13th, 2020
package _2016;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class _21a {

    public static void main(String[] args) {

        char[] string = new char[]{ 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' };

        try {

            File f = new File("input/2016/21.txt");
            Scanner s = new Scanner(f);

            while (s.hasNextLine()) {
                String[] line = s.nextLine().split(" ");
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
                                for (int i = 0; i < string.length; i++) string[((i + rotate) % string.length + string.length) % string.length] = temp[i];
                                break;
                            case "based":
                                while (string[rotate] != line[6].charAt(0)) rotate++;
                                if (rotate >= 4) rotate++;
                                rotate++;
                                for (int i = 0; i < string.length; i++) string[((i + rotate) % string.length + string.length) % string.length] = temp[i];
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
            s.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(new String(string));

    }

}
