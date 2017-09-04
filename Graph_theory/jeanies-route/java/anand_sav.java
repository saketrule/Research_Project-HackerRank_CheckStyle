import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class node{
    int n;
    public node(int n){
        this.n=n;
    }
}

public class Solution {
    public static final int INF=9999;
    static ArrayList<node[]> list=new ArrayList<>();
    
    
    public static int solve(int[][] graph,int[] dest,int n,int l){
        int[][] dist=new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++)
                dist[i][j]=graph[i][j];
        }
        
        //printArr(dist);
        for(int k=0;k<n;k++){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(dist[i][k]+dist[k][j]<dist[i][j]){
                        dist[i][j]=dist[i][k]+dist[k][j];
                        //dist[j][i]=dist[i][k]+dist[k][j];
                    }
                        
                    
                }
            }
        }
        
        node[] dArr=new node[dest.length];
        for(int i=0;i<dest.length;i++){
            dArr[i]=new node(dest[i]);
        }
        //printArr1(dist);
        return solveUtil(graph,dArr,n,l,dist);
        
    }
    public static int solveUtil(int[][] graph,node[] dest,int n,int k,int[][] dist){
        permute(dest,0,dest.length-1);
        //printArr();
        int cost=0;
        int minCost=Integer.MAX_VALUE;
        for(node[] temp:list){
            cost=0;
            for(int j=0;j<temp.length-1;j++){
                int n1=temp[j].n-1;
                int n2=temp[j+1].n-1;
                if(dist[n1][n2]!=0 && dist[n1][n2]!=INF)
                    cost+=dist[n1][n2];
            }
            if(cost<minCost)
                minCost=cost;
        }
        return minCost;
        
    }
    
    static void permute(node[] str,int l,int r){
        if(l==r){
            node[] t=new node[str.length];
            for(int i=0;i<str.length;i++)
                t[i]=str[i];
            //System.out.println("here: "+str[0].n+" "+str[1].n+" "+str[2].n);
            list.add(t);
        }
        for(int i=l;i<=r;i++){
            swap(str,l,i);
            permute(str,l+1,r);
            swap(str,l,i);
            
        }
    }
    static void swap(node[] str,int i,int j){
        node tmp=str[i];
        str[i]=str[j];
        str[j]=tmp;
    }
    
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int k=sc.nextInt();
        int[] dest=new int[k];
        for(int i=0;i<k;i++)
            dest[i]=sc.nextInt();
        int[][] graph=new int[n][n];
        
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i==j)
                    graph[i][j]=0;
                else graph[i][j]=INF;
            }
        }
            
            
        
                
        for(int i=0;i<n-1;i++){
            int a=sc.nextInt();
            int b=sc.nextInt();
            int c=sc.nextInt();
            graph[a-1][b-1]=c;
            graph[b-1][a-1]=c;
        }
        
        //printArr(graph);
        System.out.println(solve(graph,dest,n,k));
            
    }
}