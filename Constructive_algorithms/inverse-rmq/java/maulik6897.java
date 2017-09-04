import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;




public class minrmq {
 static int segment[];
public static void main(String args[])
{
 InputReader in = new InputReader(System.in);
    PrintWriter w = new PrintWriter(System.out);
    int n=in.nextInt();
    Set<Integer> s=new HashSet<Integer>();
    HashMap<Integer,Integer> hm=new HashMap<Integer,Integer>();
    int a[]=new int[2*n-1];
    for(int i=0;i<2*n-1;i++)
    {
     int t=in.nextInt();
     a[i]=t;
     s.add(t);
     if(hm.containsKey(t))
     {
      hm.put(t,hm.get(t)+1);
     }
     else
     {
      hm.put(t, 1);
     }
     
    }
    if(s.size()!=n)
    {
     w.println("NO");
     w.flush();
    }
    else
    {
     int input[]=new int[n];
     int i=0;
     Iterator iter = s.iterator();
     while (iter.hasNext()) {
         input[i]=(int) iter.next();
         i++;
     }
     segment=new int[2*n-1];
     construct(input,0,n-1,0);
     HashMap<Integer,Integer> hm1=new HashMap<Integer,Integer>();
     for(int j=0;j<2*n-1;j++)
     {
      int t=segment[j];
      if(hm1.containsKey(t))
         {
          hm1.put(t,hm1.get(t)+1);
         }
         else
         {
          hm1.put(t, 1);
         }
     }
      for(Map.Entry m:hm.entrySet()){  
         int tv=(int) m.getValue();
         int t=(int) m.getKey();
         if(tv!=hm1.get(t))
         {
          w.print("NO");
          //System.out.println(tv+" "+t);
          w.flush();
          return;
         }
      }  
     w.println("YES");
     for(int j=0;j<2*n-1;j++)
      w.print(segment[j]+" ");
     w.flush();
    }
    

}
public static void construct(int input[],int low,int high,int pos)
{
    if(low==high)
    {
        segment[pos]=input[low];
        return;
    }
    int mid=(low+high)/2;
    construct(input,low,mid,2*pos+1);
    construct(input,mid+1,high,2*pos+2);
    segment[pos]=Math.min(segment[pos*2+1],segment[pos*2+2]);
    return;
}
static class InputReader {
  
 private final InputStream stream;
 private final byte[] buf = new byte[8192];
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

 public int nextInt() {
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