import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    

 public static void main(String[] args) {
  Scanner scan = new Scanner(System.in);

  int n = scan.nextInt();
  int a[] = new int[2 * n - 1];
  for (int i = 0; i < a.length; i++) {
   a[i] = scan.nextInt();
  }

  Arrays.sort(a);

  boolean possible = true;
  int b[] = new int[a.length];
  int prev = a[a.length - 1];
  int prevind = a.length - n - 1;
  int currind = a.length - 2;
  int aind = a.length - 2;
  b[b.length - 1] = a[a.length - 1];
  for (int i = a.length - 2; i >= 0; i--) {
   if (a[i] == prev) {
    if (prevind < 0) {
     possible = false;
     break;
    }
    b[prevind--] = a[i];
   } else {
    b[currind--] = a[i];
   }
   prev = a[i];
   if (currind < a.length - n - 1) {
    break;
   }
   aind = i;
  }

  if (!possible) {
   System.out.println("NO");
  } else {
   System.out.println("YES");

   for (int i = aind - 1; i >= 0; i--) {
    b[prevind--] = a[i];
   }
   
   for (int i : b) {
    System.out.print(i + " ");
   }
  }

 }

}