// December 12th, 2019
package _2017;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class _4b {

	public static void main(String args[]) {

		try {
			File input = new File("input/2017/4.txt");
			Scanner s = new Scanner(input);
			int[] letters = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			int count = 0;

			while (s.hasNextLine()){
				String line = s.nextLine();
				String[] tokens = line.split(" ");

				// Check if valid
				boolean valid = true;
				for (int i = 0; i < tokens.length; i++){
					for (int j = 0; j < tokens.length; j++){
						if (i == j){ continue; }
						if (tokens[i].length() == tokens[j].length()){
							for (int k = 0; k < 26; k++){
								letters[k] = 0;
							}
							for (int k = 0; k < tokens[i].length(); k++){
								letters[(int) tokens[i].charAt(k) - 97]++;
								letters[(int) tokens[j].charAt(k) - 97]--;
							}
							int zero = 0;
							for (int k = 0; k < 26; k++){
								if (letters[k] == 0){
									zero++;
								}
							}
							if (zero == 26){
								valid = false;
								break;
							}
						}
					}
					if (!valid){ break; }
				}
				if (valid){ count++; }
			}
			s.close();

			System.out.println("Valid phrases: " + count);
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
	}
}
