import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int numCases = in.nextInt();
        for (int i = 0; i < numCases; i++) {
            int numPiles = in.nextInt();
            int[] piles = new int[numPiles];
            for (int j = 0; j < numPiles; j++) {
                piles[j] = in.nextInt();
            }
            if (checkWinner(piles)) {
                System.out.println("First");
            } else {
                System.out.println("Second");
            }
        }
    }
    
    public static boolean checkWinner(int[] piles) {
        int checker = 1;
        int curPile = piles[0];
        for (int i = 1; i < piles.length; i++) {
            curPile ^= piles[i];
        }
        for (int j = 0; j < piles.length; j++) {
            if (piles[j] > 1) {
                checker = 0;
                break;
            }
        }
        if (checker == curPile) {
            return false;
        } else {
            return true;
        }
        
    }
}