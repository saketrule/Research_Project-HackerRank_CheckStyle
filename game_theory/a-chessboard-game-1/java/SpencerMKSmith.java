import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static int[][] winners;
 
 public static void main(String[] args) throws FileNotFoundException {
  DiscoverWinners();
  
        Scanner in = new Scanner(System.in);
        int numberOfCases = in.nextInt();
        
        for( int i = 0; i < numberOfCases; i++)
        {
         int xValue = in.nextInt();
         int yValue = in.nextInt();
         
         if(winners[xValue - 1][yValue - 1] == 1)
          System.out.println("First");
         else if(winners[xValue - 1][yValue - 1] == 2)
          System.out.println("Second");
        }
    }
 
 public static void DiscoverWinners()
 {
  //Anyone that tries to move from the first four places will lose
  //1 = Starting here means that winner will be First
  //2 = Starting here means that winner will be Second
  winners = new int[15][15];
  winners[0][0] = 2;
  winners[0][1] = 2;
  winners[1][0] = 2;
  winners[1][1] = 2;

  evaluateSpace(0,0);
  while( !hasFinished())
  {
   for(int x = 0; x < 15; x++)
   {
    for(int y = 0; y < 15; y++)
    {
     if (winners[x][y] == 0)
      evaluateSpace(x, y);
    }
   }
  }
 }
 
 public static void evaluateSpace(int x, int y)
 {
  boolean canEvaluate = true;
  int newValue = 0;
  
  if(y - 2 >= 0)
  {
   if(x - 1 >= 0)   //Check up left
   {
    if(winners[x - 1][y - 2] == 0)
     canEvaluate = false;
    else if(winners[x-1][y-2] == 2)
     winners[x][y] = 1;
   }
   if(x + 1 <= 14)  //Check up right
   {
    if(winners[x + 1][y - 2] == 0 )
     canEvaluate = false;
    else if(winners[x + 1][y - 2] == 2 )
     winners[x][y] = 1;
   }
  }
  
  if(x - 2 >= 0)
  {
   if(y - 1 >= 0)  //Check left up
   {
    if(winners[x - 2][y - 1] == 0)
     canEvaluate = false;
    else if(winners[x - 2][y - 1] == 2)
     winners[x][y] = 1;

   }
   if(y + 1 <= 14)  //Check left down
   {
    if(winners[x - 2][y + 1] == 0 )
     canEvaluate = false;
    else if(winners[x - 2][y + 1] == 2 )
     winners[x][y] = 1;
   }
  }
  
  if( winners[x][y] == 0 && canEvaluate)
   winners[x][y] = 2;
 }
 
 public static boolean hasFinished()
 {
  for (int i = 14; i >= 0; i--)
  {
   for(int j = 14; j >= 0; j--)
   {
    if(winners[i][j] == 0)
     return false;
   }
  }
  
  return true;
 }
}