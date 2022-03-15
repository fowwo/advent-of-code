// December 10th, 2019
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class a {

	public static void main(String args[]) {

		try {
			File input = new File("input.txt");
			Scanner s = new Scanner(input);
			int valid = 0;

			while (s.hasNextLine()){
				String line = s.nextLine();
				String[] tokens = line.split(" ");
				Arrays.sort(tokens);

				// Check if valid
				valid++;
				for (int i = 0; i < tokens.length - 1; i++){
					if (tokens[i].equals(tokens[i+1])){
						valid--;
						break;
					}
				}
			}
			s.close();

			System.out.println("Valid phrases: " + valid);
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
	}
}
