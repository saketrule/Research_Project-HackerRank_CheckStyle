import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
  List<Integer> lista = new ArrayList<Integer>();
  int foo = 1;
  lista.add(foo);
  for (int j = 0; j < 100; j++) {
   foo += 6;
   lista.add(foo);
   foo++;
   lista.add(foo);
  }
  Scanner in = new Scanner(System.in);
  int cases = in.nextInt();
  for (int i = 0; i < cases; i++) {
   if (lista.contains(in.nextInt())) {
    System.out.println("Second");
   }
   else {
    System.out.println("First");
   }
  }
  in.close();
 }
}