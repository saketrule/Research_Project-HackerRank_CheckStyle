import java.io.*;
import java.util.InputMismatchException;
public class abcd {
 
 public static void main(String args[])throws IOException{
  Fa o = new Fa(System.in);
  int Brr[];
  Brr = new int[100000+1];
  
  for(int i=2;i<=100000+1;i++){
   
   int pri_count=0;
   for(int j=2;j<=Math.sqrt(i);j++){
    
    if(i%j==0)
     pri_count++;
    
   
    
   }
   if(pri_count==0)
    Brr[i]=1;
   
  }
  int N = o.ni();
  while(N>0){
   int num = o.ni();
   int primes=0;
   
   for(int i=2;i<=num;i++){
    
   if(Brr[i]==1)primes++;
    
    
    
    
   }
   if(primes%2==1)
   System.out.println("Alice");
   else System.out.println("Bob");
   
   N--;
  }
  
  
  
  
 }

}


class Fa
{
 
    public InputStream stream;
    private byte[] buf = new byte[1024]; 
    private int curChar;
    private int numChars;
    private SpaceCharFilter filter;
 
    public Fa(InputStream stream)
    {
        this.stream = stream;
    }
 
    public int read()
    {
        if (numChars == -1)
        {
            throw new InputMismatchException ();
        }
        if (curChar >= numChars)
        {
            curChar = 0;
            try
            {
                numChars = stream.read (buf);
            } catch (IOException e)
            {
                throw new InputMismatchException ();
            }
            if (numChars <= 0)
            {
                return -1;
            }
        }
        return buf[curChar++];
    }
 
    public int ni()
    {
        int c = read ();
        while (isSpaceChar (c))
            c = read ();
        int sgn = 1;
        if (c == '-')
        {
            sgn = -1;
            c = read ();
        }
        int res = 0;
        do
        {
            if (c < '0' || c > '9')
            {
                throw new InputMismatchException ();
            }
            res *= 10;
            res += c - '0';
            c = read ();
        } while (!isSpaceChar (c));
        return res * sgn;
    }
 
    public long nl()
    {
        int c = read ();
        while (isSpaceChar (c))
            c = read ();
        int sgn = 1;
        if (c == '-')
        {
            sgn = -1;
            c = read ();
        }
        long res = 0;
        do
        {
            if (c < '0' || c > '9')
            {
                throw new InputMismatchException ();
            }
            res *= 10;
            res += c - '0';
            c = read ();
        } while (!isSpaceChar (c));
        return res * sgn;
    }
 
    public boolean isSpaceChar(int c)
    {
        if (filter != null)
        {
            return filter.isSpaceChar (c);
        }
        return isWhitespace (c);
    }
 
    public static boolean isWhitespace(int c)
    {
        return c==' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }
 
    public interface SpaceCharFilter
    {
        public boolean isSpaceChar(int ch);
    }
 
 
 
 
 
}
class Ou{
 private final PrintWriter writer;
 
 public Ou(OutputStream outputStream) {
  writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
 }
 
 public Ou(Writer writer) {
  this.writer = new PrintWriter(writer);
 }
 
 public void print(Object...objects) {
  for (int i = 0; i < objects.length; i++) {
   if (i != 0)
    writer.print(' ');
   writer.print(objects[i]);
  }
 }
 
 public void printLine(Object...objects) {
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