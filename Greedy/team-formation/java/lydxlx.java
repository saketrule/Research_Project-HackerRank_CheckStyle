import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Solution {

 public static void main(String[] args) throws Exception {
  Reader.init(System.in);
  BufferedWriter cout = new BufferedWriter(new OutputStreamWriter(System.out));

  for (int T = Reader.nextInt(); T > 0; T--) {
   int n = Reader.nextInt();
   if (n == 0) {
    cout.write("0\n");
    continue;
   }

   Map<Integer, Integer> map = new TreeMap<>();
   for (int i = 0; i < n; i++) {
    int num = Reader.nextInt();
    if (!map.containsKey(num))
     map.put(num, 0);
    map.put(num, map.get(num) + 1);
   }

   ArrayDeque<Integer> queue = new ArrayDeque<>();
   int pre = Integer.MIN_VALUE;
   int ans = Integer.MAX_VALUE;
   for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
    if (!queue.isEmpty() && pre + 1 < entry.getKey()) {
     ans = Math.min(ans, pre - queue.getLast() + 1);
     queue.clear();
    }

    if (queue.isEmpty()) {
     for (int i = 0; i < entry.getValue(); i++)
      queue.add(entry.getKey());
    } else {
     if (queue.size() > entry.getValue()) {
      int len = queue.size() - entry.getValue();
      for (int i = 0; i < len; i++) {
       ans = Math.min(ans, pre - queue.getFirst() + 1);
       queue.removeFirst();
      }
     } else if (queue.size() < entry.getValue()) {
      int len = entry.getValue() - queue.size();
      for (int i = 0; i < len; i++)
       queue.addLast(entry.getKey());
     }
    }
    pre = entry.getKey();
   }
   if (!queue.isEmpty())
    ans = Math.min(ans, pre - queue.getLast() + 1);

   cout.write(String.format("%d%n", ans));
  }

  cout.close();
 }

 static class Pair<U extends Comparable<U>, V extends Comparable<V>> implements Comparable<Pair<U, V>> {
  final U _1;
  final V _2;

  private Pair(U key, V val) {
   this._1 = key;
   this._2 = val;
  }

  public static <U extends Comparable<U>, V extends Comparable<V>> Pair<U, V> instanceOf(U _1, V _2) {
   return new Pair<U, V>(_1, _2);
  }

  @Override
  public String toString() {
   return _1 + " " + _2;
  }

  @Override
  public int hashCode() {
   int res = 17;
   res = res * 31 + _1.hashCode();
   res = res * 31 + _2.hashCode();
   return res;
  }

  @Override
  public int compareTo(Pair<U, V> that) {
   int res = this._1.compareTo(that._1);
   if (res < 0 || res > 0)
    return res;
   else
    return this._2.compareTo(that._2);
  }

  @Override
  public boolean equals(Object obj) {
   if (this == obj)
    return true;
   if (!(obj instanceof Pair))
    return false;
   Pair<?, ?> that = (Pair<?, ?>) obj;
   return _1.equals(that._1) && _2.equals(that._2);
  }
 }

 /** Class for buffered reading int and double values */
 static class Reader {
  static BufferedReader reader;
  static StringTokenizer tokenizer;

  /** call this method to initialize reader for InputStream */
  static void init(InputStream input) {
   reader = new BufferedReader(new InputStreamReader(input));
   tokenizer = new StringTokenizer("");
  }

  /** get next word */
  static String next() throws IOException {
   while (!tokenizer.hasMoreTokens()) {
    // TODO add check for eof if necessary
    tokenizer = new StringTokenizer(reader.readLine());
   }
   return tokenizer.nextToken();
  }

  static int nextInt() throws IOException {
   return Integer.parseInt(next());
  }

  static double nextDouble() throws IOException {
   return Double.parseDouble(next());
  }
 }
}