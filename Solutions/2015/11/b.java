// May 5th, 2020
public class b {

    public static void main(String[] args) {

        String input = "hxbxxyzz";
        int[] number = new int[input.length()];

        for (int i = 0; i < input.length(); i++) {
            number[i] = input.charAt(i);
        }

        boolean increasing = false;
        boolean bad = true;
        int pairs = 0;

        while (!increasing || bad || pairs < 2) {

            increasing = false;
            bad = false;
            pairs = 0;

            // ...he finds his new password by incrementing his old password string
            int index = 7;
            number[index]++;
            while (number[index] == 'z' + 1) {
                number[index] = 'a';
                index--;
                number[index]++;
            }
            input = "" + (char) number[0] + (char) number[1] + (char) number[2] + (char) number[3] + (char) number[4] + (char) number[5] + (char) number[6] + (char) number[7];

            // Passwords must include one increasing straight of at least three letters
            for (int i = 0; i < input.length() - 2; i++) {
                if (input.charAt(i) + 1 == input.charAt(i + 1) && input.charAt(i + 1) + 1 == input.charAt(i + 2)) {
                    increasing = true;
                    break;
                }
            }

            // Passwords may not contain the letters i, o, or l
            if (increasing) {
                for (int i = 0; i < input.length(); i++) {
                    if (input.charAt(i) == 'i' || input.charAt(i) == 'o' || input.charAt(i) == 'l') {
                        bad = true;
                        break;
                    }
                }
            }

            // Passwords must contain at least two different, non-overlapping pairs of letters
            if (!bad) {
                for (int i = 0; i < input.length() - 1; i++) {
                    if (input.charAt(i) == input.charAt(i + 1)) {
                        pairs++;
                        i++;
                        if (pairs == 2) {
                            break;
                        }
                    }
                }
            }

        }

        System.out.println(input);

    }

}
