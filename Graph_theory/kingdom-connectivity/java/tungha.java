import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.*;

/**
 * solve1: www.interviewstreet.com/challenges/dashboard/#problem/4e14b83d5fd12
 * solve2: www.interviewstreet.com/challenges/dashboard/#problem/4f7272a8b9d15
 * solve3: www.interviewstreet.com/challenges/dashboard/#problem/4f904e704e404
 * solve4: www.interviewstreet.com/challenges/dashboard/#problem/4f40dfda620c4
 * @author tunght
 */
public class Solution {

 private static void solve1(Scanner reader, Writer writer) throws IOException {
  int n = reader.nextInt();
  int k = reader.nextInt();
  long total = 0;
  Set<Long> numbers = new HashSet<Long>();
  Map<Long, Set<Long>> diff = new HashMap<Long, Set<Long>>();
  for (int i = 0; i < n; i++) {
   long number = reader.nextLong();
   long num = number - k;
   if (numbers.contains(num)) {
    if (diff.containsKey(num)) {
     Set<Long> set = diff.get(num);
     if (!set.contains(number)) {
      total++;
      set.add(number);
     }
    } else {
     total++;
     Set<Long> set = new HashSet<Long>();
     set.add(number);
     diff.put(num, set);
    }
   }
   num = number + k;
   if (numbers.contains(num)) {
    if (diff.containsKey(number)) {
     Set<Long> set = diff.get(number);
     if (!set.contains(num)) {
      total++;
      set.add(num);
     }
    } else {
     total++;
     Set<Long> set = new HashSet<Long>();
     set.add(num);
     diff.put(number, set);
    }
   }
   numbers.add(number);
  }
  writer.write(total + "\n");
 }

 private static Map<Long, Integer> getElements(long n) {
  Map<Long, Integer> map = new HashMap<Long, Integer>();
  if (n == 0) {
   return map;
  }
  if (n % 2 == 0) {
   map.put(2L, 0);
   while (n % 2 == 0) {
    map.put(2L, map.get(2L) + 1);
    n = n / 2;
   }
  }
  if (n % 3 == 0) {
   map.put(3L, 0);
   while (n % 3 == 0) {
    map.put(3L, map.get(3L) + 1);
    n = n / 3;
   }
  }
  long div = 5;
  int add = 2;
  long sqrt = (long) Math.sqrt(n + 1);
  while (div <= sqrt) {
   boolean change = false;
   if (n % div == 0) {
    change = true;
    map.put(div, 0);
    while (n % div == 0) {
     map.put(div, map.get(div) + 1);
     n = n / div;
    }
   }
   div += add;
   add = 6 - add;
   if (change) {
    sqrt = (long) Math.sqrt(n + 1);
   }
  }
  if (n > 1) {
   map.put(n, 1);
  }
  return map;
 }

 private static long getD(long n1, long n2) {
  long d = n1;
  if (n1 > n2) {
   n1 = n2;
   n2 = d;
   d = n1;
  }
  if (d == 0) {
   return n2;
  }
  while (n2 % n1 != 0) {
   n1 = n2 % n1;
   n2 = d;
   d = n1;
  }
  return n1;
 }

 private static void checkIn(long div, Set<Long> divs, List<Long> elems) {
  if (divs.contains(div)) {
   return;
  }
  divs.add(div);
  for (long elem : elems) {
   if (elem >= div) {
    return;
   }
   if (div % elem == 0) {
    checkIn(div / elem, divs, elems);
   }
  }
 }

 private static void solve2(Scanner reader, Writer writer) throws IOException {
  Set<Long> inp = new HashSet<Long>();
  int n = reader.nextInt();
  long k = reader.nextLong();
  List<Long> input = new ArrayList<Long>();
  for (int i = 0; i < n; i++) {
   long num = reader.nextLong();
   if (num == 0) {
    writer.write(0 + "\n");
    return;
   } else if (num < 0) {
    num = -num;
   }
   long d = getD(num, k);
   if (d == k) {
    writer.write(0 + "\n");
    return;
   }
   if (d > 1 && !inp.contains(d)) {
    inp.add(d);
    input.add(d);
   }
  }
  Map<Long, Integer> elements = getElements(k);
  List<Long> elems = new ArrayList<Long>(elements.keySet());
  Collections.sort(elems);
//  Collections.sort(input);
  long result = 1L;
  for (long elem : elems) {
   result *= elements.get(elem) + 1;
  }
  result--;
  Set<Long> divs = new HashSet<Long>();
  for (int count = input.size(); count > 0; count--) {
   long div = input.get(count - 1);
   checkIn(div, divs, elems);
   if (divs.size() == result) {
    break;
   }
  }
  writer.write((result - divs.size()) + "\n");
 }

 private static void test2() {
  Map<Long, Integer> divides = getElements(103400790234500L);
  for (long key : divides.keySet()) {
   System.out.println(key + " " + divides.get(key));
  }
 }

 private static final int MAX_WEIGHT = 10000000;

 private static long compute3() {
  long total = 0L;
  int len = tree.length;
  int[] income = new int[len];
  int[] childs = new int[len];
  int[] que = new int[len];
  int size = 0;
  for (int i = 0; i < len; i++) {
   childs[i] = tree[i].size();
   if (leaves[i]) {
    income[i] = comps[i] ? MAX_WEIGHT : 0;
    que[size++] = i;
   }
  }
  int current = 0;
  while (true) {
   int range = size;
   for (; current < range; current++) {
    int fnode = father[que[current]];
    childs[fnode] = childs[fnode] - 1;
    if (childs[fnode] == 0) {
     que[size++] = fnode;
     if (comps[fnode]) {
      for (int node : tree[fnode].keySet()) {
       int weight = tree[fnode].get(node);
       total += comps[node] ? weight : Math.min(income[node], weight);
      }
      income[fnode] = MAX_WEIGHT;
     } else {
      int max = -1;
      for (int node : tree[fnode].keySet()) {
       int weight = tree[fnode].get(node);
       if (comps[node]) {
        total += weight;
        if (weight > max) {
         max = weight;
        }
       } else {
        int min = Math.min(weight, income[node]);
        total += min;
        if (min > max) {
         max = min;
        }
       }
      }
      if (max > 0) {
       total -= max;
       income[fnode] = max;
      }
     }
    }
   }
   if (size == len) {
    break;
   }
  }
  return total;
 }

 static Map<Integer, Integer>[] tree;
 static boolean[] comps;
 static boolean[] leaves;
 static int[] father;

 private static void solve3(Scanner reader, Writer writer) throws IOException {
  int n = reader.nextInt();
  int k = reader.nextInt();
  tree = new Map[n];
  for (int i = 0; i < n; i++) {
   tree[i] = new HashMap<Integer, Integer>();
  }
  father = new int[n];
  for (int i = 0; i < n - 1; i++) {
   father[i + 1] = i + 1;
   int node1 = reader.nextInt();
   int node2 = reader.nextInt();
   int weight = reader.nextInt();
   tree[node1].put(node2, weight);
   tree[node2].put(node1, weight);
  }
  comps = new boolean[n];
  for (int i = 0; i < k; i++) {
   comps[reader.nextInt()] = true;
  }
  int[] que = new int[n];
  leaves = new boolean[n];
  int size = 1;
  int current = 0;
  while (true) {
   int range = size;
   for (; current < range; current++) {
    int node = que[current];
    if (tree[node].isEmpty()) {
     leaves[node] = true;
    } else {
     for (int child : tree[node].keySet()) {
      tree[child].remove(node);
      father[child] = node;
      que[size++] = child;
     }
    }
   }
   if (current == size) {
    break;
   }
  }
  writer.write(compute3() + "\n");
 }

 private static boolean infinite = false;
 private static final int SOLVE_4_MODULE = 1000000000;
 private static final long SOLVE_4_MAX = 1L << 50;
 private static boolean[] s4CurrPath;
 private static long[] s4Connect;
 private static boolean[] s4Used;
 private static S4Entry[] s4OutCome[];

 static class S4Entry {

  int weight;
  int node;

  public S4Entry(int node, int weight) {
   this.node = node;
   this.weight = weight;
  }

 }

 private static long getConnect(int node) {
  if (infinite) {
   return -1;
  }
  if (s4Connect[node] > 0) {
   return s4Connect[node];
  }
  s4CurrPath[node] = true;
  if (s4OutCome[node] != null) {
   for (S4Entry entry : s4OutCome[node]) {
    int nnode = entry.node;
    if (s4Used[nnode]) {
     if (s4CurrPath[nnode]) {
      infinite = true;
      break;
     }
     s4Connect[node] += getConnect(nnode) * entry.weight;
     if (s4Connect[node] >= SOLVE_4_MAX) {
      s4Connect[node] = s4Connect[node] % SOLVE_4_MODULE;
     }
    }
   }
  }
  s4CurrPath[node] = false;
  return s4Connect[node];
 }

 private static void solve4(Scanner reader, Writer writer) throws IOException {
  int n = reader.nextInt();
  int m = reader.nextInt();
  Map<Integer, Integer>[] outcome = new Map[n + 1];
  Set<Integer>[] income = new Set[n + 1];
  for (int i = 0; i < m; i++) {
   int x = reader.nextInt();
   int y = reader.nextInt();
   if (outcome[x] != null) {
    int count = 1;
    if (outcome[x].containsKey(y)) {
     count += outcome[x].get(y);
    }
    outcome[x].put(y, count);
   } else {
    outcome[x] = new HashMap<Integer, Integer>();
    outcome[x].put(y, 1);
   }
   if (income[y] != null) {
    income[y].add(x);
   } else {
    income[y] = new HashSet<Integer>();
    income[y].add(x);
   }
  }
  s4OutCome = new S4Entry[n + 1][];
  for (int i = 0; i < n + 1; i++) {
   if (outcome[i] == null) {
    s4OutCome[i] = null;
    continue;
   }
   s4OutCome[i] = new S4Entry[outcome[i].size()];
   int count = 0;
   for (int node : outcome[i].keySet()) {
    s4OutCome[i][count++] = new S4Entry(node, outcome[i].get(node));
   }
  }
  s4Used = new boolean[n + 1];
  s4Used[n] = true;
  int[] next = new int[n];
  next[0] = n;
  int size = 1;
  int current = 0;
  while (current < size) {
   int range = size;
   for (; current < range; current++) {
    int node = next[current];
    if (income[node] == null) {
     continue;
    }
    for (int nnode : income[node]) {
     if (!s4Used[nnode]) {
      s4Used[nnode] = true;
      next[size++] = nnode;
     }
    }
   }
  }
  if (!s4Used[1]) {
   writer.write("0\n");
   return;
  }
  s4CurrPath = new boolean[n + 1];
  s4Connect = new long[n + 1];
  s4Connect[n] = 1L;
  long result = getConnect(1);
  writer.write(!infinite ? result % SOLVE_4_MODULE + "\n" : "INFINITE PATHS\n");
 }

 public static void main(String args[]) throws IOException {
  Scanner reader = new Scanner(System.in);
  Writer writer = new OutputStreamWriter(System.out);
//  solve1(reader, writer);
//  solve2(reader, writer);
//  solve3(reader, writer);
  solve4(reader, writer);
  reader.close();
  writer.close();
 }

}