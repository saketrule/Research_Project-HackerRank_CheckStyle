import java.math.BigInteger;
import java.util.Scanner;


public class Solution {

 public static final long mod = 1000000007;

 public static void main(String[] args) 
 {

  Scanner sc = new Scanner(System.in);

  int T = sc.nextInt();

  while(T-- > 0)
  {
   int n = sc.nextInt();
   int a[] = new int[n];
   for(int i=0; i<n; i++){
    a[i] = sc.nextInt();
   }   

   System.out.println(getComb(a));
  }
 }

 public static long getComb(int[] a)
 {
  int mod = 1000000007;
  int n = a.length;
  long val = 1;
  if(n==1) val = power(a[0], a[0]-2);
  else {
   long sum=0;
   for(int i=0; i<n; i++)
   {
    val = multiply(val, power(a[i], a[i]-1));
    
    sum += a[i];
   }
   val = multiply(val, power(sum, n-2));
   if(val >= mod) {
    val -= ((int)(val/mod)*mod);
   }
  }
  return val;
 }

 public static long power(long num, long pow)
 {
  long mul = 1;
  for(int i=0; i<pow; i++)
  {
   mul *= num;
   if(mul >= mod) {
    mul -= ((int)(mul/mod) * mod);
   }
  }
  return mul;
 }

 public static long multiply(long a, long b)
 {
  BigInteger bmod = BigInteger.valueOf(mod);
  BigInteger bi = BigInteger.valueOf(a);
  bi = bi.multiply(BigInteger.valueOf(b));
  bi = bi.subtract(bi.divide(bmod).multiply(bmod));
  return bi.longValue();
 }
}