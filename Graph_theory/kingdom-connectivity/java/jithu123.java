/* Enter your code here. Read input from STDIN. Print output to STDOUT */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Solution {

 private static boolean[] marked;
 private static boolean[] inPath;
 private static int[] Paths;
 private static ArrayList<Integer>[] adj;
 private static Set<Integer> loopSet;
 private static int N;
 private static int M;
 
 public static void main(String[] args) {

  Scanner scnr = new Scanner(System.in);
  N = scnr.nextInt();
  M = scnr.nextInt();
  
  adj = (ArrayList<Integer>[])new ArrayList[N+1];
  
  for (int i=0 ; i <= N ; i++)
  {
   adj[i] = new ArrayList<Integer>();
  }
  
  for(int i=0;i <M; i++)
  {
   int City1 = scnr.nextInt();
   int City2 = scnr.nextInt();
   adj[City1].add(City2);
  }
  
  marked = new boolean[N+1];
  inPath = new boolean[N+1];
  Paths = new int[N+1];
  
  loopSet = new HashSet<Integer>();
  
  long paths = findPaths( 1);
  if(paths == -1)
   System.out.println("INFINITE PATHS");
  else
   System.out.println(paths%(1000000000));
//  printAdj();
//  for (long p:Paths)
//  System.out.println(p);

 }
 
 private static long findPaths(int city)
 {

  if(city == N)
  {
   if(!loopSet.isEmpty())
   {
    return -1; //Destination reached, but with a loop in the path
   }
   
   return 1;
  }
  
  marked[city] = true;
  inPath[city] = true;

  for(int nextCity : adj[city])
  {
   if(inPath[nextCity])
   {
   
    //City is already in path /stack so loop detected
    loopSet.add(nextCity);
    continue;
   }
   if(!marked[nextCity])
   {
    long futurePath = findPaths(nextCity);
    if(futurePath == -1)
    {
     //There is a full path in future to destination but with loop.
     return -1;
    
    }
    else
    {
     if(loopSet.contains(city) && futurePath > 0)
     {
      return -1;
     }
     Paths[city] += futurePath;
     Paths[city] %= 1000000000;
    }
   }
   else
   {

    Paths[city] += Paths[nextCity];
    Paths[city] %= 1000000000;
   }
   
  }

  inPath[city] = false;
  if(loopSet.contains(city))
  {
   loopSet.remove(city);
  }

  return (Paths[city]);

 }
 
 public static void printAdj()
 {
  for(int x = 0 ;x < N ; x++)
  {
   System.out.print(x+"-");
   for(int y: adj[x])
    System.out.print(y+",");
   
   System.out.println();
  }
 }
 
}    