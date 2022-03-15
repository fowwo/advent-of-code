// December 17th, 2019
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class a {
	public static void main(String[] args){
		try {
			File input = new File("input.txt");
			Scanner s = new Scanner(input);
			String line = s.nextLine();
			int min = 150;
			int minLayer = 0;
			s.close();
	
			// Find layer with most zeros
			for (int layer = 0; layer < line.length() / 150; layer++){
				int zero = 0;
				for (int i = 0; i < 150; i++){
					if (line.charAt(i + layer * 150) == '0'){
						zero++;
					}
				}
				if (zero < min){
					min = zero;
					minLayer = layer;
				}
			}
	
			// Count ones and twos in min layer
			int one = 0;
			int two = 0;
			for (int i = 0; i < 150; i++){
				if (line.charAt(i + minLayer * 150) == '1'){
					one++;
				} else if (line.charAt(i + minLayer * 150) == '2'){
					two++;
				}
			}
			System.out.println("Ones * Twos = " + one * two);
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
	}
}
