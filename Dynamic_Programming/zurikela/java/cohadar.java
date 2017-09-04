import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class ZurikelasGraph {
 static long vertices = 0;
 static long result = 0;

 static UnionFind UF;

 static void a(int x) {
  debug('A', x);
  UF.newSet(x);
  vertices += x;
  result += x;
 }

 static void b(int x, int y) {
  debug('B', x, y);
  long cx = (long)UF.cardinality[UF.find(x)];
  long cy = (long)UF.cardinality[UF.find(y)];
  Set<Integer> S = new HashSet<>();
  for (int i = 0; i < UF.size(); i++) {
   S.add(UF.find(i));
  }
  int fx = UF.find(x);
  int fy = UF.find(y);
  int sum = 0;
  for (int u : S) {
   int fu = UF.find(u);
   if (fu != fx && fu != fy) {
    sum += UF.cardinality[u];
   }
  }
  result = sum;
  result += Math.max(cx, cy);
  UF.union(x, y);
 }

 static void c(int x) {
  debug('C', x);
  int k = UF.newSet(0);
  UF.union(k, x);
 }

 public static void main(String[] args) {
  Scanner scanner = new Scanner(System.in);
  int q = Integer.valueOf(scanner.nextLine());
  UF = new UnionFind(1 + q);
  UF.newSet(0);
  for (int i = 0; i < q; i++) {
   String[] command = scanner.nextLine().split(" ");
   char c = command[0].charAt(0);
   switch (c) {
   case 'A':
    a(Integer.valueOf(command[1]));
    break;
   case 'B':
    b(Integer.valueOf(command[1]), Integer.valueOf(command[2]));
    break;
   case 'C':
    c(Integer.valueOf(command[1]));
    break;
   default:
    throw new RuntimeException("bad command: " + command);
   }
  }
  System.out.println(result);
 }

 static boolean DEBUG = false;
 
 static void debug(Object...os) {
  if (!DEBUG) { return; }
  System.err.printf("%.65536s\n", Arrays.deepToString(os));
 }
}

/***/
class UnionFind {
 final int capacity;
 int[] parent;
 int[] cardinality;
 byte[] rank;
 int size;
 int count;
 public UnionFind(int n) {
  if (n < 0) throw new IllegalArgumentException("capacity cannot be negative: " + n);
  this.capacity = n;
  this.count = 0;
  this.size = 0;
  this.parent = new int[n];
  this.cardinality = new int[n];
  this.rank = new byte[n];
 }
 public int newSet(int card) {
  count++;
  int k = size++;
  parent[k] = k;
  rank[k] = 0;
  cardinality[k] = card;
  return k;
 }
 public int find(int index) {
  if (index < 0 || index >= size) throw new IndexOutOfBoundsException("index: " + index);
  if (parent[index] != index) {
   parent[index] = find(parent[index]);
  }
  return parent[index];
 }
 public void union(int a, int b) {
  int pa = find(a);
  int pb = find(b);
  if (pa == pb) return;
  if (rank[pa] > rank[pb]) {
   parent[pb] = pa;
   cardinality[pa] += cardinality[pb];
  } else if (rank[pa] < rank[pb]) {
   parent[pa] = pb;
   cardinality[pb] += cardinality[pa];
  } else {
   parent[pa] = pb;
   cardinality[pb] += cardinality[pa];
   rank[pb]++;
  }
  count--;
 }
 public boolean connected(int a, int b) {
  return find(a) == find(b);
 }
 public int count() {
  return count;
 }
 public int size() {
  return size;
 }   
 public int capacity() {
  return capacity;
 }  
}