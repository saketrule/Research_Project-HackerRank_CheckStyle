import java.awt.Point;
import java.io.*;
import java.io.ObjectInputStream.GetField;
import java.math.BigInteger;
import java.sql.Array;
import java.util.*;
import java.util.jar.Attributes.Name;
import java.util.logging.Level;








































































































import javax.print.attribute.HashAttributeSet;
import javax.swing.text.MaskFormatter;







class TestClass {

static long mod ;


static ArrayList<Batman>[] route;
static long[] pow;
static long[][] dp;
static int[][] mask;
static int n;
static boolean[] V;
static int[] ans;
static long[][] lazy;
static int[] Pos;
static char[][] A;
static long[] Size;
static int nextnode;
static long max;
static boolean imp;
static int[] P;
static long[] PP;
static int[][] T;
static String S;
static String L;


static  class Batman{
   int start;
   int end;
   int ans;
   public Batman(int start , int end , int ans){
      this.start = start;
      this.end = end;
      this.ans = ans;
    
   }
   public Batman(){
    
   }
}



public static int pa(int i){
 
 return P[i]= P[i]==i ?i:pa(P[i]);
}

public static void search(int i, int pa){
 Size[i]=1;
 
 for(Batman j:route[i]){
  
  if(j.start==pa) continue;
  search(j.start,i);
  Size[i]+=Size[j.start];
 }
 
 for(Batman j:route[i]){
  
  if(j.start==pa) continue;
  
  PP[j.end]+= (Size[j.start])*(n-Size[j.start]);
 }
}

public static void main(String args[] ) throws java.lang.Exception {



//InputStream inputStream = System.in;
//InputReader in = new InputReader(inputStream);

//BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\Sompathak\\Desktop\\yes.txt"));
//Scanner in = new Scanner(new FileReader("C:\\Users\\Sompathak\\Desktop\\yes.java"));
//PrintWriter pw = new PrintWriter(new FileWriter("C:\\Users\\sompathak\\Desktop\\output.txt"));
InputStream inputStream = System.in;
InputReader in = new InputReader(inputStream);
//Scanner in = new Scanner(new InputStreamReader(System.in));
//Scanner in = new Scanner(new FileReader("C:\\Users\\sompathak\\Desktop\\yes.java"));
//we can we will ??? !!!!!! SOM RISES
//long startTime = System.currentTimeMillis();
//long startTime = System.currentTimeMillis();

//float endTime   = System.currentTimeMillis();
//float totalTime = (endTime - startTime)/(float)1000;
//System.out.println(totalTime+" sec....."); 

//{is, in ,the ,to ,he ,she}

mod = 1000000007;
 n = in.nextInt();
int mm =in.nextInt();
route = new ArrayList[n];
int[] A = new int[n];
for(int i=0;i<n;i++) A[i] =in.nextInt();
boolean[][] C = new boolean[n][n];
for(int i=0;i<mm;i++){
 int a = in.nextInt();
 int b = in.nextInt();
 a--;b--;
 C[a][b]=C[b][a]=true;
}

//int[][] dp = new int[1<<n][3500];
   int ways =0;
    int max=-1;
    int mad=n/2;
    int rem = n-mad;
for(int i=0;i<1<<mad;i++){
 
 boolean[] V = new boolean[n];
    int sum=0;
    boolean is_good=true;
 
 for(int j=0;j<mad;j++)
        if(((1<<j)&i)!=0){ V[j]=true; sum+=A[j];}
 
 for(int j=0;j<mad && is_good;j++)
  for(int k=0;k<mad && is_good;k++)
   if(V[j] && V[k] && C[j][k]) is_good=false;
        
        if(!is_good) continue;
        
        
        if(i!=0 &&sum>max){
  max=sum;
       
  ways=1;
    }else if(i!=0 && sum==max) ways++;
      
    
        for(int m=1;m<1<<rem;m++){
 
       boolean is_good_2=true;
       boolean[] V2 = new boolean[n];
            int temp = sum;
            for(int j=0;j<mad;j++) V2[j]=V[j];
            
        for(int j=0;j<rem;j++){
            if(((1<<j)&m)!=0){ V2[j+mad]=true; temp+=A[j+mad];}
        }
            
            for(int j=0;j<n && is_good_2;j++)
  for(int k=0;k<n && is_good_2;k++)
   if(V2[j] && V2[k] && C[j][k]) is_good_2=false;
        
        
 
            if(!is_good_2) continue;
                
                if( (m|i)!=0 && temp>max && is_good_2){
  max=temp;
  ways=1;
    }else if( (m|i)!=0 && temp==max && is_good_2) ways++;
                
                
                 /*for(int j=0;j<rem;j++)
                      if(((1<<j)&m)!=0){ V[j+mad]=false; sum-=A[j+mad];}*/
 
 
    
    }
}
System.out.println(max+" "+ways);

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