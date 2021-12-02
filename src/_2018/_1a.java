// December 10th, 2019
package _2018;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class _1a {
	public static void main(String[] args){
		try {
			File input = new File("input/2018/1.txt");
			Scanner s = new Scanner(input);
			int sum = 0;
			while (s.hasNextLine()){
				String line = s.nextLine();
				sum += Integer.parseInt(line);
			}
			s.close();

			System.out.println("Sum: " + sum);
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
	}
}
