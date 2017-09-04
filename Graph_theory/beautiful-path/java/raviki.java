import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Edge
    {
    int start;
    int end;
}

public class Solution {

      static int path[][];
    static int dst[][];
       ArrayList<Edge> Edges=new ArrayList<Edge>();
    public static void main(String[] args) {
        
     Scanner scan=new Scanner(System.in);
        
        int n=scan.nextInt();
        int e=scan.nextInt();
        
        dst = new int[n+1][n+1];
      path=new int[n+1][n+1];
        
        for(int i=0;i<=n;i++)
            {
          
            
            for(int j=0;j<=n;j++)
                {
                
                if(i==j)
                    dst[i][j]=0;
                else
                dst[i][j]=Integer.MAX_VALUE;
                
              
            }
        }
        for(int i=0;i<e;i++)
            {
            
            int u=scan.nextInt();
            int v=scan.nextInt();
            int weight=scan.nextInt();
            
            if(weight<dst[u][v])
                dst[u][v]=weight;
            if(weight<dst[v][u])
                dst[v][u]=weight;
            
          
           
            }
        
        for(int k=1;k<=n;k++)
            {
            for(int i=1;i<=n;i++)
                {
                for(int j=1;j<=n;j++)
                    {
                  
                    
                    if((dst[i][k]|dst[k][j])<dst[i][j])
                    {
                     dst[i][j]=dst[i][k]|dst[k][j];
                     path[i][j]=k;
                    }
                     
                }
               
            }
        }
        
        int start=scan.nextInt();
        int end=scan.nextInt();
        System.out.println(dst[start][end]);
        
       
    
    }
    
    public static int ShortPath(int start,int end)
        {
        if(start==end)
            return 0;
        if(start==0 || end==0)
            return 0;
        
        int a= ShortPath(start,path[start][end])|ShortPath(path[start][end],end)| dst[start][end];
        System.out.println(start+" "+end+" "+a);
        return a;
        
    }
}