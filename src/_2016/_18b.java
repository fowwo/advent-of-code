package _2016;

public class _18b {

    public static void main(String[] args) {

        String input = "^^^^......^...^..^....^^^.^^^.^.^^^^^^..^...^^...^^^.^^....^..^^^.^.^^...^.^...^^.^^^.^^^^.^^.^..^.^";

        int total = 0;
        for (int i = 0; i < 400000; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < input.length(); j++) {
                if (!isTrap(j, input)) total++;
                if (isNextTrap(j, input)) {
                    sb.append('^');
                } else {
                    sb.append('.');
                }
            }
            input = sb.toString();
        }

        System.out.println(total);

    }

    public static boolean isNextTrap(int i, String row) {
        return isTrap(i - 1, row) != isTrap(i + 1, row);
    }

    public static boolean isTrap(int i, String row) {
        if (i >= 0 && i < row.length()) {
            return row.charAt(i) == '^';
        }
        return false;
    }

}
