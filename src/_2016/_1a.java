// December 10th, 2019
package _2016;

public class _1a {
	public static void main(String[] args){

		String input = "R3, L5, R2, L2, R1, L3, R1, R3, L4, R3, L1, L1, R1, L3, R2, L3, L2, R1, R1, L1, R4, L1, L4, R3, L2, L2, R1, L1, R5, R4, R2, L5, L2, R5, R5, L2, R3, R1, R1, L3, R1, L4, L4, L190, L5, L2, R4, L5, R4, R5, L4, R1, R2, L5, R50, L2, R1, R73, R1, L2, R191, R2, L4, R1, L5, L5, R5, L3, L5, L4, R4, R5, L4, R4, R4, R5, L2, L5, R3, L4, L4, L5, R2, R2, R2, R4, L3, R4, R5, L3, R5, L2, R3, L1, R2, R2, L3, L1, R5, L3, L5, R2, R4, R1, L1, L5, R3, R2, L3, L4, L5, L1, R3, L5, L2, R2, L3, L4, L1, R1, R4, R2, R2, R4, R2, R2, L3, L3, L4, R4, L4, L4, R1, L4, L4, R1, L2, R5, R2, R3, R3, L2, L5, R3, L3, R5, L2, R3, R2, L4, L3, L1, R2, L2, L3, L5, R3, L1, L3, L4, L3";
		String[] tokens = input.split(",");
		String[] turn = new String[tokens.length];
		int[] distance = new int[tokens.length];
		int direction = 0;
		int x = 0;
		int y = 0;

		// Create values for arrays
		for (int i = 0; i < tokens.length; i++){
			tokens[i] = tokens[i].trim();
			turn[i] = tokens[i].substring(0,1);
			distance[i] = Integer.parseInt(tokens[i].substring(1));
		}

		// Travel
		for (int i = 0; i < tokens.length; i++){
			// Turn
			if (turn[i].equals("L")){
				direction--;
				if (direction < 0){
					direction = 3;
				}
			} else {
				direction++;
				if (direction > 3){
					direction = 0;
				}
			}

			// Move
			if (direction == 0){ // Up
				y += distance[i];
			} else if (direction == 1){ // Right
				x += distance[i];
			} else if (direction == 2){ // Down
				y -= distance[i];
			} else { // Left
				x -= distance[i];
			}
		}

		System.out.printf("x: %d, y: %d, Manhattan distance: %d\n", x, y, Math.abs(x) + Math.abs(y));
	}
}
