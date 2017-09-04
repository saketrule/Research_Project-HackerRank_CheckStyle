import java.util.Scanner;

public class Solution {
public static void main(String[] args) {
  Scanner s = new Scanner(System.in);
  T = s.nextInt();
  for(int i=0;i<T;i++){
   N=0;
   sh=0;
   N = s.nextInt();
   ins = new int[N][2];
   for(int n=0;n<N;n++){
    ins[n][0] = s.nextInt();
    ins[n][1] = s.nextInt();
   }
   coun();
   q += sh+" "+N+"\n";
  }
  System.out.println(q);
 }
 public static void coun(){
  for(int i=0;i<2;i++){
   for(int j=1;j<N;j++){
  if(ins[j][0]!=ins[j-1][0])
   sh++;
   }
  }
  N = sh*N;
 }
private static int T,N,ins[][],sh;
private static String q="";
}