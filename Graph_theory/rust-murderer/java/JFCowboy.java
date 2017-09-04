import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
        
    static int N, M; 
    static TreeMap<Integer, TreeMap<Integer, Integer> > adjList;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
        StringTokenizer st;
        int u, v, cas = 0;
        String line;
        for( int T = Integer.parseInt( br.readLine() ); cas<T; cas++ ){
            line = br.readLine();
            st = new StringTokenizer( line );
            N = Integer.parseInt( st.nextToken() );
            M = Integer.parseInt( st.nextToken() );
            adjList = new TreeMap<>();
            
            for( int i=0; i < M; i++){
                st = new StringTokenizer( br.readLine() );
                u = Integer.parseInt( st.nextToken() );
                v = Integer.parseInt( st.nextToken() );
                u--;v--;
                addEdge(u, v);
            }
            
            u = Integer.parseInt( br.readLine() );
            u--;
                      
            int[] dist = BFS( u );
            int first = 1;
            
            StringBuilder sb = new StringBuilder("");
            for( int i=0; i < N; i++){
                if( i!=u ){
                    if( first==1 ){
                        sb.append( dist[i]-1 );
                        first = 0;
                    } else {
                        sb.append(" ").append(dist[i]-1);
                    }
                }
            }
            System.out.println(sb);
            
        }
    }
    
    static int solve( int u, int v){
        if( !adjList.containsKey(u) || !adjList.get(u).containsKey(v) )
            return 1;
        return 2;
    }
    
    static int[] BFS( int u ){
        int[] dist = new int[N];
        Queue<Integer> Q = new LinkedList<Integer>();
        TreeSet<Integer> l1, l2;
        l1 = new TreeSet<Integer>();
        l2 = new TreeSet<Integer>();
        int v, d = 1;
        
        for( int i=0; i <N; i++)
            l1.add( i );
        l1.remove( u );
        Q.add( u );
        dist[ u ] = 1;
        
        while( !Q.isEmpty() && l1.size()>0 ) {
            u = Q.remove();
            if( adjList.containsKey(u) ){
                
                for( Integer vv : adjList.get(u).keySet() ) {
                    if( dist[vv]==0 ) {
                        l1.remove( vv );
                        l2.add( vv );
                    }
                }
            }
            
            for( Integer vv: l1 ){
                dist[ vv ]= dist[u]+1;
                Q.add(vv);
            }
            l1 = l2;
            l2 = new TreeSet<>();
        }
        
        return dist;
    }
    
    static void addEdge( int u, int v){
        if( !adjList.containsKey( u ) )
            adjList.put( u, new TreeMap<Integer, Integer>() );
        adjList.get( u ).put(v, 1);
            
        if( !adjList.containsKey( v ) )
            adjList.put( v, new TreeMap<Integer, Integer>() );
        adjList.get( v ).put(u, 1);
    }
    
    static class Pair implements Comparable<Pair>{
        int first, second;
        
        public Pair(){
            this.first = 0;
            this.second = 0;
        }
        
        public Pair( int first, int second){
            this.first = first;
            this.second = second;
        }
        
        public int compareTo( Pair o ){
            if( this.first!=o.first )
                return this.first-o.first;
            return this.second-o.second;
        }
    }
}