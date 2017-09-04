import java.util.*;

public class Solution 
{
 static final int infinity = Integer.MAX_VALUE;
 static int  nodes, edges;
 static int[] distance;                  //used for nearest neighbour computation
       
 static List<Edge>[] list;               //Store the edges
 static Scanner in = new Scanner(System.in);

 public static void main(String[] args)
 {

  
   nodes = in.nextInt();
   edges = in.nextInt();
                        
                      
                                    
    createGraph();
        int min_sum=0;
            for (int i = 0; i < nodes; i++)
                {         
            findShortestPaths(i); 
                
                     for(int j=i+1;j<nodes;j++)
                     {
                         min_sum+=distance[j];
                         //System.out.println(i+" "+j+" "+distance[j]);
                     }
            }
        System.out.println(Integer.toBinaryString(min_sum));
    }
    
 static void createGraph()
 {
  list = new ArrayList[nodes];

  for (int i = 0; i < edges; i++)
  {
   int from, to, weight;

   from = in.nextInt() - 1;                //start vertex of edge
   to = in.nextInt() - 1;                  //end vertex of edge
   int temp = in.nextInt(); 
            weight=1;
            while(temp-->0)
                weight*=2;

   if (list[from] == null)
    list[from] = new ArrayList<>();

   list[from].add(new Edge(to, weight));

   if (list[to] == null)
    list[to] = new ArrayList<>();

   list[to].add(new Edge(from, weight));
  }
 }

 static void findShortestPaths(int node) 
 {
  distance = new int[nodes];

  for (int i = 0; i < nodes; i++)
   distance[i] = infinity;

  distance[node] = 0;

  PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
  priorityQueue.add(new Node(node, 0, -1));
               
                
  while (priorityQueue.size() > 0)
  {
                       
   Node min = priorityQueue.poll();
                        
   Iterator<Edge> iterator = list[min.node].iterator();
                        
                       
                       
   while (iterator.hasNext())
   {
    Edge curr = iterator.next();
                                
    if (distance[min.node] + curr.weight < distance[curr.to] )
    {
     distance[curr.to] = distance[min.node] + curr.weight;
     priorityQueue.add(new Node(curr.to, distance[curr.to], min.node));
                                        
                                    
    }
                                
                                    
   }
                        
                      
  }
              
 }

 static class Edge
 {
  int to, weight;

  public Edge(int to, int weight)
  {
   this.to = to;
   this.weight = weight;
  }

 }

 static class Node implements Comparable<Node>
 {
  int node, shortestPathWeight, parent;

  public Node(int node, int shortestPathWeight, int parent)
  {
   this.node = node;
   this.shortestPathWeight = shortestPathWeight;
   this.parent = parent;
  }

  
                @Override
  public int compareTo(Node o)
  {
   return Integer.compare(shortestPathWeight, o.shortestPathWeight);
  }

 }

}