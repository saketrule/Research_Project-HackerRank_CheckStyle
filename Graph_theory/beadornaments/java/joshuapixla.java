import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Solution {
    
    private static int N;
    private static int[] beads;
    
    
    private static void compute(){
  
  int total = 0;
  BigInteger special = BigInteger.ZERO;
  BigInteger first = BigInteger.ONE;
  BigInteger temp = BigInteger.ZERO;
  BigInteger second = BigInteger.ZERO;
  BigInteger mod = BigInteger.valueOf(1000000007);
  
  if(N == 1){
   if(beads[0] == 1){
    System.out.println(1);
    return;
   }
   special = BigInteger.valueOf(beads[0]);
   special = special.pow(beads[0]-2);
   
   System.out.println(special.mod(mod));
   return;
  }
  
  for(int i = 0; i < beads.length; i++){
   temp = BigInteger.valueOf(beads[i]);
   temp = temp.pow(beads[i]-1);
   first = first.multiply(temp);
     }
    
  for(int j = 0; j < beads.length; j++){
   total += beads[j];
  }
  
  second = BigInteger.valueOf(total);
  second = second.pow(N-2);
  
  System.out.println(first.multiply(second).mod(mod));
 }
    
    
    
    public static void main(String[] args) throws IOException {
  
        String[] temp;
        String line = "";
        int T;
  
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
           line = br.readLine();
        T = Integer.parseInt(line);
        
        for(int i = 0; i < T; i++){
     line = br.readLine();
     N = Integer.parseInt(line);
     beads = new int[N];
        line = br.readLine();
        temp = line.split(" ");
        for(int j = 0; j < N; j++){
      beads[j] = Integer.parseInt(temp[j]);
     }
     compute();
     }
 }
}