import java.io.*;
import java.util.*;

public class Solution
{

@SuppressWarnings("unchecked")   
public static ArrayList[] reverseGraph(ArrayList[] nodes, int n)
{
    ArrayList[] rnodes = new ArrayList[n];
    for ( int i=0; i < n; i++)
    {
      rnodes[i] = new ArrayList<Integer>();
    }

    for ( int i=0; i <  n ; i++)
    {
      for ( int j=0; j < nodes[i].size(); j++)
      {
          int nbr = (Integer) nodes[i].get(j);
          rnodes[nbr].add(i);
      }
    }

    return rnodes;

    //printGraph(rnodes);

}


public static void countPaths(ArrayList[] nodes, int n, int[] topolist, int[] topolabel)
{
        long[] pathCount = new long[n];

        //int finishTimeOfN = topolabel[n-1];

        pathCount[n-1]=1;

        for ( int i = 0  ; i < n ; i++ )
        {
          
          int current = topolist[i];

          for ( int j = 0; j < nodes[current].size(); j++)
          {
              pathCount[(Integer)nodes[current].get(j)] +=  ( pathCount[current] % 1000000000 );
              //System.out.println( pathCount[(Integer)nodes[current].get(j)]  );
          }

          if ( current == 0 )
          {
            System.out.print( pathCount[current] % 1000000000 );
            break;
          }
        }
        
        //pathCount[0] = 1000000001;
}


public static int toporder=0;
public static void topoSortRecursive(ArrayList[] nodes, int n, int[] topolist, int[] topolabel, int[] inPath, int[] inPathR)
{
      //topolist[0]=0;
      int[] visited = new int[n];
      visited[0]=1;
      topoSortVisit(nodes, n, 0, topolist, topolabel, visited, inPath, inPathR);

}

public static void topoSortVisit(ArrayList[] nodes, int n, int startNode, int[] topolist, int[] topolabel, int[] visited, int[] inPath, int[] inPathR)
{   
    visited[startNode]=1;
    for ( int j=0; j < nodes[startNode].size(); j++)
    {
      int nbr = (Integer)nodes[startNode].get(j);

      if ( inPath[nbr] != 1 || inPathR[nbr] != 1 ) continue;

      //System.out.println( startNode + ", " + nbr);
      
      if ( visited[nbr] == 0 )
      {
        visited[nbr]=1;
        topoSortVisit(nodes, n, nbr, topolist, topolabel, visited, inPath, inPathR);
      }
      else if ( visited[nbr] ==  1 )
      {
        System.out.println("INFINITE PATHS");
        System.exit(0);
      }
    }
    //System.out.println( startNode );
    visited[startNode]=2; 
    
    topolabel[startNode]=toporder;
    topolist[toporder]=startNode;
    toporder++;

}


public static void dfsNonRecursive(ArrayList[] nodes, int n, int startNode, int[] visited, int[] inPath)
{
  Stack<Integer> s = new Stack<Integer>();
  s.push(startNode);

  while ( ! s.isEmpty() )
  {
    int currentNode = s.pop();
    visited[currentNode]=2;
    inPath[currentNode] = 1;

    for ( int j = 0; j < nodes[currentNode].size(); j++)
    {
      int nbr = (Integer)nodes[currentNode].get(j);
      if ( visited[nbr] == 0 )
      {
        s.push(nbr);
        visited[nbr]=1;
      }
    }
  }
}


public static void buildSubGraph(ArrayList[] nodes, int n, int[] inPath, int[] inPathR)
{
  
  int[] visited = new int[n];
  dfsNonRecursive(nodes, n, 0, visited, inPath);
  ArrayList[] rnodes = reverseGraph(nodes, n);
  //reverse and do dfs
  visited = new int[n];
  dfsNonRecursive(rnodes, n, n-1, visited, inPathR);

  /*
  for ( int i=0; i < n ; i++)
  {
    System.out.print(inPath[i] + ", " );
  }

   System.out.println();

  for ( int i=0; i < n ; i++)
  {
    System.out.print(inPathR[i] + ", " );
  }

    System.out.println();
    */

}


@SuppressWarnings("unchecked")
public static void main(String args[]) {

   Integer n=0, m, u, v;
   long mod = 1000000000;

   ArrayList[] nodes = new ArrayList[10000];


    try {
        BufferedReader br = 
          new BufferedReader(new InputStreamReader(System.in));
        
        String s = br.readLine();
        String s1[] = s.split(" ");
        n = Integer.parseInt(s1[0]);
        m = Integer.parseInt(s1[1]);
        for (int i=0;i<n; i++ ) 
        {
          nodes[i] = new ArrayList<Integer>();
        }
        s = br.readLine();
        while ( s != null)
        {
         s1 = s.split(" ");
         u = Integer.parseInt(s1[0]);
         v = Integer.parseInt(s1[1]);
         nodes[u-1].add(v-1);
         s = br.readLine();
        }
       

        int[] inPath = new int[n];
        int[] inPathR = new int[n];
        buildSubGraph(nodes,n, inPath, inPathR);
        
        int[] topolist = new int[n];
        int[] topolabel = new int[n];
        topoSortRecursive(nodes,n,topolist, topolabel, inPath, inPathR );

        ArrayList[] rnodes = reverseGraph(nodes,n);
        countPaths(rnodes, n,topolist, topolabel);
   
        /* 
        for (int j=0;j<n ;j++ ) 
        {
          System.out.print(topolist[j] + ", ");
        }
        System.out.println();
        for (int j=0;j<n ;j++ ) 
        {
          System.out.print(topolabel[j] + ", ");
        }
        System.out.println();       
        */
        

    } catch (Exception e) {
        e.printStackTrace();
        System.err.println("Error:" + e.getMessage());
    }

  }

public static void printGraph(ArrayList[] a, int nodes)
{
  for (int i=0; i<nodes;i++ ) 
  {
    for ( int j=0; j < a[i].size(); j++)
    {
      System.out.println( (i+1) + " " + ( (Integer)a[i].get(j) + new Integer(1)) + " " );
    }

  }
}

}