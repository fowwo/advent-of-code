// April 5th, 2021
package _2018;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class _4a {

    public static void main(String[] args) {

        try {

            File f = new File("input.txt");
            Scanner s = new Scanner(f);
            List<String[]> list = new ArrayList<>();

            while (s.hasNextLine()) {
                String str = s.nextLine();
                list.add(new String[]{ str.substring(6, 8), str.substring(9, 11), str.substring(15, 17), str.substring(19) });
            }
            list.sort((a, b) -> compare(a,b));

            Map<String, int[]> map = new HashMap<>();
            String guard = "";
            int sleep = -1;
            for (String[] str : list) {
                String[] str2 = str[3].split(" ");
                if (str2[0].startsWith("Guard")) {
                    if (sleep != -1) for (int j = sleep; j < 60; j++) map.get(guard)[j]++;
                    sleep = -1;
                    guard = str2[1];
                    if (!map.containsKey(guard)) map.put(guard, new int[60]);
                } else if (str2[0].startsWith("falls")) {
                    sleep = Integer.parseInt(str[2]);
                } else {
                    if (sleep != -1) for (int j = sleep; j < Integer.parseInt(str[2]); j++) map.get(guard)[j]++;
                    sleep = -1;
                }
            }

            // Find guard with most sleep
            String maxGuard = null;
            int maxTime = 0;
            for (String key : map.keySet()) {
                int[] arr = map.get(key);
                int value = 0;
                for (int i = 0; i < 60; i++) value += arr[i];
                if (value > maxTime) {
                    maxTime = value;
                    maxGuard = key;
                }
            }

            // Find minute with most sleep
            int[] arr = map.get(maxGuard);
            int max = 0;
            for (int i = 1; i < 60; i++) {
                if (arr[i] > arr[max]) max = i;
            }
            System.out.println(max * Integer.parseInt(maxGuard.substring(1)));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static int compare(String[] a, String[] b) {
        int v = Integer.parseInt(a[0]) - Integer.parseInt(b[0]);
        if (v == 0) {
            v = Integer.parseInt(a[1]) - Integer.parseInt(b[1]);
            if (v == 0) {
                return Integer.parseInt(a[2]) - Integer.parseInt(b[2]);
            }
        }
        return v;
    }

}
