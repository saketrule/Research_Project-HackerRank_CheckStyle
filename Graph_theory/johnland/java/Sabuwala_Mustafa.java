import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Fourth {

   

    public static void main(String[] args) {
        int n = i();
        int m = i();
        int arr1[] = new int[m];
        int arr2[] = new int[m];
        for (int i = 0; i < m; i++)
        {
            int x = i();
            int y = i();
            int z = i();
            x--; y--;
            arr1[z] = x;
            arr2[z] = y;
        }

        UnionFind unionFind = new UnionFind(n);
        res = new int[3 * m];

        arrayList = new ArrayList[n];
        for (int i = 0; i < n; i++) 
        {
            arrayList[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < m; i++) 
        {
         int aa = unionFind.find(arr1[i]);
         int bb = unionFind.find(arr2[i]);
            if (aa != bb) {
                arrayList[arr1[i]].add(arr2[i]);
                arrayList[arr2[i]].add(arr1[i]);
                arrayList[arr1[i]].add(i);
                arrayList[arr2[i]].add(i);
                unionFind.merge(arr1[i], arr2[i]);
            }
        }

        DFS(0, -1);

        int max = 0;
        for (int i = 0; i < res.length; i++)
        {
         if( res[i]<=0 ) continue;
         max = i;
        }

        for (int i = max; i >= 0; i--) {
            out.print(res[i]);
        }
        out.println();

        out.close();
    }

    static int DFS(int root, int par) {
        int tot = 1;
        for (int i = 0; i < arrayList[root].size();) {
            int v = arrayList[root].get(i++);
            int h = arrayList[root].get(i++);
            if( v!=par )
            {
              int ss = DFS(v, root);
                 long times = ss * (long)1 * (arrayList.length - ss);
                 int idx = h;
                 int carry = 0;
                 while (times >= 1 || carry > 0) {
                     carry = add(idx, carry + (int) (times & 1));
                     times /= 2;
                     idx++;
                 }
                 tot += ss;
            }
        }
        return tot;
    }

    static ArrayList<Integer> arrayList[];
    static int res[];
    
    static public int add(int idx, int val) {
        res[idx] += val;
        int ret = Math.max(0, res[idx]/2);
        res[idx] = (res[idx] & 1);
        return ret;
    }

    static public class UnionFind {

        public int rank[], parent[], size[];
        public int n, noOfComp;

        public UnionFind(int n) {
            this.n = n;
            makeSet();
        }

        public void makeSet() {
            rank = new int[n];
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            size = new int[n];
            Arrays.fill(size, 1);
            noOfComp = n;
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void merge(int x, int y) {
            int xRoot = find(x);
            int yRoot = find(y);

            if (xRoot == yRoot)
                return;

            size[xRoot] = size[yRoot] = size[xRoot] + size[yRoot];

            if (rank[xRoot] < rank[yRoot]) {
                parent[xRoot] = yRoot;
            } else {
                parent[yRoot] = xRoot;
                if (rank[xRoot] == rank[yRoot]) {
                    rank[xRoot]++;
                }
            }

            noOfComp--;
        }

    }

    static InputReader in = new InputReader(System.in);
 static OutputWriter out = new OutputWriter(System.out);
    
 static int i()
 {
     return in.readInt();
 }
    
 static long l()
 {
     return in.readLong();
 }
    
 static double d()
 {
     return in.readDouble();
 }
    
 static String s()
 {
     return in.readString();
 }
    
 static void Iarr( int[] array, int no )
 {
     for( int i=0 ; i<no ; i++ )
     {
         array[i] = i();
     }
 }
    
 static void Larr( long[] array, int no )
 {
     for( int i=0 ; i<no ; i++ )
     {
         array[i] = l();
     }
 }
    
 static void Darr( double[] array, int no )
 {
     for( int i=0 ; i<no ; i++ )
     {
         array[i] = d();
     }
 }
    
 static void Sarr( String[] array, int no )
 {
     for( int i=0 ; i<no ; i++ )
     {
         array[i] = s();
     }
 }
    
 private static class InputReader
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