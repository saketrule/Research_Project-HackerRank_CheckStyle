import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
public class Solution {

    public static void main(String[] args) {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       try {
           String line = br.readLine();
           int[] count = new int[26];
           for (int i = 0; i < line.length(); i++) {
               count[line.charAt(i) - 'a']++;
           }
           for (int i = 0; i < 26; i++) {
               count[i] /= 2;
           }
           StringBuilder sb = new StringBuilder();
           int[] shuffle = new int[26];
           int[] orginal = new int[26];
           int min = -1;
           for (int i = line.length() - 1; i >= 0; i--) {
               char curChar = line.charAt(i);
               if ((min == -1 || line.charAt(min) > line.charAt(i)) &&
                   orginal[curChar - 'a'] < count[curChar - 'a']) {
                   min = i;
               }
               if (shuffle[curChar - 'a'] == count[curChar - 'a']) {
                   for (int j = min; j > i; j--) {
                       shuffle[line.charAt(j) - 'a']--;
                   }
                   orginal[line.charAt(min) - 'a']++;
                   i = min;
                   sb.append(line.charAt(min));
                   min = -1;
               } else {
                   shuffle[line.charAt(i) - 'a']++;
               }
           }
           System.out.println(sb);
       } catch (IOException e) {
           e.printStackTrace();
       }
    }
}