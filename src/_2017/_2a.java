// December 10th, 2019
package _2017;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class _2a {

	public static void main(String args[]) {

		try {
			File input = new File("input/2017/2.txt");
			Scanner s = new Scanner(input);
			String line;
			int sum = 0;

			while (s.hasNextLine()){
				line = s.nextLine();
				String[] tokens = line.split("\t");

				// Find difference
				int min = Integer.parseInt(tokens[0]);
				int max = Integer.parseInt(tokens[0]);

				for (int i = 0; i < tokens.length; i++){
					if (Integer.parseInt(tokens[i]) < min){
						min = Integer.parseInt(tokens[i]);
					}
					if (Integer.parseInt(tokens[i]) > max){
						max = Integer.parseInt(tokens[i]);
					}
				}

				sum += max - min;
			}
			s.close();

			System.out.printf("Checksum: %d\n", sum);
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
	}
}
