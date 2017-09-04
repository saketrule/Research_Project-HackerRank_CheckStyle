import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static class Node
    {
        public final int id;
        public int cost = Integer.MAX_VALUE;
        public List<Edge> adjacents;
        public Set<Edge> adjMemo;
        public boolean visited;
        
        // Cheapest edge to this node.
        public Edge cheapestEdge;
        
        public Node(int id)
        {
            this.id = id;
            adjacents = new ArrayList<Edge>();
            adjMemo = new HashSet<Edge>();
        }
        
        public void add(Edge edge)
        {
            boolean added = adjMemo.add(edge);
            
            if(added)
            {
                adjacents.add(edge);
            }
        }
        
        public List<Edge> getAdjacents()
        {
            return adjacents;
        }
    
    }
    
    public static class Edge
    {
        public int cost;
        public Node u;
        public Node v;
        private String key;
        public Edge(Node u, Node v, int cost)
        {
            key = u.id + "-"+v.id + "-"+cost;
            this.u = u;
            this.v = v;
            this.cost = cost;
        }
        
        public Node end(Node start)
        {
            if(start.id == u.id)
            {
                return v;
            }
            else if(start.id == v.id)
            {
                return u;
            }
            
            return null;
        }
        
        @Override
        public int hashCode()
        {
            return key.hashCode();
        }
        
        @Override
        public boolean equals(Object o)
        {
            if(!(o instanceof Edge))
            {
                return false;
            }
            
            Edge e = (Edge)o;
            return e.u.equals(u) && e.v.equals(v) && e.cost == cost;
        }
        
    }
    
    public static Node getMin(List<Node> nodes)
    {
        int smallestIndex =0;
        Node smallest = nodes.get(smallestIndex);
        
        Node tempNode;
        for(int i =1; i < nodes.size(); i++)
        {
            tempNode = nodes.get(i);
            
            if(smallest.cost > tempNode.cost)
            {
                smallestIndex = i;
                smallest = tempNode;
            }
        }
        
        nodes.remove(smallestIndex);
        return smallest;
    }
    
    public static Edge findMinEdge(List<Edge> edges)
    {
        if(edges.size()<=0)
        {
            return null;
        }
        
        int smallestIndex = 0;
        Edge smallest = edges.get(0);
        Edge temp;
        for(int i =1; i < edges.size(); i++)
        {
            temp = edges.get(i);
            if(smallest.cost >  temp.cost)
            {
                smallestIndex = i;
                smallest = temp;
            }
        }
        
        return smallest;
    }
    
    public static Edge getMinEdge(List<Edge> edges)
    {
        if(edges.size()<=0)
        {
            return null;
        }
        
        int smallestIndex = 0;
        Edge smallest = edges.get(0);
        Edge temp;
        for(int i =1; i < edges.size(); i++)
        {
            temp = edges.get(i);
            if(smallest.cost >  temp.cost)
            {
                smallestIndex = i;
                smallest = temp;
            }
        }
        edges.remove(smallestIndex);
        return smallest;
    }
    
    public static void dk(Node start, Node end)
    {
        List<Node> openSet = new ArrayList<Node>();
        Set<Node> closedSet = new HashSet<Node>();
        
        start.cost = 0;
        openSet.add(start);
        
        
        Node temp;
        List<Edge> adjacents;
        while(openSet.size() > 0)
        {
            temp = getMin(openSet);
            
            if(temp.visited)
            {
                continue;
            }
            
            temp.visited = true;
            adjacents = temp.getAdjacents();
            for(int i =0; i < adjacents.size(); i++)
            {
                Edge edge = temp.adjacents.get(i);
                
                Node temp2 = edge.end(temp);
                
                
                /*
                if(temp2.id != end.id)
                {
                    costToEnd = end.cheapestEdge.cost;
                }
                */
                
                int alt = edge.cost | temp.cost;
                
                if(temp2.cost > alt)
                {
                    temp2.cost = alt;
                }
                
                if(temp2.visited)
                {
                    continue;
                }
                
                if(!closedSet.contains(temp2))
                {
                    openSet.add(temp2);
                    closedSet.add(temp2);
                }
            }
            
        }
    }
    
    public static void resetNodes(Node[] nodes)
    {
        for(int i=0; i < nodes.length; i++)
        {
            nodes[i].cost = Integer.MAX_VALUE;
            nodes[i].visited = false;
        }
    }
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        int nodeCount = scan.nextInt();
        int edgeCount = scan.nextInt();
        
        Node [] nodes = new Node[nodeCount];
        for(int i =0; i < nodes.length; i++)
        {
            nodes[i] = new Node(i+1);
        }
        
        //Edge [][] memo = new Edge[nodeCount][nodeCount];
        
        Edge tempEdge;
        for(int i =0; i < edgeCount; i ++)
        {
            int uIndex = scan.nextInt() - 1;
            int vIndex = scan.nextInt() - 1;
            int cost = scan.nextInt();
            
            /*
            tempEdge = memo[uIndex][vIndex];
            
            if(tempEdge != null)
            {
                if(cost < tempEdge.cost)
                {
                    tempEdge.cost = cost;
                }
                continue;
            }
            */
            
            Node u = nodes[uIndex];
            Node v = nodes[vIndex];
            
            tempEdge = new Edge(u, v, cost);
            
            u.add(tempEdge);
            v.add(tempEdge);
            
        }
        int sIndex = scan.nextInt() - 1;
        int eIndex = scan.nextInt() - 1;
        
        Node start = nodes[sIndex];
        Node end = nodes[eIndex];
        
        
        /*
        Edge minEdgeEnd = findMinEdge(end.adjacents);
        if(minEdgeEnd == null)
        {
            // End is disconnected;
            System.out.println("-1");
            return;
        }
        
        end.cheapestEdge = minEdgeEnd;
        */
        
        dk(start, end);
        
        int smallestCost = Integer.MAX_VALUE;
        int pathCost = end.cost;
        
        
        if(pathCost == Integer.MAX_VALUE)
        {
            System.out.println("-1");
            return;
        }
        
        smallestCost = pathCost;    
        while(start.adjacents.size() > 0)
        {
            // Remove Min Edge
            getMinEdge(start.adjacents);
            resetNodes(nodes);
            dk(start, end);
            
            pathCost = end.cost;
            
            if(pathCost == Integer.MAX_VALUE)
            {
                // Unreachable
                break;
            }
                
            if(smallestCost > pathCost)
            {
                // Found a smallest path
                smallestCost = pathCost;
            }
            
        }
        
        System.out.println(smallestCost+"");
        
    }
}