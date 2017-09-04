import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static int getNumOfPrimeFactor(int n) {
        if (1 == n)
            return 0;
        if (0 == n % 2) {
            return 1 + getNumOfPrimeFactor(n/2);
        }
        for (int i = 3; i < Math.sqrt(n) + 1;i += 2) {
            if (0 == n % i)
                return 1 + getNumOfPrimeFactor(n/i);
        }
        return 1;
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);

        int T = scan.nextInt();
        int N = 0;
        int current = 0;
        int result = 0;
        
        for (int i = 0; i < T;++i) {
            N =  scan.nextInt();
            current = 0;
            result = 0;
    
            for (int j = 0; j < N; ++j) {
                current = getNumOfPrimeFactor(scan.nextInt());
                result =  result ^ current;
            }
            System.out.println(result == 0 ? 2 : 1);
        }
        scan.close();
    }
}