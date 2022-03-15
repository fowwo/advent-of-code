// December 12th, 2019
import java.util.ArrayList;

public class b {
	public static void main(String[] args){
		String input = "3,225,1,225,6,6,1100,1,238,225,104,0,1,192,154,224,101,-161,224,224,4,224,102,8,223,223,101,5,224,224,1,223,224,223,1001,157,48,224,1001,224,-61,224,4,224,102,8,223,223,101,2,224,224,1,223,224,223,1102,15,28,225,1002,162,75,224,1001,224,-600,224,4,224,1002,223,8,223,1001,224,1,224,1,224,223,223,102,32,57,224,1001,224,-480,224,4,224,102,8,223,223,101,1,224,224,1,224,223,223,1101,6,23,225,1102,15,70,224,1001,224,-1050,224,4,224,1002,223,8,223,101,5,224,224,1,224,223,223,101,53,196,224,1001,224,-63,224,4,224,102,8,223,223,1001,224,3,224,1,224,223,223,1101,64,94,225,1102,13,23,225,1101,41,8,225,2,105,187,224,1001,224,-60,224,4,224,1002,223,8,223,101,6,224,224,1,224,223,223,1101,10,23,225,1101,16,67,225,1101,58,10,225,1101,25,34,224,1001,224,-59,224,4,224,1002,223,8,223,1001,224,3,224,1,223,224,223,4,223,99,0,0,0,677,0,0,0,0,0,0,0,0,0,0,0,1105,0,99999,1105,227,247,1105,1,99999,1005,227,99999,1005,0,256,1105,1,99999,1106,227,99999,1106,0,265,1105,1,99999,1006,0,99999,1006,227,274,1105,1,99999,1105,1,280,1105,1,99999,1,225,225,225,1101,294,0,0,105,1,0,1105,1,99999,1106,0,300,1105,1,99999,1,225,225,225,1101,314,0,0,106,0,0,1105,1,99999,1108,226,226,224,102,2,223,223,1005,224,329,101,1,223,223,107,226,226,224,1002,223,2,223,1005,224,344,1001,223,1,223,107,677,226,224,102,2,223,223,1005,224,359,101,1,223,223,7,677,226,224,102,2,223,223,1005,224,374,101,1,223,223,108,226,226,224,102,2,223,223,1006,224,389,101,1,223,223,1007,677,677,224,102,2,223,223,1005,224,404,101,1,223,223,7,226,677,224,102,2,223,223,1006,224,419,101,1,223,223,1107,226,677,224,1002,223,2,223,1005,224,434,1001,223,1,223,1108,226,677,224,102,2,223,223,1005,224,449,101,1,223,223,108,226,677,224,102,2,223,223,1005,224,464,1001,223,1,223,8,226,677,224,1002,223,2,223,1005,224,479,1001,223,1,223,1007,226,226,224,102,2,223,223,1006,224,494,101,1,223,223,1008,226,677,224,102,2,223,223,1006,224,509,101,1,223,223,1107,677,226,224,1002,223,2,223,1006,224,524,1001,223,1,223,108,677,677,224,1002,223,2,223,1005,224,539,1001,223,1,223,1107,226,226,224,1002,223,2,223,1006,224,554,1001,223,1,223,7,226,226,224,1002,223,2,223,1006,224,569,1001,223,1,223,8,677,226,224,102,2,223,223,1006,224,584,101,1,223,223,1008,677,677,224,102,2,223,223,1005,224,599,101,1,223,223,1007,226,677,224,1002,223,2,223,1006,224,614,1001,223,1,223,8,677,677,224,1002,223,2,223,1005,224,629,101,1,223,223,107,677,677,224,102,2,223,223,1005,224,644,101,1,223,223,1108,677,226,224,102,2,223,223,1005,224,659,101,1,223,223,1008,226,226,224,102,2,223,223,1006,224,674,1001,223,1,223,4,223,99,226";
		int inputValue = 5;

		// Convert from String array to Integer ArrayList
		String[] strArr = input.split(",");
		ArrayList<Integer> intArr = new ArrayList<Integer>();
		for (int i = 0; i < strArr.length; i++){
			intArr.add(Integer.parseInt(strArr[i]));
		}

		// Doing opcode stuff
		int x = 0;
		while (intArr.get(x) != 99){
			int para1 = -1;
			int para2 = -1;
			int para3 = -1;
			try {
				para1 = intArr.get(x + 1);
				para2 = intArr.get(x + 2);
				para3 = intArr.get(x + 3);
			} catch (Exception e){}

			// Find opcode and parameters
			int opcode = intArr.get(x) % 10;
			int mode = (int) Math.floor(intArr.get(x) / 100.0);
			
			if (mode == 0){
				try {
					para1 = intArr.get(para1);
					para2 = intArr.get(para2);
				} catch (Exception e){}
			} else if (mode == 1){
				try {
					para2 = intArr.get(para2);
				} catch (Exception e){}
			} else if (mode == 10){
				try {
					para1 = intArr.get(para1);
				} catch (Exception e){}
			} else if (mode != 11){
				System.out.println("Invalid mode: " + mode);
				break;
			}

			// Compute
			if (opcode == 1){
				intArr.set(para3, para1 + para2);
				x += 4;
			} else if (opcode == 2){
				intArr.set(para3, para1 * para2);
				x += 4;
			} else if (opcode == 3){
				intArr.set(intArr.get(x + 1), inputValue);
				x += 2;
			} else if (opcode == 4){
				System.out.println(para1);
				x += 2;
			} else if (opcode == 5){
				if (para1 != 0){
					x = para2;
				} else {
					x += 3;
				}
			} else if (opcode == 6){
				if (para1 == 0){
					x = para2;
				} else {
					x += 3;
				}
			} else if (opcode == 7){
				if (para1 < para2){
					intArr.set(para3, 1);
				} else {
					intArr.set(para3, 0);
				}
				x += 4;
			} else if (opcode == 8){
				if (para1 == para2){
					intArr.set(para3, 1);
				} else {
					intArr.set(para3, 0);
				}
				x += 4;
			} else {
				System.out.println("Something went wrong! opcode = " + opcode + "; x = " + x);
				break;
			}
		}
	}
}
