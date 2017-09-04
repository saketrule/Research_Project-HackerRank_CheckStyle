import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) throws Exception{
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  int T = Integer.parseInt(br.readLine());
  String out[] = new String[T];
  for(int i=0;i<T;i++)
  {
   int c =1;
   int loc = Integer.parseInt(br.readLine());
   String str[] = br.readLine().split(" ");
   int a = Integer.parseInt(str[0]);
   for(int j=1;j<loc;j++)
   {
    a = a^Integer.parseInt(str[j]);
   }
   for(int j=0;j<loc;j++)
   {
    if(Integer.parseInt(str[j]) > 1)
     c =0;
   }
   
   if(a==c)
    out[i] = "Second";
   else
    out[i] = "First";
  }
  
  for(int i=0;i<T;i++)
   System.out.println(out[i]);
    }
}