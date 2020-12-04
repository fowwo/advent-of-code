// December 3rd, 2020
package _2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class _4a {

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
                        switch (value.split(":")[0]) {
                            case "byr":
                                arr[0] = true;
                                break;
                            case "iyr":
                                arr[1] = true;
                                break;
                            case "eyr":
                                arr[2] = true;
                                break;
                            case "hgt":
                                arr[3] = true;
                                break;
                            case "hcl":
                                arr[4] = true;
                                break;
                            case "ecl":
                                arr[5] = true;
                                break;
                            case "pid":
                                arr[6] = true;
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
