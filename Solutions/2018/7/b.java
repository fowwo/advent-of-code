// July 7th, 2022
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class b {

	public static void main(String[] args) {

		List<Character> list = new ArrayList<>();
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

		// Work
		Map<Character, Integer> working = new HashMap<>();
		int time = -1;
		while (mustWaitFor.size() > 0) {
			for (char c : new HashSet<>(working.keySet())) {
				working.put(c, working.get(c) - 1);
				if (working.get(c) == 0) {
					working.remove(c);
					mustWaitFor.remove(c);
					for (char d : mustWaitFor.keySet()) {
						mustWaitFor.get(d).remove(c);
					}
				}
			}
			if (working.size() < 5) {
				for (char c : mustWaitFor.keySet()) {
					if (!working.containsKey(c) && mustWaitFor.get(c).size() == 0 && working.size() < 5) {
						working.put(c, c - 4);
					}
				}
			}
			time++;
		}
		System.out.println(time);

	}

}
