import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
class Node{
        int dest;
        int cost;
        Node(int d, int c){
            dest = d;
            cost = c;
        }
}
class Pair{
    Pair(int n, int m){
        node = n;
        mask = m;
    }
    int node;
    int mask;
}
public class Solution {
    

    static int MAXNODE = 1002;
    static int MAXFISH = 1050;
    static int[][] optimal_cost = new int[MAXNODE][MAXFISH];
    static int[] gathered = new int[MAXNODE];
    
    public static void dijkstra(List< List<Node> > graph, int src){
        int gathered_optimal = 0;
        LinkedList<Pair> queue = new LinkedList<Pair>();
        
        queue.offer(new Pair(src,gathered[src]));
        optimal_cost[src][gathered[src]] = 0;
        while(!queue.isEmpty()){
            Pair current = queue.poll();
            for(Node node:graph.get(current.node)){
                //System.out.println("Current "+current.node);
                //System.out.println("Neighbor "+node.dest);
                int newmask = (current.mask | gathered[node.dest]);
                //System.out.println("Optimal cost is " + optimal_cost[current.node][current.mask]+ " "+node.cost+" "+optimal_cost[node.dest][newmask]);
                if(optimal_cost[current.node][current.mask] + node.cost<optimal_cost[node.dest][newmask]){
                    optimal_cost[node.dest][newmask] = optimal_cost[current.node][current.mask] + node.cost;
                    queue.offer(new Pair(node.dest,newmask));
                    //System.out.println("Going "+node.dest +" "+optimal_cost[node.dest][newmask]+" "+newmask);
                }
                
            }
            
        }
    }
    public static void main(String[] args) throws IOException{
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<List<Node>> graph = new ArrayList<List<Node>>();
        
        String[] temp = br.readLine().split(" ");
        int n = Integer.parseInt(temp[0]);
        int m = Integer.parseInt(temp[1]);
        int k = Integer.parseInt(temp[2]);
        for(int i = 0 ; i < n ; i++){
            graph.add(new ArrayList<Node>());
            for(int j = 0 ; j < MAXFISH ; j++){
                optimal_cost[i][j] = Integer.MAX_VALUE/2;
               
            }
        }

        for(int i = 0; i < n; i++){
            String[] temp1 = br.readLine().split(" ") ;
            int no_fish_type = Integer.parseInt(temp1[0]);
            for(int j = 1; j <=no_fish_type ; j++){
                int fish_type = Integer.parseInt(temp1[j]);
                gathered[i] = gathered[i] | 1 << (fish_type-1);
                
            }
        }
        /*for(int i=0;i<n;i++){
            System.out.println(gathered[i]);
        }*/
        for(int i = 0; i < m ; i++){
            String[] temp2 = br.readLine().split(" ");
            int from = Integer.parseInt(temp2[0]);
            int to = Integer.parseInt(temp2[1]);
            int cost = Integer.parseInt(temp2[2]);
            graph.get(from-1).add(new Node(to-1 , cost));
            graph.get(to-1).add(new Node(from-1 , cost));
            
        }
        /*for(int i = 0 ; i < n ; i++){
            for(Node node:graph.get(i)){
                System.out.println(i+ " "+ node.dest + " " + node.cost);
            }
        }*/
        dijkstra(graph,0);
        
        int ans = Integer.MAX_VALUE;
        for(int i = 0 ; i < (1 << k) ; i++){
            for(int j = i ; j < (1 << k) ; j++){
                if((i|j) == (1 << k) - 1){
                    int val = Math.max(optimal_cost[n-1][i],optimal_cost[n-1][j]);
                    if(val < ans){
                        ans = val;
                    }
                }
            }
        }
        System.out.println(ans);
        
        
    }
}