/*
 Enter your code here. Read input from STDIN. Print output to STDOUT
 Your class should be named Solution
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {

 public static void getAllConnect(Map<Integer, Map<Integer, Integer>> roads,
   Set<Integer> usefulNodes, Set<Integer> startNodes) {
  Set<Integer> tmpStartNodes = new HashSet<Integer>();
  for (int node : startNodes) {
   if (roads.containsKey(node)) {
    Set<Integer> addNodes = roads.get(node).keySet();
    if (addNodes != null) {
     for (int addNode : addNodes) {
      if (!usefulNodes.contains(addNode)) {
       tmpStartNodes.add(addNode);
      }
     }
    }
   }
  }
  for (int addNode : tmpStartNodes) {
   if (usefulNodes.contains(addNode)) {
    // loop = true;
   } else {
    usefulNodes.add(addNode);
   }
  }
  startNodes.clear();
  if (tmpStartNodes.size() > 0) {
   getAllConnect(roads, usefulNodes, tmpStartNodes);
  }
 }

 public static void refineMap(Map<Integer, Map<Integer, Integer>> roads,
   Set<Integer> usefulNodes) {
  Map<Integer, Map<Integer, Integer>> tmp = new HashMap<Integer, Map<Integer, Integer>>();
  for (int n1 : roads.keySet()) {
   if (usefulNodes.contains(n1)) {
    tmp.put(n1, new HashMap<Integer, Integer>());
    for (int n2 : roads.get(n1).keySet()) {
     if (usefulNodes.contains(n2)) {
      tmp.get(n1).put(n2, roads.get(n1).get(n2));
     }
    }
   }
  }
  roads.clear();
  roads.putAll(tmp);
 }

 public static long solve(int N,
   Map<Integer, Map<Integer, Integer>> inRoads,
   Map<Integer, Map<Integer, Integer>> outRoads) {
  long modulo = (long) 1000000000;
  Set<Integer> usefulOutNodes = new HashSet<Integer>();
  Set<Integer> startNodes = new HashSet<Integer>();
  usefulOutNodes.add(1);
  startNodes.add(1);
  getAllConnect(outRoads, usefulOutNodes, startNodes);
  Set<Integer> usefulInNodes = new HashSet<Integer>();
  usefulInNodes.add(N);
  startNodes.clear();
  startNodes.add(N);
  getAllConnect(inRoads, usefulInNodes, startNodes);
  Set<Integer> usefulNodes = new HashSet<Integer>();
  for (int n : usefulInNodes) {
   if (usefulOutNodes.contains(n)) {
    usefulNodes.add(n);
   }
  }
  if (usefulNodes.size() == 0) {
   return 0;
  }
  refineMap(inRoads, usefulNodes);
  refineMap(outRoads, usefulNodes);
  Set<Integer> endNodes = new HashSet<Integer>();
  endNodes.add(N);
  Map<Integer, Long> countMap = new HashMap<Integer, Long>();
  countMap.put(N, (long) 1);
  while (endNodes.size() > 0) {
   Set<Integer> tmpEndNodes = new HashSet<Integer>();
   for (int endNode : endNodes) {
    if (outRoads.containsKey(endNode)
      && outRoads.get(endNode).size() != 0) {
     return -1;
    }
    if (inRoads.containsKey(endNode)) {
     for (int newEndNode : inRoads.get(endNode).keySet()) {
      // if (outRoads.containsKey(newEndNode)) {
      outRoads.get(newEndNode).remove(endNode);
      if (countMap.containsKey(newEndNode)) {
       countMap.put(
         newEndNode,
         (countMap.get(newEndNode) + ((long) (inRoads
           .get(endNode).get(newEndNode)))
           * ((long) (countMap.get(endNode)))
           % modulo)
           % modulo);
      } else {
       countMap.put(
         newEndNode,
         ((long) (inRoads.get(endNode)
           .get(newEndNode)))
           * ((long) (countMap.get(endNode)))
           % modulo);
      }
      if (outRoads.get(newEndNode).size() == 0) {
       tmpEndNodes.add(newEndNode);
      }
      // }
     }
    }
    outRoads.remove(endNode);
   }
   endNodes.clear();
   endNodes.addAll(tmpEndNodes);
  }
  if (outRoads.size() != 0) {
   return -1;
  } else {
   return countMap.get(1);
  }
 }

 /**
  * @param args
  */
 public static void main(String[] args) throws Exception {
  // TODO Auto-generated method stub
  BufferedReader reader = new BufferedReader(new InputStreamReader(
    System.in));

  String line = reader.readLine();
  String[] tokens = line.split(" ");
  int N = Integer.parseInt(tokens[0]);
  int M = Integer.parseInt(tokens[1]);

  Map<Integer, Map<Integer, Integer>> inRoads = new HashMap<Integer, Map<Integer, Integer>>();
  Map<Integer, Map<Integer, Integer>> outRoads = new HashMap<Integer, Map<Integer, Integer>>();

  for (int i = 0; i < M; ++i) {
   tokens = reader.readLine().split(" ");
   int from = Integer.parseInt(tokens[0]);
   int to = Integer.parseInt(tokens[1]);
   if (!outRoads.containsKey(from)) {
    outRoads.put(from, new HashMap<Integer, Integer>());
   }
   if (!outRoads.get(from).containsKey(to)) {
    outRoads.get(from).put(to, 1);
   } else {
    outRoads.get(from).put(to, 1 + outRoads.get(from).get(to));
   }
   if (!inRoads.containsKey(to)) {
    inRoads.put(to, new HashMap<Integer, Integer>());
   }
   if (!inRoads.get(to).containsKey(from)) {
    inRoads.get(to).put(from, 1);
   } else {
    inRoads.get(to).put(from, 1 + inRoads.get(to).get(from));
   }
  }
  long result = solve(N, inRoads, outRoads);
  if (result < 0) {
   System.out.println("INFINITE PATHS");
  } else {
   System.out.println(result);
  }
  reader.close();
 }

}