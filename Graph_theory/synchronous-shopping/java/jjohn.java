import java.io.BufferedWriter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.StringTokenizer;
import java.util.TreeSet;
public class Main {
 class Heap{
  Edge array[];
  int pos[];
  int currsize;
  int size;
  public Heap(int n){
   array=new Edge[n];
   pos=new int[n];
   for (int i=0;i<n;i++) 
    pos[i]=-1;
   size=n;
   for (int i=0;i<size;i++) 
    array[i]=null;
   currsize=0;
  }
 public Edge getTop(){return array[0];}
 public void deleteTop(){
  if (currsize==0){}  
    pos[array[0].getY()]=-1;
    Edge y=array[currsize-1];
    currsize--;
    int i=0;
    int ci=1;
    while (ci<currsize){
     if (ci<currsize-1 && array[ci].getWeight()> array[ci+1].getWeight()) 
      ci++;
     if (y.getWeight()<=array[ci].getWeight())
      break;
        array[i]=array[ci];
        pos[array[i].getY()]=i;
        i=ci;
        ci*=2;
        ci+=1;
      }          
    array[i]=y;
    pos[array[i].getY()]=i;
 }
 public void insert(Edge Y){
   if (size==currsize) {}
     
     int i=currsize;
       currsize++;
    while (i!=0 && Y.getWeight()<array[(int)Math.ceil((float)i/2)-1].getWeight()){
                    array[i]=array[(int)Math.ceil((float)i/2)-1];  
                    pos[array[i].getY()]=i;
                    
                    i=(int)Math.ceil((float)i/2)-1;
             }
             array[i]=Y;
            pos[array[i].getY()]=i;
 }
 public void decreaseKey(Edge Y){

  if (Y.w <  array[pos[Y.getY()]].getWeight() ){
  int i=pos[Y.getY()];
   while (i!=0 && Y.getWeight()<array[(int)Math.ceil((float)i/2)-1].getWeight()){
         array[i]=array[(int)Math.ceil((float)i/2)-1];      
         pos[array[i].getY()]=i;
         i=(int)Math.ceil((float)i/2)-1;
         }
         array[i]=Y;
         pos[array[i].getY()]=i;
  }
 }
 public int getSize(){return currsize;}
 public void insertn(Edge Y){
     int nd=Y.getY();    
       if (pos[nd]== -1) 
       insert(Y);
       else
       decreaseKey(Y); 
  
 }
 }
 class Edge implements Comparable<Edge>{
  public int y;
  public int  bitmask;
  public int w;
  public int pos;
  public int getWeight() {return w;}
  public int getY(){return pos;}
    public Edge(int yy,int ww,int b,int p){
   y=yy;
   bitmask=b;
   w=ww;
   pos=p;
  }
  @Override
  public int compareTo(Edge e) {
   if (w<e.w) return -1;
   if (w>e.w) return 1;
   return 0;
  }
  
 }
    public interface SpaceCharFilter {
        public boolean isSpaceChar(int ch);
        }
    class InputReader {


        private InputStream stream;

        private byte[] buf = new byte[1024];

        private int curChar;

        private int numChars;

        private SpaceCharFilter filter;
        

        public InputReader(InputStream stream) {

            this.stream = stream;

        }


        public int read() {

            if (numChars == -1)

                throw new InputMismatchException();

            if (curChar >= numChars) {

                curChar = 0;

                try {

                    numChars = stream.read(buf);

                } catch (IOException e) {

                    throw new InputMismatchException();

                }

                if (numChars <= 0)

                    return -1;

            }

            return buf[curChar++];

        }


        public int readInt() {

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

        public String readString() {

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


        public boolean isSpaceChar(int c) {

            if (filter != null)

                return filter.isSpaceChar(c);

            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;

        }


        public String next() {

            return readString();

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

    /**
     * @param args
     */

    InputReader in= new InputReader(System.in);
    OutputWriter out = new OutputWriter(System.out);
        StringTokenizer tok;
      
        public static void main(String[] args) throws IOException
        {
           new Main().run();
        }
        void run() throws IOException
        {
            
           solve();
           out.flush();
           tok=null;
        }
    
        void solve() throws IOException{
         int n=in.readInt();
         int m=in.readInt();
         int k=in.readInt();
         int[] sell=new int[n];
         int[] pow=new int[k];
         pow[0]=1;
         for (int i=1;i<k;++i) pow[i]=pow[i-1]*2;
         for (int i=0;i<n;++i){
          int q=in.readInt();
          for (int j=0;j<q;++j){
           sell[i]^=pow[in.readInt()-1];
          }
          //System.out.println(i+" "+sell[i]);
         }
         ArrayList<ArrayList<Edge>> edges=new ArrayList<ArrayList<Edge>>();
         for (int i=0;i<n;++i) edges.add(new ArrayList<Edge>());
         for (int i=0;i<m;++i){
          int a=in.readInt()-1;
          int b=in.readInt()-1;
          int c=in.readInt();
          edges.get(a).add(new Edge(b,c,0,0));
          edges.get(b).add(new Edge(a,c,0,0));
         }
        
         int l=(int)Math.pow(2, k);
         boolean[][] visited=new boolean[n][l];
         for (int i=0;i<n;++i){
          for (int j=0;j<l;++j) visited[i][j]=false;
         }
         long[][] cost=new long[n][l];
         //visited[0][sell[0]]=true;
         Heap hq=new Heap(n*l);
         hq.insertn(new Edge(0,0,sell[0],sell[0]));
         while (hq.getSize()>0){
       
          Edge q=hq.getTop();
       hq.deleteTop();
       
      // cost+=q.getWeight();
       visited[q.y][q.bitmask]=true;
       cost[q.y][q.bitmask]=q.w;
       //System.out.println(q.y+" "+q.bitmask+" "+visited[q.y][q.bitmask]);
       for( Edge e: edges.get(q.y)){
        if (!visited[e.y][q.bitmask | sell[e.y]]){
         //System.out.println("I try to add: "  + e.y+" "+(q.bitmask | sell[e.y]));
         hq.insertn(new Edge(e.y,e.w+q.w,(q.bitmask | sell[e.y]), e.y*l + (q.bitmask | sell[e.y])));
      //    
        }
       }
      }
         
         long ans=-1;
         for (int i=0;i<l;++i){
          for (int j=0;j<l;++j){
           if ((i | j)== l -1 && (Math.min(cost[n-1][i], cost[n-1][j])>0)){
            if (ans==-1) ans=Math.max(cost[n-1][i], cost[n-1][j]);
            ans=Math.min(ans, Math.max(cost[n-1][i], cost[n-1][j]));
           }
          }
         }
         out.printLine(ans);

        }
}