import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        
        int numTestCases = in.nextInt();
        for (int i = 0; i< numTestCases; i++ ){
            int numPiles = in.nextInt();
            int[] piles = new int[numPiles];
            boolean bOverOneStone = false;
            // keep Nim addition, aka Xor
            // do not need to really keep track of piles
            int NimAddition = in.nextInt();  // Get the first one outside of the loop.
            if (NimAddition > 1) bOverOneStone = true;
            for (int j = 1; j< numPiles; j++) {
                piles[j] = in.nextInt();
                if (piles[j] > 1) bOverOneStone = true;
                NimAddition ^= piles[j];
                }
            //And my writeup was all wet.
            // wikipedia for the win. https://en.wikipedia.org/wiki/Nim
            // http://mathoverflow.net/questions/71802/analysis-of-misere-nim
            if((NimAddition == 0 && bOverOneStone == false) ||
                (NimAddition >= 1 && bOverOneStone == true)){
                System.out.println("First");
            }
            else {
                System.out.println("Second");
            }
        }
    }
}