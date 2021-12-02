// December 10th, 2019
package _2017;

public class _3a {

	public static void main(String args[]) {

		int input = 347991;
		int exp = 1;

		// Find square
		while (Math.pow(exp, 2) < input){
			exp += 2;
		}
		
		System.out.println("Distance: " + ((exp - 1) - ((Math.pow(exp, 2) - input) % (exp - 1))));
	}
}
