import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

 public class client
 {
  int area;
  int price;
  public client(int a,int p)
  {
   this.area=a;
   this.price=p;
  }
 }
 public class house
 {
  int area;
  int minprice;
  public house(int a,int p)
  {
   this.area=a;
   this.minprice=p;
  }
 }
    static int  M = 6;
     static int N = 6;
  

     static boolean bpm(boolean bpGraph[][], int u, boolean seen[],
                 int matchR[])
     {
      
         for (int v = 0; v < N; v++)
         {
             
             if (bpGraph[u][v] && !seen[v])
             {
                 seen[v] = true; 
  
             
                 if (matchR[v] < 0 || bpm(bpGraph, matchR[v],
                                          seen, matchR))
                 {
                     matchR[v] = u;
                     return true;
                 }
             }
         }
         return false;
     }
  
     
     static int maxBPM(boolean bpGraph[][])
     {
         
         int matchR[] = new int[N];
  
       
         for(int i=0; i<N; ++i)
             matchR[i] = -1;
  
         int result = 0; 
         for (int u = 0; u < M; u++)
         {
             
             boolean seen[] =new boolean[N] ;
             for(int i=0; i<N; ++i)
                 seen[i] = false;
  
             if (bpm(bpGraph, u, seen, matchR))
                 result++;
         }
         return result;
     }
 public static void main(String[] args) throws IOException{
  BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
  //int t= Integer.parseInt(br.readLine());
  int t=1;
  int case1=1;
  while(t>0)
  {

   String line[]=br.readLine().split(" ");
   int n=Integer.parseInt(line[0]);
   int m=Integer.parseInt(line[1]);
   ArrayList<client> clients=new ArrayList<client>();
   ArrayList<house> houses=new ArrayList<house>();
   for(int i=0;i<n;i++)
   {
    line=br.readLine().split(" ");
    clients.add(new Solution().new client(Integer.parseInt(line[0]),Integer.parseInt(line[1])));
    
   }
   for(int i=0;i<m;i++)
   {
    line=br.readLine().split(" ");
    houses.add(new Solution().new house(Integer.parseInt(line[0]),Integer.parseInt(line[1])));
    
   }
   M=n;
   N=m;
   boolean graph[][]=new boolean [n][m];
   
   for(int i=0;i<n;i++)
   {
    client currClient=clients.get(i);
    for(int j=0;j<m;j++)
    {
     house currHouse=houses.get(j);
     if(currClient.area<currHouse.area&&currClient.price>=currHouse.minprice)
      graph[i][j]=true;
    }
   }
   
   System.out.println(maxBPM(graph));
   
   t--;
  }
 }
}