import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

   
    public static void main(String[] args)
 {
  Scanner s = new Scanner(System.in);
  int t =s.nextInt();
  for (int i = 0; i < t; i++)
  {
   int n = s.nextInt();
   int m = s.nextInt();
   if(m == 1 || n % 2 == 0) //? 2 : 1)
    System.out.println("2");
   else
    System.out.println("1");
  }

 }

}

        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
  