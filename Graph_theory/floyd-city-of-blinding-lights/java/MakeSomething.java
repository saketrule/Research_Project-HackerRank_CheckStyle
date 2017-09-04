import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sn=new Scanner(System.in);
            String data[]=sn.nextLine().split(" ");
            int N=Integer.parseInt(data[0]);
            int M=Integer.parseInt(data[1]);
            int adj[][]=new int[N+1][N+1];
            for(int i=0;i<M;i++)
                {
                data=sn.nextLine().split(" ");
                adj[Integer.parseInt(data[0])][Integer.parseInt(data[1])]=Integer.parseInt(data[2]);
            }
            long path[][]=new long[N+1][N+1];
            for(int i=0;i<N+1;i++)
                {
                for(int j=0;j<N+1;j++)
                    {
                    if(adj[i][j]!=0)
                    path[i][j]=adj[i][j];
                    else
                     path[i][j]=9999;
                }
                
            }
            for(int k=0;k<N+1;k++)
                {
                for(int i=0;i<N+1;i++)
                {
                for(int j=0;j<N+1;j++)
                    {
                    if(path[i][k]+path[k][j]<path[i][j])
                        {
                        path[i][j]=path[i][k]+path[k][j];
                    }
                }
                
            }   
            }
            int no=Integer.parseInt(sn.nextLine());
            for(int i=0;i<no;i++)
                {
                String ad[]=sn.nextLine().split(" ");
                if(ad[0].equals(ad[1]))
                    System.out.println("0");
                else
                if(path[Integer.parseInt(ad[0])][Integer.parseInt(ad[1])]!=9999)
                System.out.println(path[Integer.parseInt(ad[0])][Integer.parseInt(ad[1])]);
                else
                System.out.println("-1");
            }
        }
}