// December 10th, 2019
public class b {

	private static int surrSum(int[][] matrix, int i, int j){
		return matrix[i-1][j-1] + matrix[i][j-1] + matrix[i+1][j-1] + matrix[i-1][j] + matrix[i+1][j] + matrix[i-1][j+1] + matrix[i][j+1] + matrix[i+1][j+1];
	}
	public static void main(String args[]) {

		int input = 347991;
		int[][] matrix = new int[25][25];
		matrix[7][7] = 1;

		int i = 8;
		int j = 7;

		while (matrix[i][j] <= input){
			while (matrix[i][j-1] != 0){
				matrix[i][j] = surrSum(matrix, i, j);
				if (matrix[i][j] > input){
					break;
				}
				i++;
			}
			while (matrix[i-1][j] != 0){
				matrix[i][j] = surrSum(matrix, i, j);
				if (matrix[i][j] > input){
					break;
				}
				j--;
			}
			while (matrix[i][j+1] != 0){
				matrix[i][j] = surrSum(matrix, i, j);
				if (matrix[i][j] > input){
					break;
				}
				i--;
			}
			while (matrix[i+1][j] != 0){
				matrix[i][j] = surrSum(matrix, i, j);
				if (matrix[i][j] > input){
					break;
				}
				j++;
			}
		}

		System.out.printf("Next value: %d\n", matrix[i][j]);
	}
}
