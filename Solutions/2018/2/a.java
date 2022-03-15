// December 12th, 2019
package _2018;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class _2a {

	public static void main(String args[]) {

		try {
			File input = new File("input.txt");
			Scanner s = new Scanner(input);
			int two = 0;
			int three = 0;

			while (s.hasNextLine()){
				String line = s.nextLine();
				int[] letters = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

				for (int i = 0; i < line.length(); i++){
					letters[(int) line.charAt(i) - 97]++;
				}
				for (int i = 0; i < 26; i++){
					if (letters[i] == 2){
						two++;
						break;
					}
				}
				for (int i = 0; i < 26; i++){
					if (letters[i] == 3){
						three++;
						break;
					}
				}
			}
			s.close();

			System.out.printf("Doubles: %d, Triples: %d\n", two, three);
			System.out.println("Doubles * triples = " + two * three);
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
	}
}
