import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution
{
    private static Solution s = new Solution();
    
    public static void main(String[] args)
    {
        Scanner in = new Scanner( System.in);
        int noOfCities = in.nextInt();
        int noOfLetters = in.nextInt();
        
        TreeMap<Integer,Node> cities = new TreeMap<Integer,Node>();
        for( int i = 1; i <= noOfCities; i++)
        {
            cities.put( i, s.new Node( i));
        }
        
        HashSet<Integer> letters = new HashSet<Integer>( noOfLetters);
        int letter;
        for( int i = 0; i < noOfLetters; i++)
        {
            letter = in.nextInt();
            letters.add( letter);
            cities.get( letter).addLetter();
        }
        
        int u, v, d;
        for( int i = 0; i < noOfCities - 1; i++)
        {
            u = in.nextInt();
            v = in.nextInt();
            d = in.nextInt();
            cities.get( u).addNeighbor( cities.get( v), d);
            cities.get( v).addNeighbor( cities.get( u), d);
        }
        in.close();
        
        // Remove leaves without a letter
        LinkedList<Node> leaves = new LinkedList<Node>();
        for( Node n : cities.values())
        {
            if( !n.hasLetter() && n.getNoOfNeighbors() == 1)
            {
                leaves.add(n);
            }
        }
        
        Node leaf, neighbor;
        TreeMap<Node,Integer> neighbors;
        while( !leaves.isEmpty())
        {
            leaf = leaves.removeFirst();
            neighbors = leaf.getNeighbors(); // As a leaf, should have only 1 neighbor
            neighbor = neighbors.firstKey();
            
            neighbor.removeNeighbor( leaf);
            if( !neighbor.hasLetter() && neighbor.getNoOfNeighbors() == 1)
            {
                leaves.add( neighbor);
            }
            cities.remove( leaf.getIndex());
        }
        
        // Find total length and longest path
        int twiceTotalLength = 0;
        for( Node n : cities.values())
        {
            for( Integer distance : n.getNeighbors().values())
            {
                twiceTotalLength += distance;
            }
        }
        
        Node root = cities.firstEntry().getValue();
        Map.Entry<Node,Integer> farthest = weightedBfs( cities, root);
        farthest = weightedBfs( cities, farthest.getKey());
        
        int longestPath = farthest.getValue();
        System.out.println( twiceTotalLength - longestPath);
    }
    
    private static Map.Entry<Node,Integer> weightedBfs( TreeMap<Integer,Node> cities, Node root)
    {
        LinkedList<Node> bfsQueue = new LinkedList<Node>();
        bfsQueue.add( root);
        
        TreeMap<Node,Integer> distanceMap = new TreeMap<Node,Integer>();
        distanceMap.put( root, 0);
        
        Node current;
        int distance;
        Node farthestNode = root;
        int maxDistance = 0;
        while( !bfsQueue.isEmpty())
        {
            current = bfsQueue.removeFirst();
            distance = distanceMap.get( current);
            
            for( Map.Entry<Node,Integer> neighbor : current.getNeighbors().entrySet())
            {
                if( !distanceMap.containsKey( neighbor.getKey()))
                {
                    bfsQueue.add( neighbor.getKey());
                    distanceMap.put( neighbor.getKey(), distance + neighbor.getValue());
                }
            }
             
            if( distance > maxDistance)
            {
                maxDistance = distance;
                farthestNode = current;
            }
        }
        
        return distanceMap.ceilingEntry( farthestNode);
    }
    
    private class Node implements Comparable
    {
        private int index;
        private TreeMap<Node,Integer> neighbors;
        private int noOfNeighbors;
        private boolean hasLetter;
        
        public Node( int index)
        {
            this.index = index;
            this.neighbors = new TreeMap<Node,Integer>();
            this.noOfNeighbors = 0;
            this.hasLetter = false;
        }
        
        public int getIndex()
        {
            return this.index;
        }
        
        public void addNeighbor( Node n, int d)
        {
            this.neighbors.put( n, d);
            ++this.noOfNeighbors;
        }
        
        public void removeNeighbor( Node n)
        {
            this.neighbors.remove( n);
            --this.noOfNeighbors;
        }
        
        public TreeMap<Node,Integer> getNeighbors()
        {
            return this.neighbors;
        }
        
        public int getNoOfNeighbors()
        {
            return this.noOfNeighbors;
        }
        
        public boolean hasLetter()
        {
            return this.hasLetter;
        }
        
        public void addLetter()
        {
            this.hasLetter = true;
        }
        
        @Override
        public int compareTo( Object o)
        {
            if( o == null || ! (o instanceof Solution.Node))
            {
                return 1;
            }
            else if( this.index < ((Solution.Node)o).getIndex())
            {
                return -1;
            }
            else if( this.index > ((Solution.Node)o).getIndex())
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
    }
}