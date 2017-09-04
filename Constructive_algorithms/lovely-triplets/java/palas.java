import java.math.BigInteger;
import java.util.Scanner;
import java.util.Vector;

public class Triplets2 {
 public static BigInteger factorial(int n) {
  BigInteger fac = BigInteger.ONE;
  for (int i = n; i >= 1; i--) {
   fac = fac.multiply(BigInteger.valueOf(i));
  }
  return fac;
 }

 public static int comb(int n, int r) {
  return (factorial(n).divide(factorial(r).multiply(factorial(n - r)))).intValue();
 }

 public static int find_comb(int n) {
  int i;
  for (i = 1; comb(i, 3) <= n; i++);
  return i - 1;
 }

 public static int[] decompose(int n) {
  Vector<Integer> v = new Vector<Integer>();
  while (n > 0) {
   int comb = find_comb(n);
   v.addElement(comb);
   n -= comb(comb, 3);
  }
  int[] a = new int[v.size()];
  for (int i = 0; i < v.size(); i++) {
   a[i] = v.elementAt(i) - 2;
  }
  return a;
 }

 public static void main(String[] args) {
  Scanner sc = new Scanner(System.in);
  int p = sc.nextInt();
  int q = sc.nextInt();
  sc.close();
  int[] deco = decompose(p);
  int num_nodes = 0;
  int num_trans = 0;
  for (int i : deco) {
   num_nodes += q + 1 + i;
   num_trans += q + i;
  }
  System.out.print(num_nodes);
  System.out.print(" ");
  System.out.println(num_trans);
  int last_node = 0;
  int app = 0;
  for (int n : deco) {
   int base_node = ++last_node;
   System.out.print(base_node);
   System.out.print(" ");
   System.out.println(++last_node);
   for (int j = 1; j < q; j++) {
    System.out.print(last_node);
    System.out.print(" ");
    System.out.println(++last_node);
   }
   if (q == 1) {
    app = base_node;
   } else {
    app = last_node - 1;
   }
   for (int i = 0; i < n; i++) {
    System.out.print(app);
    System.out.print(" ");
    System.out.println(++last_node);
   }
  }
  System.out.println();
 }

}