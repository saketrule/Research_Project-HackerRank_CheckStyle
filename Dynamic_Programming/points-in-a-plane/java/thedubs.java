import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Solution{
 
 static long MOD = 1000000007;
 private static class Point{
  int x;
  int y;
  
  Point(int x, int y){
   this.x = x;
   this.y = y;
  }
  
  public String toString(){
   return x + " " + y;
  }

  @Override
  public int hashCode() {
   final int prime = 31;
   int result = 1;
   result = prime * result + x;
   result = prime * result + y;
   return result;
  }

  @Override
  public boolean equals(Object obj) {
   if (this == obj)
    return true;
   if (obj == null)
    return false;
   if (getClass() != obj.getClass())
    return false;
   Point other = (Point) obj;
   if (x != other.x)
    return false;
   if (y != other.y)
    return false;
   return true;
  }
 }
 
 public static void main(String[] args) throws Exception{

  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  //BufferedReader br2 = new BufferedReader(new FileReader("out.txt"));

  //PrintWriter out = new PrintWriter(new FileWriter("diff.txt")); 
  String line = br.readLine();
  int T = Integer.parseInt(line);
    //[0 0, 0 1, 1 0, 1 1, 1 2, 2 0]
  for(int i=0; i<T; i++){
   line = br.readLine();
   int N = Integer.parseInt(line);
   Point[] points = new Point[N];
   
   for(int j=0; j<N; j++){
    line = br.readLine();
    String[] input = line.split(" ");
    points[j] = new Point(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
   }
   
   int minTurns = getMinTurns(points);
   long numWays = getNumWays(points, minTurns);
   long ways = (fact(minTurns)*numWays) % MOD; 
   
   //String actualLine = br2.readLine();
   //String testLine = minTurns + " " + ways;
   
   System.out.println(minTurns + " " + ways);

  }
 }
 
 private static long fact(int minTurns){
  long prod = 1;
  
  for(int i=1; i<=minTurns; i++){
   prod = (prod*i) % MOD;
  }
  
  return prod;
 }
 
 
 private static long getNumWays(Point[] points, int minTurns){
  int visited = 1;
  memo2 = new HashMap<Integer, Long> ();
  memo3 = new HashMap<Integer, Integer> ();
  return getNumWaysHelper(points, 0, visited, null, 1, minTurns, (1 << points.length) -1);
 }
 
 
 private static HashMap<Integer, Long> memo2 = new  HashMap<Integer, Long>();
 private static HashMap<Integer, Integer> memo3 = new  HashMap<Integer, Integer>();

 private static long getNumWaysHelper(Point[] points, int idx, int visited, Double slope, int numTurns, int minTurns, int done){
  
  if(numTurns > minTurns)
   return 0;
  
  if(visited == done)
   return 1;
    
  long total = 0;

  if(slope != null){
   
   for(int i=idx+1; i<points.length; i++){
    if(!visitedContains(visited, i)){
     Double m = getSlope(points[idx], points[i]);
     
     if(m.equals(slope) ||  Math.abs(m-slope)< 0.000001){
      visited = visitedAdd(visited, i);
      total = (total + getNumWaysHelper(points, i, visited, slope, numTurns, minTurns, done)) % MOD;
      visited = visitedRemove(visited, i);
     }
    }
   }
   
   for(int i=0; i<points.length; i++){
    if(!visitedContains(visited, i)){
     
     Integer minNumTurnsForVisited = memo3.get(visited);
     
     if(minNumTurnsForVisited == null)
      memo3.put(visited, numTurns);
     else {
      if(numTurns > minNumTurnsForVisited) 
       break;
      else if(numTurns == minNumTurnsForVisited.intValue()){
       Long tmp = memo2.get(visited);
       total = (total + tmp) % MOD;
       break;
      }
      else{
       memo3.put(visited, numTurns);
       visited = visitedAdd(visited, i);     
       Long tmp = getNumWaysHelper(points, i, visited, null, numTurns+1, minTurns, done);
       visited = visitedRemove(visited, i);
       memo2.put(visited, tmp);
       total = (total + tmp) % MOD;
       break;
      }
     }
     
     Long tmp = memo2.get(visited);
     if(tmp == null){
      visited = visitedAdd(visited, i);     
      tmp = getNumWaysHelper(points, i, visited, null, numTurns+1, minTurns, done);
      visited = visitedRemove(visited, i);
      memo2.put(visited, tmp);
     }
     total = (total + tmp) % MOD;
     break;
    }
   }
   
  }
  else{
   
   for(int i=idx+1; i<points.length; i++){
    if(!visitedContains(visited, i)){
     Double m = getSlope(points[idx], points[i]);
     visited = visitedAdd(visited, i);
     total = (total + getNumWaysHelper(points, i, visited, m, numTurns, minTurns, done)) % MOD;
     visited = visitedRemove(visited, i);
    }
   }
   
   for(int i=0; i<points.length; i++){
    if(!visitedContains(visited, i)){  
     visited = visitedAdd(visited, i);
     total =  (total + getNumWaysHelper(points, i, visited, null, numTurns+1, minTurns, done)) % MOD;
     visited = visitedRemove(visited, i);
     break;
    }
   }
  }
  
  return total;
 }
 
 
 private static int getMinTurns(Point[] points){
  int visited = 1;
  memo = new  HashMap<Integer, Integer>();
  return getMinTurnsHelper(points, 0, visited, null, 1, (points.length % 2 == 0) ? points.length/2 : points.length/2+1, (1 << points.length) -1);
 }
 
 private static HashMap<Integer, Integer> memo = new  HashMap<Integer, Integer>();
 
 private static boolean visitedContains(int visited, int i){
  return ((visited & (1 << i)) > 0);
 }
 
 private static int visitedAdd(int visited, int i){
  return visited | (1 << i);
 }
 
 private static int visitedRemove(int visited, int i){
  return visited & ~(1 << i);
 }

 private static int getMinTurnsHelper(Point[] points, int idx, int visited, Double slope, int numTurns,  int minSoFar, int done){
  /*
  if(numTurns >= minSoFar){
   return minSoFar;
  }
  */
  
  if(visited == done)
   return numTurns;
    
  if(slope != null){
   for(int i=idx+1; i<points.length; i++){
    if(!visitedContains(visited, i)){
     Double m = getSlope(points[idx], points[i]);
     
     if(m.equals(slope) ||  Math.abs(m-slope)< 0.0000001){
      visited = visitedAdd(visited, i);
      int tmp= getMinTurnsHelper(points, i, visited, slope, numTurns, minSoFar, done);
      visited = visitedRemove(visited, i);
      return tmp;
     }
    }
   }
   
   for(int i=0; i<points.length; i++){
    if(!visitedContains(visited, i)){
     //Integer tmp = memo.get(visited);
     //if(tmp == null){
      visited = visitedAdd(visited, i);
      int tmp = getMinTurnsHelper(points, i, visited, null, numTurns+1, minSoFar, done);
      visited = visitedRemove(visited, i);
      //memo.put(visited, tmp);
     //}

     return tmp;
    }
   }
  }
  else{
   int min = points.length;
   
   for(int i=0; i<points.length; i++){
    if(!visitedContains(visited, i)){
     Double m = getSlope(points[idx], points[i]);
     visited = visitedAdd(visited, i);
     int tmp = getMinTurnsHelper(points, i, visited, m, numTurns, minSoFar, done);
     visited = visitedRemove(visited, i);
     if(tmp < min)
      min = tmp;
    }
   }
   
   return min;
  }
  
  return 0;
 }
 
 /*
 private static int getMinTurnsHelper(Point[] points, int idx, int visited, Double slope, int numTurns,  int minSoFar, int done){
  if(numTurns >= minSoFar){
   return minSoFar;
  }
  
  if(visited == done)
   return numTurns;
    
  if(slope != null){
   for(int i=0; i<points.length; i++){
    if(!visitedContains(visited, i)){
     Double m = getSlope(points[idx], points[i]);
     
     if(Math.abs(m-slope) < 0.01){
      visited = visitedAdd(visited, i);
      int tmp= getMinTurnsHelper(points, i, visited, slope, numTurns, minSoFar, done);
      if(tmp < minSoFar)
       minSoFar = tmp;
      visited = visitedRemove(visited, i);
      return tmp;
     }
    }
   }
   
   for(int i=0; i<points.length; i++){
    if(!visitedContains(visited, i)){
     //Integer tmp = memo.get(visited);
     //if(tmp == null){
      visited = visitedAdd(visited, i);
      int tmp = getMinTurnsHelper(points, i, visited, null, numTurns+1, minSoFar, done);
      if(tmp < minSoFar)
       minSoFar = tmp;
      visited = visitedRemove(visited, i);
      //memo.put(visited, tmp);
     //}

     return tmp;
    }
   }
  }
  else{
   for(int i=0; i<points.length; i++){
    if(!visitedContains(visited, i)){
     Double m = getSlope(points[idx], points[i]);
     visited = visitedAdd(visited, i);
     int tmp = getMinTurnsHelper(points, i, visited, m, numTurns, minSoFar, done);
     visited = visitedRemove(visited, i);
     if(tmp < minSoFar)
      minSoFar = tmp;
    }
   }
  }
  
  return minSoFar;
 }
 */
 
 private static Double getSlope(Point a, Point b){
  double dx = a.x - b.x;
  double dy = a.y - b.y;
  
  if(dx == 0)
   return Double.NEGATIVE_INFINITY;
  return dy/dx;
 }
 
 
}