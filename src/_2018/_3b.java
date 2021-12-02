// December 16th, 2019
package _2018;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class _3b {

	public static void main(String args[]) {

		try {
			File input = new File("input/2018/3.txt");
			Scanner s = new Scanner(input);

			// Create grid
			int[][] matrix = new int[1000][1000];

			while (s.hasNextLine()){
				String[] line = s.nextLine().split(" ");

				// Parse input
				int loc[] = new int[2];
				int dim[] = new int[2];
				String[] locStrings = line[2].split(",");
				String[] dimStrings = line[3].split("x");
				loc[0] = Integer.parseInt(locStrings[0]);
				loc[1] = Integer.parseInt(locStrings[1].substring(0, locStrings[1].length() - 1));
				dim[0] = Integer.parseInt(dimStrings[0]);
				dim[1] = Integer.parseInt(dimStrings[1]);

				// Add fabric claims
				for (int i = loc[0]; i < loc[0] + dim[0]; i++){
					for (int j = loc[1]; j < loc[1] + dim[1]; j++){
						matrix[i][j]++;
					}
				}
			}
			s = new Scanner(input);
			while (s.hasNextLine()){
				String[] line = s.nextLine().split(" ");

				// Parse input
				int loc[] = new int[2];
				int dim[] = new int[2];
				String[] locStrings = line[2].split(",");
				String[] dimStrings = line[3].split("x");
				loc[0] = Integer.parseInt(locStrings[0]);
				loc[1] = Integer.parseInt(locStrings[1].substring(0, locStrings[1].length() - 1));
				dim[0] = Integer.parseInt(dimStrings[0]);
				dim[1] = Integer.parseInt(dimStrings[1]);

				// Check fabric claims
				boolean overlap = false;
				for (int i = loc[0]; i < loc[0] + dim[0]; i++){
					for (int j = loc[1]; j < loc[1] + dim[1]; j++){
						if (matrix[i][j] > 1){
							overlap = true;
							break;
						}
					}
					if (overlap){ break; }
				}
				if (!overlap){
					System.out.println("Claim " + line[0] + " does not overlap.");
					System.exit(0);
				}
			}
			s.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
	}
}
