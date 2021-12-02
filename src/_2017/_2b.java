// December 10th, 2019
package _2017;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class _2b {

	public static void main(String args[]) {

		try {
			File input = new File("input/2017/2.txt");
			Scanner s = new Scanner(input);
			String line;
			int sum = 0;

			while (s.hasNextLine()){
				line = s.nextLine();
				String[] tokens = line.split("\t");
				boolean found = false;

				for (int i = 0; i < tokens.length; i++){
					for (int j = 0; j < tokens.length; j++){
						if (i == j){continue;}
						if (Math.floorMod(Integer.parseInt(tokens[i]), Integer.parseInt(tokens[j])) == 0){
							sum += Integer.parseInt(tokens[i]) / Integer.parseInt(tokens[j]);
							found = true;
							break;
						}
					}
					if (found){break;}
				}
			}
			s.close();

			System.out.printf("Checksum: %d\n", sum);
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
	}
}
