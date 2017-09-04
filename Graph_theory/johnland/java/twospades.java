import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static int M;
 static int[][] roads;
 static int distance = 0;
 static ArrayList<ArrayList<Integer>> pathDistances = new ArrayList<ArrayList<Integer>>();
 public static void main(String[] args) throws UnsupportedEncodingException {
  Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        M = in.nextInt();
        roads = new int[M][3];
        for(int i = 0; i < M; i++) {
         roads[i][0] = in.nextInt();
         roads[i][1] = in.nextInt();
         roads[i][2] = in.nextInt();
        }
        
        int sum = 0;
        for(int i = 1; i < N + 1; i++) {
         for(int j = i + 1; j < N + 1; j++) {
          ArrayList<Integer> startingPath = new ArrayList<>();
          startingPath.add(i);
          findPaths(startingPath, new ArrayList<Integer>(), j);
          sum += findMinDistance();
          pathDistances.clear();
         }
        }
        System.out.println(Integer.toBinaryString(sum));
 }
 
 public static boolean findPaths(ArrayList<Integer> currentPath, ArrayList<Integer> distances, int endingCity) {
  boolean doNotKeepGoing = false;
  int currentCity = currentPath.get(currentPath.size() - 1);
  if(currentPath.get(currentPath.size() -1) == endingCity) {
   pathDistances.add(distances);
   return true;
  }
  
  for(int i = 0; i < M; i++) {
   ArrayList<Integer> currentPathCopy = new ArrayList<>();
   for(int j:currentPath) {
    currentPathCopy.add(j);
   }
   
   if(currentCity == roads[i][0] && !currentPath.contains(roads[i][1])) {
    currentPathCopy.add(roads[i][1]);
   } else if(currentCity ==roads[i][1] && !currentPath.contains(roads[i][0])) {
    currentPathCopy.add(roads[i][0]);
   } else {
    continue;
   }
   ArrayList<Integer> distancesCopy = new ArrayList<>();
   for(int j:distances) {
    distancesCopy.add(j);
   }
   distancesCopy.add(roads[i][2]);
   doNotKeepGoing = findPaths(currentPathCopy, distancesCopy, endingCity);
  }
  
  return false;
 }
 
 public static int findMinDistance() {
  int minDistance = -1;
  for(ArrayList<Integer> distance : pathDistances) {
   int sum = 0;
   for(int i = 0; i < distance.size(); i++) {
    sum += (int) Math.pow(2, distance.get(i));
   }
   if(minDistance == -1) {
    minDistance = sum;
   }
   if(sum < minDistance) {
    minDistance = sum;
   }
  }
  return minDistance;
 }
}