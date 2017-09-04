/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kyle
 */

 import java.io.*;

 /*
 This file uses a dynamic programming approach
 */
 
public class Solution {

 public static void main (String[] args){
  //variables to read input
     BufferedReader br;
     BufferedReader br1;
     String input;
     int pos1 = 0;   
     int pos2 = 0; 
     String curWord;
     
     
     //variables that are given as input from the user
     int N = 0;
  int K = 0;
  long[] profits;
  profits = new long[0];
     
     
     //stores the max profit that is the output of the program
     long maxProfit = 0;
     
     //used as temporary storage in the dynamic programming algorithm so that we only need to access the profits array once per BB instead of K times
     long curProfit = 0;
     
     
     //variable store the largest possible streak before a BB. Min of K and number of BB's before current one
     int maxInStreak = 0;
     //variable store the largest possible streak after a BB. Min of K and number of BB's before and including current one
     int maxOutStreak = 0;
     //stores the profit from removing a BB
     long profitRemove = 0;
     //indicator variable for whether we should read from second and write to first, or read from first and write to second for dynamicApproach array
     //0 indicates read from second, write first. 1 indicates read first, write second
     int readWriteIndicator = 0;
     

     
     //these variables are to find the cutoff point where for any streak less than the cutoff point, it is better to keep the billboard and for any streak greater than
     //the cutoff point it is better to remove the BB
     int cutoff = 0;
     int low;
     int high;
     int initLow;
     int initHigh;
     int numStreaksCovered = 0;
     boolean cont = true;
     long cutoffProfit;
 
     //first dimension has one row for current BB and another for next BB. Order switches back and forth so that the same space can be reused
     //second dimension is over the possible streaks before current BB. 0 is no streak and K+1 is a maximum streak of K
     //stores the profit from the current BB to the last BB given the current streak
     long[][] dynamicApproach;
     dynamicApproach = new long[0][0];
     
     
  try {
   br = new BufferedReader(new InputStreamReader(System.in));
   

         input = br.readLine();
         //get position of blank space
         pos1 = input.indexOf(" ");
         //now store value of N
         curWord = input.substring(0,pos1);
         N = Integer.parseInt(curWord);
         
         
         //get position of blank space
         pos2 = pos1 + 1;
         pos1 = input.indexOf(" ",pos2);

         //now store value of K
         //if there is no trailing space
         if(pos1 == -1){
          curWord = input.substring(pos2);
         //otherwise if there is a trailing space
         }else{
          curWord = input.substring(pos2,pos1); 
         }

         K = Integer.parseInt(curWord);

         profits = new long[N];
                 
         //loop to get N profits
         for(int i = 0; i < N; i++){
          input = br.readLine();
          //get position of blank space
          pos1 = input.indexOf(" ");
          //if there was no trailing space
          if(pos1 == -1){
           profits[i] = Long.parseLong(input);
          }else{
           //remove trailing space
           curWord = input.substring(0,pos1);
           profits[i] = Long.parseLong(curWord);
          }
         }

     } catch (Exception e) {
   //System.out.println(e);
     }
     
     
      
     //initialize dynamicApproach array. 
     //rows 0 and 2 will store the set of unique profits
     //rows 1 and 3 store the number of each unique profit
     dynamicApproach = new long[4][K+2];
     for(int i = 0; i < 4; i++){
      for(int j = 0; j < K+2; j++){
       dynamicApproach[i][j] = 0;
      }
     }
     
  
     dynamicApproach[3][0] = K+1;
    
     //calculate the best profit at each stage given any possible streak
     for(int i = N-1; i >= 0; i--){
      
      
      if(i < K){
       maxInStreak = i;
       maxOutStreak = i+1;
      }else{
       maxInStreak = K;
       maxOutStreak = K;
      }
      
      
      //determine how we are reading/writing from/to dynamicApproach array
      readWriteIndicator = (N - 1 - i) % 2;
      
      //get the profit for the ith BB and store it so that we do not need to access it K times
      curProfit = profits[i];
      
      
      //read from second, write first
      if(readWriteIndicator == 0){
      
       //get the profit from removing the current BB. This is the profit at the next BB with no initial streak
       profitRemove = dynamicApproach[2][0];
       
       cont = true;
       
       
       //here we take advantage of the fact that the profit from keeping the BB is a decreasing function as cutoff (the streak) increases
       //therefore we will find the cutoff point to determine when to keep the BB and when to remove the BB given an initial streak
       
       
       if(dynamicApproach[3][0] > 1){
        initLow = 0;
       }else{
        initLow = 1;
       }
       low = initLow;
       
       initHigh = (int)dynamicApproach[3][K+1];
       high = initHigh;
       
       //any streak less than cutoff point, we will keep the BB. any streak greater than or equal to the cutoff point will remove the BB
       
      
       while(cont){
        cutoff = (high + low)/2;
        cutoffProfit = curProfit + dynamicApproach[2][cutoff];
        
        //if low is within one index of high, then we want to stop
        if(low+1 >= high){
         //stop loop
         cont = false;
         
        }
         
        
        //other stopping condition is that the profit from the cutoff point equals the profit from removing the BB
        if(cutoffProfit == profitRemove){
         cont = false;
        } 
        
        
        //if we have not met any of the stopping conditions, we need to get the new values of low or high. Only one will change
        if(cutoffProfit > profitRemove){
         low = cutoff;
        }else{
         high = cutoff;
        }
         
       }
       
       
       //if there is another index above cutoff, check if we should increase cutoff by 1
       if(cutoff < initHigh){
        cutoffProfit = curProfit + dynamicApproach[2][cutoff];
        
        if(cutoffProfit > profitRemove){
         cutoff++;
        }
       }
       
       //if cutoff is at initHigh, check if we should increase cutoff by 1
       if(cutoff == initHigh){
        cutoffProfit = curProfit + dynamicApproach[2][cutoff];
        
        if(cutoffProfit > profitRemove){
         cutoff++;
        }
       }
       
       //now we construct the dynamicApproach array for the current BB
       
       numStreaksCovered = 0;
       
       //need to determine if we should keep the current BB for any streak.
       if(cutoff == initLow){
        //we do not keep the BB
        dynamicApproach[0][0] = profitRemove;
        dynamicApproach[1][0] = maxInStreak+1;
        dynamicApproach[1][K+1] = 0;
        
       }else{
        //we do keep BB for some streaks
        
        //if we starting looking from index 0
        if(initLow == 0){
         dynamicApproach[0][0] = dynamicApproach[2][0] + curProfit;
         dynamicApproach[1][0] = dynamicApproach[3][0] - 1;
         numStreaksCovered = (int)dynamicApproach[3][0] - 1;
        }
       
        for(int j = 1; j < cutoff; j++){
         dynamicApproach[0][j-initLow] = dynamicApproach[2][j] + curProfit;
         dynamicApproach[1][j-initLow] = dynamicApproach[3][j];
         
         numStreaksCovered = numStreaksCovered + (int)dynamicApproach[3][j];
        }
         
       
        
        //add profit if we need to remove BB
        if(maxInStreak + 1 > numStreaksCovered){
         dynamicApproach[0][cutoff - initLow] = profitRemove;
         dynamicApproach[1][cutoff - initLow] = maxInStreak + 1 - numStreaksCovered;
         dynamicApproach[1][K+1] = cutoff - initLow;
        
        //otherwise if we dont need to remove profit
        }else{
         dynamicApproach[1][K+1] = cutoff - initLow - 1;
        }
       }
        
      
      //read from first, write second 
      }else{
      
    //get the profit from removing the current BB. This is the profit at the next BB with no initial streak
       profitRemove = dynamicApproach[0][0];
       
       cont = true;
       
       
       //here we take advantage of the fact that the profit from keeping the BB is a decreasing function as cutoff (the streak) increases
       //therefore we will find the cutoff point to determine when to keep the BB and when to remove the BB given an initial streak
       if(dynamicApproach[1][0] > 1){
        initLow = 0;
       }else{
        initLow = 1;
       }
       low = initLow;
       
       initHigh = (int)dynamicApproach[1][K+1];
       high = initHigh;
       
       //any streak less than cutoff point, we will keep the BB. any streak greater than or equal to the cutoff point will remove the BB
       
      
       while(cont){
        cutoff = (high + low)/2;
        cutoffProfit = curProfit + dynamicApproach[0][cutoff];
        
        //if low is within one index of high, then we want to stop
        if(low+1 >= high){
         //stop loop
         cont = false;
         
        }
         
        
        //other stopping condition is that the profit from the cutoff point equals the profit from removing the BB
        if(cutoffProfit == profitRemove){
         cont = false;
        } 
        
        
        //if we have not met any of the stopping conditions, we need to get the new values of low or high. Only one will change
        if(cutoffProfit > profitRemove){
         low = cutoff;
        }else{
         high = cutoff;
        }
         
       }
       
       
       //if there is another index above cutoff, check if we should increase cutoff by 1
       if(cutoff < initHigh){
        cutoffProfit = curProfit + dynamicApproach[0][cutoff];
        
        if(cutoffProfit > profitRemove){
         cutoff++;
        }
       }
       
       //if cutoff is at initHigh, check if we should increase cutoff by 1
       if(cutoff == initHigh){
        cutoffProfit = curProfit + dynamicApproach[0][cutoff];
        
        if(cutoffProfit > profitRemove){
         cutoff++;
        }
       }
       
       //now we construct the dynamicApproach array for the current BB
       
       numStreaksCovered = 0;
       
       //need to determine if we should keep the current BB for any streak.
       if(cutoff == initLow){
        //we do not keep the BB
        dynamicApproach[2][0] = profitRemove;
        dynamicApproach[3][0] = maxInStreak+1;
        dynamicApproach[3][K+1] = 0;
        
       }else{
        //we do keep BB for some streaks
        
        //if we starting looking from index 0
        if(initLow == 0){
         dynamicApproach[2][0] = dynamicApproach[0][0] + curProfit;
         dynamicApproach[3][0] = dynamicApproach[1][0] - 1;
         numStreaksCovered = (int)dynamicApproach[1][0] - 1;
        }
       
        for(int j = 1; j < cutoff; j++){
         dynamicApproach[2][j-initLow] = dynamicApproach[0][j] + curProfit;
         dynamicApproach[3][j-initLow] = dynamicApproach[1][j];
         
         numStreaksCovered = numStreaksCovered + (int)dynamicApproach[1][j];
        }
         
       
        
        //add profit if we need to remove BB
        if(maxInStreak + 1 > numStreaksCovered){
         dynamicApproach[2][cutoff - initLow] = profitRemove;
         dynamicApproach[3][cutoff - initLow] = maxInStreak + 1 - numStreaksCovered;
         dynamicApproach[3][K+1] = cutoff - initLow;
        
        //otherwise if we dont need to remove profit
        }else{
         dynamicApproach[3][K+1] = cutoff - initLow - 1;
        }
       }
   }
     }
     
     
     //maxProfit is the profit from the first BB given no initial streak
     readWriteIndicator = (N - 1) % 2;
     if(readWriteIndicator == 1){
      maxProfit = dynamicApproach[2][0];
     }else{
      maxProfit = dynamicApproach[0][0];
     }
     
     //output result to user
     System.out.println(maxProfit);
     
     
    }

    
}