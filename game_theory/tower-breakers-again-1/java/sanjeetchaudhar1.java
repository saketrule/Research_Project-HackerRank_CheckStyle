import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
      
       
        Scanner sc=new Scanner(System.in);
        
        int testCase=sc.nextInt();
      
        for(int runCase=0;runCase<testCase;runCase++){
            int numberOfTower=sc.nextInt();
            int player=0;
            for (int j = 0; j < numberOfTower; j++) {
                 int tower = sc.nextInt();
                 player ^= countWiningPlayer(tower);
                // System.out.println("Move "+player);
             }
             if (player == 0){
                 System.out.println("2");
                }
             else{
                 System.out.println("1");
                }      
        }
        
    }
    
    
static int countWiningPlayer(int tower) {
            int move = 0;
            if (tower % 2 == 0){
                move++;
            }

            while (tower % 2 == 0) {
                tower /= 2;
            }
               for (int i = 2; i*i <= tower; i++) {
        while (tower % i == 0) {
            tower /= i;
            if (i % 2 != 0){
             move++;
            }
        }
    }
            if (tower > 2){
                move++;  
            }
            return move;
        }
 
}