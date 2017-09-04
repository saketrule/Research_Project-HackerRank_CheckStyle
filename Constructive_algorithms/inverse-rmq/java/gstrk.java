import java.util.*;

public class Gon {

 public static void main(String[] args) {
  Scanner sc = new Scanner(System.in);
  int n = sc.nextInt();
  int[] a = new int[2 * n];
  Map<Integer, Boolean> map = new HashMap<>();
  for (int i = 1; i < 2 * n; i++) {
   a[i] = sc.nextInt();
   map.put(a[i], true);
  }
  Nod root = new Nod();
  root.v = 1;
  if (map.keySet().size() < n)
   System.out.println("NO");
  else {
   Queue<Nod> q = new LinkedList<Nod>();
   q.add(root);
   for (int i = 2; i < 2 * n; i++) {
    Nod cur = q.poll();
    Nod lft = new Nod();
    lft.v = a[i];
    cur.left = lft;
    q.add(lft);
    i++;
    Nod rght = new Nod();
    rght.v = a[i];
    cur.right = rght;
    q.add(rght);
   }

   swap(root);
   // levord(root); // remove
   Map<Integer, Boolean> mapp = new HashMap<>();
   try {
    updv(root, mapp);
    System.out.println("YES");
    levord(root);
   } catch (Exception e) {
    System.out.println("NO");
   }
  }
 }

 private static void levord(Nod root) {
  StringBuilder sb = new StringBuilder();
  Queue<Nod> q = new LinkedList<Nod>();
  q.add(root);
  while (q.peek() != null) {
   Nod rt = q.poll();
   sb.append(rt.v + " ");
   q.add(rt.left);
   q.add(rt.right);
  }
  System.out.println(sb.toString().trim());
 }

 private static void updv(Nod root, Map<Integer, Boolean> map) throws Exception {
  if (root.left == null)
   return;
  else {
   updv(root.left, map);
   updv(root.right, map);
   if (root.left.left == null) {
    if (map.get(root.left.v) == null && map.get(root.right.v) == null) {
     root.v = Math.min(root.left.v, root.right.v);
     map.put(root.left.v, true);
     map.put(root.right.v, true);
    } else {
     if (map.get(root.left.v) != null && map.get(root.right.v) == null)
     {
      root.left.v = root.v;
      map.put(root.left.v, true);
     }
     else if (map.get(root.right.v) != null && map.get(root.left.v) == null) {
      int temp = root.right.v;
      root.right.v = root.left.v;
      root.left.v = root.v;
      map.put(root.v, true);
     } else {
      throw new Exception();
     }
    }
   } else {
    root.v = Math.min(root.left.v, root.right.v);
   }
  }
 }

 private static void swap(Nod root) {

  if (root.left == null)
   return;

  if (root.left.v > root.right.v) {
   Nod temp = root.left;
   root.left = root.right;
   root.right = temp;
  }

  swap(root.left);
  swap(root.right);
 }

 static class Nod {
  int v;
  Nod left;
  Nod right;
 }

}