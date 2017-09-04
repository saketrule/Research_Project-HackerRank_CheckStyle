import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;


public class Main {

 public static void main(String[] args) {
  Scanner scanner = new Scanner(System.in);
  
  int n,m;
  n = scanner.nextInt();
  m=scanner.nextInt();
  BigInteger[][] arr = new BigInteger[n+5][n+5];
  
  BigInteger inf = BigInteger.ONE;
  
  for(int i = 2; i <= 2005; i++)
   inf = inf.multiply(BigInteger.valueOf(2));
  
  for (int i = 1; i <= n; i++) {
   for (int j = 1; j <= n; j++) {
    if(i==j)
     arr[i][j] = BigInteger.ZERO;
    else {
     arr[i][j] = inf;
    }
   }
  }
  
  
  while(m--!=0)
  {
   int a,b,c;
   a = scanner.nextInt();
   b = scanner.nextInt();
   c = scanner.nextInt();
   BigInteger d = BigInteger.ONE;
   for (int i = 0; i < c; i++) {
    d = d.multiply(BigInteger.valueOf(2));
   }
   arr[a][b] = d;
   arr[b][a] = d;
  }
  
  
  
  
  for (int k = 1; k <= n; k++) {
   for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n; j++) {
      BigInteger ans = arr[i][k].add(arr[k][j]);
      arr[i][j] = arr[i][j].min(ans);
     }
    }
   }
  
  BigInteger sum = BigInteger.ZERO;
  
  for (int i = 1; i <= n; i++) {
   for (int j = i+1; j <= n; j++) {
    sum = sum.add(arr[i][j]);
   }
  }
  
  String string = sum.toString(2);
  
  System.out.println(string);
  
  
  
 }

}