//computes the mst of an undirected connected graph
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) throws IOException{
        BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));        
        StringTokenizer tok = new StringTokenizer(bi.readLine());
        int n = Integer.parseInt(tok.nextToken());
        int m = Integer.parseInt(tok.nextToken());
        Edge[] edges = new Edge[m];
        
        for(int i = 0; i < m; i++) {
            tok = new StringTokenizer(bi.readLine());            
            int x = Integer.parseInt(tok.nextToken());
            int y = Integer.parseInt(tok.nextToken());
            int w = Integer.parseInt(tok.nextToken());            
            edges[i] = new Edge(x,y,w);
        }
        Arrays.sort(edges,new Comparator<Edge>(){
            @Override
            public int compare(Edge e1, Edge e2) {
                int result = 0;
                if(e1.w < e2.w) {
                    result = -1;
                } else if(e1.w > e2.w) {
                    result = 1;
                }
                return result;
            }
        });        
        
        int[] unionFind = new int[n+1];
        int edgesInTree = 1;       
        unionFind[edges[0].x] = edges[0].x;
        unionFind[edges[0].y] = edges[0].x;
        ArrayList<LinkedList<Edge>> mstEdges = new ArrayList<LinkedList<Edge>>(n);
        for(int i = 0; i < n; i++) {
            mstEdges.add(new LinkedList<Edge>());
        }
        mstEdges.get(edges[0].x-1).add(edges[0]);
        mstEdges.get(edges[0].y-1).add(edges[0]);
        
           
        for(int i = 1; i < m && edgesInTree < n-1; i++) {
            Edge e = edges[i];
            int findX = find(unionFind,e.x);
            int findY = find(unionFind,e.y);
            
            if(findX == 0 && findY == 0){
                edgesInTree++;
                unionFind[e.x] = e.x;
                unionFind[e.y] = e.x;
                mstEdges.get(e.x-1).add(e);
                mstEdges.get(e.y-1).add(e);
            } else if(findX == 0 && findY > 0) {
                edgesInTree++;
                unionFind[e.x] = unionFind[e.y];
                mstEdges.get(e.x-1).add(e);
                mstEdges.get(e.y-1).add(e);
            } else if(findY == 0 && findX > 0) {
                edgesInTree++;
                unionFind[e.y] = unionFind[e.x];
                mstEdges.get(e.x-1).add(e);
                mstEdges.get(e.y-1).add(e);
            } else if(findX != findY) {
                edgesInTree++;
                unionFind[findX] = unionFind[findY];
                mstEdges.get(e.x-1).add(e);
                mstEdges.get(e.y-1).add(e);
            } 
        }        
           
        int[] visited = new int[n];      
        long[] numberOfChildren = new long[n];        
        LinkedList<Integer> q = new LinkedList<Integer>();    
        q.push(0);
        
        int last = -1;
        while(!q.isEmpty()){
            int current = q.peek();            
            visited[current] = 1;
            
            if(last >= 0 && visited[last] == 2) {
                numberOfChildren[current] += numberOfChildren[last];
            }
            
            int added = 0;
            for(Edge e : mstEdges.get(current)) {                
                int neighbour = e.x-1;
                if(neighbour == current) {
                    neighbour = e.y-1;
                }
                if(visited[neighbour] == 0){
                    q.push(neighbour);
                    added++;                   
                    break;
                } 
            }           
            
            if(added == 0){
                q.pop();
                numberOfChildren[current]++;
                visited[current] = 2;
            }
            
            last = current;
            
        }       
        
        
        long[] exp = new long[m];
        q.push(0);
        visited = new int[n];
        long nn = n;
        while(!q.isEmpty()){
            int current = q.pop();
            visited[current] = 1;
            
            for(Edge e: mstEdges.get(current)) {
                int neighbour = e.x-1;
                if(neighbour == current) {
                    neighbour = e.y-1;
                }
                if(visited[neighbour] == 0) {
                    exp[e.w] = numberOfChildren[neighbour] *(nn - numberOfChildren[neighbour]);  
                    q.add(neighbour);
                }
            }
        }
        
        
        LinkedList<Integer> output = new LinkedList<Integer>();
        int curr = 0;
        long number = exp[0];
        while(number > 0 || curr < m){
            if(number % 2 == 0){
                output.add(0);
            } else {
                output.add(1);
            }
            curr++;
            number = number / 2;
            if(curr < m) {
                number += exp[curr];
            }
        }
        while(output.peekLast() == 0){
            output.pollLast();
        }
        while(output.size() > 0){
            System.out.print(output.pollLast());
        }
        
           
    }
    
    public static int find(int[] unionFind, int x) {
        if(unionFind[x] == 0) {
            return 0;
        }
        int index = x;
        while(index != unionFind[index]){
            index = unionFind[index];
        }
  int result = index;
  index = x;
  while(index != unionFind[index]){
            unionFind[index] = result;
   index = unionFind[index];   
        }
        return result;
    }
    
        
    static class IntComparator implements Comparator<Integer>{
            int[] minDistance;
        
            IntComparator(int[] minDistance){
                this.minDistance = minDistance;
            }
        
            @Override
            public int compare(Integer e1, Integer e2) {                
                int result = 0;
                if(minDistance[e1] < minDistance[e2]) {
                    result = -1;
                } else if(minDistance[e1] > minDistance[e2]){
                    result = 1;
                }
                return result;
            } 
    }
    
    static class Edge {
        int x;
        int y;
        int w;        
        
        Edge(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }     
        
        @Override
        public String toString(){
            return "(" + x + "," + y + ")"; 
        }
    }
}