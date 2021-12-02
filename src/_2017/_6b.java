// December 12th, 2019
package _2017;

import java.util.ArrayList;

public class _6b {
	private static int dupl(ArrayList<int[]> list, int[] arr){
		for (int i = 0; i < list.size(); i++){
			for (int j = 0; j < arr.length; j++){
				if (list.get(i)[j] != arr[j]){
					break;
				}
				if (j == arr.length - 1){
					return i;
				}
			}
		}
		return -1;
	}
	public static void main(String args[]){
		String input = "4	1	15	12	0	9	9	5	5	8	7	3	14	5	12	3";
		String[] memory = input.split("\t");
		int[] arr = new int[memory.length];
		for (int i = 0; i < memory.length; i++){
			arr[i] = Integer.parseInt(memory[i]);
		}
		ArrayList<int[]> list = new ArrayList<int[]>();

		int step = 0;
		while (dupl(list, arr) == -1){
			list.add(arr.clone());
			step++;

			// Find max
			int max = 0;
			for (int i = 0; i < arr.length; i++){
				if (arr[i] > arr[max]){
					max = i;
				}
			}

			// Distribute
			int count = arr[max];
			arr[max] = 0;
			int loc = max;
			while (count > 0){
				if (loc == arr.length - 1){
					loc = 0;
				} else {
					loc++;
				}
				arr[loc] += 1;
				count--;
			}
		}

		System.out.println("Steps: " + (step - dupl(list, arr)));
	}
}
