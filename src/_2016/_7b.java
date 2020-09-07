// September 6th, 2020
package _2016;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class _7b {

    public static void main(String[] args) {

        try {

            File f = new File("input/2016/7.txt");
            Scanner s = new Scanner(f);

            int total = 0;
            while (s.hasNextLine()) {
                String line = s.nextLine();
                Set<String> aba = new HashSet<>();
                Set<String> bab = new HashSet<>();
                for (int i = 0; i < line.length() - 2; i++) {
                    if (line.charAt(i) == line.charAt(i + 2) && line.charAt(i) != line.charAt(i + 1)) {
                        String sequence = "" + line.charAt(i) + line.charAt(i + 1) + line.charAt(i + 2);
                        for (int j = i; j >= 0; j--) {
                            if (line.charAt(j) == ']' || j == 0) {
                                aba.add(sequence);
                                break;
                            }
                            if (line.charAt(j) == '[') {
                                bab.add(sequence);
                                break;
                            }
                        }
                    }
                }
                for (String a : aba) {
                    if (bab.contains("" + a.charAt(1) + a.charAt(0) + a.charAt(1))) {
                        total++;
                        break;
                    }
                }
            }

            System.out.println(total);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
