// December 2nd, 2021
package _2018;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _6a {

	public static void main(String[] args) {

		List<int[]> list = new ArrayList<>();

		try {

			File f = new File("input/2018/6.txt");
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
		int[][] matrix = new int[max[0] - min[0] + 1][max[1] - min[1] + 1];
		for (int i = 0; i < list.size(); i++) {
			int[] point = list.get(i);
			matrix[point[0] - min[0]][point[1] - min[1]] = i + 1;
		}

		// Fill matrix
		boolean empty = false;
		while (!empty) {

			// Copy matrix
			int[][] copy = new int[matrix.length][matrix[0].length];
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix[0].length; j++) {
					copy[i][j] = matrix[i][j];
				}
			}

			// Iterate fill
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix[0].length; j++) {
					if (matrix[i][j] == 0) {
						copy[i][j] = getClosest(matrix, i, j);
					}
				}
			}

			// Update matrix
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix[0].length; j++) {
					matrix[i][j] = copy[i][j];
				}
			}

			// Check empty
			empty = true;
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix[0].length; j++) {
					if (matrix[i][j] == 0) {
						empty = false;
						break;
					}
				}
			}

		}

		// Count each area
		int[] count = new int[list.size()];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				int value = matrix[i][j];
				if (value > 0) count[value - 1]++;
			}
		}

		// Areas on the edge are infinite
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i][0] > 0) count[matrix[i][0] - 1] = 0;
			if (matrix[i][matrix[0].length - 1] > 0) count[matrix[i][matrix[0].length - 1] - 1] = 0;
		}
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[0][i] > 0) count[matrix[0][i] - 1] = 0;
			if (matrix[matrix.length - 1][i] > 0) count[matrix[matrix.length - 1][i] - 1] = 0;
		}

		// Find maximum area's number
		int index = 0;
		for (int i = 1; i < count.length; i++) {
			if (count[i] > count[index]) index = i;
		}
		index++;

		// Find maximum area
		int area = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == index) area++;
			}
		}

		System.out.println(area);
	}

	static int getClosest(int[][] matrix, int x, int y) {
		int near = 0;
		if (x > 0                    && matrix[x - 1][y] != 0) near = matrix[x - 1][y];
		if (y > 0                    && matrix[x][y - 1] != 0) {
			if (near != 0 && near != matrix[x][y - 1]) return -1;
			near = matrix[x][y - 1];
		}
		if (x < matrix.length - 1    && matrix[x + 1][y] != 0) {
			if (near != 0 && near != matrix[x + 1][y]) return -1;
			near = matrix[x + 1][y];
		}
		if (y < matrix[0].length - 1 && matrix[x][y + 1] != 0) {
			if (near != 0 && near != matrix[x][y + 1]) return -1;
			near = matrix[x][y + 1];
		}
		return near;
	}

}
