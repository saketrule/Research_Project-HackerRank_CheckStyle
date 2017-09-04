import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


class Solution {
 
 public static long min, ways;
 public static final long MODULE = 1000000007;
 static boolean[] checkedCollinear;
 static byte[] checkedPoints; 
 static List<Point> points;
 static List<CollinearPoints> colPointList;
 
 public static void main(String[] args) throws FileNotFoundException { 
  Scanner scan = new Scanner(System.in); 
  int t = scan.nextInt();
  for (int i = 0; i < t; i++) { 
   int n = scan.nextInt();
   points = new LinkedList<Point>();
   colPointList = new LinkedList<CollinearPoints>();
   checkedPoints = new byte[n]; 
   
   for (int j = 0; j < n; j++) { 
    Point point =new Point(scan.nextInt(), scan.nextInt(), j); 
    points.add(point); 
    CollinearPoints temp = new CollinearPoints(checkedPoints);
    temp.addPoint(point);
    colPointList.add(temp); 
   }
   
   for (Iterator<Point> firstIter = points.iterator(); firstIter.hasNext();) {
    boolean[] tempCheck = new boolean[n]; 
    Point firstPoint = firstIter.next(); 
    CollinearPoints collinear = new CollinearPoints(checkedPoints);
    
    for (Iterator<Point> secondIter = points.iterator(); secondIter.hasNext();) {
     // create the first ColliearPoint
     // get two points out 
     Point secondPoint = secondIter.next(); 
     if (firstPoint.equals(secondPoint) || tempCheck[secondPoint.getIndex()]) {
      continue; 
     }
     tempCheck[secondPoint.getIndex()] = true; 
     collinear.addPoint(firstPoint);  
     collinear.addPoint(secondPoint); 
     for (Iterator<Point> thirdIter = points.iterator(); thirdIter.hasNext();) {
      Point thirdPoint = thirdIter.next(); 
      if (collinear.addPoint(thirdPoint)) { 
       tempCheck[thirdPoint.getIndex()] = true; 
      }
     }
     if (!colPointList.contains(collinear))
      colPointList.add(collinear);
    }
   }
   
   checkedCollinear = new boolean[colPointList.size()];
   min = Long.MAX_VALUE;
   ways = 0;
   findMax(0);

   System.out.println(min + " " + ways); 
  }
 }
 
 public static void findMax(int turn) { 
  if (countOne(checkedPoints) == checkedPoints.length) { 
   if (turn == min) { 
    ways = (ways + 1) % MODULE; 
   }
   if (turn < min) { 
    min = turn; 
    ways = 1; 
   } 
   return;
  }
  
  if (turn >= min) return; 
  for (int i = 0; i < colPointList.size(); i++) { 
   if (!checkedCollinear[i]) { 
    colPointList.get(i).setCheck(); 
    checkedCollinear[i] = true; 
    findMax(turn + 1);
    colPointList.get(i).unSetCheck(); 
    checkedCollinear[i] = false; 
   }
  }
 }
 
 public static int countOne(byte[] checked) {
  int count = 0; 
  for (int i = 0; i < checked.length; i++)
   if (checked[i] == 1) count++; 
  return count; 
 }
 
 static class CollinearPoints {
  private List<Point> listOfPoints = new LinkedList<Point>();  
  private byte[] checkedPoints; 
  public CollinearPoints(byte[] checkedPoints) {
   this.checkedPoints = checkedPoints; 
  }
  
  public boolean addPoint(Point p) {
   if (listOfPoints.size() == 0) {
    listOfPoints.add(p);
    return true;
   }
   else if (listOfPoints.size() == 1) { 
    Point firstPoint = listOfPoints.get(0);
    if (!firstPoint.equals(p)) { 
     listOfPoints.add(p);
     return true; 
    }
    return false; 
   }
   else { 
    Point firstPoint = listOfPoints.get(0);
    Point secondPoint = listOfPoints.get(1); 
    if (firstPoint.isCollinear(p) &&  secondPoint.isCollinear(p) && !listOfPoints.contains(p)) {  
     listOfPoints.add(p);
     return true; 
    }
    else 
     return false; 
   }
  }
  
  public void setCheck() {
   for (Iterator<Point> iter = listOfPoints.iterator(); iter.hasNext();) { 
    checkedPoints[iter.next().getIndex()] ++; 
   }
  }
  
  public void unSetCheck() { 
   for (Iterator<Point> iter = listOfPoints.iterator(); iter.hasNext();) {
    checkedPoints[iter.next().getIndex()] --; 
   }
  }
  
  public int size() { 
   return listOfPoints.size(); 
  }
  
  public List<Point> getList() { 
   return listOfPoints; 
  }
  
  public boolean equals(CollinearPoints other) {
   if (other.size() < 2) return false; 
   Point firstPoint = other.getList().get(0); 
   Point secondPoint = other.getList().get(1); 
   
   if (this.listOfPoints.contains(firstPoint) && this.listOfPoints.contains(secondPoint))
    return true;
   else 
    return false;
  }
  
  public String toString() {
   String result = "";
   for (int i = 0; i < listOfPoints.size(); i++) { 
    result += listOfPoints.get(i);
   }
   return result; 
  }
  
 }
 
 static class Point { 
  private int x, y; 
  private int index; 
  public Point(int x, int y, int index) { 
   this.x = x; this.y = y; 
   this.index = index; 
  }
  
  public int getX() { return x;} 
  public int getY() { return y;}
  public int getIndex() { return index;}
  
  public boolean equals(Point other) { 
   return this.x == other.x && this.y == other.y; 
  }
  
  public boolean isCollinear(Point other) { 
   return this.x == other.x || this.y == other.y; 
  }
  
  public String toString() { 
   return "(" + x + "  " + y + ")";
  }
 }
}