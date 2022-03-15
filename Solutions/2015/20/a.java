// June 13th, 2020
import java.util.ArrayList;

public class a {

    public static void main(String[] args) {

        int input = 29000000;

        int presents = 0;
        int house = 0;
        while (presents < input) {
            ArrayList<Integer> factors = new ArrayList<>();
            presents = 0;
            house++;
            for (int i = 1; i <= house; i++) {
                if (house % i == 0) factors.add(i);
            }
            for (int x : factors) {
                presents += x;
            }
            presents *= 10;
        }

        System.out.println(house);

    }

}
