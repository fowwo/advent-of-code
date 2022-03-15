// April 5th, 2021
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class b {

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

            // Find guard and minute with most sleep
            String maxGuard = null;
            int maxTime = 0;
            int maxIndex = -1;
            for (String key : map.keySet()) {
                int[] arr = map.get(key);
                for (int i = 0; i < 60; i++) {
                    if (arr[i] > maxTime) {
                        maxTime = arr[i];
                        maxIndex = i;
                        maxGuard = key;
                    }
                }

            }

            System.out.println(maxIndex * Integer.parseInt(maxGuard.substring(1)));

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
