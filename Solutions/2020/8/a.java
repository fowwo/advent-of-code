// December 8th, 2020
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _8a {

    public static void main(String[] args) {

        try {

            File f = new File("input.txt");
            Scanner s = new Scanner(f);
            List<String> instructions = new ArrayList<>();

            while (s.hasNextLine()) instructions.add(s.nextLine());

            int acc = 0;
            int[] mem = new int[instructions.size()];
            int l = 0;
            while (mem[l] == 0) {
                mem[l] = 1;
                String[] spl = instructions.get(l).split(" ");
                switch (spl[0]) {
                    case "acc":
                        acc += Integer.parseInt(spl[1]);
                    case "nop":
                        l++;
                        break;
                    case "jmp":
                        l += Integer.parseInt(spl[1]);
                        break;
                }
            }

            System.out.println(acc);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
