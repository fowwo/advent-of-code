// September 12th, 2020
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class a {

    public static void main(String[] args) {

        try {

            String input = "pxxbnzuo";
            Set<String> current = new HashSet<>(Collections.singleton(input));
            Set<String> checked = new HashSet<>();
            Map<String, int[]> location = new HashMap<>();
            location.put(input, new int[]{ 0, 0 });
            MessageDigest md5 = MessageDigest.getInstance("MD5");

            while (true) {
                Set<String> temp = new HashSet<>();
                for (String path : current) {
                    if (isOpen(path, 'U', md5) && !checked.contains(path)) {
                        temp.add(path + 'U');
                        location.put(path + 'U', new int[]{ location.get(path)[0], location.get(path)[1] - 1 });
                        if (location.get(path + 'U')[0] == 3 && location.get(path + 'U')[1] == 3) {
                            System.out.println((path + 'U').substring(input.length()));
                            System.exit(0);
                        }
                    }
                    if (isOpen(path, 'D', md5) && !checked.contains(path)) {
                        temp.add(path + 'D');
                        location.put(path + 'D', new int[]{ location.get(path)[0], location.get(path)[1] + 1 });
                        if (location.get(path + 'D')[0] == 3 && location.get(path + 'D')[1] == 3) {
                            System.out.println((path + 'D').substring(input.length()));
                            System.exit(0);
                        }
                    }
                    if (isOpen(path, 'L', md5) && !checked.contains(path)) {
                        temp.add(path + 'L');
                        location.put(path + 'L', new int[]{ location.get(path)[0] - 1, location.get(path)[1] });
                        if (location.get(path + 'L')[0] == 3 && location.get(path + 'L')[1] == 3) {
                            System.out.println((path + 'L').substring(input.length()));
                            System.exit(0);
                        }
                    }
                    if (isOpen(path, 'R', md5) && !checked.contains(path)) {
                        temp.add(path + 'R');
                        location.put(path + 'R', new int[]{ location.get(path)[0] + 1, location.get(path)[1] });
                        if (location.get(path + 'R')[0] == 3 && location.get(path + 'R')[1] == 3) {
                            System.out.println((path + 'R').substring(input.length()));
                            System.exit(0);
                        }
                    }
                    checked.add(path);
                }
                current = temp;
            }

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

    public static boolean isOpen(String path, char direction, MessageDigest md) {
        String hash = createHash(path, md);
        switch (direction) {
            case 'U':
                return  hash.charAt(0) == 'b' ||
                        hash.charAt(0) == 'c' ||
                        hash.charAt(0) == 'd' ||
                        hash.charAt(0) == 'e' ||
                        hash.charAt(0) == 'f';
            case 'D':
                return  hash.charAt(1) == 'b' ||
                        hash.charAt(1) == 'c' ||
                        hash.charAt(1) == 'd' ||
                        hash.charAt(1) == 'e' ||
                        hash.charAt(1) == 'f';
            case 'L':
                return  hash.charAt(2) == 'b' ||
                        hash.charAt(2) == 'c' ||
                        hash.charAt(2) == 'd' ||
                        hash.charAt(2) == 'e' ||
                        hash.charAt(2) == 'f';
            case 'R':
                return  hash.charAt(3) == 'b' ||
                        hash.charAt(3) == 'c' ||
                        hash.charAt(3) == 'd' ||
                        hash.charAt(3) == 'e' ||
                        hash.charAt(3) == 'f';
        }
        return false;
    }

}
