import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
       Scanner in = new Scanner(System.in);
  int caseCount = in.nextInt();
  
  ArrayList<int[]> caseList = new ArrayList<int[]>();  
        
  for(int i = 0; i < caseCount; i++){
   int piles = in.nextInt();
   int iArray[] = new int[piles]; 
   for(int j=0; j < piles; j++){
                iArray[j] = in.nextInt();
            }
   caseList.add(iArray);
        }
        in.close();

        for (int[] testCase : caseList) {
         int xor = 0;
         int maxStones = 0; 
         for (int i : testCase) {
    //System.out.print("" + i);
          if(i > maxStones){
           maxStones = i;
          }
    xor = xor ^ i; 
   }
         
         if ( (maxStones > 1 && xor == 0) || (maxStones <=1 &&  xor == 1 ) ){
           System.out.println("Second");
         }else{
          System.out.println("First");
         }
  }
        
    }
}