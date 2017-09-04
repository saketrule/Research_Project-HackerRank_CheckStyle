import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {
 static int[][] inArr = null;
 static Map<Integer,int[]> shortestPathMap = null;
 public static void main(String[] args) {

  Scanner scan = new Scanner(System.in);
  int nodes = scan.nextInt();
  inArr = new int[nodes][nodes]; 
  shortestPathMap= new HashMap<Integer,int[]>();
  int edges = scan.nextInt();
  while(edges>0)
  {
   int rowNum = scan.nextInt();
   int colNum = scan.nextInt();
   int weight = scan.nextInt();
   inArr[rowNum-1][colNum-1] = weight;
   edges--;
  }

  int noOfQueries = scan.nextInt();
  
  while(noOfQueries>0)
  {   int[] distance = new int[nodes];
      boolean[] isVisited = new boolean[nodes];
   for(int i=0;i<nodes;i++)
   {
    distance[i] = Integer.MAX_VALUE;
   }

   int startNode = scan.nextInt();
   int endNode = scan.nextInt();
   if(!shortestPathMap.containsKey((startNode-1)))
   {
       int[] distanceArr = shortestPath(nodes, startNode,endNode,distance,isVisited);
       shortestPathMap.put((startNode-1), distanceArr);
   }
   else
   {
    int arr[] = shortestPathMap.get((startNode-1));
    if(arr[endNode-1]<Integer.MAX_VALUE)
    {
     System.out.println(arr[endNode-1]);
    }
    else
    {
     System.out.println(-1);
    }
   }
   noOfQueries--;
  }


 }
 private static int[] shortestPath(int nodes, int startNode,int endNode,int[] distance,boolean[] isVisited) {
  distance[startNode-1] = 0;

  for(int i=0;i<(nodes-1);i++)
  {
   int u = minDistance(distance,isVisited);
   isVisited[u] = true;

   for(int j=0;j<nodes;j++)
   {
    if(!isVisited[j] && inArr[u][j]>0 && distance[u]!=Integer.MAX_VALUE &&((distance[u]+inArr[u][j])<distance[j]))
    {
     distance[j] = distance[u]+inArr[u][j];
    }
   }

  }
        if(distance[endNode-1]<Integer.MAX_VALUE)
        {
         System.out.println(distance[endNode-1]);
        }
        else
        {
           System.out.println("-1");
        }
        return distance;
 }
 private static int minDistance(int[] distance2, boolean[] isVisited2) {

  int min = Integer.MAX_VALUE;
  int min_index = 0;
  for(int i=0;i<distance2.length;i++)
  {
   if(distance2[i]<=min && !isVisited2[i])
   {
    min = distance2[i];
    min_index = i;
   }
  }
  return min_index;
 }





}