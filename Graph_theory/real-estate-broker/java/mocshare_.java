 //package Awesome;
 import java.awt.Point;
 import java.io.*;
 import java.io.ObjectInputStream.GetField;
 import java.sql.Array;
 import java.util.*;
 import java.util.jar.Attributes.Name;
 import java.util.logging.Level;

import javax.print.attribute.HashAttributeSet;
import javax.swing.text.MaskFormatter;
  
  
  
  
  
  
  
  class TestClass {
  
 static long mod ;
static int[] Hp;
static int[] Ha;
static int[] Ca;
static int[] Cp;
static int[] M;
static boolean[] V;
public static boolean find_me_baby(int i){
 
    if(V[i]) return false;
    V[i] = true;
    
    for(int j=0;j<Cp.length;j++){
     
     
     if(Ha[i]>=Ca[j] || Hp[i]<Cp[j]) continue;
     
     if(M[j]==-1 || find_me_baby(M[j])){
       
        M[j] = i;
        return true;
     }
     
    }
    
    return false;
}

 public static void main(String args[] ) throws java.lang.Exception {
  
  
  
// InputStream inputStream = System.in;
 //InputReader in = new InputReader(inputStream);
  
 //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
 //BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\Sompathak\\Desktop\\yes.txt"));
 //Scanner in = new Scanner(new FileReader("C:\\Users\\Sompathak\\Desktop\\yes.txt"));
  //PrintWriter pw = new PrintWriter(new FileWriter("C:\\Users\\sompatha\\Desktop\\output.txt"));
 //InputStream inputStream = System.in;
 //InputReader in = new InputReader(inputStream);
 Scanner in = new Scanner(new InputStreamReader(System.in));
 //Scanner in = new Scanner(new FileReader("C:\\Users\\sompatha\\Desktop\\yes.txt"));
 //we can we will ??? !!!!!! SOM RISES
 //long startTime = System.currentTimeMillis();
 //long startTime = System.currentTimeMillis();
  
 //float endTime   = System.currentTimeMillis();
 //float totalTime = (endTime - startTime)/(float)1000;
 //System.out.println(totalTime+" sec....."); 
  
 // {is, in ,the ,to ,he ,she}
 mod=1000000007;
int n = in.nextInt();
int m = in.nextInt();
Hp = new int[n];
Ha = new int[n];
for(int i=0;i<n;i++){ Ha[i] = in.nextInt();
                     Hp[i] = in.nextInt();}
Ca = new int[m];
Cp = new int[m];
for(int i=0;i<m;i++){ Ca[i] = in.nextInt();
                     Cp[i] = in.nextInt();}
M = new int[m];
Arrays.fill(M,-1);
int ans=0;
for(int i=0;i<n;i++){
    V  =new boolean[n];
 if(find_me_baby(i)) ans++;
 
}
System.out.println(ans);

}public static class InputReader
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
  
 public int nextInt()
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
 return res * Math.pow(10, nextInt());
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
 return res * Math.pow(10, nextInt());
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
 }                       