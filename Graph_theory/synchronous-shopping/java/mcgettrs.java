import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static LinkedList<PathHead> Q;
    static int timesForFish[];
    static int mask = 0;
    static int N=0;
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // number of nodes
        int M = sc.nextInt(); // number of roads
        int K = sc.nextInt(); // Types of fish
        Q = new LinkedList<PathHead>();
        timesForFish = new int[(int)Math.pow(2,K)];
        mask = (int)Math.pow(2,K)-1;
        Arrays.fill(timesForFish,0,timesForFish.length,-1);
        // Read in the Fish
        int fish[] = new int[N];
        for(int i = 0; i<N; i++){
            int fishToAdd =0;
            int T = sc.nextInt();
            int binary =0;
            for(int t =0; t<T; t++){
                int fish4sale = sc.nextInt();
                binary = binary|(1<<(fish4sale-1));
            }
            fish[i] = binary;
            //System.out.println(binary);
        }
        // Add the edges to the graph
        ArrayList<ArrayList<Edge>> edges = new ArrayList<ArrayList<Edge>>(N);
        for(int iy =0;iy<N;iy++){
            edges.add(iy,new ArrayList<Edge>());
        }
        
        
        for(int m = 0; m<M; m++){
            int s = sc.nextInt()-1;
            int e = sc.nextInt()-1;
            int c = sc.nextInt();
            Edge newEdge = new Edge(m,s,e,c);
            
            edges.get(s).add(newEdge);
            edges.get(e).add(newEdge);
            
        }
        
        // now we are ready to start the computation
        //first add source node to queue
        addValue(new PathHead(0,0,fish[0]," "));
        
        while(!Q.isEmpty()){
            PathHead p = Q.remove();
            //System.out.println(p.currentNode+ " "+ p.currentDist+" "+ p.fish);
            int newFish = p.fish|fish[p.currentNode];
            // check to see if it is destination
            if(p.currentNode==N-1){
                // we need to check to see if we should update distance for fish
                if(timesForFish[newFish]>p.currentDist|| timesForFish[newFish]==-1){
                    timesForFish[newFish]=p.currentDist;
                    int time = checkForComplete(newFish);
                    if(time != -1){
                        
                        System.out.println(time);
                        break;
                    }
                }
            }
            // This test checks to see have you looped without getting new fish
            if(p.path.contains(p.currentNode+":"+newFish)==false){ 
                 // add children nodes to Q
            for( Edge e : edges.get(p.currentNode)){
                addValue(new PathHead(e.destination(p.currentNode),p.currentDist+e.cost,newFish,p.path+p.currentNode+":"+newFish+" "));
            }
            }
           
            
        }
        
        
    }
    public static int checkForComplete(int newFish){
        int test = newFish^mask;
        int timeToReturn = -1;
        if(test==0){
            timeToReturn = timesForFish[newFish];
        }
        else{
            test = -1;
            for(int i =0; i<timesForFish.length; i++)
            {
                
                if((i|newFish) == mask && timesForFish[i]!=-1){
                    // valid answer
                    // get the smallest
                    if(test==-1 || timesForFish[i]<test){
                        test = timesForFish[i];
                    }
                }
            }
            if(test != -1){
                // return biggest number
                if(test >timesForFish[newFish] ){
                    timeToReturn = test;
                }
                else{
                    timeToReturn = timesForFish[newFish];
                } 
            }
               
           
        }
        return timeToReturn;
        
        
    }
    public static void addValue(PathHead val) {

        if (Q.size() == 0) {
            Q.add(val);
        } else if (Q.get(0).currentDist > val.currentDist) {
            Q.add(0, val);
        } else if (Q.get(Q.size() - 1).currentDist < val.currentDist) {
            Q.add(Q.size(), val);
        } else {
            int i = 0;
            while (Q.get(i).currentDist < val.currentDist ) {
                i++;
            }
            
            Q.add(i, val);
        }

    }

}
class PathHead{
    int currentNode;
    int currentDist;
    int fish;
    String path;
    PathHead(int currentNode, int currentDist, int fish, String path){
        this.currentNode = currentNode;
        this.currentDist = currentDist;
        this.fish = fish;
        this.path = path;
       // System.out.println(path);
    }
    
}
class Edge{
    int id;
    int start;
    int end;
    int cost;
    Edge(int id,int start, int end, int cost){
        this.id = id;
        this.start = start;
        this.end = end;
        this.cost = cost;
    }
    int destination(int s){
        if(s==start){
            return end;
        }else{
            return start;
        }
    }
}