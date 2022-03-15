// December 8th, 2020
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _8b {

    public static void main(String[] args) {

        try {

            File f = new File("input.txt");
            Scanner s = new Scanner(f);
            List<String> base = new ArrayList<>();

            while (s.hasNextLine()) base.add(s.nextLine());

            for (int i = 0; i < base.size(); i++) {
                if (base.get(i).startsWith("acc")) continue;
                List<String> instructions = new ArrayList<>(base);
                if (base.get(i).startsWith("jmp")) {
                    instructions.set(i, "nop" + instructions.get(i).substring(3));
                } else {
                    instructions.set(i, "jmp" + instructions.get(i).substring(3));
                }

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
                    if (l == instructions.size()) {
                        System.out.println(acc);
                        System.exit(0);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
