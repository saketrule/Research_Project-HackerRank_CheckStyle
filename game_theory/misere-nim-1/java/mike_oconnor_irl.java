import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    private static boolean winner(int[] piles)
    {   
             int stone = piles[0];
             boolean ones = true;
             for (int i=1; i < piles.length; i++)
             {
                 stone = piles[i] ^ stone;
             }
             for (int i=0; i < piles.length; i++)
             {
                 if (piles[i] > 1)
                     ones = false;
                    
             } 
             if (stone == 0)
                 return (ones);
             else
                 return(!ones);
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for(int games=0; games < T; games++){
            int n = in.nextInt();
            int arr[] = new int[n];
            for(int arr_i=0; arr_i < n; arr_i++){
                arr[arr_i] = in.nextInt();
            }
            boolean winnerisfirst = winner(arr);
            if (winnerisfirst)
                System.out.println("First");
            else 
                System.out.println("Second");
         }
    }
}