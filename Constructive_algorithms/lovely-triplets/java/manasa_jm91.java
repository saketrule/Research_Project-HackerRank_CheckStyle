import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


public class worldcode_first {
 public static void main( String args[]) throws NumberFormatException, IOException
 {
  BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
  String [] r=br.readLine().split(" ");
  int p=Integer.parseInt(r[0]);
  int q=Integer.parseInt(r[1]);
  if(p+q<100)
  {
   System.out.println((p+q)+" "+(p+q-1) );
   for(int i=1;i<=p+q-1;i++)
    System.out.println(i+" "+(i+1));
   
   
  }
  
 }
}