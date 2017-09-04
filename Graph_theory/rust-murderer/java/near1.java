import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Solution {
 public static void main(String[] args) throws IOException,
   NumberFormatException {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedOutputStream bos = new BufferedOutputStream(System.out);
  byte[] eolb = System.getProperty("line.separator").getBytes();

  int test = Integer.parseInt(br.readLine().trim());
  for (int k = 0; k < test; k++) {
   int n, m;
   String[] values = br.readLine().trim().split(" ");
   n = Integer.parseInt(values[0]);
   m = Integer.parseInt(values[1]);
   HashMap<Integer, ArrayList<Integer>> hashMap = new HashMap<Integer, ArrayList<Integer>>();
   int arr[] = new int[n];
   for (int j = 0; j < m; j++) {
    values = br.readLine().trim().split(" ");
    int val1 = Integer.parseInt(values[0]) - 1;
    int val2 = Integer.parseInt(values[1]) - 1;
    if(val1 != val2){
     insert(hashMap, val1, val2);
     insert(hashMap, val2, val1);
     arr[val1]++;
     arr[val2]++;
    }
   }
   StringBuilder sb = new StringBuilder();
   int s = Integer.parseInt(br.readLine().trim()) - 1;
   for (int i = 0; i < n; i++) {
    int ans = 1;
    if (i != s) {
     if (arr[i] != 0 && arr[s] != 0) {
      ArrayList<Integer> list = hashMap.get(s);
      ans = list.contains(i) ? 2 : 1;
     }
     sb.append(String.valueOf(ans) + " ");
    }

   }
   bos.write(sb.toString().trim().getBytes());
   bos.write(eolb);

  }
  bos.flush();

 }
 
 public static void insert(HashMap<Integer, ArrayList<Integer>> hashMap, int val1, int val2){
  if(hashMap.get(val1) != null){
   ArrayList<Integer> list = hashMap.get(val1);
   list.add(val2);
   hashMap.put(val1, list);
  }else{
   ArrayList<Integer> list = new ArrayList<Integer>();
   list.add(val2);
   hashMap.put(val1, list);
  }
 }

}