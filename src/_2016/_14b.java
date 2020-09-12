// September 12th, 2020
package _2016;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class _14b {

    public static void main(String[] args) {

        try {

            String input = "ihaygndm";
            MessageDigest md5 = MessageDigest.getInstance("MD5");

            int i = -1;
            int total = 0;

            // Create first 25,000 hashes
            System.out.println("Creating hashes...");
            String[] hashes = new String[25000];
            for (int a = 0; a < hashes.length; a++) {
                hashes[a] = createHash(input + a, md5);
            }

            // 2016 additional hashes
            for (int a = 0; a < hashes.length; a++) {
                for (int b = 0; b < 2016; b++) hashes[a] = createHash(hashes[a], md5);
            }

            // Find keys
            System.out.println("Finding keys...");
            while (total < 64) {
                i++;
                String hash = hashes[i];
                for (int c = 0; c < hash.length() - 2; c++) {
                    if (hash.charAt(c) == hash.charAt(c + 1) && hash.charAt(c + 1) == hash.charAt(c + 2)) {
                        for (int j = 1; j <= 1000; j++) {
                            String hash2 = hashes[i + j];
                            boolean b = false;
                            for (int c2 = 0; c2 < hash2.length() - 4; c2++) {
                                if (hash.charAt(c) == hash2.charAt(c2) && hash2.charAt(c2) == hash2.charAt(c2 + 1) && hash2.charAt(c2 + 1) == hash2.charAt(c2 + 2) && hash2.charAt(c2 + 2) == hash2.charAt(c2 + 3) && hash2.charAt(c2 + 3) == hash2.charAt(c2 + 4)) {
                                    b = true;
                                    total++;
                                    break;
                                }
                            }
                            if (b) break;
                        }
                        break;
                    }
                }
            }

            System.out.println(i);

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

}
