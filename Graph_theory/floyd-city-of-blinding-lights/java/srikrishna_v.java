import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String args[])
 {
  readData(args);
 }
 
 static void readData(String[] inData)
 {
  Scanner sc = new Scanner(System.in);
  
  int nodes = sc.nextInt();
  int edges = sc.nextInt();
  
  int[][] adjMat = new int[nodes + 1][nodes + 1];
  
  for (int i = 1; i < adjMat.length; i++)
   for (int j = 1; j < adjMat[i].length; j++)
    adjMat[i][j] = (i == j) ? 0 : Integer.MAX_VALUE / 2;
  
  for (int i = 0; i < edges; i++)
  {
   int row = sc.nextInt();
   int col = sc.nextInt();
   int weight = sc.nextInt();
   
   adjMat[row][col] = weight;
   adjMat[row][0]++;
  }
  
  for (int inter = 1; inter < adjMat.length; inter++)
  {
   for (int source = 1; source < adjMat.length; source++)
   {
    for (int dest = 1; dest < adjMat[source].length; dest++)
    {
     adjMat[source][dest] = Math.min(adjMat[source][dest], adjMat[source][inter] + adjMat[inter][dest]);
    }
   }
  }
  
//  for (int i = 1; i < adjMat.length; i++)
//  {
//   for (int j = 1; j < adjMat.length; j++)
//   {
//    System.out.print(adjMat[i][j]+"\t");
//   }
//   
//   System.out.println();
//  }
  
  int q = sc.nextInt();
  
  for (int i = 0; i < q; i++)
  {
   int row = sc.nextInt();
   int col = sc.nextInt();
   
   int ans = (adjMat[row][col] == Integer.MAX_VALUE / 2) ? -1 : adjMat[row][col];
   System.out.println(ans);
  }
  
  
//  try
//  {
//   FileOutputStream fout = new FileOutputStream("D:\\op.txt");
//   for (int i = 0; i < q; i++)
//   {
//    int row = sc.nextInt();
//    int col = sc.nextInt();
//    
//    int ans = (adjMat[row][col] == Integer.MAX_VALUE / 2) ? -1 : adjMat[row][col];
////    System.out.print(ans+" ");
//    String str = ans+""+"\n";
//    
//    fout.write(str.getBytes());
//    
//    
//   }
////   fout.close();
//  }
//  catch (FileNotFoundException e)
//  {
//   // TODO Auto-generated catch block
//   e.printStackTrace();
//  }
//  catch (IOException e)
//  {
//   // TODO Auto-generated catch block
//   e.printStackTrace();
//  }
  
  sc.close();
 }
}