import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class SocialGraph{
        int n;
        long[][] cost;
        long a[][];
        public SocialGraph(int n){
                this.n = n;
                cost = new long[n][n];
                for(int i=0;i<n;i++)
                        Arrays.fill(cost[i],(long)1e18); // infinity
                a = new long[n][n];
        }
        public void makeFriends(int u,int v,int c){
                this.cost[u][v]=c;
        }
        public void allPath(){
                for(int i=0;i<n;i++)
                        {       a[i][i]=0;
                                for(int j=0;j<n;j++)
                                        a[i][j] = cost[i][j];
                        }
                for(int k=0;k<n;k++)
                        for(int i=0;i<n;i++)
                                for(int j=0;j<n;j++)
                                        if(a[i][k]+a[k][j]<a[i][j])
                                                a[i][j]=a[i][k]+a[k][j];
        }
        public long getHop(int u,int v){
                return a[u][v];
        }
}
public class Solution{
        public static void main(String args[]){
            Scanner input = new Scanner(System.in);
            int n = input.nextInt();
                SocialGraph sg = new SocialGraph(n); //number of nodes
            int m = input.nextInt();
                for(int i=0;i<m;i++){
                    int x = input.nextInt();
                    int y = input.nextInt();
                    x--;y--;
                    int c = input.nextInt();
                    sg.makeFriends(x,y,c);
                }
                sg.allPath();
            long Q = input.nextLong();
            for(long j=0;j<Q;j++){
                    int x = input.nextInt();
                    int y = input.nextInt();
                    x--;y--;
                if(x==y){
                    System.out.println("0");
                    continue;
                }
                long cost = sg.getHop(x,y);
                if(cost==(long)1e18)
                System.out.println("-1");
                else
                    System.out.println(cost);
            }
                
        }
}