import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int g = in.nextInt();
        for(int a0 = 0; a0 < g; a0++)
        {
            int n = in.nextInt();
            boolean prime[] = new boolean[n+1];
            for(int i=0;i<=n;i++)
                prime[i] = true;
         
            for(int p = 2; p*p <=n; p++)
            {
                if(prime[p] == true)
                {
                    for(int i = p*2; i <= n; i += p)
                        prime[i] = false;
                }
            }
        int cnt=0;
        for(int i = 2; i <= n; i++)
        {
            if(prime[i] == true)
                cnt++;
        }
        if(cnt%2==0)
            System.out.println("Bob");
        else
            System.out.println("Alice");
    }
    }
}