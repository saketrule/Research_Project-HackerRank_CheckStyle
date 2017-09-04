import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static int findLoser(int[] piles){
        int find = 0; //int flag = 0;
        int count = 0;
        int xor = 0;
        for(int i = 0; i < piles.length; i++){
            xor ^= piles[i];
            if(piles[i] > 1){
                count = 1;
            }
         }
       // System.out.println(count1);
        if(xor == 1 && count == 0){
            return 2;
        }
        else if(xor == 0 && count == 1){
        return 2;
        }
        return 1;
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int q = scan.nextInt();
        for(int i = 0; i < q; i++){
            int no_piles = scan.nextInt();
            int[] pile = new int[no_piles];
            for(int j = 0; j < no_piles; j++){
                pile[j] = scan.nextInt();
                if(pile[j]!=0){
                }
            }
            int loser = findLoser(pile);
            if(loser == 1){
                System.out.println("First");
            }
            else{
                System.out.println("Second");
            }
        }
    }
}