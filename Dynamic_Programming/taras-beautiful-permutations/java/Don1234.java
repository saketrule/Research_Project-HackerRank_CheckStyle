import java.io.*;
import java.math.BigInteger;
import java.util.*;
 
 
public class Main {
 //static int ans[]=new int[1000001];
  public static void main(String[] args) throws IOException {
  InputReader in = new InputReader(System.in);
  PrintWriter pw = new PrintWriter(System.out);
        int t=in.nextInt();
        for(int it=0;it<t;it++)
        {
         int n=in.nextInt();
         int a[]=in.nextIntArray(n);
         int c=0;
         HashSet<Integer> ss=new HashSet<Integer>();
         for(int i=0;i<n;i++)
         {
          if(ss.contains(a[i]))
          {
           c++;
          }
          else
           ss.add(a[i]);
         }
         if(c==0)
         {
          int ans=1;
          for(int i=1;i<=n;i++)
           ans*=(i)%1000000007;
          System.out.println(ans);
         }
        }
  }
  public static boolean isPrime(long n) {
         // Corner cases
         if (n <= 1)
             return false;
         if (n <= 3)
             return true;

         // This is checked so that we can skip 
         // middle five numbers in below loop
         if (n % 2 == 0 || n % 3 == 0)
             return false;

         for (int i = 5; i * i <= n; i = i + 6)
             if (n % i == 0 || n % (i + 2) == 0)
                 return false;

         return true;
     }
  public static long pow(long n,long p,long m)
 {
   long  result = 1;
    if(p==0)
      return 1;
  if (p==1)
      return n;
  while(p!=0)
  {
      if(p%2==1)
          result *= n;
      if(result>=m)
      result%=m;
      p >>=1;
      n*=n;
      if(n>=m)
      n%=m;
  }
  return result;
 }
 
 
 
 static class Pair implements Comparable<Pair>{
  int area;
  int price;
  Pair(int mr,int er){
   area=mr;price=er;
  }
  @Override
  public int compareTo(Pair o) {
   if(o.area>this.area)
    return 1;
   else if(o.area<this.area)
    return -1;
   else
   {
    if(o.price<this.price)
     return 1;
    else
     return -1;
   }
   }
 }
 static class TVF implements Comparable<TVF>{
  int index,size;
  TVF(int i,int c){
   index=i;
   size=c;
  }
  @Override
  public int compareTo(TVF o) {
   if(o.size>this.size)
    return -1;
   else if(o.size<this.size)
    return 1;
   else return 0;
  }
 }
 public static long gcd(long a, long b) {
    if (b == 0) return a;
    return gcd(b, a%b);
  }
 static class InputReader {
 
  private InputStream stream;
  private byte[] buf = new byte[8192];
  private int curChar, snumChars;
  private SpaceCharFilter filter;
 
  public InputReader(InputStream stream) {
   this.stream = stream;
  }
 
  public int snext() {
   if (snumChars == -1)
    throw new InputMismatchException();
   if (curChar >= snumChars) {
    curChar = 0;
    try {
     snumChars = stream.read(buf);
    } catch (IOException e) {
     throw new InputMismatchException();
    }
    if (snumChars <= 0)
     return -1;
   }
   return buf[curChar++];
  }
 
  public   int nextInt() {
   int c = snext();
   while (isSpaceChar(c))
    c = snext();
   int sgn = 1;
   if (c == '-') {
    sgn = -1;
    c = snext();
   }
   int res = 0;
   do {
    if (c < '0' || c > '9')
     throw new InputMismatchException();
    res *= 10;
    res += c - '0';
    c = snext();
   } while (!isSpaceChar(c));
   return res * sgn;
  }
 
  public long nextLong() {
   int c = snext();
   while (isSpaceChar(c))
    c = snext();
   int sgn = 1;
   if (c == '-') {
    sgn = -1;
    c = snext();
   }
   long res = 0;
   do {
    if (c < '0' || c > '9')
     throw new InputMismatchException();
    res *= 10;
    res += c - '0';
    c = snext();
   } while (!isSpaceChar(c));
   return res * sgn;
  }
 
  public int[] nextIntArray(int n) {
   int a[] = new int[n];
   for (int i = 0; i < n; i++)
    a[i] = nextInt();
   return a;
  }
 
  public String readString() {
   int c = snext();
   while (isSpaceChar(c))
    c = snext();
   StringBuilder res = new StringBuilder();
   do {
    res.appendCodePoint(c);
    c = snext();
   } while (!isSpaceChar(c));
   return res.toString();
  }
 
  public boolean isSpaceChar(int c) {
   if (filter != null)
    return filter.isSpaceChar(c);
   return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
  }
 
  public interface SpaceCharFilter {
   public boolean isSpaceChar(int ch);
  }
 }
}
 