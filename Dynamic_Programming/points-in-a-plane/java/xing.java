import java.io.*;
import java.util.*;
import java.util.Map.*;
import java.math.BigInteger;

public class Solution {

  private static String get_slope(int x1, int y1, int x2, int y2) {
  int y = y2-y1;
  int x = x2-x1;
  if(x == 0) {
   return "P:" + x1;
  }

  double slope = (double)y / x;
  double y_int = y1 - slope * x1;
  String slp =  "" + slope + ":" + y_int;
  return slp;
  }

  private static int countOnes(int value) {
 int ones = 0;
 while(value !=0) {
  if((value & 1) != 0)
   ones++;
  value >>= 1;
 }
 return ones;
  }

  private static int _countOnes(int value) {
 int ones = 0;
 String disp="";
 int pos = 0;
 while(value !=0) {
  if((value & 1) != 0) {
   ones++;
   disp+= ( pos + ", ") ; 
  }
  pos++;
  value >>= 1;
 }
 System.out.println("(" + disp + ")" +   ones);
 return ones;
  }
 

  private static void work(int n, int[]x, int y[]) {
 int mod = 1000000007;
 BigInteger bMod = new BigInteger("" + mod);
 if(n <= 2) {
  System.out.println("1 1");
  return;
 }
 Map<String, Integer> lines = new HashMap<String, Integer>();
 String slope;
 Integer line;

 int best[] = new int[n];
 for(int i=0; i<(n-1); i++) {
  for(int j=(i+1); j<n; j++) {
   
   slope = get_slope(x[i], y[i], x[j], y[j]);
   line = lines.get(slope);
   if(line == null)
    line = 0;
   line |= ( 1 << i);
   line |= ( 1 << j);
   lines.put(slope, line);
  }
 }

 Map<String, Integer> baseLines = new HashMap<String, Integer>();
 baseLines.putAll(lines);


 int covered = 0, passes = 0;
 int ones;
 
 while(countOnes(covered) < n) {
  int bestLine=-1, bestOffer = -1;
  for(int lin : lines.values()) {
   ones = countOnes(lin);
   if(ones > bestOffer) {
    bestOffer = ones;
    bestLine = lin;
   }
  }
  best[passes]=((passes>0)?best[passes-1]:0) + bestOffer;
  covered|=bestLine;
 
  Map<String, Integer> tmpLines = new HashMap<String, Integer>();
  tmpLines.putAll(lines);
  lines.clear();
  for(Entry<String, Integer> entry : tmpLines.entrySet()) {
   String key = entry.getKey();
   int value = entry.getValue();
   lines.put(key, value & (~bestLine));
  }
  passes++;
 }

 Set<Integer> all = new HashSet<Integer>();
 for(int b : baseLines.values()) {
  for(int i=b; i>0; i=(i-1)&b) {
   all.add(i);
  }
 }

// System.out.println("Line Count " + all.size() + " ::: " + baseLines.size());

 Map<Integer, Integer> bhaskar = new HashMap<Integer, Integer>();
 Integer current;
 for(int b : all) {
  bhaskar.put(b, 1);
 }

 Set<Integer> duronto = new HashSet<Integer>();
 duronto.addAll(all);

 int key = (int) Math.pow(2, n) - 1; 
 boolean sebastian = false; 
 int mithun = passes;
 for(int i=1; i<passes && !sebastian; i++) {
  HashMap<Integer, Integer> dainik = new HashMap<Integer, Integer>();
  dainik.putAll(bhaskar);
  bhaskar.clear();

  for(Entry<Integer, Integer> chintu : dainik.entrySet()) {
   int a = chintu.getKey();

   for(int b : all) {
    if(b < a)
     continue;

    if( (a&b) != 0 )
     continue;

    int ganga = a | b;
    if(ganga == key && i != passes - 1) {
     sebastian = true;
     mithun = i+1;
    }
    int curr = countOnes(ganga);
    int remainingPasses = passes -i - 1 - 1;
    if(remainingPasses < 0)
     remainingPasses = 0;
    if((n - curr <= ( best[remainingPasses]  + 1)) ) {
     int tunga = chintu.getValue();
     Integer dam = bhaskar.get(ganga);
     if(dam == null) {
      bhaskar.put(ganga, tunga%mod);
     } else {
      bhaskar.put(ganga, (dam + tunga)%mod);
     }

    }
   }
  }
 }


 int rvalue = new BigInteger("" + bhaskar.get(key)).multiply(new BigInteger("" + fact(mithun))).mod(bMod).intValue();
 System.out.println(mithun + " " + rvalue );
  }
 

  static long fact(int n) {
  long mod = 1000000007;
  long value = 1;
  for(int i=1; i<=n;i++){
   value = ((value * i));
  }
  return value % mod;
 }

  public static void main(String args[]) throws Exception {
 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 int t = Integer.parseInt(br.readLine());

 for(int i=0; i<t; i++) {
  int n;
  n = Integer.parseInt(br.readLine());
  int x[] = new int[n];
  int y[] = new int[n];
  for(int j=0; j<n; j++) {

   String s[] = br.readLine().split(" ");
   x[j] = Short.parseShort(s[0]);
   y[j] = Short.parseShort(s[1]);
  }
  work(n, x, y);
 }
  }
}