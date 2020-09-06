// May 5th, 2020
package _2015;

public class _10a {

    public static void main(String[] args) {

        String input = "1113222113";
        StringBuilder sb = new StringBuilder();

        for (int repeat = 0; repeat < 40; repeat++) {
            int count = 1;
            for (int i = 0; i < input.length() - 1; i++) {
                if (input.charAt(i) == input.charAt(i + 1)) {
                    count++;
                } else {
                    sb.append(count).append(input.charAt(i));
                    count = 1;
                }
            }
            input = sb.append(count).append(input.charAt(input.length() - 1)).toString();
            sb.setLength(0);
        }

        System.out.println(input.length());

    }

}
