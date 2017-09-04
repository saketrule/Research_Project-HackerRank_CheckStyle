import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class Solution {
 
 public static void main(String[] args) {
  Solution solution = new Solution();  
     try {
      solution.start();
     } catch (Exception e) {
         System.err.println("Error:" + e.getMessage());
     }
 }
 
 private void start() throws Exception{
  //Reads Input
  int mod = 1000000007;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
              
        for(int i=0;i<t;i++){
         int n = Integer.parseInt(br.readLine());
         Set<Point> points = new HashSet<Point>();
         for(int j=0;j<n;j++){
          String[] s = br.readLine().split(" ");
          Point p = new Point();
          p.x = Integer.parseInt(s[0]);
          p.y = Integer.parseInt(s[1]);
          points.add(p);
         }
                  
         solutions = new TreeMap<Integer,Integer>();
         depth = 0;
         solve(points);         
         System.out.println(solutions.firstKey()%mod+" "+solutions.get(solutions.firstKey())%mod);
         
        }
 }

 private SortedMap<Integer,Integer> solutions;
 private int depth;
 
 private void solve(Set<Point> points) {
  depth++;
  Set<Set<Point>> collinears = getCollinears(points);  
  for (Set<Point> col : collinears) {
   Set<Point> points_ = new HashSet<Point>(points);
   points_.removeAll(col);  

   if(points_.size()>0){    
    solve(points_);
    depth--;
   }else{
    Integer count = solutions.get(depth);
    if(count==null){
     count = 0;
    }    
    solutions.put(depth, ++count);
   }
  }  
 }

 private Set<Set<Point>> getCollinears(Set<Point> points){
  Map<String,Set<Point>> collinears = new HashMap<String,Set<Point>>();
  
  for (Point p1 : points) {
   for (Point p2 : points) {
    if(p1.equals(p2)) continue;
    
    double slope = getSlope(p1,p2);
    if(slope==-0.0) slope=0.0;
    double yintercept = getYIntercept(p1, slope);
    
    String key = slope+"_"+yintercept;
    Set<Point> cols = collinears.get(key);
    if(cols==null){
     cols = new HashSet<Point>();
     collinears.put(key, cols);
    }
    cols.add(p1);
    cols.add(p2);
   }   
  }
  
  Set<Set<Point>> tmp = new HashSet<Set<Point>>();
  for (Set<Point> x : collinears.values()) {
   if(x.size()>2){
    Set<Set<Point>> y = powerSet(x);
    for (Set<Point> z : new HashSet <Set<Point>>(y)) {
     if(z.size()==0 || z.size()==1)
      y.remove(z);
    }
    tmp.addAll(y);
   }else{
    tmp.add(x);
   }
  }
  
  for (Point p : points) {
   Set<Point> s = new HashSet<Point>();
   s.add(p);
   tmp.add(s);
  }

  return tmp;
 }
 
 public static <T> Set<Set<T>> powerSet(Set<T> originalSet) {
     Set<Set<T>> sets = new HashSet<Set<T>>();
     if (originalSet.isEmpty()) {
         sets.add(new HashSet<T>());
         return sets;
     }
     List<T> list = new ArrayList<T>(originalSet);
     T head = list.get(0);
     Set<T> rest = new HashSet<T>(list.subList(1, list.size())); 
     for (Set<T> set : powerSet(rest)) {
         Set<T> newSet = new HashSet<T>();
         newSet.add(head);
         newSet.addAll(set);
         sets.add(newSet);
         sets.add(set);
     }           
     return sets;
 }
 
 private double getYIntercept(Point p, double m) {
  if(m==Double.NaN) return m;
  return p.y - (m * p.x);
 }

 private double getSlope(Point p1, Point p2) {
  if((p1.x-p2.x)==0) return Double.NaN;
  return ((double)(p1.y-p2.y))/(p1.x-p2.x);
 }

}

class Point{
 public int x;
 public int y;

 public int hashCode() {
  final int prime = 31;
  int result = 1;
  result = prime * result + x;
  result = prime * result + y;
  return result;
 }

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