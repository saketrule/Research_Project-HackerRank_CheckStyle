import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution 
{

    public static void main(String[] args) 
    {
        int[] ar = new int[100001];
        formPrimeAr(ar);
        StringBuilder st = new StringBuilder();
        Scanner in = new Scanner(System.in);
        int g = in.nextInt();
        for(int a0 = 0; a0 < g; a0++){
            int n = in.nextInt();
            if(ar[n]%2==0) {    st.append("Bob\n"); }
            else {    st.append("Alice\n"); }
        }
        System.out.println(st.toString());
    }
    
    private static boolean isPrime(int n)
    {
        int f = 2, lim = (int)(Math.sqrt(n));
        if(n<2) {return false;}
        while(f<=lim)
        {
            if(n%f==0) {return false;}
            ++f;
        }
        return true;
    }
    
    private static void formPrimeAr(int[] ar)
    {
        for(int i = 2; i < ar.length; ++i)
        {
            if(isPrime(i))  {   ar[i] = ar[i-1]+1;  }
            else    {   ar[i] = ar[i-1];    }
        }
    }
}