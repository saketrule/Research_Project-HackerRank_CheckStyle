import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;





public class Solution {
    
    
    static class Edge{
        public int s;
        public int t;
        public int cost;
        public int number_edge;

        public Edge(int s, int t, int cost, int number_edge){
            this.s=s;
            this.t=t;
            this.cost=cost;
            this.number_edge=number_edge;
        }
    }

    static class Graph{
        int n;
        ArrayList<Edge> edges = new ArrayList<Edge>();
        HashMap<Integer, Edge> connect = new HashMap<>();

        public Graph(int n){
            this.n=n;
        }

        public Edge getEdge(int connect_key){
            return connect.get(connect_key);
        }

        public void addEdge(int u, int v, int cost,int number){
            Edge e = new Edge(u,v,cost,number);
            edges.add(e);
            connect.put(number,e);
        }

        public void getAllPaths(int s, int t){
            boolean[] visited = new boolean[n];

            int[] path = new int[n];
            int path_index = 0;

            for(int j=0; j<n; j++){
                visited[j]=false;
            }
            //System.out.println("From "+s+ " to "+t);

            getAllPathsUtils(s,t,visited,path,path_index,-1);
        }

        public void getAllPathsUtils(int s, int t, boolean[] visited, int[] path, int path_index, int number_edge){
            visited[s]=true;
            path[path_index]=number_edge;
            path_index++;
            
            if(s==t){
                int sum = 0;
                for(int i=1; i<path_index; i++){
                    //System.out.print(path[i]+" ");
                    sum += connect.get(path[i]).cost;
                }
                //System.out.print(sum+": "+sum%10+"\n");
                counter.put(sum%10, counter.get(sum%10)+1);
            }
            else{
                for(Edge edge : edges){
                    if(edge.s==s && visited[edge.t]==false){
                        getAllPathsUtils(edge.t, t, visited, path, path_index, edge.number_edge);
                    }
                }
            }

            path_index--;
            visited[s]=false;
        }
    }
    
    
    public static HashMap<Integer,Integer> counter = new HashMap<>();
    public static Graph graph;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int e = in.nextInt();
        
        counter.put(0,0);
        counter.put(1,0);
        counter.put(2,0);
        counter.put(3,0);
        counter.put(4,0);
        counter.put(5,0);
        counter.put(6,0);
        counter.put(7,0);
        counter.put(8,0);
        counter.put(9,0);
        
        graph = new Graph(n);
        
        for(int a0 = 0; a0 < e; a0++){
            int x = in.nextInt()-1;
            int y = in.nextInt()-1;
            int r = in.nextInt();
            graph.addEdge(x,y,r,a0);
            graph.addEdge(y,x,(1000-r),2*a0+1);
        }
        
        for(int x = 0; x<n; x++){
            for(int y=0; y<n; y++){
                if(x!=y){
                    graph.getAllPaths(x,y);
                }
            }
        }
        
        for(int i=0; i<10; i++){
            System.out.println(counter.get(i));
        }
        
        
    }
}