import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class ZurikelasGraph {
 private static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
 HashMap<Integer, Node> ids = new HashMap<>();
 HashMap<Integer, Integer> memoa, memob;
 int k = 1;
 boolean konec = false;
 boolean[] used;
 int result = 0;
 
 public int a(int parent, int root) {
  if (konec) used[root] = true;
  else {
   ids.get(root).exists = false;
  }
  
  if (memoa.containsKey(root)) return memoa.get(root);
  
  Node n = ids.get(root);
  int res = n.independent;
  
  if ((n.neighs.size() == 1 && n.neighs.get(0) == parent) || n.neighs.size() == 0) {
   memoa.put(root, res);
   return res;
  }
  
  for (int ch : n.neighs) {
   if (ch != parent) res += b(root, ch);
  }
  memoa.put(root, res);
  return res;
 }
 
 public int b(int parent, int root) {
  if (konec) used[root] = true;
  else {
   ids.get(root).exists = false;
  }
  if (memob.containsKey(root)) return memob.get(root);
  
  Node n = ids.get(root);
  int res = 0;
  
  if ((n.neighs.size() == 1 && n.neighs.get(0) == parent) || n.neighs.size() == 0) {
   memob.put(root, res);
   return res;
  }
  
  for (int ch : n.neighs) {
   if (ch != parent) res += Math.max(b(root, ch), a(root, ch));
  }
  memob.put(root, res);
  return res;
 }
 
 public void join(int root) {
  memoa = new HashMap<>();
  memob = new HashMap<>();
  
  int fn = Math.max(a(-42, root), b(-42, root));

  ids.put(k, new Node(fn));
  
  if (konec) result += fn;
  else k++;
 }
 
 public void solve() throws IOException {
  int q = i();
  
  while (q-- > 0) {
   String[] s = in.readLine().split(" ");
   String type = s[0];
   
   if (type.equals("A")) {
    Node node = new Node(Integer.parseInt(s[1]));
    ids.put(k++, node);
   }
   else if (type.equals("B")) {
    int x = Integer.parseInt(s[1]);
    int y = Integer.parseInt(s[2]);
    ids.get(x).addNeigh(y);
    ids.get(y).addNeigh(x);
   }
   else {
    join(Integer.parseInt(s[1]));
   }
  }
  
  konec = true;
  used = new boolean[k];

  for (int i = 1; i < k; i++) {
   if (!used[i] && ids.get(i).exists) join(i);
  }
  System.out.println(result);
 }

 public static void main(String[] args) throws IOException {
  new ZurikelasGraph().solve();
 }
 
 class Node {
  int independent;
  boolean exists;
  ArrayList<Integer> neighs;
  
  public Node(int ind) {
   independent = ind;
   exists = true;
   neighs = new ArrayList<>();
  }
  
  public void addNeigh(int id) {
   neighs.add(id);
  }
 }
 
 /*
  * INPUT and OUTPUT
  */
 
 static <T> void p(T s) {
  System.out.println(s);
 }
 
 static <T> void pa(T[] a) {
  String r = "";
  for (T e : a) r += e + " ";
  System.out.println(r.substring(0, r.length() - 1));
 }
 
 static int i() throws IOException {
  return Integer.parseInt(in.readLine());
 }
 
 static int[] ia() throws IOException {
  String[] s = in.readLine().split(" ");
  int[] r = new int[s.length];
  for (int i = 0; i < s.length; i++) r[i] = Integer.parseInt(s[i]);
  return r;
 }
 
 static long l() throws IOException {
  return Long.parseLong(in.readLine());
 }
 
 static long[] la() throws IOException {
  String[] s = in.readLine().split(" ");
  long[] r = new long[s.length];
  for (int i = 0; i < s.length; i++) r[i] = Long.parseLong(s[i]);
  return r;
 }
 
 static double d() throws IOException {
  return Double.parseDouble(in.readLine());
 }
 
 static double[] da() throws IOException {
  String[] s = in.readLine().split(" ");
  double[] r = new double[s.length];
  for (int i = 0; i < s.length; i++) r[i] = Double.parseDouble(s[i]);
  return r;
 }
}