import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Solution {


 public static void main(String[] args) throws IOException {
  BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
  String temp = bufferRead.readLine();
  int numOfTestCases=Integer.parseInt(temp);
  List<TestCase> testCases=new ArrayList<TestCase>();
  for(int i=0;i<numOfTestCases;i++){
   int numOfPoints=Integer.parseInt(bufferRead.readLine());
   List<Point> pointsInPlane=new ArrayList<Point>();
   for (int j = 0; j < numOfPoints; j++) {
    temp=bufferRead.readLine();
    String[] input=temp.split(" ");
    Point p=new Point();
    p.setX(Integer.parseInt(input[0]));
    p.setY(Integer.parseInt(input[1]));
    pointsInPlane.add(p);
   }
   TestCase tc=new TestCase();
   tc.setNumOfPoints(numOfPoints);
   tc.setPointsInPlane(pointsInPlane);
   testCases.add(tc);
  }
  for (TestCase testCase : testCases) {
   int numOfPoints=testCase.getNumOfPoints();
   List<Point> pointsInPlane=testCase.getPointsInPlane();
   List<Line> linesInPlane=new ArrayList<Line>();
   List<String> collPoints=new ArrayList<String>();
   for(int i=0;i<numOfPoints;i++){
    for(int j=i+1;j<numOfPoints;j++){
     Point point1=pointsInPlane.get(i);
     Point point2=pointsInPlane.get(j);
     if(pointsOnAnyLine(linesInPlane,point1.getPointName(),point2.getPointName())){
      continue;
     }
     List<String> pointsOnLine=new ArrayList<String>();
     pointsOnLine.add(point1.getPointName());
     pointsOnLine.add(point2.getPointName());
     float slope1=getSlope(point1, point2);
     for(int k=j+1;k<numOfPoints;k++){
      Point point3=pointsInPlane.get(k);
      float slope2=getSlope(point1, point3);
      if(slope2==slope1){
       pointsOnLine.add(point3.getPointName());
      }
     }
     if(pointsOnLine.size()>=3){
      Line line=new Line();
      line.setPointsOnLine(pointsOnLine);
      linesInPlane.add(line);
      for(String pointName:line.getPointsOnLine()){
       collPoints.add(pointName);
      }
     }
    }
   }
   long nonColPoints=0;
   for(Point point:pointsInPlane){
    if(!collPoints.contains(point.getPointName())){
     nonColPoints++;
    }
   }
   //System.out.println("nonColPoints="+nonColPoints);
   long minTermsForNonColPoints=nonColPoints/2+nonColPoints%2;
   //System.out.println("minTermsForNonColPoints="+minTermsForNonColPoints);
   
   long noOfwaysForNonColPoints=1;
   for(long i=nonColPoints;i>=2;i=i-2){
    noOfwaysForNonColPoints=noOfwaysForNonColPoints*ncr(i,2);
    //System.out.println("noOfwaysForNonColPoints="+noOfwaysForNonColPoints);
   }
   
   noOfwaysForNonColPoints=noOfwaysForNonColPoints*minTermsForNonColPoints;
   //System.out.println("noOfwaysForNonColPoints="+noOfwaysForNonColPoints);
   long minTermsForColPoints=linesInPlane.size();
   //System.out.println("minTermsForColPoints1="+minTermsForColPoints);
   long noOfwaysForColPoints=0;
   if(minTermsForColPoints!=0){
    noOfwaysForColPoints=factorial(minTermsForColPoints); 
   }
   if(nonColPoints%2==1){
    noOfwaysForColPoints=noOfwaysForColPoints*2+(numOfPoints-nonColPoints)*2;
   }
   //System.out.println("minTermsForColPoints2="+minTermsForColPoints);
   
   long totalMinTerms=minTermsForNonColPoints+minTermsForColPoints;
   long totalNumOfWays=1;
   if(noOfwaysForColPoints!=0){
    totalNumOfWays=noOfwaysForColPoints;
   }
   if(noOfwaysForNonColPoints!=0){
    totalNumOfWays=totalNumOfWays*noOfwaysForNonColPoints;
   }
   
   
   System.out.println(totalMinTerms+" "+totalNumOfWays%1000000007);
  }
 }

 private static long ncr(long n, long r) {
  long nr=1;
  long dr=1;
  for(long i=r;i>=1;n--,i--){
   nr=nr*n;
  }
  for(;r>1;r--){
   dr=dr*r;
  }
  return nr/dr;
 }

 private static long factorial(long n) {
  long nr=1;
  for(;n>1;n--){
   nr=nr*n;
  }
  return nr;
 }

 private static boolean pointsOnAnyLine(List<Line> linesInPlane,
   String pointName1, String pointName2) {
  boolean point1Exists=false;
  boolean point2Exists=false;
  mainLoop:
  for(Line line:linesInPlane){
   for(String pointOnLine:line.getPointsOnLine()){
    if(pointOnLine.equals(pointName1)){
     point1Exists=true;
    }
    if(pointOnLine.equals(pointName2)){
     point2Exists=true;
    }
    if(point1Exists && point2Exists){
     break mainLoop; 
    }
   }
  }
  return point1Exists && point2Exists;
 }

 private static float getSlope(Point point1, Point point2) {
  float slope;
  int xdiff=(point1.getX()-point2.getX());
  if(xdiff!=0){
   slope=(point1.getY()-point2.getY())/(float)xdiff;
  }else{
   slope=Float.POSITIVE_INFINITY;
   
  }
  return slope;
 }
 
}

class TestCase{
 private int numOfPoints;
 private List<Point> pointsInPlane;
 public int getNumOfPoints() {
  return numOfPoints;
 }
 public void setNumOfPoints(int numOfPoints) {
  this.numOfPoints = numOfPoints;
 }
 public List<Point> getPointsInPlane() {
  return pointsInPlane;
 }
 public void setPointsInPlane(List<Point> pointsInPlane) {
  this.pointsInPlane = pointsInPlane;
 }
}
class Point{
 private int x,y;
 private String pointName;
 public String getPointName() {
  if(pointName==null){
   pointName=x+"_"+y;
  }
  return pointName;
 }
 public int getX() {
  return x;
 }
 public void setX(int x) {
  this.x = x;
 }
 public int getY() {
  return y;
 }
 public void setY(int y) {
  this.y = y;
 }
 
}
class Line{
 List<String> pointsOnLine;
 public List<String> getPointsOnLine() {
  return pointsOnLine;
 }
 public void setPointsOnLine(List<String> pointsOnLine) {
  this.pointsOnLine = pointsOnLine;
 }

 
 
}