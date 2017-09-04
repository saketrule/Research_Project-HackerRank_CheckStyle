import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numOfGames = sc.nextInt();
        //System.out.println("games="+numOfGames);
        for(int i = 0; i < numOfGames; i++)
        {
            int piles = sc.nextInt();
            //System.out.println("piles="+piles);
            int[] stone = new int[piles];
            int sum=0;
            for(int j = 0; j < piles; j++)
            {
                
              stone[j] = sc.nextInt();
                //System.out.println("stone " + j + "="+ stone[j]);
            
                if(j==0)
                {
                    sum = stone[j] ;
                }
                else
                    {
                     sum = sum ^  stone[j];
                }
            }
            
            
            if(sum!=0)
                System.out.println("First");
            else
                System.out.println("Second");
        }
        
    }
}