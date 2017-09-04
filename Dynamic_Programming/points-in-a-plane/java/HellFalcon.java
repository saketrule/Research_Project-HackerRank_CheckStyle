import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

final class PointsInAPlainSolver {
 
 static final class Operation {

  private static final int FINITE_FIELD_MODULO = 1000000007;

  public static int add(int a, int b) {
   return a + b < FINITE_FIELD_MODULO ? a + b : a + b - FINITE_FIELD_MODULO;
  }

  public static int mul(int a, int b) {
   return (int) (((long) a * (long) b) % FINITE_FIELD_MODULO);
  }
 }

 private int n;
 private int x[];
 private int y[];
 
 public PointsInAPlainSolver(int n, int[] x, int[] y) {
  this.n = n;
  this.x = Arrays.copyOf(x, x.length);
  this.y = Arrays.copyOf(y, y.length);
 }
 
 public void solve() {
  // - edge case handling
  if (n <= 2) {
   System.out.println("1 1");
   return;
  }
  // - general solution
  int setsLength = 0;
  int[] sets = new int[n * (n - 1) >>> 1];
  // - generate sets based on points collinearity
  for (int i = 0; i < n; i++) {
   for (int j = 0; j < i; j++) {
    int set = (1 << i) | (1 << j);
    for (int q = 0; q < n; q++) {
     if (q != i & q != j) {
      if (isCollinear(x[i], x[j], x[q], y[i], y[j], y[q])) {
       set |= 1 << q;
      }
     }
    }
    sets[setsLength++] = set;
   }
  }
  sets = Arrays.copyOf(sets, setsLength);
  // - order sets and remove duplicates
  sets = cleanUpSets(sets);

  // - evaluate minimum number of moves
  final int targetConfig = (1 << n) - 1;
  int[] lcf = new int[targetConfig + 1];
  int[] lpf = new int[targetConfig + 1];
  lcf[0] = 1;
  int turns = 0;
  for (int i = 0; i < n; i++) {
   int[] tf = lpf;
   lpf = lcf;
   lcf = tf;
   for (int j = 0; j <= targetConfig; j++) {
    int flag = lpf[j];
    if (flag > 0) {
     for (int q = 0, len = sets.length; q < len; q++) {
      lcf[j | sets[q]] = 1;
     }
     lpf[j] = 0;
    }
   }
   if (lcf[targetConfig] > 0) {
    turns = i + 1;
    break;
   }
  };

  // - generate additional sets
  for (int t = 1; t < turns; t++) {
   setsLength = sets.length;
   int[] generatedSets = new int [setsLength * (setsLength - 1)];
   int gslen = 0;
   for (int i = 1, len = sets.length; i < len; i++) {
    int seti = sets[i];
    if (Integer.bitCount(seti) > 1) {
     for (int j = 0; j < i; j++) {
      if (Integer.bitCount(sets[j]) > 1 && Integer.bitCount(seti & sets[j]) == 1) {
       int diff = seti & sets[j];
       generatedSets[gslen++] = seti ^ diff;
       generatedSets[gslen++] = sets[j] ^ diff;
      }
     }
    }
   }
   sets = Arrays.copyOf(sets, setsLength + gslen);
   for (int i = 0; i < gslen; i++) {
    sets[setsLength + i] = generatedSets[i];
   }
   // - order sets and remove duplicates
   sets = cleanUpSets(sets);
  }

  // - evaluate requested data
  lcf = new int[targetConfig + 1];
  lpf = new int[targetConfig + 1];
  lcf[0] = 1;
  for (int i = 0; i < (turns + 1) >>> 1; i++) {
   int[] tf = lpf;
   lpf = lcf;
   lcf = tf;
   for (int j = 0; j <= targetConfig; j++) {
    int value = lpf[j];
    if (value > 0) {
     for (int q = 0, len = sets.length; q < len; q++) {
      int set = sets[q];
      if ((j & set) == 0) {
       lcf[j | set] = Operation.add(lcf[j | set], value);
      }
     }
     lpf[j] = 0;
    }
   }
  }

  int[] rcf = new int[targetConfig + 1];
  int[] rpf = new int[targetConfig + 1];
  rcf[0] = 1;
  for (int i = 0; i < turns >>> 1; i++) {
   int[] tf = rpf;
   rpf = rcf;
   rcf = tf;
   for (int j = 0; j <= targetConfig; j++) {
    int value = rpf[j];
    if (value > 0) {
     for (int q = 0, len = sets.length; q < len; q++) {
      int set = sets[q];
      if ((j & set) == 0) {
       rcf[j | set] = Operation.add(rcf[j | set], value);
      }
     }
     rpf[j] = 0;
    }
   }
  }
  int ways = 0;

  for (int j = 0; j < targetConfig; j++) {
   if (rcf[j] > 0 && lcf[targetConfig ^ j] > 0) {
    ways = Operation.add(ways, Operation.mul(rcf[j], lcf[targetConfig ^ j]));
   }
  }

  System.out.println("" + turns + " " + ways);
 }

 private int[] cleanUpSets(int[] sets) {
  Arrays.sort(sets);
  int setsLength = sets.length;
  int dups = 0;
  for (int i = 1; i < setsLength; i++) {
   if (sets[i] == sets[i-dups-1]) {
    dups++;
   }
   sets[i-dups] = sets[i];
  }
  setsLength -= dups;
  return Arrays.copyOf(sets, setsLength);
 }

 private boolean isCollinear(int x0, int x1, int x2, int y0, int y1, int y2) {
  if (x0 == x1) {
   return x1 == x2;
  }
  else if (y0 == y1) {
   return y1 == y2;
  }
  else {
   return (x1 - x0) * (y2 - y0) == (x2 - x0) * (y1 - y0);
  }
 }

}

public class Solution {
 public static void main(String[] args) {
  try {
   BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 4 << 10);
   int testsNumber = Integer.parseInt(br.readLine());
   for (int t = 0; t < testsNumber; t++) {
    int n = Integer.parseInt(br.readLine());
    int[] x = new int[n];
    int[] y = new int[n];
    for (int i = 0; i < n; i++) {
     StringTokenizer tokenizer = new StringTokenizer(br.readLine());
     x[i] = Integer.parseInt(tokenizer.nextToken());
     y[i] = Integer.parseInt(tokenizer.nextToken());
    }
    new PointsInAPlainSolver(n, x, y).solve();
   }
  }
  catch (Exception e) {
   System.err.println("Error:" + e.getMessage());
  }
 }
}