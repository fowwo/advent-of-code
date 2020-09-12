// September 12th, 2020
package _2016;

public class _16b {

    public static void main(String[] args) {

        StringBuilder input = new StringBuilder("01111001100111011");
        int diskSize = 35651584;

        while (input.length() < diskSize) {
            StringBuilder sb = new StringBuilder();
            for (int i = input.length() - 1; i >= 0; i--) {
                if (input.charAt(i) == '0') {
                    sb.append('1');
                } else {
                    sb.append('0');
                }
            }
            input.append('0').append(sb.toString());
        }

        System.out.println(getChecksum(input.toString(), diskSize));

    }

    public static String getChecksum(String data, int diskSize) {
        String sub = data.substring(0, diskSize);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sub.length() - 1; i += 2) {
            if (sub.charAt(i) == sub.charAt(i + 1)) {
                sb.append('1');
            } else {
                sb.append('0');
            }
        }
        if (sb.toString().length() % 2 == 0) {
            return getChecksum(sb.toString(), sb.length());
        } else {
            return sb.toString();
        }
    }

}
