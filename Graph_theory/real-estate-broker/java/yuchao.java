import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
  Scanner in = new Scanner(System.in);
  int clients = in.nextInt();
  int houses = in.nextInt();
  
  int[][] c_ary = new int[clients][2];
  for(int c = 0; c < clients;c++){
   c_ary[c][0] = in.nextInt();
   c_ary[c][1] = in.nextInt();
  }
  
  boolean[][] graph = new boolean[clients][houses];
  for(int h = 0;h < houses; h++){
   int x = in.nextInt();
   int y = in.nextInt();
   for(int c = 0;c < clients;c++){
    if(x > c_ary[c][0] && y <= c_ary[c][1])
     graph[c][h] = true;
    else
     graph[c][h] = false;
   }
  }
  
  int result = maxBPM(graph, houses, clients);
  System.out.println(result);
  
 }
 
 public static boolean bpm(boolean graph[][], int houses, int c, boolean assigned[],
             int match[])
 {
     for (int h = 0; h < houses; h++)
     {
         if (graph[c][h] && !assigned[h])
         {
             assigned[h] = true;
             if (match[h] < 0 || bpm(graph,houses,match[h],assigned, match))
             {
                 match[h] = c;
                 return true;
             }
         }
     }
     return false;
 }
 
 public static int maxBPM(boolean graph[][], int houses, int clients)
 {
     int[] match = new int[houses];

     for(int i=0; i<houses;++i)
         match[i] = -1;
 
     int result = 0;
     for (int c = 0; c < clients; c++)
     {
         boolean[] assigned = new boolean[houses] ;
         for(int i = 0; i < houses; ++i)
             assigned[i] = false;
 
         if (bpm(graph, houses, c, assigned, match))
             result++;
     }
     return result;
 }
}