import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class GraphH {

 public static void main(String[] args) {
  List<List<Integer>> s = new ArrayList<List<Integer>>();
  List<Integer> l = new ArrayList<Integer>();
  Map<Integer, List<Integer>> g = new HashMap<Integer, List<Integer>>();
  s.add(l);
  String Char;
  int si, ni, a, b;
  si = ni = 0;
  Scanner input = new Scanner(System.in);
  long p = input.nextLong();
  while (p-- > 0) {
   Char = input.next();
   switch (Char) {
   case "A":
    a = input.nextInt();
    l = new ArrayList<>();
    while (a-- > 0)
     l.add(++ni);
    s.add(l);
    si++;
    break;
   case "B":
    a = input.nextInt();
    b = input.nextInt();
    for (int i1 : s.get(a)) {
     for (int i2 : s.get(b)) {
      if (!g.containsKey(i1))
       g.put(i1, new ArrayList());
      if (!g.containsKey(i2))
       g.put(i2, new ArrayList());
      l = g.get(i1);
      l.add(i2);
      g.put(i1, l);
      l = g.get(i2);
      l.add(i1);
      g.put(i2, l);
     }
    }
    break;
   case "C":
    a = input.nextInt();
    boolean flag[] = new boolean[ni + 1];
    Stack<Integer> stck = new Stack<>();
    for (int item : s.get(a)) {
     stck.push(item);
     l = new ArrayList<>();
     while (!stck.isEmpty()) {
      a = stck.pop();
      flag[a] = true;
      l.add(a);
      if (g.containsKey(a)) {
       for (int val : g.get(a)) {
        if (!flag[val])
         stck.push(val);
       }
      }
     }
    }
    s.add(l);
    si++;
    break;

   }
  }

  // finding independent nodes;
  List<Integer> Min = new ArrayList<>(), taken = new ArrayList<>(), ke = new ArrayList<>();
  int t = 0, j = 0, m = Integer.MAX_VALUE;
  while (!g.isEmpty()) {
   Min = new ArrayList<>();
   j = 0;
   m = Integer.MAX_VALUE;
   ke = new ArrayList<>(g.keySet());
   for (int key : ke) {
    if (taken.contains(key)) {
     g.remove(key);
     continue;
    }
    if (g.get(key).size() < m) {
     Min = g.get(key);
     m = Min.size();
     j = key;
    }
   }
   for (int nd : Min) {
    if (!taken.contains(nd))
     taken.add(nd);
   }
   if (g.containsKey(j))
    g.remove(j);
   t++;
  }
  System.out.println(t);
 }
}