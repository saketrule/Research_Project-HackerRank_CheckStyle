import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        System.out.println(solution(reverse(str)));
    }
    private static String solution(String str) {
        int[] sum = new int[26];
        int[] need = new int[26];
        for (char c : str.toCharArray()) {
            sum[c - 'a']++;
        }
        
        for (int i = 0; i < sum.length; i++) {
            int temp = sum[i] / 2;
            need[i] = temp;
        }

        StringBuilder sb = new StringBuilder();
        int i = 0;
        while(sb.length() < str.length() / 2) {
         int minCharAt = -1;
         while (true) {
          char c = str.charAt(i);
          if (need[c - 'a'] > 0 && (minCharAt < 0 || c < str.charAt(minCharAt))) {
           minCharAt = i;
          }
          sum[c - 'a']--;
          if (sum[c - 'a'] < need[c - 'a']) {
           break;
          }
          i++;
         }
         for (int j = minCharAt + 1; j <= i; j++) {
          sum[str.charAt(j) - 'a']++;
         }
         need[str.charAt(minCharAt) - 'a']--;
         sb.append(str.charAt(minCharAt));
         i = minCharAt + 1;
        }
        return sb.toString();
    }
    private static String reverse(String str) {
     char[] chars = str.toCharArray();
     int start = 0;
     int end = chars.length - 1;
     while (start < end) {
      swap(chars, start++, end--);
     }
     return new String(chars);
    }
    private static void swap(char[] chars, int i, int j) {
     char c = chars[i];
     chars[i] = chars[j];
     chars[j] = c;
    }
}