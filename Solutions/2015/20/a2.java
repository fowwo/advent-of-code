// June 14th, 2020 - Day 20a optimized
import java.util.HashMap;
import java.util.Map;

public class a2 {

    public static void main(String[] args) {

        int input = 29000000;

        int presents = 0;
        int house = 0;
        while (presents < input) {
            HashMap<Integer, Integer> factors = new HashMap<>();
            house++;
            int number = house;
            boolean prime = false;
            while (!prime) {
                prime = true;
                for (int i = 2; i <= number; i++) {
                    if (number % i == 0) {
                        if (!factors.containsKey(i)) {
                            factors.put(i, 1);
                        } else {
                            factors.put(i, factors.get(i) + 1);
                        }
                        number /= i;
                        prime = false;
                        break;
                    }
                }
            }
            presents = 10;
            for (Map.Entry<Integer, Integer> x : factors.entrySet()) {
                presents *= (Math.pow(x.getKey(), x.getValue() + 1) - 1) / (x.getKey() - 1);
            }
        }

        System.out.println(house);

    }

}
