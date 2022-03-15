// March 10th, 2022
// This was so incredibly hard to maintain because I solved Day 5 two and a half years before this.
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _7a {
	public static void main(String[] args){
		String input = "3,8,1001,8,10,8,105,1,0,0,21,46,55,72,85,110,191,272,353,434,99999,3,9,1002,9,5,9,1001,9,2,9,102,3,9,9,101,2,9,9,102,4,9,9,4,9,99,3,9,102,5,9,9,4,9,99,3,9,1002,9,2,9,101,2,9,9,1002,9,2,9,4,9,99,3,9,1002,9,4,9,101,3,9,9,4,9,99,3,9,1002,9,3,9,101,5,9,9,1002,9,3,9,101,3,9,9,1002,9,5,9,4,9,99,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,99,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,2,9,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,2,9,9,4,9,99,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,99,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,2,9,4,9,99";

		// Convert from String array to Integer ArrayList
		String[] strArr = input.split(",");
		ArrayList<Integer> intArr = new ArrayList<>();
		for (String str : strArr) {
			intArr.add(Integer.parseInt(str));
		}

		int[] list = new int[]{ 0, 1, 2, 3, 4 };
		List<List<Integer>> permutations = new ArrayList<>();
		permutations.add(new ArrayList<>());
		for (Integer item : list) {
			permutations = add(item, permutations);
		}

		int max = 0;
		for (List<Integer> phase : permutations) {
			int output = 0;
			for (int i = 0; i < 5; i++) {
				output = run(intArr, new Integer[]{ phase.get(i), output });
			}
			if (output > max) max = output;
		}
		System.out.println(max);

	}

	public static int run(ArrayList<Integer> intArr, Integer[] input) {
		List<Integer> inputValues = new ArrayList<>(Arrays.asList(input));

		// Doing opcode stuff
		int x = 0;
		while (intArr.get(x) != 99){
			int para1 = -1;
			int para2 = -1;
			int para3 = -1;
			try {
				para1 = intArr.get(x + 1);
				para2 = intArr.get(x + 2);
				para3 = intArr.get(x + 3);
			} catch (Exception e){}

			// Find opcode and parameters
			int opcode = intArr.get(x) % 10;
			int mode = (int) Math.floor(intArr.get(x) / 100.0);

			if (mode == 0){
				try {
					para1 = intArr.get(para1);
					para2 = intArr.get(para2);
				} catch (Exception e){}
			} else if (mode == 1){
				try {
					para2 = intArr.get(para2);
				} catch (Exception e){}
			} else if (mode == 10){
				try {
					para1 = intArr.get(para1);
				} catch (Exception e){}
			} else if (mode != 11){
				System.out.println("Invalid mode: " + mode);
				break;
			}

			// Compute
			if (opcode == 1){
				intArr.set(para3, para1 + para2);
				x += 4;
			} else if (opcode == 2){
				intArr.set(para3, para1 * para2);
				x += 4;
			} else if (opcode == 3){
				intArr.set(intArr.get(x + 1), inputValues.remove(0));
				x += 2;
			} else if (opcode == 4){
				return para1;
			} else if (opcode == 5){
				if (para1 != 0){
					x = para2;
				} else {
					x += 3;
				}
			} else if (opcode == 6){
				if (para1 == 0){
					x = para2;
				} else {
					x += 3;
				}
			} else if (opcode == 7){
				if (para1 < para2){
					intArr.set(para3, 1);
				} else {
					intArr.set(para3, 0);
				}
				x += 4;
			} else if (opcode == 8){
				if (para1 == para2){
					intArr.set(para3, 1);
				} else {
					intArr.set(para3, 0);
				}
				x += 4;
			} else {
				System.out.println("Something went wrong! opcode = " + opcode + "; x = " + x);
				break;
			}
		}
		return 0;
	}

	private static <T> List<List<T>> add(T item, List<List<T>> list) {
		List<List<T>> permutations = new ArrayList<>();
		for (List<T> permutation : list) {
			for (int i = 0; i < permutation.size() + 1; i++) {
				List<T> copy = new ArrayList<>(permutation);
				copy.add(i, item);
				permutations.add(copy);
			}
		}
		return permutations;
	}

}
