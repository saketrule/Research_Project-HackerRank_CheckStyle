import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int g = in.nextInt();
        boolean a[] = new boolean[1000000];
        long i, j;
        for(i = 2; i < 100000; i++)
            a[(int)i] = true;
        for(i = 2; i <= Math.sqrt(100000); i++) {
            if(a[(int)i] == true) {
               for(j = (i*i); j <= 100000; j+=i) {
                    a[(int)j] = false;
               }
            }
        }
        for(int a0 = 0; a0 < g; a0++){
            int n = in.nextInt();
            int count  = 0;
            for(i = 2; i <= n; i++) {
                if(a[(int)i] == true)
                    count++;
            }
            if(count%2 == 0)
                System.out.println("Bob");
            else
                System.out.println("Alice");
        }
    }
    
   
}