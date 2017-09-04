import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner s = new Scanner(System.in);
        int numTests = s.nextInt();
        
        for (int test = 0; test < numTests; test++){
            int numTiles = s.nextInt();
            
            s.nextInt(); // ignore 0
            boolean firstPlayerWins = false;
            
            long mask = 0;
            for (int tile = 1; tile < numTiles; tile++){
                int numCoins = s.nextInt();
                if (numCoins % 2 == 1){
                    mask = mask ^ tile;
                }
            }
            
            if (mask == 0){
                firstPlayerWins = false;
            } else {
                firstPlayerWins = true;
            }
            
            if (firstPlayerWins){
                System.out.println("First");
            } else {
                System.out.println("Second");
            }
        }
    }
}