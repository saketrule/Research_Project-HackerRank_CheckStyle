import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution
{
 public static void main( String[] args) throws FileNotFoundException
 {
        Scanner scan = new Scanner( System.in);
  int noOfCases = scan.nextInt();
  int noOfVertices, noOfEdges, startingIndex;
  HashMap<Integer,HashMap<Integer,Integer>> edges;
  int e1, e2;
  HashMap<Integer,Integer> neighbourMap;
  
  for( int i = 0; i < noOfCases; i++)
  {
         noOfVertices = scan.nextInt();
         noOfEdges = scan.nextInt();
   edges = new HashMap<Integer,HashMap<Integer,Integer>>( 2 * noOfEdges);
         
         for( int j = 0; j < noOfEdges; j++)
         {
    e1 = scan.nextInt();
    e2 = scan.nextInt();
    
    neighbourMap = edges.get( e1);
    if( neighbourMap == null)
     neighbourMap = new HashMap<Integer,Integer>();
    
    neighbourMap.put( e2, e2);
    edges.put( e1, neighbourMap);
    
    neighbourMap = edges.get( e2);
    if( neighbourMap == null)
     neighbourMap = new HashMap<Integer,Integer>();

    neighbourMap.put( e1, e1);
    edges.put( e2, neighbourMap);
         }
         startingIndex = scan.nextInt();
   
   rustAndMurderer( noOfVertices, noOfEdges, edges, startingIndex);
  }
  scan.close();
 }

 public static void rustAndMurderer( int noOfVertices, int noOfEdges, 
          HashMap<Integer, HashMap<Integer, Integer>> edges, int startingIndex)
 {
  LinkedList<int[]> bfsList = new LinkedList<int[]>();
  HashSet<Integer> unvisited = new HashSet<Integer>( noOfVertices);
  HashMap<Integer,Integer> neighbourMap;
  Iterator<Integer> it;
  int index;
  int[] bfsElement, neighbour;
  int[] distances = new int[noOfVertices];
  
  for( int i = 1; i <= noOfVertices; i++)
   unvisited.add( i);
  unvisited.remove( startingIndex);
  
  bfsElement = new int[2];
  bfsElement[0] = startingIndex;
  bfsElement[1] = 0;
  bfsList.add( bfsElement);
  
  while( !bfsList.isEmpty())
  {
   bfsElement = bfsList.remove();
   neighbourMap = edges.get( bfsElement[0]);
   it = unvisited.iterator();
   while( it.hasNext())
   {
    index = it.next();
    if( index != bfsElement[0] && ( neighbourMap == null || !neighbourMap.containsKey( index)))
    {
     it.remove();
     unvisited.remove( index);
     neighbour = new int[2];
     neighbour[0] = index;
     neighbour[1] = bfsElement[1] + 1;
     bfsList.add( neighbour);
     distances[index-1] = neighbour[1];
    }
   }
  }
  
  StringBuilder sb = new StringBuilder();
  for( int i = 0; i < noOfVertices; i++)
  {
   if( i != startingIndex - 1)
   {
    sb.append( distances[i]);
    sb.append( " ");
   }
  }
  System.out.println( sb.toString());
 }
}