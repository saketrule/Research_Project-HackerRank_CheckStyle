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
            int x = scanner.nextInt();
            int y = scanner.nextInt();

            boolean win = isWinningPosition(x, y);
            String result = win ? "First" : "Second";
            System.out.printf("%s\n", result);
        }
    }

    // Create the map of winning/losing positions.
    static Map<Integer,Boolean> winningPosition;
    static {
        winningPosition = new HashMap<>();
    }

    // A winning position is one where a losing position can be reached
    static boolean isWinningPosition(int x, int y) {
        Integer hash = x * 100 + y;
        Boolean cachedResult = winningPosition.get(hash);
        if (cachedResult != null) {
            return cachedResult;
        }
        boolean reachesLosingPosition1 = isValid(x - 2, y + 1) ? !isWinningPosition(x - 2, y + 1) : false;
        boolean reachesLosingPosition2 = isValid(x - 2, y - 1) ? !isWinningPosition(x - 2, y - 1) : false;
        boolean reachesLosingPosition3 = isValid(x + 1, y - 2) ? !isWinningPosition(x + 1, y - 2) : false;
        boolean reachesLosingPosition4 = isValid(x - 1, y - 2) ? !isWinningPosition(x - 1, y - 2) : false;
        boolean result = reachesLosingPosition1 || reachesLosingPosition2 || reachesLosingPosition3 || reachesLosingPosition4;
//        System.out.printf("From %d,%d  the options are [%s, %s, %s, %s], result=%s\n", x, y, reachesLosingPosition1, reachesLosingPosition1, reachesLosingPosition1, reachesLosingPosition1, result);
        winningPosition.put(hash, result);
        return result;
    }

    static boolean isValid(int x, int y) {
        return x >= 1 && x <= 15 && y >= 1 && y <= 15;
    }
}