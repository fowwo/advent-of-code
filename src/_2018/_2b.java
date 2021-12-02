// December 12th, 2019
package _2018;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class _2b {

	public static void main(String args[]) {

		try {
			File input = new File("input/2018/2.txt");
			Scanner s = new Scanner(input);
			ArrayList<String> list = new ArrayList<String>();

			while (s.hasNextLine()){
				String line = s.nextLine();
				list.add(line);
			}
			s.close();

			for (int i = 0; i < list.size() - 1; i++){
				for (int j = i + 1; j < list.size(); j++){
					int bad = 0;
					for (int k = 0; k < 26; k++){
						if (list.get(i).charAt(k) != list.get(j).charAt(k)){
							bad++;
							if (bad == 2){
								break;
							}
						}
					}
					if (bad == 1){
						System.out.printf("Two box IDs: \"%s\" and \"%s\"\n", list.get(i), list.get(j));
						System.exit(0);
					}
				}
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
	}
}
