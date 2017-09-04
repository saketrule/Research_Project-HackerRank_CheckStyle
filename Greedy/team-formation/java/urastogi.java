import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;


public class Solution {
 static BufferedReader in = new BufferedReader(new InputStreamReader(
          System.in));

 /**
  * @param args
  * @throws IOException
  */
 public static void main(String[] args) throws IOException {
  
  int num = Integer.parseInt(in.readLine());
  while(num > 0) {
  String str = in.readLine();
  String[] line = str.split(" ");
  Map<Integer,PriorityQueue<Integer>> sizes = new HashMap<>();
  Integer[] members = new Integer[Integer.parseInt(line[0])];
     int j=0;
  for(int i=1; i < line.length; i++) {
   members[j] = Integer.parseInt(line[i]);
        j++;
  }
  Arrays.sort(members);
  for(int i=0;i< members.length ;i++) {
   int size = getSize(sizes,members[i]-1);
   addSize(sizes,members[i],size+1);
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
        if(result == null)
        System.out.println(0);
        else
         System.out.println(result);
  num--;
  }
 }

 private static void addSize(Map<Integer, PriorityQueue<Integer>> sizes,
   Integer key, int i) {
  
  PriorityQueue<Integer> pr = sizes.get(key);
  if(pr == null) {
   pr = new PriorityQueue<>();
  } 
  pr.add(i);
  sizes.put(key, pr);  
 }

 private static int getSize(Map<Integer, PriorityQueue<Integer>> sizes,
   int i) {
  PriorityQueue<Integer> val = sizes.get(i);
  if(val == null || val.peek() == null) {
   return 0;
  }
  return val.poll();
 }


}