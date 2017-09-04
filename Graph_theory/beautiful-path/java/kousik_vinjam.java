import java.io.*;
import java.util.*;

public class Test {
    public static class Pair implements Comparable{
        int v;
        long p;
        public Pair(long penalty,int vertex){
            this.p=penalty;
            this.v=vertex;
        }
        public int compareTo(Object o1){
            if(this.p==((Pair)o1).p && this.v==((Pair)o1).v)
                return 0;
//            else if(this.p>((Pair)o1).p)
//                return 1;
            else
                return -1;
        }
    }

    public static void main(String[] args) throws IOException {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int n=Integer.parseInt(s[0]),m=Integer.parseInt(s[1]);
        LinkedList<Pair>[] adj = new LinkedList[n];
        for(int i=0;i<n;i++)
            adj[i]=new LinkedList<Pair>();
        for(int i=0;i<m;i++){
            s = br.readLine().split(" ");
            int a=Integer.parseInt(s[0])-1,b=Integer.parseInt(s[1])-1,c=Integer.parseInt(s[2]);
            adj[a].add(new Pair(c,b));
            adj[b].add(new Pair(c,a));
        }
        s = br.readLine().split(" ");
        int src=Integer.parseInt(s[0])-1,dst=Integer.parseInt(s[1])-1;
        PriorityQueue<Pair> pq1 = new PriorityQueue<Pair>();
        long[] pen = new long[n];
        Arrays.fill(pen,Integer.MAX_VALUE);
        pen[src]=0;
        pq1.add(new Pair(0,src));
        boolean[] visited = new boolean[n];
        while(!pq1.isEmpty()){
            Pair p2 = pq1.poll();
//            if(visited[p2.v])
//                continue;
//            visited[p2.v]=true;
//            if(p2.v==dst)
//                break;
            for(Pair p3 : adj[p2.v]){
                if((pen[p2.v] | p3.p)<pen[p3.v]){
                    pen[p3.v]=pen[p2.v]|p3.p;
                    pq1.add(new Pair(pen[p3.v],p3.v));
                }
            }
        }
        if(pen[dst]==Integer.MAX_VALUE)
            System.out.println("-1");
        else
            System.out.println(pen[dst]);
    }
}