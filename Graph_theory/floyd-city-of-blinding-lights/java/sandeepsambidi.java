import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    int[][] sol;
 
 Solution(int v){
  sol = new int[v][v];
  
  for(int i=0;i<v;i++){
   for(int j=0;j<v;j++){
    if(i==j)
     sol[i][j]=0;
    else
     sol[i][j]=Integer.MAX_VALUE;
   }
  }
 }
 
 void meth(int v){
  for(int k=0;k<v;k++){
   for(int i=0;i<v;i++){
    for(int j=0;j<v;j++){
     if(sol[i][k]!=Integer.MAX_VALUE &&sol[k][j]!=Integer.MAX_VALUE)
      sol[i][j]=min(sol[i][k]+sol[k][j], sol[i][j]);
    }
   }
  }
  
//  for(int p=0;p<v;p++){
//   for(int q=0;q<v;q++){
//    System.out.println(sol[p][q]);
//   }
//  }
 }
 
 int min(int a, int b){
  if(a>b)
   return b;
  else
   return a;
 }

 public static void main(String[] args) throws IOException {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  String ve = br.readLine();
  String[] veArr= ve.split(" ");
  int vertices= Integer.parseInt(veArr[0]);
  int edges = Integer.parseInt(veArr[1]);
  Solution sol = new Solution(vertices);
  for(int i=0;i<edges;i++){
   String uvw = br.readLine();
   String[] uvwArr = uvw.split(" ");
   int u = Integer.parseInt(uvwArr[0])-1;
   int v = Integer.parseInt(uvwArr[1])-1;
   int w = Integer.parseInt(uvwArr[2]);
   sol.sol[u][v]=w;
  }
  sol.meth(vertices);
  int q_count = Integer.parseInt(br.readLine());
  for(int q=0;q<q_count;q++){
   String uv = br.readLine();
   String[] uvArr = uv.split(" ");
   int u = Integer.parseInt(uvArr[0])-1;
   int v = Integer.parseInt(uvArr[1])-1;
   if(sol.sol[u][v]==Integer.MAX_VALUE)
    System.out.println("-1");
   else
    System.out.println(sol.sol[u][v]);
  }
 }

}