import java.io.DataInputStream;
import java.io.InputStream;

public class Solution {
 
 int n, k;
 long sum;
 int[] v;
 SegmentTreeLong tree;
 
 private void solve() throws Exception {
  InParser inp = new InParser(System.in);
  
  n = inp.nextInt();
  k = inp.nextInt();
  
  v = new int[n+1];
  for(int i = 0; i < n; i++) {
   v[i] = inp.nextInt();
   sum += v[i];
  }
  
  tree = new SegmentTreeLong(n+1) {
   long union(long a, long b) {
    return (a > b) ? a : b;
   }
  };

  for(int i = 0; i <= n; i++) {
   if(i <= k) {
    tree.update(i, sum-v[i]);
   } else {
    long x = tree.get(i-k-1, i);
    tree.update(i, x-v[i]);
   }
  }
  
  System.out.println(tree.get(n, n+1));
  System.out.flush();
 }
 
 public static void main(String[] args) throws Exception {
  Solution m = new Solution();
  m.solve();
 }
 
 abstract class SegmentTreeLong {

     abstract long union(long a, long b);

     long[] tree;
     int n;
     
     public SegmentTreeLong(int n) {
      this.n = n;
      tree = new long[n*4+1];
     }

     public SegmentTreeLong(int n, long[] v) {
      this(n);
         build(v, 1, 0, n);
     }

     public long get(int l, int r) {
         return get(l, r, 1, 0, n);
     }

     public void update(int pos, long newval) {
         update(pos, newval, 1, 0, n);
     }
     
     private void build(long[] v, int p, int l, int r) {
         if (l + 1 == r) {
             tree[p] = v[l];
         } else {
             int m = (l + r) >> 1;
             build (v, p*2,   l, m);
             build (v, p*2+1, m, r);
             tree[p] = union(tree[p*2], tree[p*2+1]);
         }
     }

     private long get(int l, int r, int p, int tl, int tr) {
         if (l == tl && r == tr) {
             return tree[p];
         }
         int m = (tl + tr) / 2;
         if (r <= m) {
             return get(l, r, p*2, tl, m);
         }
         if (l >= m) {
             return get(l, r, p*2+1, m, tr);
         }
         return union(get(l, m, p*2, tl, m), get(m, r, p*2+1, m, tr));
     }

     private void update(int pos, long newval, int p, int l, int r) {
         if (l + 1 == r) {
             tree[p] = newval;
         } else {
             int m = (l + r) / 2;
             if (pos < m) {
                 update(pos, newval, p*2, l, m);
             } else {
                 update(pos, newval, p*2+1, m, r);
             }
             tree[p] = union(tree[p*2], tree[p*2+1]);
         }
     }
 }

 class InParser {
  
    final private int BUFFER_SIZE = 1 << 17;

     private DataInputStream din;
     private byte[] buffer;
     private int bufferPointer, bytesRead;

     public InParser(InputStream in) {
      din = new DataInputStream(in);
      buffer = new byte[BUFFER_SIZE];
      bufferPointer = bytesRead = 0;
     }
      
     public String nextString() throws Exception {
      StringBuffer sb = new StringBuffer( "" );
      byte c = read();
      while( c <= ' ' ) {
       c = read();
      }
      do {
       sb.append( (char) c );
       c = read();
      } while( c > ' ' );
      return sb.toString();
     }
      
     public char nextChar() throws Exception {
      byte c = read();
      while( c <= ' ' ) {
       c = read();
      }
      return (char) c;
     }
      
     public int nextInt() throws Exception {
      int ret = 0;
      byte c = read();
      while( c <= ' ' ) {
       c = read();
      }
      boolean neg = c == '-';
      if (neg) {
       c = read();
      }
      do {
       ret = ret * 10 + c - '0';
       c = read();
      } while( c > ' ' );
      if( neg ) {
       return -ret;
      }
      return ret;
     }
      
     public long nextLong() throws Exception {
      long ret = 0;
      byte c = read();
      while( c <= ' ' ) {
       c = read();
      }
      boolean neg = c == '-';
      if( neg ) {
       c = read();
      }
      do {
       ret = ret * 10 + c - '0';
       c = read();
      } while( c > ' ' );
      if( neg ) {
       return -ret;
      }
      return ret;
     }
      
     private void fillBuffer() throws Exception {
      bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
      if( bytesRead == -1 ) {
       buffer[0] = -1;
      }
     }
      
     private byte read() throws Exception {
      if( bufferPointer == bytesRead ) {
       fillBuffer();
      }
      return buffer[bufferPointer++];
     }
 }
}