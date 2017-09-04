import java.util.*;

public class Solution{
 public static void main(String[] args){
  Scanner sc = new Scanner(System.in);
  final int nodes = sc.nextInt();
  int[][] distances = new int[nodes+1][nodes+1];
  for(int[] row : distances){
   Arrays.fill(row, -1);
  }
  final int edges = sc.nextInt();
  for(int i = 0; i < edges; ++i){
   final int from = sc.nextInt();
   final int to = sc.nextInt();
   final int length = sc.nextInt();
   distances[from][to] = length;
  }
  int[][] minDistances = calcMinDistances(distances);

  final int queries = sc.nextInt();
  for(int i = 0; i < queries; ++i){
   final int from = sc.nextInt();
   final int to = sc.nextInt();
   System.out.println(minDistances[from][to]);
  }
 }
 
 static int[][] calcMinDistances(int[][] distances){
  int[][] minDistances = new int[distances.length][distances.length];
  for(int from = 0; from < distances.length; ++from){
   for(int to = 0; to < distances.length; ++to){
    minDistances[from][to] = distances[from][to];
   }
   minDistances[from][from] = 0;
  }
  for(int k = 0; k < distances.length; ++k){
   for(int from = 0; from < distances.length; ++from){
    for(int to = 0; to < distances.length; ++to){
     if(-1 == minDistances[from][k] || -1 == minDistances[k][to]){
      continue;
     }
     final int altDistance = minDistances[from][k] + minDistances[k][to];
     if(-1 == minDistances[from][to] || altDistance < minDistances[from][to]){
      minDistances[from][to] = altDistance;
     }
    }
   }
  }
  return minDistances;
 } 
}