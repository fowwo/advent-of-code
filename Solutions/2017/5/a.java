// December 12th, 2019
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class a {

	public static void main(String args[]) {

		try {
			File input = new File("input.txt");
			Scanner s = new Scanner(input);
			ArrayList<Integer> list = new ArrayList<Integer>();

			// Create ArrayList
			while (s.hasNextLine()){
				String line = s.nextLine();
				list.add(Integer.parseInt(line));
			}
			s.close();

			// Navigate
			int i = 0;
			int step = 0;
			while(i < list.size()){
				step++;
				list.set(i, list.get(i) + 1);
				i += list.get(i) - 1;
			}

			System.out.println("Steps: " + step);
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
	}
}
