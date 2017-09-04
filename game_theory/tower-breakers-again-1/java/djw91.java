import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

   public static int breakTower(int h) {
        int count = 0;
        if (h % 2 == 0) count++;

        while (h % 2 == 0) {
            h /= 2;
        }
        for (int i = 3; i*i <= h; i++) {
            while (h % i == 0) {
                h /= i;
                if (i % 2 != 0) count++;
            }
        }
        if (h > 2) count++;
        return count;
    }


    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();

        for(int i = 0; i < T; i++) {
            int n = scanner.nextInt();
            int p = 0;
            for(int j = 0; j < n; j++) {
                int h = scanner.nextInt();

                p ^= breakTower(h);
            }
            System.out.println( p==0 ? "2" : "1" );
        }

    }
}