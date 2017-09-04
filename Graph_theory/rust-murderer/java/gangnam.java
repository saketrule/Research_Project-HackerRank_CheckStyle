import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    public static StringBuilder findSmallestPath(ArrayList<Integer>[] a, int src) {
        src--;        
        int V = a.length;
        int dist[] = new int[V];

        HashMap<Integer,Integer> l1 = new HashMap<Integer,Integer>();
        HashMap<Integer,Integer> l2 = new HashMap<Integer,Integer>();
        
        for(int i=0;i<V;i++) {
            dist[i] = Integer.MAX_VALUE;
            if(i!=src)l1.put(i,i);
        }
        
        dist[src] = 0;
        ArrayList<Integer> q = new ArrayList<Integer>();
        q.add(src);
        
        
        while(!(l1.isEmpty() || q.isEmpty())) {
            int u = (int)q.remove(0);
            for(int v = 0; v<a[u].size(); v++) {
                int temp = a[u].get(v);
                l1.remove(temp);
                l2.put(temp,temp);
            }
            
            Set<Integer> set = l1.keySet();
            Iterator it = set.iterator();
            while(it.hasNext()) {
                int temp = (Integer)it.next();
                if(dist[temp]>dist[u]+1) {
                    dist[temp]= dist[u]+1;
                    q.add(temp);
                }
            }
           
            HashMap<Integer, Integer> temp = l1;
            l1=l2;
            temp.clear();
            l2=temp;
            
        }
        
        StringBuilder abc = new StringBuilder();
        
        for(int i=0;i<dist.length;i++) {
            if(i!=src)abc.append(dist[i]+" ");
        }
        return abc;
        
    }
    
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        StringBuilder abc = new StringBuilder();
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
            
            abc.append(findSmallestPath(adj,sc.nextInt())+"\n");
            
            t--;
        }
        System.out.println(abc);
    }
}