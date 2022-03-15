// December 10th, 2019
import java.util.ArrayList;

public class a {
	public static void main(String[] args){
		String input = "1,0,0,3,1,1,2,3,1,3,4,3,1,5,0,3,2,9,1,19,1,9,19,23,1,23,5,27,2,27,10,31,1,6,31,35,1,6,35,39,2,9,39,43,1,6,43,47,1,47,5,51,1,51,13,55,1,55,13,59,1,59,5,63,2,63,6,67,1,5,67,71,1,71,13,75,1,10,75,79,2,79,6,83,2,9,83,87,1,5,87,91,1,91,5,95,2,9,95,99,1,6,99,103,1,9,103,107,2,9,107,111,1,111,6,115,2,9,115,119,1,119,6,123,1,123,9,127,2,127,13,131,1,131,9,135,1,10,135,139,2,139,10,143,1,143,5,147,2,147,6,151,1,151,5,155,1,2,155,159,1,6,159,0,99,2,0,14,0";
		
		// Convert from String array to Integer ArrayList
		String[] strArr = input.split(",");
		ArrayList<Integer> intArr = new ArrayList<Integer>();
		for (int i = 0; i < strArr.length; i++){
			intArr.add(Integer.parseInt(strArr[i]));
		}

		// "...before running the program, replace position 1 with the value 12 and replace position 2 with the value 2."
		intArr.set(1, 12);
		intArr.set(2, 2);

		// Doing opcode stuff
		int x = 0;
		while (intArr.get(x) != 99){
			if (intArr.get(x) == 1){
				intArr.set(intArr.get(x + 3), intArr.get(intArr.get(x + 1)) + intArr.get(intArr.get(x + 2)));
			} else if (intArr.get(x) == 2){
				intArr.set(intArr.get(x + 3), intArr.get(intArr.get(x + 1)) * intArr.get(intArr.get(x + 2)));
			} else {
				System.out.println("Something went wrong!");
				break;
			}
			x += 4;
		}

		System.out.printf("The first element in the list is: %d\n", intArr.get(0));
	}
}
