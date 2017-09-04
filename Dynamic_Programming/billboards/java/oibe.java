import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.io.IOException;

public class Solution 
{
 private void solve()
        {
                Scanner sc = new Scanner(System.in);
                String[] vals = sc.nextLine().split(" ");
  final int n = Integer.parseInt(vals[0]);
  final int k = Integer.parseInt(vals[1]);
  final int a[] = new int[n];
  for (int i = 0; i < n; i++) {
   a[i] = Integer.parseInt(sc.nextLine().trim());
  }

   final long[] board = new long[n+1];

   for (int i = 0; i < n; i ++) 
                        {
    board[i+1] = board[i] + a[i];
   }
   final PriorityQueue<Mapping> heap = new PriorityQueue<Mapping>();
   heap.offer(new Mapping(board[n], n + 1));
   Mapping last = new Mapping(board[n-1], n);
   heap.offer(last);
   long res = 0;
   for (int i = n -1 ; i >= 0; i--) 
                        {

    while(heap.peek().index > i + k + 1) 
                                    heap.poll();
    final long max = heap.peek().value - board[i];
    if (max > res) 
                                    res = max;
    last = new Mapping(max + (i > 0 ? board[i - 1] : 0), i);
    heap.offer(last);
   }
                        System.out.println(res);
  }
 

 class Mapping implements Comparable<Mapping>
        {
  long value;
  int index;
  public Mapping(final long value, final int index) {
   this.value = value;
   this.index = index;
  }

  public int compareTo(final Mapping other) {
   return (int) (other.value - this.value);
  }
 }


 public static void main(final String[] args) throws IOException 
        {
  new Solution().run();
 }

 private void run() throws IOException 
        {

  solve();

 }
}