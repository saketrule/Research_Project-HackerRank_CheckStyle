import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
       int[] T = new int[26];
        int[] begin = new int[26];
        Scanner in = new Scanner(System.in);
        String S = in.nextLine();
        char[] s = S.toCharArray();
        for (int i = 0; i < s.length; i++) {
            T[s[i] - 'a']++;
        }
        int sum = 0;
        for (int i = 0; i < 26; i++) {
            begin[i] = T[i];
            T[i] = T[i] / 2;
            sum = sum + T[i];
        }
        int first = s.length - 1;
        char min = 0;
        int minPos = 0;
        char[] A = new char[sum];
        int j = 0;
        while (j < sum) {
            int[] copy = new int[26];
            System.arraycopy(begin, 0, copy, 0, 26);
            min = 200;
            for (int i = first; i >= 0; i--) {
                
                if (T[s[i] - 'a'] > 0) {
                    if (copy[s[i] - 'a'] - 1 < T[s[i] - 'a']) {
                        if (min == 200 || s[i]<min) {
                            min = s[i];
                            minPos = i;

                        }
                        break;
                    } else {
                        copy[s[i] - 'a']--;
                        if (s[i] < min) {
                            min = s[i];
                            minPos = i;
                        }

                    }
                }
            }
            for (int i = first; i >= minPos; i--) {
                begin[s[i] - 'a']--;
            }
            first = minPos - 1;
         
            T[min - 'a']--;
            A[j] = min;
            j++;
        }
        for (int i = 0; i < A.length; i++) {
            System.out.print(A[i]);
        }
    }
}