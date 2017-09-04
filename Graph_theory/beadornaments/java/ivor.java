//package com.prebeg.interviewstreet.beadornaments;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

 public static long modpow(long base, long exponent, long mod) {
  long res = 1;
  while (exponent > 0) {
   if (exponent % 2 == 1)
    res = ((res * base) % mod);
   exponent = exponent >> 1;
   base = ((base * base) % mod);
  }
  return res;
 }
 
 public static void solve() 
 {
  //System.out.println(Arrays.toString(A));
  
  BigInteger mod = new BigInteger(new String(new Integer(MOD).toString()));
  BigInteger mul = new BigInteger("1");
  BigInteger sum = new BigInteger("0");
  BigInteger nm2 = new BigInteger(new String(new Integer(N-2).toString()));
  
  for (BigInteger a : A) {
   sum = sum.add(a);
   sum = sum.mod(mod);
  }
  
  
  for (BigInteger a : A) { 
   a = a.modPow(a.subtract(BigInteger.ONE), mod);
   mul = mul.multiply(a);
   mul = mul.mod(mod); 
  }
  
  //sum.modPow(nm2, mod);
  sum = sum.modPow(nm2, mod);
  mul = mul.multiply(sum);
  mul = mul.mod(mod);
  
  //System.out.println(mul.longValue());
  
  out.println(mul.longValue());
 }
 
 static InputStream in = System.in;
 static PrintStream out = System.out;
 
 static int N;
 static int M;
 static int T;
 
 static int MOD = 1000000007;
 
 static BigInteger[] A;
 
 public static void main(String[] args) throws Exception {
  
  if (args.length != 0) {
   in = new FileInputStream(new File(args[0]));
   out = new PrintStream(new File(args[1]));
  }
   
  Scanner s = new Scanner(in);
              
  T = s.nextInt();
             
        for (int i = 0; i < T; i++) 
        {
         N = s.nextInt();
         A = new BigInteger[N];
         for (int j = 0; j < N; j++) {
          A[j] = s.nextBigInteger();
         }
            
            solve();
        }
 }
}