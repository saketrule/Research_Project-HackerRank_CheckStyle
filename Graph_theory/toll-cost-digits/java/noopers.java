import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.InputMismatchException;
import java.util.Vector;



public class Fifth {

 /**
  * @param args
  */
 
 static Vector<Integer> vi[];
 static boolean reachable [];
 static boolean mark[][];
 static Vector<Integer> w[];
 static long count[];
 static long ans[];
 static boolean reach[];
 public static void main(String[] args) {
  // TODO Auto-generated method stub
  InputReader in=new InputReader(System.in);
  OutputWriter out=new OutputWriter(System.out);
        
  int n=in.readInt();
  int m=in.readInt();
  vi=new Vector[n];
  reachable=new boolean[n];
  mark=new boolean [10][n];
  w=new  Vector[n];
  reach=new boolean[n];
  
  for(int i=0;i<n;i++){
   vi[i]=new Vector<Integer>();
   w[i]=new  Vector<Integer>();
  }
  
  
  for(int i=0;i<m;i++){
   int u=in.readInt();
   int v=in.readInt();
   u--;
   v--;
   int ww=in.readInt()%10;
   vi[u].add(v);
   vi[v].add(u);
   w[u].add(ww);
   w[v].add((10-ww)%10);
   
  }
  ans=new long[10];
  
  for(int i=0;i<n;i++){
   boolean b=false;
   for(int j=0;j<10;j++){
   if(mark[j][i]==true){
    b=true;
   }
   
   }
   
   
   if(b==true){
    continue;
   }
   
   int source=i;
   mark[0][i]=true;
   dfs1(i,0,i);
   count=new long[10];
   
   dfs(i,i);
   
   for(int j=0;j<10;j++){
  ans[j]=ans[j]+count[j];
 // System.out.println("wow"+" "+j+" "+count[j]);
   }
   dfs2(i,i);
  
   
   
  }
  
  
  
  
     for(int i=0;i<10;i++){
      out.printLine(ans[i]);
     }
  
  out.close();

 }
 static void dfs1(int u,int w1,int sc){
 // if(mark[w1][u])return;
  mark[w1][u]=true;
 // System.out.println(u+" "+w1);
  for(int i=0;i<vi[u].size();i++){
   int w2=w[u].get(i);
   w2=(w1+w2)%10;
   if(mark[w2][vi[u].get(i)]==false){
    dfs1(vi[u].get(i),w2,sc);
   }
  }
  
 }
 static void dfs(int u,int sc){
  reachable[u]=true;
  if(u!=sc)
  for(int  i=0;i<10;i++){
   if(mark[i][u]==true){
    count[i]++;
   }
   
  }
  
  
  for(int i=0;i<vi[u].size();i++){
     if(reachable[vi[u].get(i)]==false){
      dfs(vi[u].get(i),sc);
       
      
      
     } 
     
  }
  
 }
 
 
 static void dfs2(int u,int sc){
  reach[u]=true;
  //System.out.println(u+" asdasd");
  if(u!=sc)
  {
   boolean what[]=new boolean[10];
   for(int i=0;i<10;i++){
    if(mark[i][u]==true){
     int ind=10-i;
     if(ind==10)ind=0;
     ans[ind]++;
     
     for(int j=0;j<10;j++){
      if(count[j]!=0){
       
     //  System.out.println(u+" "+i+" "+j);
       int tot=j+ind;
       
       tot=tot%10;
       if(what[tot]==false){
       if(tot==0||mark[j][u]==true){ans[tot]=ans[tot]+count[j]-1;}else{ans[tot]=ans[tot]+count[j];}
       what[tot]=true;
       
       }
       
      }
     }
    }
   }
   
   
  }
  
  
  for(int i=0;i<vi[u].size();i++){
     if(reach[vi[u].get(i)]==false){
      dfs2(vi[u].get(i),sc);
       
      
      
     } 
     
  }
  
 }

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

 public interface SpaceCharFilter {
  public boolean isSpaceChar(int ch);
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

class IOUtils {

 public static int[] readIntArray(InputReader in, int size) {
  int[] array = new int[size];
  for (int i = 0; i < size; i++)
   array[i] = in.readInt();
  return array;
 }

 }