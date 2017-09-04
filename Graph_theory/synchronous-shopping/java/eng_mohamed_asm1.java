import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static class Pair{
        int to, cost;
        public Pair(int to , int cost){
            this.to = to;
            this.cost = cost;
        }
    }
    
    static class State implements Comparable<State>{
        int store, cat , types, cost1 , cost2;
        public State(int store, int cat , int types , int cost1 , int cost2){
            this.store = store;
            this.cat =cat;
            this.types = types;
            this.cost1 = cost1;
            this.cost2 = cost2;
        }
        
        public int compareTo(State s ){
            return (cost1+cost2) - (s.cost1 + s.cost2);
        }
    }
    static void addEdge(int f , int t , int c ,List<Pair> [] graph ){
        List<Pair> l = graph[f];
        if(l == null){
            l = new Vector<>();
            graph[f] = l;
        }
        l.add(new Pair(t ,c));
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt() , m = in.nextInt() , k = in.nextInt();
        int []types = new int[n];
        List<Pair> [] graph = new List[n];
        for(int i = 0 ; i<n ; i++){
            int t = in.nextInt();
            for(int j =0  ; j< t ; j++){
                types[i] =  types[i] | (1<<(in.nextInt()-1));
            }
        }
        
        for(int i = 0 ; i<m ; i++){
            int f = in.nextInt() - 1 ;
            int t = in.nextInt() - 1;
            int c = in.nextInt();
            addEdge(f , t ,c ,graph );
            addEdge(t, f ,c ,graph );
        }
        
        boolean visited[][][] = new boolean[n][2][1024];
        PriorityQueue<State> q = new PriorityQueue<>();
        q.add(new State(0,0,types[0],0,0));
        int res = -1;
        while(!q.isEmpty()){
            State s = q.poll();
            if(s.cat ==1 && s.store == (n-1) && s.types == (1<<k)-1){
                res = Math.max(s.cost1 , s.cost2);
                break;
            }
            if(visited[s.store][s.cat][s.types])
                continue;
            visited[s.store][s.cat][s.types] = true;
            if(s.cat == 0 && s.store == n-1){
                q.add(new State(0,1,s.types|types[0],s.cost1 , 0));
            }
            if(graph[s.store]!=null){
                for(Pair p : graph[s.store]){
                    if(visited[p.to][s.cat][s.types|types[p.to]] )
                        continue;
                    q.add(new State(p.to,s.cat,s.types|types[p.to],s.cat == 0 ? s.cost1+p.cost : s.cost1 , s.cat == 1 ? s.cost2+p.cost                      : s.cost2));
                }
            }
            
        }
        System.out.println(res);
        
    }
}