import java.io.*;
import java.math.BigInteger;
import java.util.*;


public class Solution {
 
 static ArrayList<Integer> primes;
 static int MOD = 1000000000 + 7;
 static BigInteger BMOD = new BigInteger("1000000007");
 static BigInteger BTWO = new BigInteger("2");
 
 private static int parseInt(String s){
  return Integer.parseInt(s.replaceAll("\\s+", ""));
 }
 
 private static long parseLong(String s){
  return Long.parseLong(s.replaceAll("\\s+", ""));
 }
 
 private static int[] parseIntArr(String str){
  String[] s = str.split("\\s+");
  int[] array = new int[s.length];
  for(int i = 0; i < s.length; i++){
   array[i] = Integer.parseInt(s[i]);
  }
  return array;
 }
 
 private static long[] parseLongArr(String str){
  String[] s = str.split("\\s+");
  long[] array = new long[s.length];
  for(int i = 0; i < s.length; i++){
   array[i] = Integer.parseInt(s[i]);
  }
  return array;
 }
 
 private static BigInteger[] parseBigArr(String str){
  String[] s = str.split("\\s+");
  BigInteger[] array = new BigInteger[s.length];
  for(int i = 0; i < s.length; i++){
   array[i] = new BigInteger(s[i]);
  }
  return array;
 }
 
 private static void printIntArray(int[] a){
  for(int i = 0; i < a.length; i++){
   System.out.print(a[i] + " ");
  }
  System.out.println();
 }
 
 private static void printLongArray(long[] a){
  for(int i = 0; i < a.length; i++){
   System.out.print(a[i] + " ");
  }
  System.out.println();
 }
   
  public static void main (String [] args) throws Exception {

    try {
      InputStreamReader converter = new InputStreamReader(System.in);
      BufferedReader in = new BufferedReader(converter);
      //primes = getPrimes(100000);
      int n = parseInt(in.readLine());
      int[] arr = parseIntArr(in.readLine());
      Arrays.sort(arr);
      //printIntArray(arr);
      int[] pars = parseIntArr(in.readLine());
      getResult(arr, n, pars[0], pars[1]);
    }catch (Exception e) {
      System.err.println("Error! Exception: " + e); 
    } 

  }
  
  private static void getResult(int[] arr, int n, int a, int b){
   if(b < arr[0]) System.out.println(b);
   if(a > arr[n-1]) System.out.println(a);
   int max = 0;
   int idx = a;
   int i = 0;
   while(arr[i] <= a) i++;
   int j = n-1;
   while(arr[j] >= b) j--;
   if(i == 0) {
    max = Math.max(max, arr[i] - a);
    idx = a;
   }else{
    int tmp = Math.min(a - arr[i-1], arr[i] - a);
    if(tmp > max){
     max = tmp;
     idx = a;
    }
   }
   if(i != 0 && a < ((arr[i] - arr[i-1]) / 2 + arr[i-1]) ){
    i--;
   }
   if(j != n-1 && b > ((arr[j+1] - arr[j]) / 2 + arr[j])){
    j++;
   }
   
   while(i < j){
    int tmp = (arr[i+1] - arr[i]) / 2;
    if(tmp > max){
     max = tmp;
     idx = arr[i] + tmp;
    }
    i++;
   }
   
   if(j == n-1) {
    if(b - arr[n-1] > max){
     max = b - arr[n-1];
     idx = b;
    }
   }else{
    int tmp = Math.min(b - arr[j], arr[j+1] - b);
    if(tmp > max){
     max = tmp;
     idx = b;
    }
   }
   System.out.println(idx);
  }
  
}