// December 17th, 2019
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class _8b {
	public static void main(String[] args){
		try {
			File input = new File("input.txt");
			Scanner s = new Scanner(input);
			String line = s.nextLine();
			char[][] image = new char[25][6];
			s.close();
			
			// Make image 'transparent'
			for (int i = 0; i < 25; i++){
				for (int j = 0; j < 6; j++){
					image[i][j] = '2';
				}
			}

			// Create image
			for (int layer = 0; layer < line.length() / 150; layer++){
				for (int row = 0; row < 6; row++){
					for (int i = 0; i < 25; i++){
						if (image[i][row] == '2'){
							image[i][row] = line.charAt(i + row * 25 + layer * 150);
						}
					}
				}
			}

			// Print image
			for (int row = 0; row < 6; row++){
				for (int i = 0; i < 25; i++){
					if (image[i][row] != '1'){ // The message is made of 1s
						System.out.printf(" ");
					} else {
						System.out.printf("%c", image[i][row]);
					}
				}
				System.out.printf("\n");
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
	}
}
