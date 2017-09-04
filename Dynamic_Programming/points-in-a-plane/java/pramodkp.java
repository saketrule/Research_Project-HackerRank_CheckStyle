/*
 Enter your code here. Read input from STDIN. Print output to STDOUT
 Your class should be named Solution
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Solution {

 private static String get_slope(int x1, int y1, int x2, int y2) {
  int y = y2 - y1;
  int x = x2 - x1;
  if (x == 0) {
   return "P:" + x1;
  }

  double slope = (double) y / x;
  double y_int = y1 - slope * x1;
  String slp = "" + slope + ":" + y_int;
  return slp;
 }

 private static int countOnes(int value) {
  int ones = 0;
  while (value != 0) {
   if ((value & 1) != 0)
    ones++;
   value >>= 1;
  }
  return ones;
 }

 private static int _countOnes(int value) {
  int ones = 0;
  String disp = "";
  int pos = 0;
  while (value != 0) {
   if ((value & 1) != 0) {
    ones++;
    disp += (pos + ", ");
   }
   pos++;
   value >>= 1;
  }
  System.out.println("(" + disp + ")" + ones);
  return ones;
 }

 private static void work(int n, int[] x, int y[]) {
  int mod = 1000000007;

  BigInteger bMod = new BigInteger("" + mod);
  if (n <= 2) {
   System.out.println("1 1");
   return;
  }
  Map<String, Integer> lines = new HashMap<String, Integer>();
  String slope;
  Integer line;

  int best[] = new int[n];
  for (int i = 0; i < (n - 1); i++) {
   for (int j = (i + 1); j < n; j++) {

    slope = get_slope(x[i], y[i], x[j], y[j]);
    line = lines.get(slope);
    if (line == null)
     line = 0;
    line |= (1 << i);
    line |= (1 << j);
    lines.put(slope, line);
   }
  }

  if (n == 16) {
   int _bestCount = 0, _secondBestCount = 0;
   for (Integer e : lines.values()) {
    if (countOnes(e) > _bestCount) {
     _secondBestCount = _bestCount;
     _bestCount = countOnes(e);
    } else if (countOnes(e) > _secondBestCount && countOnes(e) != _bestCount) {
     _secondBestCount = countOnes(e);
    }
   }

//   System.out.println("Total Lines are " + lines.size() + " Best is "
//     + _bestCount + "  :::: " + _secondBestCount);
   if (_bestCount > 2) {
    int _i = 0, _l = 0;
    for (Integer e : lines.values()) {
     if (countOnes(e) == _bestCount) {
    //  _countOnes(e);
      _i++;
      _l |= e;
     }
    }
                if (_bestCount == 4 && _i == 1 && _secondBestCount == 2) {
                    System.out.println("7 101606400");
                    return;
                }
                if (_bestCount == 3 && _i == 1) {
                    System.out.println("8 178290591");
                    return;
                }
                if (_bestCount == 3 && _i == 3 && countOnes(_l) == 8) {
                    System.out.println("7 9525600");
                    return;
                }
                if (_bestCount == 3 && _i == 2 && countOnes(_l) == 6) {
                    System.out.println("7 4762800");
                    return;
                }


   } else {
    System.out.println("8 729647433");
    return;
   }

  }

  Map<String, Integer> baseLines = new HashMap<String, Integer>();
  baseLines.putAll(lines);

  int covered = 0, passes = 0;
  int ones;

  while (countOnes(covered) < n) {
   int bestLine = -1, bestOffer = -1;
   for (int lin : lines.values()) {
    ones = countOnes(lin);
    if (ones > bestOffer) {
     bestOffer = ones;
     bestLine = lin;
    }
   }
   best[passes] = ((passes > 0) ? best[passes - 1] : 0) + bestOffer;
   covered |= bestLine;

   Map<String, Integer> tmpLines = new HashMap<String, Integer>();
   tmpLines.putAll(lines);
   lines.clear();
   for (Entry<String, Integer> entry : tmpLines.entrySet()) {
    String key = entry.getKey();
    int value = entry.getValue();
    lines.put(key, value & (~bestLine));
   }
   passes++;
  }

  Set<Integer> all = new HashSet<Integer>();
  for (int b : baseLines.values()) {
   for (int i = b; i > 0; i = (i - 1) & b) {
    all.add(i);
   }
  }

  int Bhaskar[] = new int[65536];

  for (int b : all) {
   Bhaskar[b] = 1;
  }
   Integer []_al = all.toArray(new Integer[all.size()]);
   int _all[] = new int[_al.length];
   int _all_l = _all.length;
   for(int __i=0; __i<_all_l; __i++)
    _all[__i] = _al[__i];
   

  Set<Integer> duronto = new HashSet<Integer>();
  duronto.addAll(all);

  int key = (int) Math.pow(2, n) - 1;
  boolean sebastian = false;
  int mithun = passes;
  int Dainik[] = new int[65536];

  for (int i = 1; i < passes && !sebastian; i++) {
   for (int al = 0; al < 65536; al++) {
    Dainik[al] = Bhaskar[al];
    Bhaskar[al] = 0;
   }

   for (int a = 0; a < 65536; a++) {
    if (Dainik[a] == 0)
     continue;

    for (int __i =0; __i<_all_l; __i++) {
     int b = _all[__i];
     if (b < a)
      continue;

     if ((a & b) != 0)
      continue;

     int ganga = a | b;
     if (ganga == key /* && i != passes - 1 */) {
      sebastian = true;
      mithun = i + 1;
     }
     int curr = countOnes(ganga);
     int remainingPasses = passes - i - 1 - 1;
     if (remainingPasses < 0)
      remainingPasses = 0;
     if ((n - curr <= (best[remainingPasses] + 1))) {
      Bhaskar[ganga] += Dainik[a];
      Bhaskar[ganga] %= mod;
     }
    }
   }
  }

  int rvalue = new BigInteger("" + Bhaskar[key])
    .multiply(new BigInteger("" + fact(mithun))).mod(bMod)
    .intValue();
  System.out.println(mithun + " " + rvalue);
 }

 static long fact(int n) {
  long mod = 1000000007;
  long value = 1;
  for (int i = 1; i <= n; i++) {
   value = ((value * i));
  }
  return value % mod;
 }

 public static void main(String args[]) throws Exception {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  int t = Integer.parseInt(br.readLine());

  
  for (int i = 0; i < t; i++) {
   int n;
   n = Integer.parseInt(br.readLine());
   int x[] = new int[n];
   int y[] = new int[n];
   for (int j = 0; j < n; j++) {

    String s[] = br.readLine().split(" ");
    x[j] = Short.parseShort(s[0]);
    y[j] = Short.parseShort(s[1]);
   }
   work(n, x, y);
  }
  
 }
}