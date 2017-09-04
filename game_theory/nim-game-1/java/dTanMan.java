import java.util.*;
import java.io.*;

public class Nim
{
 static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
 
 public static void main(String[] args) throws IOException
 {
  int numCases = Integer.parseInt(br.readLine());
  for(int cc=0; cc<numCases; cc++)
  {
   br.readLine();
   String[] s = br.readLine().split(" ");
   int x = 0;
   for(int i=0; i<s.length; i++)
    x^=Integer.parseInt(s[i]);
   pw.println((x==0?"Second":"First"));
  }
  pw.close();
 }
}