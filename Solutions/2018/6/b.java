// December 2nd, 2021
package _2018;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _6b {

	public static void main(String[] args) {

		List<int[]> list = new ArrayList<>();

		try {

			File f = new File("input.txt");
			Scanner s = new Scanner(f);

			while (s.hasNextLine()) {
				String[] str = s.nextLine().split(", ");
				list.add(new int[]{ Integer.parseInt(str[0]), Integer.parseInt(str[1]) });
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Find matrix bounds
		int[] min = { list.get(0)[0], list.get(0)[1] };
		int[] max = { list.get(0)[0], list.get(0)[1] };
		for (int[] point : list) {
			if (point[0] < min[0]) min[0] = point[0];
			if (point[0] > max[0]) max[0] = point[0];
			if (point[1] < min[1]) min[1] = point[1];
			if (point[1] > max[1]) max[1] = point[1];
		}

		// Initialize matrix
		// I'm making the assumption that there are no coordinates outside of these bounds that have a total distance less than 10000
		int[][] matrix = new int[1000][1000];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				int distance = 0;
				for (int[] point : list) {
					distance += Math.abs(i - point[0]) + Math.abs(j - point[1]);
				}
				matrix[i][j] = distance;
			}
		}

		// Find region size
		int count = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] < 10000) count++;
			}
		}

		System.out.println(count);
	}

}
