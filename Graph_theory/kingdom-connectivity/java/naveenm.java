import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Solution {
 private static Stack<Integer> path = new Stack<Integer>();
 private static int N, numCycles = 0;
 private static HashMap<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
 private static Set<Integer> cyclePts = new HashSet<Integer>();
 private static HashMap<Integer, Long> numPaths = new HashMap<Integer, Long>();
 private static long numPaths(int s) {
  Long numP = numPaths.get(s);
  if(numP != null)
   return numP;
  List<Integer> eds = map.get(s);
  if(eds == null)
   return 0;
  long n = 0;
  path.push(s);
  for(Integer e : eds) {
   if(path.contains(e)) {
    cyclePts.add(e);
    numCycles++;
    continue;
   }
   if(e == N) {
    n += 1;
    if(n >= 1000000000)
     n -= 1000000000;
    if(numCycles > 0)
     break;
   }
   long k = numPaths(e);
   if(k < 0) {
    n = k;
    break;
   }
   n += k;
   if(n >= 1000000000)
    n -= 1000000000;
  }
  if(n > 0 || n == -2) {
   if(cyclePts.contains(s))
    n = -1; // infinite
   else if(numCycles > 0)
    n = -2; // skip
  }
  if(n > -2)
   numPaths.put(s, n);
  path.pop();
  if(cyclePts.contains(s))
   numCycles--;
  return n;
 }
 /**
  * @param args
  * @throws IOException 
  */
 public static void main(String[] args) throws IOException {
  BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
  String nm[] = r.readLine().split(" ");
  N = Integer.parseInt(nm[0]);
  int M = Integer.parseInt(nm[1]);
  for(int i = 0; i < M; i++) {
   String l[] = r.readLine().split(" ");
   int s = Integer.parseInt(l[0]);
   int t = Integer.parseInt(l[1]);
   List<Integer> list = map.get(s);
   if(list == null) {
    list = new ArrayList<Integer>();
    map.put(s, list);
   }
   list.add(t);
  }
  long n = numPaths(1);
  if(n < 0)
   System.out.println("INFINITE PATHS");
  else
   System.out.println(n);
 }
}