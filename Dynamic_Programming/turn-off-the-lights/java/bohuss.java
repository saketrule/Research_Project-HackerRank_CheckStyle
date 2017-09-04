import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.Map.Entry;


public class D
{
 String line;
 StringTokenizer inputParser;
 BufferedReader is;
 FileInputStream fstream;
 DataInputStream in;
 
 void openInput(String file)
 {

  if(file==null)is = new BufferedReader(new InputStreamReader(System.in));//stdin
  else
  {
   try{
  
    
   fstream = new FileInputStream(file);
   in = new DataInputStream(fstream);
   is = new BufferedReader(new InputStreamReader(in));
   }catch(Exception e)
   {
    System.err.println(e);
   }
  }

 }
 
 void readNextLine()
 {
  try {
   line = is.readLine();
   if(line!=null)inputParser = new StringTokenizer(line, " ");
   //System.err.println("Input: " + line);
  } catch (IOException e) {
   System.err.println("Unexpected IO ERROR: " + e);
  } 
  
 }
 
 int NextInt()
 {
  String n = inputParser.nextToken();
  int val = Integer.parseInt(n);
  
  //System.out.println("I read this number: " + val);
  return val;
 }
    
    double NextDouble()
    {
            String n = inputParser.nextToken();
            double val = Double.parseDouble(n);
            
            //System.out.println("I read this number: " + val);
            return val;
    }
 
 long NextLong()
    {
            String n = inputParser.nextToken();
            
            long val = Long.parseLong(n);
            
            return val;
    }
 
 String NextString()
 {
  String n = inputParser.nextToken();
  return n;
 }
 
 void closeInput()
 {
  try {
   is.close();
  } catch (IOException e) {
   System.err.println("Unexpected IO ERROR: " + e);
  }
   
 }
 
 
 public static void main(String [] argv)
 {
  String filePath=null;
  if(argv.length>0)filePath=argv[0];
  new D(filePath);
 }
 Integer [] c;
 TreeMap<Integer, Integer> hm;
 HashMap<Integer, Integer> hmr;
 public D(String inputFile)
 {
  
  openInput(inputFile);
  //readNextLine();
  int T=1;//NextInt();
  StringBuilder sb = new StringBuilder();
  
  for(int t=1; t<=T; t++)
  {
   readNextLine();
   int N=NextInt();
   int K=NextInt();
   int [] a = new int[N];
   long [] res = new long[N+1];
   for(int i=0; i<N; i++)
   {
    readNextLine();
    a[i] = NextInt();
   }
   
   for(int i=1; i<=N; i++)
   {
    res[i] = Long.MAX_VALUE-2000000000;
   }
   
   for(int i=0; i<N; i++)
   {
    long min = Long.MAX_VALUE-2000000000;
    for(int j=1; j<=K+1; j++)
     if(i+1>=j)min = Math.min(min, res[i+1-j]);
    for(int j=0; j<=K; j++)
    {
     int id=i+j+1;
     if(id<0||id>N)continue;
     res[id]= Math.min(res[id], min+a[i]);
    }
   }
   
   
   sb.append(res[N]+"\n");
   
   
  }
  System.out.print(sb);
  
  closeInput();  
 }


}