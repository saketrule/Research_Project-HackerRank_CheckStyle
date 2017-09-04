import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        int cases = reader.nextInt();
        while(cases-->0)
            {
            reader.nextLine();
            String[] NM = reader.nextLine().split(" ");
            int N = Integer.parseInt(NM[0]);
            int M = Integer.parseInt(NM[1]);
            HashMap<Integer, HashSet<Integer>> edges = new HashMap<Integer, HashSet<Integer>>();
            for(int i =0; i<M;i++)
                {
                String[] xy = reader.nextLine().split(" ");
                int x = Integer.parseInt(xy[0])-1;
                int y = Integer.parseInt(xy[1])-1;
                if(!edges.containsKey(x))
                    {
                    edges.put(x, new HashSet<Integer>());
                }
                
                edges.get(x).add(y);
                if(!edges.containsKey(y))
                    {
                    edges.put(y, new HashSet<Integer>());
                }
                
                edges.get(y).add(x);
                
            }
            
            int S = reader.nextInt()-1;
            int[] distance = new int[N];
            
            BFS(edges,distance,S);
            StringBuilder out = new StringBuilder(2*N);
            int i = 0;
            if(S==0)
                {
                out.append(distance[1]);
                i = 2;
            }
            else
                {
                out.append(distance[0]);
                i = 1;
            }
            
            for(;i<N;i++)
                {
                if(i!=S)
                    {
                    out.append(" ");
                    out.append(distance[i]);
                }
                    
            }
            System.out.println(out);
        }
    }
    
    public static void BFS(HashMap<Integer, HashSet<Integer>> edges, int[] distance, int S)
        {
        ArrayList<Integer> vertexLeft = new ArrayList<Integer>();
        ArrayList<Integer> vertexDonePrev = new ArrayList<Integer>();
        int d = 1;
        for(int i =0;i<distance.length;i++)
        {
            if(i!=S)
            {
                if((!edges.containsKey(i)) || !edges.containsKey(S) || !edges.get(S).contains(i))
                {
                    distance[i] = d;
                    vertexDonePrev.add(i);
                }
                else
                {
                    distance[i] = -1;
                    vertexLeft.add(i);
                }
            }
        }
        
        for(int i =0;i<distance.length && vertexLeft.size()>0 && vertexDonePrev.size()>0;i++)
        {
            d++;
            ArrayList<Integer> vertexDone = new ArrayList<Integer>();
            ArrayList<Integer> vertexLeftCurrent = new ArrayList<Integer>();
            for(int vertex:vertexLeft)
            {
                boolean vertexVisited = false;
                for(int prev:vertexDonePrev)
                    {
                    if(!edges.containsKey(prev) || !edges.get(prev).contains(vertex))
                    {
                        vertexVisited = true;
                        distance[vertex] = d;
                        vertexDone.add(vertex);
                        break;
                    }
                }
                if(!vertexVisited)
                    vertexLeftCurrent.add(vertex);
            }
            
            vertexDonePrev = vertexDone;
            vertexLeft = vertexLeftCurrent;
        }
    }
}