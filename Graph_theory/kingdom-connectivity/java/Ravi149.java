/* Enter your code here. Read input from STDIN. Print output to STDOUT */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Solution {
 private static ArrayList<HashMap<Integer, Integer>> myMap;
 private static boolean visited[];
 private static long toN[];
 private static boolean cycle;
 public static long betweenPaths(int start,int n){
  if(start==n-1)return 1;
  if(visited[start])return -1;
  if(toN[start]>=0)return toN[start];
  long partialPaths = 0;
  long childPaths = 0;
  long road=0;
  visited[start]=true;
  cycle = false;
  for(Integer key : myMap.get(start).keySet()){
   road=myMap.get(start).get(key);
   childPaths = betweenPaths(key,n);
   if(childPaths == -1) cycle =true;
   else if(childPaths == -2)return -2;
   else{
    partialPaths = (partialPaths+(road*childPaths)%1000000000)%1000000000;
   }
  }
  visited[start]=false;
  toN[start]=partialPaths;
  if(cycle&partialPaths>0)return -2;
  if(cycle)return -1;
  return partialPaths;
 }
 public static void main(String[] args) throws IOException{
  BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
  String a = bufferedReader.readLine();
  String b[] = a.split(" ");
  int n = Integer.parseInt(b[0]);
  int m = Integer.parseInt(b[1]);
  visited=new boolean[n];
  myMap = new ArrayList<HashMap<Integer, Integer>>();
  myMap.ensureCapacity(n);
  toN = new long[n];
  for(int i=0;i<n;i++){
   myMap.add(new HashMap<Integer,Integer>());
   toN[i]=-1;
  }
  int start,end;
  for(int i=0;i<m;i++){
   a = bufferedReader.readLine();
   b = a.split(" ");
   start = Integer.parseInt(b[0])-1;
   end = Integer.parseInt(b[1])-1;
   if(myMap.get(start).containsKey(end))myMap.get(start).put(end,myMap.get(start).get(end)+1);
   else myMap.get(start).put(end,1);
  }
  long finalPaths = betweenPaths(0,n);
  if(finalPaths==-2)System.out.println("INFINITE PATHS");
  else {
   if(finalPaths==-1)System.out.println(0);
   else System.out.println(finalPaths);
  }
 }
}