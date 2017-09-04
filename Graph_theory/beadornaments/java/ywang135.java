import java.util.Scanner;
public class Solution 
{
 public static long longPow(int n, int m){
  long res = 1;
  while(m>2){
   res = (res *(long)Math.pow(n, 3))%1000000007;
   m-=3;
  }
  if(m>0){
   res = (res *(long)Math.pow(n, m))%1000000007;
  }
  return res;
 }
 public static void main(String args[])
 {
  Scanner in=new Scanner(System.in);
  int T,N,i,j;
  T=in.nextInt();
  for(i=0;i<T;i++)
  {
   N=in.nextInt();
   int b[] = new int[N];
   long n[] = new long[N];
   
   long d = longPow(N,N-2)%1000000007;
   long result = 1;
   for(j=0;j<N;j++){
    b[j]=in.nextInt();
    n[j]=(long) longPow(b[j],b[j]-2)%1000000007;
    
    
    result = (result *n[j])%1000000007;
   }
   int sum=0;
   if(N==2){
    for(j=0;j<N;j++){
     result = (result *b[j])%1000000007;
    }
   }
   else if(N>2){
    for(j=0;j<N;j++){
     result = (result *b[j])%1000000007;
     sum+=b[j];
    }
    result = (longPow(sum,N-2)*result)%1000000007;
   }
   System.out.println(result%1000000007);
  }
 }
}