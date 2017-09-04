import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) throws IOException{
        new Solution().run();
    }
    
    private static long count;
    private static long globalMax;
    private static long currentSubMax;
    private static long currentMax;
    private static long currentCount;
    private static int subGraphs;
    private static int[][] adj;
    private static ArrayList<ArrayList<Integer>> a;
    private static int[] v,c;
    
    
    public void run() throws IOException{
        Scanner in = new Scanner(System.in);
        BufferedWriter log = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = in.nextInt();
        int m = in.nextInt();
        c = new int[n];
        
  int i,j,k,u,w;
        
        a = new ArrayList<ArrayList<Integer>>();
        
        for(i=0;i<n;i++){
           c[i]= in.nextInt();
           a.add(new ArrayList<Integer>());
        }
        adj = new int[n][n];
        for(i=0;i<m;i++){
           j = in.nextInt();j--;
           k = in.nextInt();k--;
           a.get(j).add(k);
           a.get(k).add(j);
           adj[j][k]=1;
           adj[k][j]=1;
        }
        
        boolean[] vis = new boolean[n];
        int visited = 0;
        int minIndex = 0;
        subGraphs = -1;
        //int[] vect = new int[n];
        ArrayDeque<Integer> d = new ArrayDeque<Integer>();
        ArrayList<ArrayList<Integer>> subg = new ArrayList<ArrayList<Integer>>();
        
        while(visited < n){
            if(d.isEmpty()){
                //select new uncolored
                for(;minIndex<n && vis[minIndex];minIndex++); 
                subGraphs++;
                subg.add(new ArrayList<Integer>());
            }
            u = minIndex;
            d.clear();
            d.push(u);
            vis[u]=true;
            subg.get(subGraphs).add(u);
            while(!d.isEmpty()){
                u = d.pop();
                //vect[u] = minIndex; //global connected set
                visited++;
                //get all neighbours
                for(i=0;i<a.get(u).size();i++){
                    w = a.get(u).get(i);
                    if(!vis[w]) {
                        vis[w]=true;
                        d.push(w);
                        subg.get(subGraphs).add(w);
                    }
                }
            }
            //end connected set = subgraph
        }
        minIndex++;
        subGraphs++;
        
        globalMax = 0l;
        count = 1l;
        //System.err.println("there are "+subGraphs);
        v = new int[n];
        for(j=0;j<subGraphs;j++){
            solveSubGraph(subg.get(j));
            //debugSubGraph(subg.get(j));
        }
        
        log.write("" +globalMax+" " +count+"\n"); 
        log.flush();
    }
    
    void debugSubGraph(ArrayList<Integer> s){
        for(int j=0;j<s.size();j++){
                System.err.print(""+s.get(j)+" ");
        }
        System.err.println();
    }
    
    void solveSubGraph(ArrayList<Integer> s){
        Arrays.fill(v,0);
        //naive
        currentSubMax=0l;
        currentMax=0l;
        currentCount=0l;
        //System.err.println("there are "+s.size());
        
        getMax(s,0, s.size());
        count*=currentCount;
        globalMax+=currentSubMax;
    }
    
    void getMax(ArrayList<Integer> s, int current, int last){
        if(current == last){
            if(currentMax > currentSubMax){
                currentCount = 1l;
                currentSubMax = currentMax;
            } else if (currentMax==currentSubMax){
                currentCount++;
            }
            //System.err.println("Grabbed "+currentMax);
            //System.err.println("From "+s.toString());
            //System.err.println("From "+Arrays.toString(v));
            //System.err.println("Current "+currentSubMax+" - "+ currentCount +" ways");
            
        } else {
            //current
            int u = s.get(current);
            
            if(v[u]>0)//blocked
            {
                getMax(s,current+1,last);
            }
            else {//not blocked
                //First don't add this, pointless, but countable
                getMax(s,current+1,last);
                
                //block all neighbours, add current, try, return;
                currentMax+=c[u];
                for(int j=0;j<a.get(u).size();j++) {v[a.get(u).get(j)]++;}
                getMax(s,current+1,last);
                //unblock all neighbours, uncount
                for(int j=0;j<a.get(u).size();j++) {v[a.get(u).get(j)]--;}
                currentMax-=c[u];
            }
        }
    }
}