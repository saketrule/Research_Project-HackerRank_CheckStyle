import java.util.*;
public class tester {

 /**
  * @param args
  */
 
 public static int sieveOfEratosthenes(int n)
    {
        // Create a boolean array "prime[0..n]" and initialize
        // all entries it as true. A value in prime[i] will
        // finally be false if i is Not a prime, else true.
        boolean prime[] = new boolean[n+1];
        for(int i=0;i<n+1;i++)
            prime[i] = true;
         
        for(int p = 2; p*p <=n; p++)
        {
            // If prime[p] is not changed, then it is a prime
            if(prime[p] == true)
            {
                // Update all multiples of p
                for(int i = p*2; i <= n; i += p)
                    prime[i] = false;
            }
        }
        int d=0;
        // Print all prime numbers
        for(int i = 2; i <= n; i++)
        {
            if(prime[i] == true)
             ++d;
        }
        return d;
    }
 public static void main(String[] args) {
  // TODO Auto-generated method stub

  Scanner sc=new Scanner(System.in);
  int t=sc.nextInt();
  
  for(int j=0;j<t;j++){
  int n=sc.nextInt();
  int num=sieveOfEratosthenes(n);
 // System.out.println(num);
  if(num%2==0){
   System.out.println("Bob");
  }
  else{
   System.out.println("Alice");
  }
  
 }
 }

}