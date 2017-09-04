import java.util.*;

public class Roads {
 private static class Edge implements Comparable<Edge> {
  int v1;
  int v2;

  int c;

  Edge(int v1, int v2, int c) {
   this.v1 = v1;
   this.v2 = v2;
   this.c = c;
  }

  @Override
  public int compareTo(Edge o) {
   return this.c - o.c;
  }
 }

 private static class Vertex {
  List<Integer> v = new ArrayList<>();
  List<Integer> c = new ArrayList<>();
 }

 private static class DisjointSet extends HashSet<Integer> {
 }

 private static int search(int root, int parent, Vertex[] vertices, long[] count) {
  int total = 1;

  for (int i = 0; i < vertices[root].v.size(); i++) {
   int v = vertices[root].v.get(i);
   int c = vertices[root].c.get(i);

   if (v != parent) {
    int k = search(v, root, vertices, count);
    total += k;
    count[c] = (long) k * (long) (vertices.length - k);
   }
  }
  return total;
 }

 public static void main(String[] args) {
  Scanner in = new Scanner(System.in);

  int n = in.nextInt();

  int m = in.nextInt();

  Edge[] edges = new Edge[m];

  for (int i = 0; i < m; i++) {
   edges[i] = new Edge(in.nextInt() - 1, in.nextInt() - 1, in.nextInt());
  }

  Arrays.sort(edges);

  DisjointSet[] ds = new DisjointSet[n];

  for (int i = 0; i < n; i++) {
   ds[i] = new DisjointSet();
   ds[i].add(i);
  }

  Vertex[] vertices = new Vertex[n];
  for (int i = 0; i < n; i++) {
   vertices[i] = new Vertex();
  }

  for (Edge e : edges) {
   int v1 = e.v1;
   int v2 = e.v2;
   int c = e.c;

   if (ds[v1] != ds[v2]) {
    vertices[v1].v.add(v2);
    vertices[v1].c.add(c);

    vertices[v2].v.add(v1);
    vertices[v2].c.add(c);

    if (ds[v1].size() > ds[v2].size()) {
     int t = v1;
     v1 = v2;
     v2 = t;
    }

    ds[v2].addAll(ds[v1]);

    DisjointSet p = ds[v1];
    for (int v : p) {
     ds[v] = ds[v2];
    }
   }
  }

  long[] count = new long[m];
  search(0, -1, vertices, count);

  StringBuilder result = new StringBuilder();
  long ost = 0;
  for (int i = 0; i < count.length; i++) {
   ost += count[i];
   result.append(ost & 1);
   ost >>= 1;
  }
  while (ost != 0) {
   result.append(ost & 1);
   ost >>= 1;
  }

  while (result.charAt(result.length() - 1) == '0') {
   result.setLength(result.length() - 1);
  }

  System.out.println(result.reverse());
 }
}