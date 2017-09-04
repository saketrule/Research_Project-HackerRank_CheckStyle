import java.io.*;
import java.util.*;
import java.awt.Point;

public class Main {
 public static void main(String args[]) throws FileNotFoundException {
  InputReader in = new InputReader(System.in);
  PrintWriter out = new PrintWriter(System.out);
  new Solver().solve(in, out);
  out.close();
 }
}

class Matrix{
 int offsetX, offsetY, width, height, circumference;
 Matrix(int offsetX, int offsetY, int width, int height){
  this.offsetX = offsetX;
  this.offsetY = offsetY;
  this.width = width;
  this.height = height;
  circumference = 2*width+2*height-4;
 }

 Matrix subMatrix(){
  return new Matrix(offsetX+1, offsetY+1, width-2, height-2);
 }

 Point rotatePoint(Point point, int amount){
  amount %= circumference;
  if(point.x == minX() && point.y != maxY()){
   int maxAmount = maxY()-point.y;
   if(amount <= maxAmount) return new Point(minX(), point.y+amount);
   else return rotatePoint(new Point(minX(), maxY()), amount-maxAmount);
  }else if(point.x == maxX() && point.y != minY()){
   int maxAmount = point.y-minY();
   if(amount <= maxAmount) return new Point(maxX(), point.y-amount);
   else return rotatePoint(new Point(maxX(), minY()), amount-maxAmount);
  }else if(point.y == minY() && point.x != minX()){
   int maxAmount = point.x-minX();
   if(amount <= maxAmount) return new Point(point.x-amount, minY());
   else return rotatePoint(new Point(minX(), minY()), amount-maxAmount);
  }else if(point.y == maxY() && point.x != maxX()){
   int maxAmount = maxX()-point.x;
   if(amount <= maxAmount) return new Point(point.x+amount, maxY());
   else return rotatePoint(new Point(maxX(), maxY()), amount-maxAmount);
  }
  return null;
 }

 int maxX(){
  return offsetX+width-1;
 }

 int maxY(){
  return offsetY+height-1;
 }

 int minX(){
  return offsetX;
 }

 int minY(){
  return offsetY;
 }

 List<Point> edgePoints(){
  List<Point> res = new ArrayList<>();
  for(int x=0; x<width; x++){
   res.add(new Point(offsetX+x, offsetY));
   res.add(new Point(offsetX+x, offsetY+height-1));
  }
  for(int y=1; y<height-1; y++){
   res.add(new Point(offsetX, offsetY+y));
   res.add(new Point(offsetX+width-1, offsetY+y));
  }
  return res;
 }
}

class Solver{
 int n, m, r;
 int[][] orig, res;

 void read(InputReader in){
  m = in.nextInt(); n = in.nextInt(); r = in.nextInt();
  orig = new int[m][n];
  for(int row=0; row<m; row++){
   for(int column=0; column<n; column++){
    orig[row][column] = in.nextInt();
   }
  }
 }

 public void solve(InputReader in, PrintWriter out){
  read(in);
  
  res = new int[m][n];

  Matrix matrix = null;
  for(int rect = 0; rect < Math.min(n, m)/2; rect++){
   matrix = (matrix == null ? new Matrix(0, 0, n, m) : matrix.subMatrix());

   for(Point point : matrix.edgePoints()){
    Point rotated = matrix.rotatePoint(point, r);
    res[rotated.y][rotated.x] = orig[point.y][point.x];
   }
  }
  for(int i=0; i<m; i++){
   for(int j=0; j<n; j++){
    out.print(res[i][j] + " ");
   }
   out.println();
  }
 }
}

class InputReader {
 public BufferedReader reader;
 String currentLine[];
 int token;

 public InputReader(InputStream stream) {
  reader = new BufferedReader(new InputStreamReader(stream), 32768);
  currentLine = null;
  token = 0;
 }

 public String next(){
  while (currentLine == null || token >= currentLine.length) {
   try {
    currentLine = reader.readLine().trim().split("\\s+");
    token = 0;
   } catch (IOException e) {
    e.printStackTrace();
   }
  }
  return currentLine[token++];
 }

 public int nextInt() {
  return Integer.parseInt(next());
 }
}