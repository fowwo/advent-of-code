// May 5th, 2020
public class a {

    public static void main(String[] args) {

        int inputRow = 2947;
        int inputColumn = 3029;

        long[][] matrix = new long[6000][6000];
        matrix[1][1] = 20151125;
        long prev = matrix[1][1];

        int row = 1;
        int column = 1;
        for (int a = 0; row != inputRow || column != inputColumn; a++) {
            if (row == 1) {
                row = column + 1;
                column = 1;
            } else {
                row--;
                column++;
            }
            matrix[row][column] = prev * 252533 % 33554393;
            prev = matrix[row][column];
        }

        System.out.println(prev);

    }

}
