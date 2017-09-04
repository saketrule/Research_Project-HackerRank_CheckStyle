import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        
        
        int numGames = in.nextInt();
        
        for (int i = 0; i < numGames; i++) {
            playOneGame();
        }
    }
    
    private static void playOneGame() {
        int numPiles = in.nextInt();
        int startingAmount = in.nextInt();
        int nimSum = 0;
        
        for (int i = 0; i < numPiles; i++) {
            nimSum = nimSum ^ in.nextInt();
        }
        
        if (nimSum == 0) {
            System.out.println("Second");
        }
        else {
            System.out.println("First");
        }
    }
}