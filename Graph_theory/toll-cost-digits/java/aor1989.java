import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
 static int count[] = new int[10];
 static Map<Integer, List<Road>> map = new HashMap<Integer, List<Road>>();

 public static void main(String[] args) {

  Scanner in = new Scanner(System.in);
  int n = in.nextInt();
  int e = in.nextInt();
  for (int a0 = 0; a0 < e; a0++) {
   int x = in.nextInt();
   int y = in.nextInt();
   int r = in.nextInt();
   int key1, value1, dist1;
   int key2, value2, dist2;

   key1 = x;
   value1 = y;
   dist1 = r;

   key2 = y;
   value2 = x;
   dist2 = 1000 - r;
   addValues(key1, value1, dist1);
   addValues(key2, value2, dist2);

  }
  // reading done
  /*
   * for(Map.Entry<Integer, List<Road>> kv:map.entrySet()){
   * System.out.println(kv.getKey()+"-->"); for(Road list:kv.getValue()){
   * System.out.println(list.destination+"---->"); for(Integer
   * i:list.price) System.out.println(i); } }
   */
  // Do DFS starting with map

  traverse();
  for(Integer i:count){
   System.out.println(i);
  }
 }

 // global persisted states
 public static void traverse() {

  for (Map.Entry<Integer, List<Road>> kv : map.entrySet()) {
   int node = kv.getKey();
   List<Integer> visited=new ArrayList<Integer>();
   visited.add(node);
   //System.out.println("starting "+node);
   dfs(visited, node, 0);

  }

 }

 public static void dfs(List<Integer> visited, int lastVisited, int score) {
  // find all possible matches for last visited node
 /* System.out.println("visiting node "+lastVisited);
  for(Integer i:visited){
   System.out.print(i+" ");
  System.out.println();
  }
 */ List<Road> currentList = map.get(lastVisited);
  for (Road road : currentList) {
   if (!visited.contains(road.destination)) {
    for (Integer cost : road.price) {
     score += cost;
     visited.add(road.destination);
     processScore(score);
     dfs(visited, road.destination, score);
     visited.remove(visited.size()-1);
     score -=cost;
    }
   }
  }
 }

 public static void processScore(int score) {
  if (score != 0) {
   int d = score % 10;
   count[d] = count[d]+1;
  }
 }

 public static void addValues(int key, int value, int dist) {
  if (map.containsKey(key)) {

   List<Road> list = map.get(key);
   boolean flag = true;
   for (Road road : list) {
    if (road.destination == value) {
     road.price.add(dist);
     flag = false;
    }
   }
   if (flag) {
    Road obj = new Road();
    obj.destination = value;
    obj.price.add(dist);
    list.add(obj);
   }

  } else if (!map.containsKey(key)) {
   Road obj = new Road();
   obj.destination = value;
   obj.price.add(dist);
   List<Road> list = new ArrayList<Road>();
   list.add(obj);
   map.put(key, list);
  }
 }


}

class Road {
 int destination;
 List<Integer> price = new ArrayList<Integer>();

}