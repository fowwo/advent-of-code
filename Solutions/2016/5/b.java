// September 6th, 2020
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class b {

    public static void main(String[] args) {

        try {

            String input = "reyedfim";
            MessageDigest md5 = MessageDigest.getInstance("MD5");

            int i = 0;
            char[] password = new char[8];
            char initial = password[0];

            boolean done = false;
            while (!done) {
                StringBuilder sb = new StringBuilder();
                while (!sb.toString().startsWith("00000") || !(sb.toString().charAt(5) >= 48 && sb.toString().charAt(5) <= 55)) {
                    i++;
                    sb.setLength(0);
                    md5.update((input + i).getBytes());
                    for (byte b : md5.digest()) {
                        sb.append(String.format("%02x", b&0xff));
                    }
                }
                if (password[Character.getNumericValue(sb.toString().charAt(5))] == initial) {
                    password[Character.getNumericValue(sb.toString().charAt(5))] = sb.toString().charAt(6);
                    System.out.println(">" + String.valueOf(password) + "<");
                }

                done = true;
                for (int j = 0; j < 8; j++) {
                    if (password[j] == initial) {
                        done = false;
                        break;
                    }
                }
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

}
