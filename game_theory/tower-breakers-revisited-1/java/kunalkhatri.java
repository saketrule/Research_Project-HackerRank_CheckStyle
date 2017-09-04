import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;


import java.io.*;



public class SharingCandies {
 //static String[] str,str2;
    public static void main(String[] args) throws IOException {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
         FasterScanner sc = new FasterScanner();
            BufferedWriter log = new BufferedWriter(new OutputStreamWriter(System.out));
          int t = sc.nextInt();
          int arr[] = new int[1000001];
          arr[1] = 0;
          for(int i = 2;i<=1000000;i++) arr[i] = 1+arr[i/getMinFactor(i)];
          while(t-->0){
           int n = sc.nextInt();
           int ans = 0;
           for(int i =0;i<n;i++) ans^=arr[sc.nextInt()];
           if(ans>0) log.write(1+"\n");
           else log.write("2\n");
          }
          log.close();
    }
    static int getMinFactor(int n){
     for(int i = 2;i*i<=n;i++){
      if(n%i==0) return i;
     }
     return n;
    }
    static int KMPSearch(String pat, String txt)
    {
     int cnt= 0;
        int M = pat.length();
        int N = txt.length();
 
        // create lps[] that will hold the longest
        // prefix suffix values for pattern
        int lps[] = new int[M];
        int j = 0;  // index for pat[]
 
        // Preprocess the pattern (calculate lps[]
        // array)
        computeLPSArray(pat,M,lps);
 
        int i = 0;  // index for txt[]
        while (i < N)
        {
            if (pat.charAt(j) == txt.charAt(i))
            {
                j++;
                i++;
            }
            if (j == M)
            {
                //System.out.println("Found pattern "+
                  //            "at index " + (i-j));
             //b[i-j] = true;
             cnt++;
                j = lps[j-1];
            }
 
            // mismatch after j matches
            else if (i < N && pat.charAt(j) != txt.charAt(i))
            {
                // Do not match lps[0..lps[j-1]] characters,
                // they will match anyway
                if (j != 0)
                    j = lps[j-1];
                else
                    i = i+1;
            }
        }
        return cnt;
    }
static void computeLPSArray(String pat, int M, int lps[])
{
    // length of the previous longest prefix suffix
    int len = 0;
    int i = 1;
    lps[0] = 0;  // lps[0] is always 0

    // the loop calculates lps[i] for i = 1 to M-1
    while (i < M)
    {
        if (pat.charAt(i) == pat.charAt(len))
        {
            len++;
            lps[i] = len;
            i++;
        }
        else  // (pat[i] != pat[len])
        {
            // This is tricky. Consider the example.
            // AAACAAAA and i = 7. The idea is similar 
            // to search step.
            if (len != 0)
            {
                len = lps[len-1];

                // Also, note that we do not increment
                // i here
            }
            else  // if (len == 0)
            {
                lps[i] = len;
                i++;
            }
        }
    }
}
    static int hcf(int n1, int n2)
    {
        if (n2 != 0)
           return hcf(n2, n1%n2);
        else 
           return n1;
    }
    static class Pair2{
     int u;
     int v;
     int z;
     double ans;
     Pair2(int u,double ans,int v,int z){
      this.u = u;
      this.ans = ans;
      this.v = v;
      this.z = z;
     }
    }
    public static int max(int x, int y, int z){
     if(x<=y&&x<=z) return x;
     else if(y<=x&&y<=z) return y;
     else return z;
    }
    static class Pair implements Comparable<Pair> {
  int u;
  int v;
  double dist=0;
  public Pair(){
   
  }
  public Pair(int u, int v) {
   this.u = u;
   this.v = v;
  }
  
  public int hashCode() {
   int hu = (int) (u ^ (u >>> 32));
   int hv = (int) (v ^ (v >>> 32));
   if(hu>hv)
   return 31*hu + hv;
   else return 31*hv+hu;
   // return (int) ((int) u * 31 + v);
  }

  public boolean equals(Object o) {
   Pair other = (Pair) o;
   return (u == other.u && v == other.v)||(u == other.v && v == other.u);
  }

  public int compareTo(Pair other) {
   return Long.compare(u, other.u) != 0 ? Long.compare(u, other.u) : 1;
  }

  public String toString() {
   return "[u=" + u + ", v=" + v + "]";
  }
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
         return nextIntArray(n, 0);
     }
     
     public int[] nextIntArray(int n, int off) {
      int[] arr = new int[n + off];
      for (int i = 0; i < n; i++) {
       arr[i + off] = nextInt();
      }
      return arr;
     }
     
     public long[] nextLongArray(int n) {
      return nextLongArray(n, 0);
     }
        
  public long[] nextLongArray(int n, int off) {
      long[] arr = new long[n + off];
      for (int i = 0; i < n; i++) {
          arr[i + off] = nextLong();
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
    
 private static class OutputWriter
    {
        private final PrintWriter writer;
 
        public OutputWriter(OutputStream outputStream)
        {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
        }
 
        public OutputWriter(Writer writer)
        {
            this.writer = new PrintWriter(writer);
        }
 
        public void print(Object... objects)
        {
            for (int i = 0; i < objects.length; i++)
            {
                if (i != 0)
                    writer.print(' ');
                writer.print(objects[i]);
            }
        }
 
        public void println(Object... objects)
        {
            print(objects);
            writer.println();
        }
 
        public void close()
        {
            writer.close();
        }
 
        public void flush()
        {
            writer.flush();
        }
     }
    }
 