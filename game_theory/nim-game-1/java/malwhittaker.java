import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

        public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        int cases = scanner.nextInt();
        for (int c = 0; c < cases; c++) {
            int heaps = scanner.nextInt();
            int[] array = new int[heaps];
            for (int h = 0; h < heaps; h++) {
                array[h] = scanner.nextInt();
            }

            boolean win = isWinningPosition(array);
            String result = win ? "First" : "Second";
            System.out.printf("%s\n", result);
        }
    }

    // Theorem. In a normal Nim game, the player making the first move has a winning strategy if and only if the nim-sum
    // of the sizes of the heaps is nonzero. Otherwise, the second player has a winning strategy.
    static boolean isWinningPosition(int[] array) {
        int nimSum = 0;
        for (int value: array) {
            nimSum = nimSum ^ value;
        }
        return nimSum != 0;
    }
}