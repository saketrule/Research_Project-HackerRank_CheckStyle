import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
public static BitSet primes (final int limit)
{
     BitSet primes = new BitSet (limit);
     for (int i = 3; i < limit; i+=2)
     {
        primes.set(i);
     }
     primes.set (2, true);
     for (int i = 3; i < limit; i+=2)
     {
        if (primes.get (i))
        {
            int j = 3 * i;
            while (j < limit)
            {
                if (primes.get (j))
                    primes.set (j, false);
                j += (2 * i);
            }
        }
    }
    return primes;
}   
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int g = in.nextInt();
        int a[] = new int[g];
        int max = 0;
        for(int a0 = 0; a0 < g; a0++){
            int n = in.nextInt();
            a[a0]=n;
            if (n>max) {
                max=n;
            }
        }
        
        BitSet myBitSet = primes(max+1);
     //               System.out.println(myBitSet);

        int b [] = new int[max+1];
        for (int i=2;i<=max;i++) {
            b[i]=b[i-1]+ ((myBitSet.get(i))?1:0) ;
       //     System.out.println(i+" "+b[i]);
        }
        for (int i=0;i<g;i++) {
            int tempN=a[i];
            int tempNP = b[tempN];
            if (tempNP%2==0) {
                System.out.println("Bob") ;
            } else {
               System.out.println("Alice") ;

            }
        }

        
    }
}