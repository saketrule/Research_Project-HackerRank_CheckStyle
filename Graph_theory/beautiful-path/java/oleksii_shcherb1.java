import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static class Edge {
        public Edge(int _m, int _u, int _v, int _c){
            m=_m;
            u=_u;
            v=_v;
            c=_c;
        }
        public int m;
        public int u;
        public int v;
        public int c;
    }

    static int traverseToBFS(int a, int b, List<Edge>[] nodes, int M){
        if(a==b) return 0;
        
        int N = nodes.length;
        
        int[] distanceToNode=new int[N];
        boolean[] visitedEdges=new boolean[M];
        Arrays.fill(distanceToNode,0,N,-1);
        Arrays.fill(visitedEdges,0,M,false);
        
        distanceToNode[a]=0;
        
        LinkedList<Edge> edgesToCheck = new LinkedList<>();
        
        for(Edge e:nodes[a]){
            if(!visitedEdges[e.m])
                edgesToCheck.addLast(e);
        }
        
        while(edgesToCheck.size()>0){
            Edge e = edgesToCheck.pollFirst();
            
            visitedEdges[e.m]=true;
            if(distanceToNode[e.v]<0 || (distanceToNode[e.u]|e.c)<distanceToNode[e.v]){
                distanceToNode[e.v]=distanceToNode[e.u]|e.c;
            }
            
            for(Edge e_add:nodes[e.v]){
                if(!visitedEdges[e_add.m])
                    edgesToCheck.addLast(e_add);
            }
        }
        return distanceToNode[b];
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();
        
        LinkedList<Edge>[] nodes=new LinkedList[N];
        for(int i=0;i<N;i++){
            nodes[i]=new LinkedList<>();
        }
        
        for(int i = 0;i<M;i++){
            int u = in.nextInt()-1;
            int v = in.nextInt()-1;
            int c = in.nextInt();
            nodes[u].add(new Edge(i,u,v,c));
            nodes[v].add(new Edge(i,v,u,c));
        }

        boolean[] visitedNodes=new boolean[N];
        boolean[] visitedEdges=new boolean[M];
        Arrays.fill(visitedNodes,0,N,false);
        Arrays.fill(visitedEdges,0,M,false);
        
        int n1 = in.nextInt() - 1;
        int n2 = in.nextInt() - 1;
        
        int minC = traverseToBFS(n1, n2, nodes, M);
        
        System.out.println(minC);
    }
}