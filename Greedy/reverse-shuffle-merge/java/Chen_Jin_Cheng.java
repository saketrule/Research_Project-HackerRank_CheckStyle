import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine().trim();
        s = new StringBuilder(s).reverse().toString();
        int[] f = new int[26];
        int[] f_prime = new int[26];
        for (int i = 0; i < 26; i++)
            f[i] = 0;
        for (int i = 0; i < s.length(); i++) {
            f[s.charAt(i) - 'a']++;
        }
        
        for (int i = 0; i < 26; i++) {
            f[i]/=2;
            f_prime[i] = f[i];
        }
        //System.out.println(s);
        int start = 0;
        int curr = 0;
        //String A = "";
        for (int i = 0; i < s.length()/2; i++) {
            for (int j = start; j < 26; j++) {
                if (f[j] != 0) {
                    start = j;
                    break;
                }
            }
            boolean found = false;
            //System.out.println("curr : " + curr);
            //System.out.println("start : " + start);
            for (int j = start; j < 26 && !found; j++) {
                if (f[j] == 0)
                    continue;
                char c = (char)('a' + j);
                int[] tmp = new int[26];
                for (int k = 0; k < 26; k++)
                    tmp[k] = 0;
                for (int k = curr; k < s.length(); k++) {
                    int ii = s.charAt(k) - 'a';
                    if (j == ii) {
                        System.out.print(c);
                        curr = k + 1;
                        found = true;
                        for (int l = 0; l < 26; l++)
                            f_prime[l] -= tmp[l];
                        f[j]--;
                        break;
                    } else {
                        tmp[ii]++;
                        if (tmp[ii] > f_prime[ii]) {
                            break;
                        }
                    }
                    
                }
            }
        }
        System.out.println();
    }
}