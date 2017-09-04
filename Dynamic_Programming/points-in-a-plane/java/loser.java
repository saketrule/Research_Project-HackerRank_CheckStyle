import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Solution {

 
 public static class Line {
  public Line(Point p1, Point p2){
   if(p1.x == p2.x)
   {
    //line is parallel to y-axis 
    this.a =1;
    this.b=0;
   }
   else 
   {
    this.a = ((float)(p1.y - p2.y))/((float)(p1.x - p2.x));
   }
   this.c = -1.0F *(float) p1.y*b - a*p1.x;
  }
  
  public Line (float a, float b, float c){
   this.a = a;
   this.b = b;
   this.c = c;
  }
  
  
  //coeff of x
  public float a;
  //default coeff of y is -1 unless line is parallel to y-axis
  public float b =-1;
  
  public float c;
  
  public int noOfPoints;
  
  @Override
  public int hashCode() {
   final int prime = 31;
   int result = 1;
   result = prime * result + Float.floatToIntBits(a);
   result = prime * result + Float.floatToIntBits(b);
   result = prime * result + Float.floatToIntBits(c);
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
   Line other = (Line) obj;
   if (Float.floatToIntBits(a) != Float.floatToIntBits(other.a))
    return false;
   if (Float.floatToIntBits(b) != Float.floatToIntBits(other.b))
    return false;
   if (Float.floatToIntBits(c) != Float.floatToIntBits(other.c))
    return false;
   return true;
  }

  public boolean passesOverPoint(Point p){
   if ( a*p.x +b*p.y+c ==0.0F)
    return true;
   return false;
  }

 }

 public static class Point {
  int x;
  int y;
  public Point(int x,int y){
   this.x =x;
   this.y = y;
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

 private static Point parse(String s){
  //TODO- use string tokenizer
  int i = s.indexOf(' ');
  return new Point(Integer.parseInt(s.substring(0, i)), Integer.parseInt(s.substring(i+1)));
 }
 /**
  * @param args
  * @throws IOException 
  * @throws NumberFormatException 
  */
 public static void main(String[] args) throws NumberFormatException, IOException {
  // TODO Auto-generated method stub
  Classifier classifier = new Classifier();
  BufferedReader inputReader= new BufferedReader(new InputStreamReader(System.in)); 
  int noOfDataSets= Integer.parseInt(inputReader.readLine());
  
  for (int i=0; i<noOfDataSets; i++){
   int numberOfPoints= Integer.parseInt(inputReader.readLine());
   List<Point> dataPoints = new ArrayList<Point>(numberOfPoints);
   for(int j=0; j<numberOfPoints; j++){
    dataPoints.add(parse(inputReader.readLine()));
   }
   classifier.classify(dataPoints);
  }
  inputReader.close();
 }
 
 public static class Classifier {
  
  private int minima = Integer.MAX_VALUE;
  //ways to achieve minima
  private int noOfWays= 0;
  
  private List<Point> inputPoints;
  private int noOfLines=0;
  private int noOfPoints=0;
  private int maxCollinear;
  //lines sorted by number of points on it; maximum at front
  private ArrayList<Line> lines =null;
  private Map<Line,HashSet<Point>> linesPointsMap = null; 
  //insert based on binary search
  private void addLine(Line l, int left, int right ){
   
   if(right<left){
    lines.add(left, l);
    noOfLines++;
   }
   else{
    int mid = (left+right)/2;
    if (l.noOfPoints<=lines.get(mid).noOfPoints){
     addLine(l,mid+1,right);
    }
    else{
     addLine(l, left, mid-1);
    }
   }
  }
  
  private void constructLines(Point pts[]){
   int i = 0;
   for(i = 0; i < pts.length; i++){
    for(int j=i+1; j<pts.length; j++){
     Line l = new Line(pts[i], pts[j]);
     if (linesPointsMap.get(l)==null){
      // construct point set for Line l
      HashSet<Point> points = new HashSet<Point>();
      points.add(pts[i]);
      points.add(pts[j]);
      for (int k = j+1; k<pts.length; k++){
       if (l.passesOverPoint(pts[k]))
        points.add(pts[k]);
      }
      l.noOfPoints = points.size();
      linesPointsMap.put(l, points);
      addLine(l, 0, noOfLines-1);
     }
    }
   }
   
  }
  
  /*private void classifyHelper(List<Line> remainingLines, int currentRemovals){
   if(currentRemovals>minima)
    return;
   if ((inputPoints.size()==0)){
    if ( (currentRemovals==minima))
     noOfWays++;
    else if (currentRemovals<minima){
     noOfWays =1;
     minima = currentRemovals;
    }
   }
   else{
    for (int i=0; i<remainingLines.size(); i++){
     Line l = remainingLines.remove(i);
     Set<Point> removedPoints = removePointsforLine(l);
     classifyHelper(remainingLines, currentRemovals+1);
     
     addPointsforLine(removedPoints);
     remainingLines.add(i, l);
    }
   }
   
  }*/
  
  //returns minimum possible turns to empty the point set
  public int  classify(List<Point> points){
   inputPoints = points;
   noOfLines=0;
   noOfWays=0;
   noOfPoints = points.size();
   minima= Integer.MAX_VALUE;
   lines = new ArrayList<Line>();
   linesPointsMap = new HashMap<Line, HashSet<Point>>();
   
   constructLines(points.toArray(new Point[points.size()]));
   //classifyHelper(lines, 0);
   noOfWays = 0;
   maxCollinear = lines.get(0).noOfPoints;
   getMinima(maxCollinear);
   System.out.println(minima + " "+ noOfWays);
   return minima;
  }
  /*private Set<Point> removePointsforLine(Line l){
   HashSet<Point> removedPoints = new HashSet<Point>();
   for (Point p: linesPointsMap.get(l)){
    if (inputPoints.remove(p))
     removedPoints.add(p);
   }
   return removedPoints;
  }
  
  private void addPointsforLine(Set<Point> pointSet){
   for (Point p: pointSet){
    inputPoints.add(p);
   }
  }*/
  
  private void getMinimaHelper(int k, int removedSoFar, List<Point> removedPoints,  int index[], int turns ){
   if(turns>minima)
    return;
   if ((inputPoints.size()==0)){
    if ( (turns==minima))
     noOfWays++;
    else if (turns<minima){
     noOfWays =1;
     minima = turns;
    }
   }
   else if (k >0){
    if (k==removedSoFar && ifCollinear(removedPoints)){
     int offset = noOfPoints - inputPoints.size()-1;
     for(int j=k; j>0; j--){
      inputPoints.remove(index[j+offset]);
     }
     int removalSetNewSize = maxCollinear<=inputPoints.size() ? maxCollinear : inputPoints.size();
     for(int j=removalSetNewSize; j>=0; j--){
      getMinimaHelper(j, 0, new ArrayList<Point>(j), index, turns+1);
     }
     for(int j=1; j<=k; j++){
      inputPoints.add(index[j+offset], removedPoints.get(j-1));
     }
     
    }
    else if (removedSoFar < k){
     int offset = noOfPoints - (inputPoints.size() - removedSoFar)-1;
     
     int begin = removedSoFar==0? 0: (offset >=0 ? (index[offset] +1) : 0);
     for(int j=begin; j<inputPoints.size(); j++){
      removedPoints.add(inputPoints.get(j));
      index[offset+1] =j;
      getMinimaHelper(k, removedSoFar+1, removedPoints, index, turns);
      removedPoints.remove(removedSoFar);
     }
    }
    
   }
  }
  
  private void getMinima(int k ){
   int index[] = new int[noOfPoints];
   for (int i=k; i>0; i--){
    getMinimaHelper(i, 0, new ArrayList<Point>(maxCollinear), index, 0);
   }
  }
  
  private boolean ifCollinear(List<Point> points){
   if (points.size() <3 )
     return true;
   Line l = new Line(points.get(0), points.get(1));
   for (int i=2; i<points.size(); i++){
    if (!l.passesOverPoint(points.get(i)))
     return false;
   }
    
   return true; 
  }
  

 }


}