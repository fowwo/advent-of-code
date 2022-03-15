// May 5th, 2020
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class b {

    public static void main(String[] args) {

        try {

            String input = "yzbqklnj";
            MessageDigest md5 = MessageDigest.getInstance("MD5");

            int i = 0;
            StringBuilder sb = new StringBuilder();
            while (!sb.toString().startsWith("000000")) {
                i++;
                sb.setLength(0);
                md5.update((input + i).getBytes());
                for (byte b : md5.digest()) {
                    sb.append(String.format("%02x", b&0xff));
                }
            }
            System.out.println(i);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

}
