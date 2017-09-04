import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution {
 public static void main(String[] args) throws Exception {

  Scanner scan = new Scanner(System.in);

  int N = scan.nextInt();
  long start = System.currentTimeMillis();

  //BruteForce b = new BruteForce();
  OnePass b = new OnePass();
  for (int i = 0; i < N; i++) {
   List<Integer> list = new ArrayList<Integer>();
   int members = scan.nextInt();
   for (int j = 0; j < members; j++) {
    list.add(scan.nextInt());
   }
   int count = list.isEmpty() ? 0 : b.solve(list);
   System.out.println(count);
  }
  //System.out.println(System.currentTimeMillis() - start);

 }

}
class OnePass {
 
 public static int solve(List<Integer> list) {
  Collections.sort(list);
        Map<Integer, PriorityQueue<Integer>> sizes = new HashMap<Integer, PriorityQueue<Integer>>();
        for (int i = 0; i < list.size(); ++i) {
            int size = getSize(sizes, list.get(i) - 1);
            addSize(sizes, list.get(i), size + 1);
        }

        Integer result = null;
        for (PriorityQueue<Integer> q : sizes.values()) {
            Integer s = q.peek();
            if (s != null) {
                if (result == null || result.compareTo(s) > 0) {
                    result = s;
                }
            }
        }
        return (result);
 }
 
    private static void addSize(Map<Integer, PriorityQueue<Integer>> sizes, Integer i, Integer size) {
        PriorityQueue<Integer> q = sizes.get(i);
        if (q == null) {
            q = new PriorityQueue<Integer>();
            sizes.put(i, q);
        }
        q.add(size);
    }

    private static int getSize(Map<Integer, PriorityQueue<Integer>> sizes, Integer i) {
        PriorityQueue<Integer> q = sizes.get(i);
        if (q == null || q.peek() == null) {
            return 0;
        }
        return q.poll();
    }
}