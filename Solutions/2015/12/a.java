// May 5th, 2020
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class a {

    public static void main(String[] args) {

        try {

            File f = new File("input.txt");
            Scanner s = new Scanner(f);
            String str = s.nextLine();
            s.close();

            int sum = 0;
            Matcher m = Pattern.compile("-?\\d+").matcher(str);
            while (m.find()) {
                sum += Integer.parseInt(m.group());
            }

            System.out.println(sum);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
