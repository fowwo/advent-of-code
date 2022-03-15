// December 10th, 2019
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class _1b {
	public static void main(String[] args){
		try {
			File input = new File("input.txt");
			Scanner s = new Scanner(input);
			ArrayList<Integer> list = new ArrayList<Integer>();
			list.add(0);

			int sum = 0;
			while (true){
				while (s.hasNextLine()){
					String line = s.nextLine();
					sum += Integer.parseInt(line);
					if (list.contains(sum)){
						System.out.println("Repeated frequency: " + sum);
						s.close();
						System.exit(0);
					} else {
						list.add(sum);
					}
				}
				s = new Scanner(input);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
	}
}
