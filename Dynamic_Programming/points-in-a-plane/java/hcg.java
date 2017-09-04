/* Enter your code here. Read input from STDIN. Print output to STDOUT */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
public class Solution {
 static Point []points;
 static HashSet<Long> hs;
 static HashSet<Integer> covers;
 static int min, target, num, p;
 static Line[] ls;
 static long[] fac;
 static long[] choose;
 static final long mod = 1000000007;
 static ArrayList<Line> lines;
 static ArrayList<Integer> coveredBy[];
 static HashSet<String> ss;
 public static void main(String[] args) throws FileNotFoundException {
  /*for(int i=0;i<16;i++){
   Random rd = new Random();
   System.out.println(rd.nextInt(100)+" "+rd.nextInt(100));
  }*/
  // Scanner sc = new Scanner(new FileInputStream(new File("input")));
  // long st = System.currentTimeMillis();
  Scanner sc = new Scanner(System.in);
  fac = new long[17];
  for(int i=1; i<=16; i++)
   if(i == 1)
    fac[i] = 1;
   else
    fac[i] = i*(fac[i-1]);
  
  choose = new long[17];
  choose[0] = 1; choose[2] = 1; choose[4] = 3; choose[6] = 15; 
  choose[1] = 1; choose[3] = 3; choose[5] =5*choose[4]; choose[7] = 7*choose[6];
  choose[8] = 105; choose[10] = 945; choose[12] = 10395; choose[14] = 135135;
  choose[9] = 10*choose[8]; choose[11] = 11*choose[10]; choose[13] = 13*choose[12];
  int count = sc.nextInt();
  for(int i = 0; i!=count; i++ ){
   p = sc.nextInt();
   if(p <= 2){ // if the num of points <= 2, there is only one answer
    System.out.println("1 1");
    continue;
   }
   points = new Point[p];
   hs = new HashSet<Long>();
   
   // get all points
   for(int j = 0; j < p; j++){  
    int x = sc.nextInt();
    int y = sc.nextInt();
    points[j] = new Point(x, y, j);
   }
   for(int j=0;j<p-1;j++){
    Point pt1 = points[j];
    for(int k=j+1;k<p;k++){
     Point pt2 = points[k];
     if(j != k){
      // get all lines
      long f = getFinger(pt1.x, pt1.y, pt2.x, pt2.y);
      pt1.lines.add(f);
      pt2.lines.add(f);
      // print((k+1)+":"+(j+1)+" ->"+f);
      hs.add(f);
     }     
    }
   }
   
   // the num of lines, no duplication
   num = hs.size();
   lines = new ArrayList<Line>(num);
   
   // convert HashSet to array and sort it 
   for(long l:hs)
    lines.add(new Line(l));
   for(Line l: lines){
    for(Point pt: points)
     if(pt.lines.contains(l.finger))
      // the points covered by this line
      l.covered |= (1<<pt.num); 
    // count how many points are covered by this line
    l.count();
   }
   Collections.sort(lines);   
   // if one line covers two points only, remove it
   for(int j = num-1; j>=0; j--){
    if(lines.get(j).cnt == 2){
     hs.remove(lines.get(j).finger);
     lines.remove(j);
    }
    else
     break;
   }  
   // the num of lines after we remove the basic lines
   num = lines.size();
   int uniques = 0;
   target = 0;
   for(Point pt: points)
    pt.lines.retainAll(hs);
   for(int j = 0; j<p; j++)
    if(points[j].lines.size()>0)
     target |= (1<<j);
    else
     uniques++;
   ls = new Line[lines.size()];
   lines.toArray(ls);
   
   covers = new HashSet<Integer>();
   for(Line line: ls)
    covers.add(line.covered);
   
   if(lines.size() != 0){
    boolean hasNew = true;    
    while(hasNew == true){
     hasNew = false;
     for(int j=lines.size()-1;j>=0;j--){
      for(int k=j-1;k>=0;k--){
       if(k == j)
        continue;
       Line l1 = lines.get(j);
       Line l2 = lines.get(k);
       int cc = ( l1.covered | l2.covered );
       if(cc != l1.covered && cc !=l2.covered && (l1.covered & l2.covered) > 0){
        int c1 = l1.covered - l1.covered & l2.covered;
        int c2 = l2.covered - l1.covered & l2.covered;      
        if(!covers.contains(c1) && countCovered(c1) > 2){
         Line l = new Line();
         l.covered = c1;
         l.cnt = countCovered(c1);
         lines.add(l);
         covers.add(c1);
         hasNew = true;
        }
        if(!covers.contains(c2) && countCovered(c2) > 2){
         Line l = new Line();
         l.covered = c2;
         l.cnt = countCovered(c2);
         lines.add(l);
         covers.add(c2);
         hasNew = true;
        }                 
       }
      }      
     }
    }
  
    HashSet<Integer> tmp = new HashSet<Integer>();
    for(int j: covers)
     for(int k=0; k<p; k++){
      int c = j - (1<<k);
      if((j & (1<<k)) > 0 && countCovered(c) > 2 && !covers.contains(c))
       tmp.add(c);
     }   
    covers.addAll(tmp);

    min = 9;
    getMin(0, 0, 0);
    min += (uniques+1)/2;
    result = 0;
    coveredBy = new ArrayList[p];
    for(int j = 0; j<p; j++){
     coveredBy[j] = null;
     for(int c: covers)
      if((c & (1<<j)) > 0){
       if(coveredBy[j] == null)
        coveredBy[j] = new ArrayList<Integer>();
       coveredBy[j].add(c);
      }
    }

    ss = new HashSet<String>();
    getResult(0, 0, min, p, 0, "");
   }
   else{
    min = (p+1)/2;
    result = fac[min] * choose[p];
   }
   // System.out.println(printLine(l.covered));
   System.out.println(min + " " + result%mod);
  }
 }
 static long result = 0;
 public static void getResult(int index, int mask, int steps, int left, int pass, String s){
  if(steps * 2 >= left){
   if(!ss.contains(s)){
    ss.add(s);
    result = (result + (fac[min] * choose[left])%mod)%mod; 
    // print("steps:"+steps+" stones:"+left+" covered:"+printLine(mask)+" s:"+s);
   }
  }
  if(steps == 0 || index >= p)
   return; 
  if(coveredBy[index] == null)
   getResult(index+1, mask, steps, left, pass, s);
  else if((mask & (1<<index)) > 0)
   getResult(index+1, mask, steps, left, pass, s);
  else{
   getResult(index+1, mask, steps, left, pass | (1<<index), s);
   int j = 0;
   for(int c: coveredBy[index]){
    j++;
    if((c & mask) == 0 && (c & pass) == 0)
     getResult(index+1, mask | c, steps-1, p - countCovered(mask | c), pass, s+"_"+c);
   }
  }     
 }
 public static int countCovered(int l){
  int cnt = 0;
  for(int i=15; i>=0; i--){
   if((l & (1<<i))>0)
    cnt++;
  }
  return cnt;
 }
 public static void print(String s){
  System.out.println(s);
 } 
 public static void getMin(int index, int mask, int count){
  if(count > min - 1 || index >= num )
   return;
  Line l = ls[index];
  if((mask | l.covered) == mask)
   getMin(index+1, mask, count );
  else{
   if((mask | l.covered) == target){    
    if(count < min-1)
     min = count+1;
    return;
   }    
   getMin(index+1, mask | l.covered, count+1 );
   getMin(index+1, mask, count );
  }   
 }
 public static long getFinger(int x1, int y1, int x2, int y2){
  int a, b;
  if(y2 == y1){
   a = 0; b = y1;
  }
  else if( x2 == x1 ){
   a = x1;
   b = 10000;   
  }
  else{
   a = 10000*(y2-y1)/(x2-x1);
   b = 10000*(y1+y2) - a*(x1+x2);
  }
  return ((long)a<<32)|(long)b;
 }
 public static String printLine(int l){
  String s = "";
  for(int i = 0;i<=15;i++)
   if((l & (1<<i)) > 0)
    s += (i+1)+" ";
  return s;
 }
}

class Point{
 int x, y, num;
 public Point(int x, int y, int num){
  this.x = x;
  this.y = y;
  this.num = num;
  this.lines = new HashSet<Long>();
 }
 HashSet<Long> lines;
}
class Line implements Comparable<Line>{
 long finger;
 int covered;
 int cnt;
 public Line(long l){
  this.finger = l;
  this.covered = 0;
  this.cnt = 0;
 }
 public Line(){
  this.covered = 0;
  this.cnt = 0;
 } 
 public String toString(){
  String s = "";
  for(int i = 0;i<=15;i++)
   if((covered & (1<<i)) > 0)
    s += (i+1)+" ";
  return s;  
 }
 public void count(){
  for(int i=15;i>=0;i--)
   if((covered & (1<<i)) > 0)
    this.cnt++;
 }
 public int compareTo(Line l) {
  return l.cnt - this.cnt;
 }
}