import java.util.*;
public class Solution {
 public static int rank(int key, int[] a){ 
  int lo = 0;
  int hi = a.length - 1;
  
  while (lo <= hi) {
   int mid = lo + (hi - lo) / 2;
   if (key < a[mid]) hi = mid - 1;
   else if (key > a[mid]) lo = mid + 1;
   else return mid;
  }
  return lo;
 }
 
 public static int index = -1;
 public static int maxDiff(int val, int[] a) {
  if (index != -1) {
   if (index < a.length) 
    if (a[index] == val - 1)
     index++;
  } else 
   index = rank(val, a);

  if (index == a.length) 
   return val - a[index - 1];
   
  if (a[index] == val)
   return 0;
  if (index == 0) 
   return a[0] - val;
  
  return Math.min(val - a[index - 1], a[index] - val);
 }
 
 public static void main(String[] args) {
  try (Scanner in = new Scanner(System.in);){
   int n = in.nextInt();
   
   HashSet<Integer> seen = new HashSet<>();
   List<Integer> l = new ArrayList<>(n);
   for (int i = 0; i < n; i++) {
    int tmp = in.nextInt();
    if (seen.contains(tmp))
     continue;
    l.add(tmp);
    seen.add(tmp);
   }
   
   n = l.size();
   int[] a = new int[n];
   for (int i = 0; i < n; i++) 
    a[i] = l.get(i);
    
   Arrays.sort(a);
   int minDiff = Integer.MIN_VALUE;
   int minValue = -1;
   
   int p = in.nextInt();
   
   int pIndex = rank(p, a);
   minValue = p;
   if (pIndex == 0) {
    minDiff = a[0] - p;
   } else {
    minDiff = Math.min(p - a[pIndex - 1], a[pIndex] - p);
    int mid = a[pIndex - 1] + (a[pIndex] - a[pIndex - 1]) / 2;
    if (mid > p) {
     minDiff = (a[pIndex] - a[pIndex - 1]) / 2;
     minValue = mid;
    }
   }
   
   int q = in.nextInt();
   int qIndex = rank(q, a);
   
   while (pIndex + 1 < qIndex) {
    int tmpDiff = (a[pIndex + 1] - a[pIndex]) / 2;
    int mid = a[pIndex] + tmpDiff;
    if (tmpDiff > minDiff) {
     minDiff = tmpDiff;
     minValue = mid;
    }
    pIndex++;
   }
   
   
   
   for (int i = a[pIndex] + 1; i <= q; i++) {
    int tmp = maxDiff(i, a);
    if (tmp > minDiff) {
     minDiff = tmp;
     minValue = i;
    }
   }
   
   System.out.println(minValue);
  }
 }
}