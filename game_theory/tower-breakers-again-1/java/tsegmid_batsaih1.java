import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.lang.*;


public class TowerBreakersRevisted{
 static long mod=1000000007;
 
    public static void main(String[] args) throws Exception{            
      InputReader in = new InputReader(System.in);       
     PrintWriter pw=new PrintWriter(System.out);   
     boolean v[]=new boolean[1000001];
     int p[]=new int[1000001];
     ArrayList<Integer>ar1=new ArrayList<Integer>();
     for(int i=2;i<=1000000;i++)
     {
      if(!v[i])
      {
       ar1.add(i);
       for(int j=i;j<=1000000;j+=i)
      {
       v[j]=true;
       int x=j;
       while(x%i==0)
       {
        p[j]++;
        x=x/i;
       }
      }
       }
      
     }
     int t=in.readInt();
         while(t-->0)
         {
         int n=in.readInt();
     int a[]=new int[n];
     int c=0,d=0;
     int ans=0;
    
     for(int i=0;i<n;i++)
     {
      a[i]=in.readInt();
      c=0;
      if(a[i]!=1)
      {
       
       c=p[a[i]];
      }
      ans=ans^c;
     }
     
     if(ans==0)
     pw.println("2"); 
     else
      pw.println("1"); 
     
     }
          
     pw.close();
    }
    
   
  
    /* returns nCr mod m */
    public static long comb(long n, long r, long m) 
    {
     long p1 = 1, p2 = 1;
     for (long i = r + 1; i <= n; i++) {
      p1 = (p1 * i) % m;
     }
     p2 = factMod(n - r, m);
     p1 = divMod(p1, p2, m);
     return p1 % m;
    }
   /* returns a/b mod m, works only if m is prime and b divides a */
    public static long divMod(long a, long b, long m) 
    {
     long c = powerMod(b, m - 2, m);
     return ((a % m) * (c % m)) % m;
    }
   /* calculates factorial(n) mod m */
    public static long factMod(long n, long m) {
     long result = 1;
     if (n <= 1)
      return 1;
     while (n != 1) {
      result = ((result * n--) % m);
     }
     return result;
    }
   /* This method takes a, b and c as inputs and returns (a ^ b) mod c */
    public static long powerMod(long a, long b, long c) {
     long result = 1;
     long temp = 1;
     long mask = 1;
     for (int i = 0; i < 64; i++) {
      mask = (i == 0) ? 1 : (mask * 2);
      temp = (i == 0) ? (a % c) : (temp * temp) % c;
      /* Check if (i + 1)th bit of power b is set */
      if ((b & mask) == mask) {
       result = (result * temp) % c;
      }
     }
     return result;
    }
 
 
public static long gcd(long x,long y)
{
 if(x%y==0)
  return y;
 else
  return gcd(y,x%y);
}
 
public static int gcd(int x,int y)
{
 if(x%y==0)
  return y;
 else 
  return gcd(y,x%y);
}
public static int abs(int a,int b)
{
 return (int)Math.abs(a-b);
}
public static long abs(long a,long b)
{
 return (long)Math.abs(a-b);
}
public static int max(int a,int b)
{
 if(a>b)
  return a;
 else
  return b;
}
public static int min(int a,int b)
{
 if(a>b)
  return b;
 else 
  return a;
}
public static long max(long a,long b)
{
 if(a>b)
  return a;
 else
  return b;
}
public static long min(long a,long b)
{
 if(a>b)
  return b;
 else 
  return a;
}

static boolean isPowerOfTwo (long v) {
 return (v & (v - 1)) == 0;
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
public static long pow(long n,long p)
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
     p >>=1;
     n*=n;     
 }
 return result;

}
static class Pair implements Comparable<Pair>
{
 int a,b;
 Pair (int a,int b)
 {
  this.a=a;
  this.b=b;
 }

 public int compareTo(Pair o) {
  // TODO Auto-generated method stub
  if(this.a!=o.a)
  return Integer.compare(this.a,o.a);
  else
   return Integer.compare(this.b, o.b);
  //return 0;
 }
 public boolean equals(Object o) {
        if (o instanceof Pair) {
            Pair p = (Pair)o;
            return p.a == a && p.b == b;
        }
        return false;
    }
    public int hashCode() {
        return new Integer(a).hashCode() * 31 + new Integer(b).hashCode();
    }
} 
    
static long sort(int a[],int n)
{   int b[]=new int[n]; 
 return mergeSort(a,b,0,n-1);}
static long mergeSort(int a[],int b[],long left,long right)
{ long c=0;if(left<right)
 {   long mid=left+(right-left)/2;
  c= mergeSort(a,b,left,mid);
  c+=mergeSort(a,b,mid+1,right);
  c+=merge(a,b,left,mid+1,right); } 
 return c;  }
static long merge(int a[],int  b[],long left,long mid,long right)
{long c=0;int i=(int)left;int j=(int)mid; int k=(int)left;
while(i<=(int)mid-1&&j<=(int)right)
{ if(a[i]<=a[j]) {b[k++]=a[i++]; }
else { b[k++]=a[j++];c+=mid-i;}}
while (i <= (int)mid - 1)   b[k++] = a[i++]; 
while (j <= (int)right) b[k++] = a[j++];
for (i=(int)left; i <= (int)right; i++) 
 a[i] = b[i];  return c;  }
    
    
   static  class InputReader
    {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private SpaceCharFilter filter;

        public InputReader(InputStream stream)
        {
            this.stream = stream;
        }

        public int read()
        {
            if (numChars == -1)
                throw new InputMismatchException();
            if (curChar >= numChars)
            {
                curChar = 0;
                try
                {
                    numChars = stream.read(buf);
                } catch (IOException e)
                {
                    throw new InputMismatchException();
                }
                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public int readInt()
        {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-')
            {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do
            {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public String readString()
        {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do
            {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }
        public double readDouble() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            double res = 0;
            while (!isSpaceChar(c) && c != '.') {
                if (c == 'e' || c == 'E')
                    return res * Math.pow(10, readInt());
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            }
            if (c == '.') {
                c = read();
                double m = 1;
                while (!isSpaceChar(c)) {
                    if (c == 'e' || c == 'E')
                        return res * Math.pow(10, readInt());
                    if (c < '0' || c > '9')
                        throw new InputMismatchException();
                    m /= 10;
                    res += (c - '0') * m;
                    c = read();
                }
            }
            return res * sgn;
        }
        public long readLong() {
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
        public boolean isSpaceChar(int c)
        {
            if (filter != null)
                return filter.isSpaceChar(c);
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public String next()
        {
            return readString();
        }

        public interface SpaceCharFilter
        {
            public boolean isSpaceChar(int ch);
        }
    }

  
//BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
 //StringBuilder sb=new StringBuilder("");
  //InputReader in = new InputReader(System.in);
 //PrintWriter pw=new PrintWriter(System.out);
 //String line=br.readLine().trim();
         
 //int t=Integer.parseInt(br.readLine());
  // while(t-->0)
   //{
   //int n=Integer.parseInt(br.readLine());
 //long n=Long.parseLong(br.readLine());
 //String l[]=br.readLine().split(" ");
  //int m=Integer.parseInt(l[0]);
 //int k=Integer.parseInt(l[1]);
 //String l[]=br.readLine().split(" ");
 //l=br.readLine().split(" ");
 /*int a[]=new int[n];
 for(int i=0;i<n;i++)
 {
  a[i]=Integer.parseInt(l[i]);
 }*/
    //System.out.println(" ");       
 
 //}
}