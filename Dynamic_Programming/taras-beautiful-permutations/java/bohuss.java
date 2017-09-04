import java.io.*;
import java.math.BigInteger;
import java.util.*;



public class B
{
    String line;
    StringTokenizer inputParser;
    BufferedReader is;
    FileInputStream fstream;
    DataInputStream in;
    String FInput="";
    
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
   inputParser = new StringTokenizer(line, " ,\t");
   //System.err.println("Input: " + line);
  } catch (IOException e) {
   System.err.println("Unexpected IO ERROR: " + e);
  } 
  catch (NullPointerException e)
  {
   line=null;
   
  }
  
 }
    
    long NextLong()
    {
            String n = inputParser.nextToken();
            
            long val = Long.parseLong(n);
            
             return val;
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
            //String filePath="circles.in";
            String filePath=null;
            if(argv.length>0)filePath=argv[0];
            new B(filePath);
            
    }
    
    public B(String inputFile)
    {
     openInput(inputFile);
  readNextLine();
  int T=NextInt();
  StringBuilder sb = new StringBuilder();
  for(int t=1; t<=T; t++)
  {
   readNextLine();
   int N=NextInt();
   readNextLine();
   int [] a = new int[N];
   for(int i=0; i<N; i++)
   {
    a[i] = NextInt();
   }
   Arrays.sort(a);
   int r = 0;
   do
   {
    if(ok(a))
     r++;
   }while(next_permutation(a));
   sb.append(r+"\n");
  }
  System.out.print(sb);
  
  closeInput();  
 }
    
 private boolean ok(int[] a) {
  for(int i=1; i<a.length; i++)
   if(a[i]==a[i-1])return false;
  return true;
 }

 boolean next_permutation(int[] p) {
  for (int a = p.length - 2; a >= 0; --a)
   if (p[a] < p[a + 1])
    for (int b = p.length - 1;; --b)
     if (p[b] > p[a]) {
      int t = p[a];
      p[a] = p[b];
      p[b] = t;
      for (++a, b = p.length - 1; a < b; ++a, --b) {
       t = p[a];
       p[a] = p[b];
       p[b] = t;
      }
      return true;
     }
  return false;
 }

}