// September 6th, 2020
package _2016;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class _5a {

    public static void main(String[] args) {

        try {

            String input = "reyedfim";
            MessageDigest md5 = MessageDigest.getInstance("MD5");

            int i = 0;
            for (int count = 0; count < 8; count++) {
                StringBuilder sb = new StringBuilder();
                while (!sb.toString().startsWith("00000")) {
                    i++;
                    sb.setLength(0);
                    md5.update((input + i).getBytes());
                    for (byte b : md5.digest()) {
                        sb.append(String.format("%02x", b&0xff));
                    }
                }
                System.out.print(sb.toString().charAt(5));
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

}
