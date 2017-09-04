/* Enter your code here. Read input from STDIN. Print output to STDOUT */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Solution {
 public static void main(String[] args) throws NumberFormatException, IOException {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  System.out.println(solve(br));
 }

 private static long solve(BufferedReader br) throws IOException {
  String line = br.readLine().trim();
  int n = Integer.parseInt(line.split(" ")[0]);
  int k = Integer.parseInt(line.split(" ")[1]);
  long profiles[] = new long[n];
  for (int i = 0; i < n; i ++) {
   profiles[i] = Long.parseLong(br.readLine().trim());
  }
  long[] dp = new long[n+1];
  
  // init the heap
  HeapNode[] nodes = new HeapNode[n];
  for (int i = 0; i < n; i ++) nodes[i] = new HeapNode();
  Heap heap = new Heap(nodes, 0);
  
  for (int i = 0; i <= k && i <= n; i ++) {
   // exclude i = 0
   if (i > 0)
    dp[i] = dp[i-1] + profiles[i-1];
   
   // if i == k, don't insert into the heap
   if (i == k || k >= n) continue;
   
   long v = dp[i];
   for (int j = i + 2; j < k + 1; j ++) {
    v += profiles[j-1];
   }
   heap.insert(i, v);
  }
  long tmp = 0;
  for (int i = k+1; i <= n; i ++) {
   // get the max, remove if it is stale
   HeapNode max = heap.getMax();
   while (max.id<i-k-1) {
    heap.removeMax();
    max = heap.getMax();
   }
   tmp += profiles[i-1];
   dp[i] = Math.max(max.value + tmp, dp[i-1]);
   heap.insert(i-1, dp[i-1]-tmp);
  }
  //printDp(dp);
  return dp[n];
 }
 
// static void printDp(long[] dp) {
//  int i = 1;
//  for (long d: dp) System.out.print(i++ + "(" + d+"),");
//  System.out.println();
// }
}

class Heap {
 HeapNode[] heap = null;
 int size = 0;
 Heap(HeapNode[] heap, int size) {
  this.size = size;
  this.heap = heap;
 }
 
 int heapify(int i) {
  int i1 = i * 2 + 1;
  int i2 = i * 2 + 2;
  if (i2 < size) {
   int max_ind = (heap[i1].value > heap[i2].value)? i1 : i2;
   if (heap[i].value < heap[max_ind].value) {
    swap(heap, i, max_ind);
    return max_ind;
   }
  } else if (i1 < size) {
   if (heap[i].value < heap[i1].value) {
    swap(heap, i, i1);
    return i1;
   }
  }
  return -1;
 }
 
 HeapNode getMax() {
  return heap[0];
 }
 
 void removeMax() {
  heap[0] = heap[size-1];
  heap[size-1] = new HeapNode();
  size --;
  int i = 0;
  while ((i = heapify(i)) >= 0);
 }
 
 void insert(int id, long value) {
  int ind = size;
  size ++;
  heap[ind].id = id;
  heap[ind].value = value;
  while ((ind+1)/2-1 >= 0 && heap[ind].value > heap[(ind+1)/2-1].value) {
   swap(heap, ind, (ind+1)/2-1);
   ind = (ind+1)/2-1;
  }
 }
 
 void build() {
  int i = this.size / 2 - 1;
  for (; i >= 0; i --) {
   heapify(i);
  }
 }

 private void swap(HeapNode[] heap, int i, int j) {
  HeapNode node = heap[i];
  heap[i] = heap[j];
  heap[j] = node;
 }
 
}

class HeapNode {
 int id;
 long value;
}