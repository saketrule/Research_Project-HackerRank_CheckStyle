import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;

public class TurnOffTheLights {
    public static void main(String[] args) {
        FasterScanner sc = new FasterScanner();
        
        int N = sc.nextInt();
        int K = sc.nextInt();
        long[] A = sc.nextLongArray(N);
        
        
        
        long best = Long.MAX_VALUE;
        
        int dx = 2 * K + 1;
        for (int i = 0; i <= K && i < N; i++) {
         long cost = A[i];
         for (int j = i + dx; ; j += dx) {
          if (j >= N) {
           if (j - N >= K) {
            best = Math.min(best, cost);
           }
           break;
          }
          cost += A[j];
         }
        }
        
        System.out.println(best);
        
        
        
        
//        long[][] dp = new long[N][K];
//        for (int i = 0; i < N; i++) {
//         Arrays.fill(dp[i], Long.MAX_VALUE);
//        }
        
//        if (K + 1 >= N) {
//         long best = Long.MAX_VALUE;
//         for (long c : A) {
//          best = Math.min(best, c);
//         }
//         System.out.println(best);
//         return;
//        }
//        
//        long[] dp = new long[N];
//        Arrays.fill(dp, Long.MAX_VALUE);
//        Arrays.fill(dp, 0, K + 1, 0);
//        
//        for (int i = 0; i < N; i++) {
//         long nextCost = dp[i] + A[i];
//         for (int j = i; j <= i + K && j < N; j++) {
//          dp[j] = Math.min(dp[j], nextCost);
//         }
//            
////            System.out.println(Arrays.toString(dp));
//        }
//        
//        System.out.println(dp[N - 1]);
    }
    
 public static class FasterScanner {
  private byte[] buf = new byte[1024];
  private int curChar;
  private int numChars;

  public int read() {
   if (numChars == -1)
    throw new InputMismatchException();
   if (curChar >= numChars) {
    curChar = 0;
    try {
     numChars = System.in.read(buf);
    } catch (IOException e) {
     throw new InputMismatchException();
    }
    if (numChars <= 0)
     return -1;
   }
   return buf[curChar++];
  }

  public String nextLine() {
   int c = read();
   while (isSpaceChar(c))
    c = read();
   StringBuilder res = new StringBuilder();
   do {
    res.appendCodePoint(c);
    c = read();
   } while (!isEndOfLine(c));
   return res.toString();
  }

  public String nextString() {
   int c = read();
   while (isSpaceChar(c))
    c = read();
   StringBuilder res = new StringBuilder();
   do {
    res.appendCodePoint(c);
    c = read();
   } while (!isSpaceChar(c));
   return res.toString();
  }

  public long nextLong() {
   int c = read();
   while (isSpaceChar(c))
    c = read();
   int sgn = 1;
   if (c == '-') {
    sgn = -1;
    c = read();
   }
   long res = 0;
   do {
    if (c < '0' || c > '9')
     throw new InputMismatchException();
    res *= 10;
    res += c - '0';
    c = read();
   } while (!isSpaceChar(c));
   return res * sgn;
  }

  public int nextInt() {
   int c = read();
   while (isSpaceChar(c))
    c = read();
   int sgn = 1;
   if (c == '-') {
    sgn = -1;
    c = read();
   }
   int res = 0;
   do {
    if (c < '0' || c > '9')
     throw new InputMismatchException();
    res *= 10;
    res += c - '0';
    c = read();
   } while (!isSpaceChar(c));
   return res * sgn;
  }
         
     public int[] nextIntArray(int n) {
         int[] arr = new int[n];
         for (int i = 0; i < n; i++) {
             arr[i] = nextInt();
         }
         return arr;
     }
        
  public long[] nextLongArray(int n) {
      long[] arr = new long[n];
      for (int i = 0; i < n; i++) {
          arr[i] = nextLong();
      }
      return arr;
  }

     private boolean isSpaceChar(int c) {
   return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
  }

  private boolean isEndOfLine(int c) {
   return c == '\n' || c == '\r' || c == -1;
  }
 }
}