// December 2nd, 2021
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class a {

	public static void main(String[] args) {

		List<Character> list = new ArrayList<>();
		List<Character> order = new ArrayList<>();
		Map<Character, HashSet<Character>> mustWaitFor = new HashMap<>();
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		try {

			File f = new File("input.txt");
			Scanner s = new Scanner(f);

			while (s.hasNextLine()) {
				String[] str = s.nextLine().split(" ");
				char a = str[1].charAt(0);
				char b = str[7].charAt(0);

				if (!mustWaitFor.containsKey(b)) {
					mustWaitFor.put(b, new HashSet<>());
				}
				mustWaitFor.get(b).add(a);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Add missing letters to map
		for (int i = 0; i < alphabet.length(); i++) {
			char c = alphabet.charAt(i);
			if (!mustWaitFor.containsKey(c)) {
				mustWaitFor.put(c, new HashSet<>());
			}
			list.add(c); // Alphabet to Character list
		}

		while (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				char c = list.get(i);
				boolean ready = true;
				for (char c2 : mustWaitFor.get(c)) {
					if (!order.contains(c2)) {
						ready = false;
						break;
					}
				}
				if (ready) {
					order.add(c);
					list.remove(i);
					break;
				}
			}
		}

		for (char c : order) System.out.print(c);
		System.out.println();

	}

}
