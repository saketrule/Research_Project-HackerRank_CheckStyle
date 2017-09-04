import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

  private static class Graph{
        private List<List<Integer>> adj = new ArrayList<>();
        Graph(int n){
            for(int i=0;i<n;i++){
                adj.add(new ArrayList<Integer>());
            }
        }
        int getNbNode(){
            return adj.size();
        }
        void addEdge(int x, int y){
            this.adj.get(x).add(y);
            this.adj.get(y).add(x);
        }
        List<Integer> getAdjOf(int node){
            return adj.get(node);
        }
    }
    
    private static int[] shortestDistance(Graph g, int startNode){
        int[] res = new int[g.getNbNode()];
        Arrays.fill(res, 1);
         Set<Integer> distanceToBeIncreased = new HashSet<>(g.getAdjOf(startNode));
       
         /*BitSet reached = new BitSet(g.getNbNode());
        reached.set(0,g.getNbNode());
        
     
         for(Integer notReached : distanceToBeIncreased){
            reached.clear(notReached);
        }*/
        Set<Integer> lastlyReached = new HashSet<>();
        
        int lastlyReachedNb = g.getNbNode() - 1 - distanceToBeIncreased.size();
        
        for(Iterator<Integer> iter = distanceToBeIncreased.iterator();iter.hasNext();){
            Integer child = iter.next();
            if(lastlyReachedNb > (g.getAdjOf(child).size() - 1)){//if there is more "lone" nodes than other children of this (except root)
                res[child] = 2;
                iter.remove();
                lastlyReached.add(child);
                
            }
            else{
                //we really count the number of unreacheable already reached nodes.
                int tmp = g.getAdjOf(child).size() - 1;
                for(Integer childchild : g.getAdjOf(child)){
                    if(distanceToBeIncreased.contains(childchild)){
                        tmp--;
                    }
                }
                if(lastlyReachedNb > tmp){
                    res[child] = 2;
                    iter.remove();
                    lastlyReached.add(child);
                    
                }
            }
        }
        
        
        int currentDistance = 2;
        Set<Integer> nextLastlyReached = new HashSet<Integer>();
        
        while(!distanceToBeIncreased.isEmpty()){
            currentDistance++;
            for(Iterator<Integer> iter = distanceToBeIncreased.iterator();iter.hasNext();){
                Integer child = iter.next();
                //we count the number of edges towards lastlyReached nodes;
                int count = 0;
                for(Integer childchild: g.getAdjOf(child)){
                    if(lastlyReached.contains(childchild)){
                        count++;
                    }
                }
                if(count < lastlyReached.size()){//there is a node lastly reached that is not reachable through this, let's go!
                    res[child] = currentDistance;
                    iter.remove();
                    nextLastlyReached.add(child);
                }
                
            }
            lastlyReached.clear();
            Set<Integer> swp = lastlyReached;
            lastlyReached = nextLastlyReached;
            nextLastlyReached = swp;
        }
        
        return res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while(t>0){
            int n = in.nextInt();
            int m = in.nextInt();
            Graph g = new Graph(n);
            for(int i=0;i<m;i++){
                g.addEdge(in.nextInt() - 1, in.nextInt() -1);
            }
            int s = in.nextInt() -1;
            int[] res = shortestDistance(g,s);
            StringBuilder sb = new StringBuilder(n * 3);
            for(int i=0;i<n;i++){
                if(i != s){
                    sb.append(Integer.toString(res[i]));sb.append(' ');
                }
            }
            System.out.println(sb);
            t--;
        }
        
    }
}