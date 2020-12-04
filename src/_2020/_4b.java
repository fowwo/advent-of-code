// December 3rd, 2020
package _2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class _4b {

    public static void main(String[] args) {

        try {

            File f = new File("input/2020/4.txt");
            Scanner s = new Scanner(f);
            int total = 0;

            boolean[] arr = new boolean[7];
            for (int i = 0; i < 7; i++) {
                arr[i] = false;
            }
            while (s.hasNextLine()) {
                String str = s.nextLine();
                if (str.equals("")) {
                    boolean b = true;
                    for (int i = 0; i < 7; i++) {
                        if (!arr[i]) {
                            b = false;
                            break;
                        }
                    }
                    for (int i = 0; i < 7; i++) {
                        arr[i] = false;
                    }
                    if (b) total++;
                } else {
                    String[] split = str.split(" ");
                    for (String value : split) {
                        String[] split2 = value.split(":");
                        int x;
                        switch (split2[0]) {
                            case "byr":
                                x = Integer.parseInt(split2[1]);
                                if (x >= 1920 && x <= 2002) arr[0] = true;
                                break;
                            case "iyr":
                                x = Integer.parseInt(split2[1]);
                                if (x >= 2010 && x <= 2020) arr[1] = true;
                                break;
                            case "eyr":
                                x = Integer.parseInt(split2[1]);
                                if (x >= 2020 && x <= 2030) arr[2] = true;
                                break;
                            case "hgt":
                                if (split2[1].endsWith("cm")) {
                                    x = Integer.parseInt(split2[1].substring(0, split2[1].length() - 2));
                                    String m = split2[1].substring(split2[1].length() - 2);
                                    if (x >= 150 && x <= 193) arr[3] = true;
                                }
                                if (split2[1].endsWith("in")) {
                                    x = Integer.parseInt(split2[1].substring(0, split2[1].length() - 2));
                                    String m = split2[1].substring(split2[1].length() - 2);
                                    if (x >= 59 && x <= 76) arr[3] = true;
                                }
                                break;
                            case "hcl":
                                if (split2[1].startsWith("#") && split2[1].length() == 7) {
                                    boolean b = true;
                                    for (int i = 0; i < 6; i++) {
                                        int val = split2[1].charAt(i + 1);
                                        if ((val < 48 || val > 57) && (val < 97 || val > 102)) {
                                            b = false;
                                            break;
                                        }
                                    }
                                    if (b) arr[4] = true;
                                }
                                break;
                            case "ecl":
                                if (split2[1].equals("amb") || split2[1].equals("blu") || split2[1].equals("brn") || split2[1].equals("gry") || split2[1].equals("grn") || split2[1].equals("hzl") || split2[1].equals("oth")) arr[5] = true;
                                break;
                            case "pid":
                                if (split2[1].length() == 9) {
                                    boolean b = true;
                                    for (int i = 0; i < 9; i++) {
                                        int val = split2[1].charAt(i);
                                        if (val < 48 || val > 57) {
                                            b = false;
                                            break;
                                        }
                                    }
                                    if (b) arr[6] = true;
                                }
                                break;
                        }
                    }
                }
            }

            System.out.println(total);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
