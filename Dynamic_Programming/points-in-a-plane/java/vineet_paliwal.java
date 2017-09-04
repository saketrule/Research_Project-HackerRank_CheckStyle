import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;

public class Solution {

 public class Point {
  int x ;
  int y;
  int i;
  public Point(int xx , int yy,int ii) {
   x = xx;
   y = yy;
   i = ii;
  }
 }

 public class Problem implements Comparable<Problem>{
  BitSet bitSet ;
  int id ;
  int moves;
  long ways;
  public Problem(BitSet b,int i,int m , long w) {
   bitSet = b;
   id = i;
   moves = m;
   ways = w;
  }
  public int compareTo(Problem p ) {
   return new Integer(id).compareTo(p.id);
  }
 }

 static int test;

 static ArrayList<ArrayList<Problem>> tree ;

 static Solution main;

 static long sp;

 static int n;

 static Point [] ar;

 static long[] fact;

 static long [] pow2;
 
 static int[] idHash;

 static ArrayList<ArrayList<ArrayList<Integer>>> mySuperSet;

 public static void main(String[] args) {
  pow2 = new long[17];
  pow2[0] = 1;
  for(int i = 1 ; i < 17 ; i++) {
   pow2[i] = 2 * pow2[i-1];
  }
  mySuperSet = new ArrayList<ArrayList<ArrayList<Integer>>>();
  for(int i = 0 ; i < 17 ; i++) {
   ArrayList<Integer> list = new ArrayList<Integer>();
   for(int j = 0 ; j < i ; j++) {
    list.add(j);
   }
   mySuperSet.add(getSuperSet(list));
  }
  sp = 1000000007;
  fact = new long[11];
  fact[0] = 1;
  for(int i = 1 ; i < 11 ; i++) {
   fact[i] = fact[i-1]*i;
   fact[i] %= sp;
  }
  main = new Solution();
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedOutputStream bos = new BufferedOutputStream(System.out);
  String eol = System.getProperty("line.separator");
  byte[] eolb = eol.getBytes();
  byte[] spaceb = " ".getBytes();
  try {
   String str = br.readLine();
   test = Integer.parseInt(str);
   for(int p = 0 ; p < test ; p++) {
    str = br.readLine();
    n = Integer.parseInt(str);
    tree = new ArrayList<ArrayList<Problem>>(n+1);
    for(int i = 0 ; i <= n ; i++) {
     tree.add(new ArrayList<Problem>());
    }
    generateInitialMaps();
    
    ar = new Point[n];
    for(int j = 0 ; j < n ; j++) {
     str=br.readLine();
     int blank = str.indexOf(" ");
     int x = Integer.parseInt(str.substring(0,blank));
     int y =  Integer.parseInt(str.substring(blank+1));
     ar[j] = main.new Point(x,y,j);
    }
    Problem ans = solve();
    bos.write(new Integer(ans.moves).toString().getBytes());
    bos.write(spaceb);
    long myWays = ans.ways * fact[ans.moves];
    myWays %= sp;
    bos.write(new Long(myWays).toString().getBytes());
    bos.write(eolb);
   }
   bos.flush();
  }catch(IOException ioe) {
   ioe.printStackTrace();
  }
 }

 public static void generateInitialMaps() {
  BitSet zero = new BitSet(n);
  zero.clear();
  tree.get(0).add(main.new Problem(zero,0,0,1));
  int pow = new Double(Math.pow(2, n)).intValue();
  idHash = new int[pow];
  idHash[0] = 0;
  for(int i = 1 ; i < pow ; i++) {
   BitSet set = new BitSet(n);
   int x = i;
   for(int j = 0 ; j < n ; j++) {
    if((x%2)==1) {
     set.set(j);
    } else {
     set.clear(j);
    }
    x/=2;
   }
   int card = set.cardinality();
   idHash[i] = tree.get(card).size();
   tree.get(card).add(main.new Problem(set,i,1,1)); 
  }
 }

 public static Problem solve() {
  for(int i = 3 ; i <= n ; i++) {
   ArrayList<Problem> myTree = tree.get(i);
   Iterator<Problem> iterator = myTree.iterator();
   while(iterator.hasNext()) {
    Problem problem = iterator.next();
    int moves = 1000;
    long ways = 0;
    ArrayList<Point> points = new ArrayList<Point>();
    for(int j = 0 ; j < n ; j++) {
     if(problem.bitSet.get(j)) {
      points.add(ar[j]);
     }
    }
    boolean [] done = new boolean[points.size()];
    for(int j = 0 ; j < points.size() ; j++) {
     done[j] = false;
    }
    for(int j = 1 ; j < points.size() ; j++) {
     if(!done[j]) {
      done[j] = true;
      ArrayList<Integer> notOnline = new ArrayList<Integer>();
      ArrayList<Integer> online = new ArrayList<Integer>();
      online.add(points.get(j).i);
      for(int k = 1 ; k < points.size() ; k++) {
       if(j!=k) {
        if(!isCollinear(points.get(0),points.get(j),points.get(k))) {
         notOnline.add(points.get(k).i);
        }  else {
         online.add(points.get(k).i);
         done[k] = true;
        }
       }
      }
      if(notOnline.size()==0) {
       ways = 1;
       moves = 1;
      }  else {
       ArrayList<ArrayList<Integer>> superSet = mySuperSet.get(online.size());
       int buffer = 0;
       for(int k = 0 ; k < notOnline.size(); k++) {
        buffer += pow2[notOnline.get(k)];
       }
       for(int rs = 0 ;rs < superSet.size() ; rs++) {
        ArrayList<Integer> mySet = superSet.get(rs);
        int id = buffer;
        for(int k = 0 ; k < mySet.size() ; k++) {
         id += pow2[online.get(mySet.get(k))];
        } 
        int card = notOnline.size()+mySet.size();
        Problem ans = tree.get(card).get(idHash[id]);
        if(moves>(ans.moves+1)) {
         moves = ans.moves + 1;
         ways = ans.ways;
        } else if ( moves == (ans.moves + 1)) {
         ways += ans.ways;
        }
       }
      }
     }
    }

    BitSet smallProblem = new BitSet(n);
    smallProblem.clear();
    for(int k = 1 ; k < points.size(); k++) {
     smallProblem.set(points.get(k).i);
    }
    int id = 0;
    for(int k = n-1 ; k >= 0 ; k--) {
     id*=2;
     if(smallProblem.get(k)) {
      id++;
     }
    }
    Problem ans = tree.get(smallProblem.cardinality()).get(idHash[id]);

    if(moves>(ans.moves+1)) {
     moves = ans.moves + 1;
     ways = ans.ways;
    } else if ( moves == (ans.moves + 1)) {
     ways += ans.ways;
    }
    ways %= sp;
    problem.moves = moves;
    problem.ways = ways;
   }  
  }
  Problem returnAns = tree.get(n).get(0);
  return returnAns;
 }

 public static boolean isCollinear(Point p1 , Point p2 , Point p3 ) {
  long x1 = p1.x;
  long x2 = p2.x;
  long x3 = p3.x;
  long y1 = p1.y;
  long y2 = p2.y;
  long y3 = p3.y;
  if(((y3-y2)*(x2-x1)) == ((y2-y1)*(x3-x2)) ) {
   return true;
  }
  return false;
 }

 public static ArrayList<ArrayList<Integer>> getSuperSet(ArrayList<Integer> list) {
  int size = list.size();
  ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
  int pow = new Double(Math.pow(2, size)).intValue();
  for(int i = 0 ; i < pow-1 ; i++) {
   ans.add(new ArrayList<Integer>());
   int x = i;
   for(int j = 0 ; j < size ; j++) {
    if((x%2)==1) {
     ans.get(i-1).add(list.get(j));
    }
    x/=2;
   }
  }
  return ans;
 }

}