// September 12th, 2020
package _2016;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class _17b {

    public static void main(String[] args) {

        try {

            String input = "pxxbnzuo";
            Set<String> current = new HashSet<>(Collections.singleton(input));
            Map<String, int[]> location = new HashMap<>();
            location.put(input, new int[]{ 0, 0 });
            MessageDigest md5 = MessageDigest.getInstance("MD5");

            int max = 0;
            for (int depth = 0; current.size() > 0; depth++) {
                Set<String> temp = new HashSet<>();
                for (String path : current) {
                    if (location.get(path)[0] == 3 && location.get(path)[1] == 3) {
                        max = depth;
                        continue; // ...paths always end the first time they reach the bottom-right room.
                    }
                    if (isOpen(path, location.get(path), 'U', md5)) {
                        temp.add(path + 'U');
                        location.put(path + 'U', new int[]{ location.get(path)[0], location.get(path)[1] - 1 });
                    }
                    if (isOpen(path, location.get(path), 'D', md5)) {
                        temp.add(path + 'D');
                        location.put(path + 'D', new int[]{ location.get(path)[0], location.get(path)[1] + 1 });
                    }
                    if (isOpen(path, location.get(path), 'L', md5)) {
                        temp.add(path + 'L');
                        location.put(path + 'L', new int[]{ location.get(path)[0] - 1, location.get(path)[1] });
                    }
                    if (isOpen(path, location.get(path), 'R', md5)) {
                        temp.add(path + 'R');
                        location.put(path + 'R', new int[]{ location.get(path)[0] + 1, location.get(path)[1] });
                    }
                }
                current = temp;
            }

            System.out.println(max);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

    public static String createHash(String input, MessageDigest hash) {
        StringBuilder sb = new StringBuilder();
        hash.update(input.getBytes());
        for (byte b : hash.digest()) {
            sb.append(String.format("%02x", b&0xff));
        }
        return sb.toString();
    }

    public static boolean isOpen(String path, int[] location, char direction, MessageDigest md) {
        String hash = createHash(path, md);
        switch (direction) {
            case 'U':
                return location[1] != 0 && (
                        hash.charAt(0) == 'b' ||
                        hash.charAt(0) == 'c' ||
                        hash.charAt(0) == 'd' ||
                        hash.charAt(0) == 'e' ||
                        hash.charAt(0) == 'f');
            case 'D':
                return location[1] != 3 && (
                        hash.charAt(1) == 'b' ||
                        hash.charAt(1) == 'c' ||
                        hash.charAt(1) == 'd' ||
                        hash.charAt(1) == 'e' ||
                        hash.charAt(1) == 'f');
            case 'L':
                return location[0] != 0 && (
                        hash.charAt(2) == 'b' ||
                        hash.charAt(2) == 'c' ||
                        hash.charAt(2) == 'd' ||
                        hash.charAt(2) == 'e' ||
                        hash.charAt(2) == 'f');
            case 'R':
                return location[0] != 3 && (
                        hash.charAt(3) == 'b' ||
                        hash.charAt(3) == 'c' ||
                        hash.charAt(3) == 'd' ||
                        hash.charAt(3) == 'e' ||
                        hash.charAt(3) == 'f');
        }
        return false;
    }

}
