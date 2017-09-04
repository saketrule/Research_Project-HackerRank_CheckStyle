/* Enter your code here. Read input from STDIN. Print output to STDOUT 
Classname must be Solution
*/

import java.util.*;
import java.util.Map.Entry;

public class Solution {
 public static int numTestCases;
 public static ArrayList<Hashtable<Integer, Point>> testCaseList;
 
 public static class Point {
  int x;
  int y;
  
  public Point(int x, int y) {
   this.x = x;
   this.y = y;
  }
  
  public String toString() {
   return x + " " + y;
  }
 }

 public static class PointsSubset {
  Hashtable<Integer, Point> points;
  BitSet value;

  public PointsSubset(Hashtable<Integer, Point> points) {
   this.points = points;
   this.value = new BitSet(points.size());
   this.value.set(0, points.size(), true);
  }
  
  public PointsSubset clone() {
   
   Hashtable<Integer, Point> clone = (Hashtable<Integer, Point>) this.points.clone();
   PointsSubset copy = new PointsSubset( clone);
   copy.value = (BitSet) this.value.clone();
   return copy;
  }
 }
 
 public static class Line {
  public Point a;
  public Point b;
  
  public Line(Point a, Point b) {
   this.a = a;
   this.b = b;
  }
  public boolean contains(Point p) {
   return ((b.x - a.x)*(p.y - a.y) == (b.y - a.y)*(p.x - a.x));
  }
 }
 
 public static void parse() {
  
  Scanner SC = new Scanner(System.in);
  Scanner lineSC = new Scanner(SC.nextLine());
  numTestCases = lineSC.nextInt();
  
  for (int i = 0; i < numTestCases; i++) {
   lineSC = new Scanner(SC.nextLine());
   int numPoints = lineSC.nextInt();
   Hashtable<Integer, Point> points = new Hashtable<Integer, Point>(numPoints);
   testCaseList.add(points);

   for (int j = 0; j < numPoints; j++) {
    Scanner pointSC = new Scanner(SC.nextLine());
    Point p = new Point(pointSC.nextInt(), pointSC.nextInt());
    points.put(j, p);
   }
  }
 }
 
 public static PointsSubset removeLine(Line line, PointsSubset subset) {
  Iterator<Entry<Integer, Point>> it = subset.points.entrySet().iterator();
  
  while (it.hasNext()) {
   Entry<Integer, Point> e = it.next();
   if (line.contains(e.getValue())) {
    it.remove();
    subset.value.set(e.getKey(), false);
   }
  }
  return subset;
 }
 
 public static Point leastLines(PointsSubset subset, Hashtable<Integer, Point> subSolutions) {
  //To use line or not use to line, that is the question!
  
  Point solution;
  if (subSolutions.get(subset.value.hashCode()) != null) {
   solution = subSolutions.get(subset.value.hashCode());
   solution.y = 0;
   return solution;
  }
  
  if (subset.points.size() <= 2) {
   solution = new Point(1, 1);
   subSolutions.put(subset.value.hashCode(), solution);
   return solution;
  }  
  
  Integer numLines = Integer.MAX_VALUE;
  Integer numWays = 0;
  Collection<Point> c = subset.points.values();
  Point[] points = c.toArray(new Point[subset.points.size()]);
  for (int i = 0; i < subset.points.size(); i++) {   
   for (int j = i+1; j < subset.points.size(); j++) {

    Line line = new Line(points[i], points[j]);
    Point sub = leastLines(removeLine(line, subset.clone()), subSolutions);
    if (numLines > 1 + sub.x) {
     numLines = 1 + sub.x;
     numWays = sub.y;
    }
    else if (numLines == 1 + sub.x )
     numWays = numWays + sub.y; 
   }
  }
  solution = new Point(numLines, numLines*numWays);
  subSolutions.put(subset.value.hashCode(), solution);
  return solution;
 }
 
 /**
  * @param args
  */
 public static void main(String[] args) {  
  numTestCases = 0;
  testCaseList = new ArrayList<Hashtable<Integer, Point>>();  
  parse();
  
  for (int i = 0; i < numTestCases; i++) {
   Hashtable<Integer, Point> subSolutions = new Hashtable<Integer, Point>();
   Solution.PointsSubset subset = new Solution.PointsSubset(testCaseList.get(i));
   System.out.println(leastLines(subset, subSolutions).toString());
   
  }
 }
}