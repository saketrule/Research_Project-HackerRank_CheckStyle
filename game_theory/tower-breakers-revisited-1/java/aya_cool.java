import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Tester {
 static int mod = 1000000007;
 static int MAX=1000007;
 public static long factorial[] = new long[2*100001];
 
 public static void main(String[] args) {
  FastScanner in=new FastScanner();
  OutputWriter out=new OutputWriter(System.out);
  
  seive();
  grundy();
  
  int t = in.nextInt();
        while (t-->0){
         int n=in.nextInt();
         int xor=0;
            for (int i=0;i<n;i++)
            {
             xor=xor^grundy[in.nextInt()];
            }
            if (xor==0)
             out.println(2);
            else
             out.println(1);
        }
 
        out.close();
 }
 static int lowestPrime[]=new int[MAX];
 static int grundy[]=new int[MAX];
 static void grundy()
 {
  grundy[1]=grundy[0]=0;
  
  for (int i=2;i<MAX;i++)
  {
   if (lowestPrime[i]==0)
    grundy[i]=1;
   else
    grundy[i]=1+grundy[i/lowestPrime[i]];
  }
 }
 static void seive() {
  
  for (int i = 2; i * i <= MAX; i++) {
   if (lowestPrime[i]==0) {
    for (int j = 2; j <= MAX; j++) {
     if (i * j >= MAX)
      break;
     
     if (lowestPrime[i*j]==0)
      lowestPrime[i * j] = i;
    }
   }
  }
 }
 
 

}
class FastScanner {
    private byte[] buf = new byte[1024];
    private int curChar;
    private int snumChars;

    public int read() {
        if (snumChars == -1)
            throw new InputMismatchException();
        if (curChar >= snumChars) {
            curChar = 0;
            try {
                snumChars = System.in.read(buf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (snumChars <= 0)
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
class InputReader {
 public BufferedReader reader;
 public StringTokenizer tokenizer;

 public InputReader(InputStream inputstream) {
  reader = new BufferedReader(new InputStreamReader(inputstream));
  tokenizer = null;
 }

 public String nextLine() {
  String fullLine = null;
  while (tokenizer == null || !tokenizer.hasMoreTokens()) {
   try {
    fullLine = reader.readLine();
   } catch (IOException e) {
    throw new RuntimeException(e);
   }
   return fullLine;
  }
  return fullLine;
 }

 public String next() {
  while (tokenizer == null || !tokenizer.hasMoreTokens()) {
   try {
    tokenizer = new StringTokenizer(reader.readLine());
   } catch (IOException e) {
    throw new RuntimeException(e);
   }
  }
  return tokenizer.nextToken();
 }

 public long nextLong() {
  return Long.parseLong(next());
 }

 public int nextInt() {
  return Integer.parseInt(next());
 }
}

class OutputWriter {
 private final PrintWriter writer;

 public OutputWriter(OutputStream outputStream) {
  writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
 }

 public OutputWriter(Writer writer) {
  this.writer = new PrintWriter(writer);
 }

 public void print(Object... objects) {
  for (int i = 0; i < objects.length; i++) {
   if (i != 0)
    writer.print(' ');
   writer.print(objects[i]);
  }
 }

 public void println(Object... objects) {
  print(objects);
  writer.println();
 }

 public void close() {
  writer.close();
 }

 public void flush() {
  writer.flush();
 }
}