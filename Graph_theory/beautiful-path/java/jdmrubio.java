import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    class Node{
        int id;
        boolean visited;
        
        HashMap<Node,List<Integer>> edges;
        
        public Node(int id){
            this.id=id;
            visited=false;
            edges=new HashMap<>();
        }
        
    }
    
    public void find(Node start, Node end,int []distance){
        ArrayDeque<Node> dq=new ArrayDeque<>();
        
        dq.add(start);
        start.visited=true;
        
        while(dq.size()>0){
            Node cur=dq.poll();
            
            for(Node nod:cur.edges.keySet()){
                List<Integer> weights=cur.edges.get(nod);
                int dist=Integer.MAX_VALUE;
                for(int test:weights){
                    dist=Math.min(dist, (distance[cur.id]|test) );
                }                
                if(!nod.visited || dist<distance[nod.id]){                    
                    nod.visited=true;
                    distance[nod.id]=dist;                    
                    dq.add(nod);                    
                }
                
            }
        }
        
        if(distance[end.id]==0){
            System.out.println(-1);
        }else{
            System.out.println(distance[end.id]);
        }
        
        
    }
    
    public void init(){
        Scanner scan=new Scanner(System.in);
        int n=scan.nextInt();
        int m=scan.nextInt();
        Node [] nodes=new Node[n];
        for(int i=0;i<n;i++){
            nodes[i]=new Node(i);
        }
        for(int i=0;i<m;i++){
            int from=scan.nextInt()-1;
            int to=scan.nextInt()-1;
            int weight=scan.nextInt();
            
            if(nodes[from].edges.containsKey(nodes[to])){
                nodes[from].edges.get(nodes[to]).add(weight);
            }else{
                List <Integer> weights=new ArrayList<>();
                weights.add(weight);
                nodes[from].edges.put(nodes[to],weights);
            }
            
            if(nodes[to].edges.containsKey(nodes[from])){
                nodes[to].edges.get(nodes[from]).add(weight);
            }else{
                List <Integer> weights=new ArrayList<>();
                weights.add(weight);
                nodes[to].edges.put(nodes[from],weights);
            }
            
        }
        
        int start=scan.nextInt()-1;
        int end=scan.nextInt()-1;
        
        int []distance=new int[n];
        
        find(nodes[start],nodes[end],distance);        
        
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Solution sol=new Solution();
        sol.init();
    }
}