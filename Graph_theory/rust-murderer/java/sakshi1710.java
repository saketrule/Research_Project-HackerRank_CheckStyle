import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    public static void findSmallestPath(ArrayList<Integer>[] a, int src) {
        src--;        
        int V = a.length;
        int dist[] = new int[V];

        ArrayList<Integer> l1 = new ArrayList<Integer>();
        ArrayList<Integer> l2 = new ArrayList<Integer>();
        
        for(int i=0;i<V;i++) {
            dist[i] = Integer.MAX_VALUE;
            l1.add(i);
        }
        
        dist[src] = 0;
        l1.remove(src);
        ArrayList<Integer> q = new ArrayList<Integer>();
        q.add(src);
        
        
        while(!(l1.isEmpty() || q.isEmpty())) {
            int u = (int)q.remove(0);
            for(int v = 0; v<a[u].size(); v++) {
                l1.remove(a[u].get(v));
                l2.add(a[u].get(v));
            }
            
            for(int v = 0; v<l1.size();v++) {
                int temp = l1.get(v);
                if(dist[temp]>dist[u]+1) {
                    dist[temp]= dist[u]+1;
                    q.add(temp);
                }
            }
           
            ArrayList<Integer> temp = l1;
            l1=l2;
            temp.clear();
            l2=temp;
            
        }
        
        StringBuilder abc = new StringBuilder();
        
        for(int i=0;i<dist.length;i++) {
            if(i!=src)abc.append(dist[i]+" ");
        }
        System.out.println(abc);
        
    }
    
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        if(t<1 ||t>10) return;
        while(t>0) {
            int n = sc.nextInt();
            int i,j;
            if(n<2 || n>150000) return;
            
            ArrayList<Integer> adj[] = new ArrayList[n];
            
            for(i=0;i<n;i++)adj[i] = new ArrayList();
            
            int m = sc.nextInt();
            if(m<0 || m>1000000) return;
            
            while(m>0) {
                i = sc.nextInt();
                j = sc.nextInt();
                adj[i-1].add(j-1);
                adj[j-1].add(i-1);
                m--;
            }
            
            findSmallestPath(adj,sc.nextInt());
            
            t--;
        }
    }
}