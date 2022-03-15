// September 6th, 2020
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class b {

    public static void main(String[] args) {

        List<String[]> list = new ArrayList<>();

        try {

            File f = new File("input.txt");
            Scanner s = new Scanner(f);

            while (s.hasNextLine()) {
                String line = s.nextLine();
                String checksum = line.substring(line.length() - 6, line.length() - 1);
                String[] split = line.substring(0, line.length() - 7).split("-");

                // Count characters
                int[] arr = new int[26];
                for (int i = 0; i < split.length - 1; i++) {
                    for (int j = 0; j < split[i].length(); j++) {
                        arr[split[i].charAt(j) - 97]++;
                    }
                }
                int[] sort = Arrays.copyOf(arr, 26);
                Arrays.sort(sort);

                // Check checksum
                boolean valid = true;
                for (int i = 0; i < 5; i++) {
                    if (arr[checksum.charAt(i) - 97] == sort[25 - i]) {

                        // Are ties in alphetical order?
                        if (sort[25 - i] == sort[24 - i]) {
                            if (i != 4 && checksum.charAt(i) > checksum.charAt(i + 1)) {
                                valid = false;
                                break;
                            }
                        }

                    } else {
                        valid = false;
                        break;
                    }
                }
                if (valid) list.add(split);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Decrypt
        for (String[] split : list) {
            StringBuilder sb = new StringBuilder();
            int shift = Integer.parseInt(split[split.length - 1]) % 26;
            for (int i = 0; i < split.length - 1; i++) {
                for (int j = 0; j < split[i].length(); j++) {
                    sb.append((char) ((split[i].charAt(j) - 97 + shift) % 26 + 97));
                }
                sb.append('-');
            }
            sb.append(split[split.length - 1]);
            System.out.println(sb.toString());
        }

    }

}
