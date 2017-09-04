import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int expectedNumTestCases = Integer.parseInt(scanner.nextLine());
        int numTestCasesFound = 0;

        Queue<String> queue = new LinkedList();

        while (numTestCasesFound < expectedNumTestCases) {
            queue.add(scanner.nextLine());
            queue.add(scanner.nextLine());
            numTestCasesFound++;
        }

        int testCasesExecuted = 0;

        while (testCasesExecuted < numTestCasesFound) {
            int numPiles = Integer.parseInt(queue.remove());
            String layout = queue.remove();

            String[] piles = layout.split(" ");

            int[] bitSum = new int[8];

            for (int i = 0; i < numPiles; i++) {
                int pileSize = Integer.parseInt(piles[i]);
                char[] bits = Integer.toString(pileSize, 2).toCharArray();

                for (int j = 0; j < bits.length; j++) {
                    final int index = 8 - bits.length + j;

                    if (bits[j] == '1') bitSum[index] ++;
                }
            }

            boolean evenNumberOfPilesAndAllOfSizeOne = false;
            boolean allOfSizeOne = false;

            // if all piles are 1 and the number of piles is even, then first will win
            for (int a = 0; a < bitSum.length; a++) {
                if (a < 7 && bitSum[a] > 0) break;

                if (a == 7) allOfSizeOne = true;
            }

            boolean firstWillWin = false;

            if (allOfSizeOne) {

            }
            else {

                if (!allOfSizeOne) {
                    for (int x = 0; x < bitSum.length; x++) {
                        if (bitSum[x] % 2 != 0) {
                            firstWillWin = true;
                            break;
                        }
                    }
                }
            }

            testCasesExecuted++;
            System.out.println(((allOfSizeOne && numPiles%2==0) || firstWillWin) ? "First" : "Second");
        }
        
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
}